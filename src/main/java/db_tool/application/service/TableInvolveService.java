package db_tool.application.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import db_tool.application.repository.ColumnRepository;
import db_tool.application.repository.IndexColumnRepository;
import db_tool.application.repository.IndexRepository;
import db_tool.application.repository.ProjectRepository;
import db_tool.application.repository.ReferentialColumnRepository;
import db_tool.application.repository.ReferentialRepository;
import db_tool.application.repository.TableRepository;
import db_tool.application.repository.database.DatabaseTableRepository;
import db_tool.domain.model.Column;
import db_tool.domain.model.Index;
import db_tool.domain.model.IndexColumn;
import db_tool.domain.model.Project;
import db_tool.domain.model.Referential;
import db_tool.domain.model.ReferentialColumn;
import db_tool.domain.model.Table;
import db_tool.domain.model.database.DatabaseColumn;
import db_tool.domain.model.database.DatabaseIndex;
import db_tool.domain.model.database.DatabaseIndexColumn;
import db_tool.domain.model.database.DatabaseReferential;
import db_tool.domain.model.database.DatabaseReferentialColumn;
import db_tool.domain.model.database.DatabaseTable;
import db_tool.domain.type.ReferentialType;
import db_tool.application.view.database.table.TableInvolve;

@Service
public class TableInvolveService {

	private ProjectRepository projectRepository;
	private DatabaseTableRepository databaseTableRepository;
	private TableRepository tableRepository;
	private ColumnRepository columnRepository;
	private ReferentialRepository referentialRepository;
	private ReferentialColumnRepository referentialColumnRepository;
	private IndexRepository indexRepository;
	private IndexColumnRepository indexColumnRepository;
	
	@Autowired
	public TableInvolveService(ProjectRepository projectRepository, 
			DatabaseTableRepository databaseTableRepository,
			TableRepository tableRepository,
			ColumnRepository columnRepository,
			ReferentialRepository referentialRepository,
			ReferentialColumnRepository referentialColumnRepository,
			IndexRepository indexRepository,
			IndexColumnRepository indexColumnRepository) {
		this.projectRepository = projectRepository;
		this.databaseTableRepository = databaseTableRepository;
		this.tableRepository = tableRepository;
		this.columnRepository = columnRepository;
		this.referentialRepository = referentialRepository;
		this.referentialColumnRepository = referentialColumnRepository;
		this.indexRepository = indexRepository;
		this.indexColumnRepository = indexColumnRepository;
	}
	
	/**
	 * プロジェクトのDBからテーブル一覧を取得する
	 * @param projectId
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<TableInvolve> getTableInvolve(Long projectId) {
		Project project = this.projectRepository.findById(projectId);
		List<DatabaseTable> databaseTables = this.databaseTableRepository.findAll(project);
		List<TableInvolve> tableInvolves = databaseTables.stream()
				.map(e -> {
					TableInvolve tableInvolve = new TableInvolve();
					tableInvolve.tableName = e.tableName;
					tableInvolve.tableComment = e.tableComment;
					return tableInvolve;
				}).collect(Collectors.toList());
		return tableInvolves;
	}
	
	/**
	 * 対象プロジェクトのDBのテーブル・カラム・外部キーの情報を取り込む
	 * @param projectId
	 */
	@Transactional(rollbackForClassName={"Exception"})
	public void involve(Long projectId) {
		Project project = this.projectRepository.findById(projectId);
		// テーブル取り込み
		List<DatabaseTable> databaseTables = this.databaseTableRepository.findAll(project);
		List<Table> tables = databaseTables.stream()
				.map(e -> {
					Table table = new Table();
					table.setProjectId(projectId);
					table.setPhysicalTableName(e.getTableName());
					table.setLogicalTableName(e.getTableComment());
					table.setTableComment(e.getTableComment());
					table.setCreatedAt(new Timestamp(System.currentTimeMillis()));
					return table;
				}).collect(Collectors.toList());
		for(Table table : tables) {
			this.tableRepository.insert(table);
			List<DatabaseColumn> databaseColumns = this.databaseTableRepository.findColumnsByTable(project, table.getPhysicalTableName());
			List<Column> columns = databaseColumns.stream()
					.map(e -> e.toColumn(table.getId()))
					.collect(Collectors.toList());
			// カラム取り込み
			for(Column column : columns) {
				this.columnRepository.insert(column);
			}
		}
		// 外部キー(テーブル間)取り込み
		List<DatabaseReferential> databaseReferentials = this.databaseTableRepository.findReferentials(project);
		for(DatabaseReferential databaseReferential : databaseReferentials) {
			Table fromTable = this.tableRepository.findByTableName(project.getId(), databaseReferential.getFromTableName());
			
			Table toTable = this.tableRepository.findByTableName(project.getId(), databaseReferential.getToTableName());
			
			Referential referential = new Referential();
			referential.setProjectId(project.getId());
			referential.setReferentialType(ReferentialType.PHYSICAL.getId());
			referential.setConstraintName(databaseReferential.getConstraintName());
			referential.setFromTableName(databaseReferential.getFromTableName());
			referential.setFromTableId(fromTable.getId());
			referential.setToTableName(databaseReferential.getToTableName());
			referential.setToTableId(toTable.getId());
			this.referentialRepository.insert(referential);
			
			// 外部キー(カラム)取り込み
			List<DatabaseReferentialColumn> databaseReferentialColumns = this.databaseTableRepository.findReferentialColumns(project, databaseReferential.getConstraintName(), databaseReferential.getFromTableName());
			for(DatabaseReferentialColumn databaseReferentialColumn : databaseReferentialColumns) {
				Column fromColumn = this.columnRepository.findSingle(project.getId(), databaseReferentialColumn.getFromTableName(), databaseReferentialColumn.getFromColumnName());
				
				Column toColumn = this.columnRepository.findSingle(project.getId(), databaseReferentialColumn.getToTableName(), databaseReferentialColumn.getToColumnName());
				
				ReferentialColumn referentialColumn = new ReferentialColumn();
				referentialColumn.setReferentialId(referential.getId());
				referentialColumn.setFromColumnId(fromColumn.getId());
				referentialColumn.setFromColumnName(databaseReferentialColumn.getFromColumnName());
				referentialColumn.setToColumnId(toColumn.getId());
				referentialColumn.setToColumnName(databaseReferentialColumn.getToColumnName());
				this.referentialColumnRepository.insert(referentialColumn);
			}
		}
		// インデックス取り込み
		for(Table table : tables) {
			Table t = this.tableRepository.findByTableName(projectId, table.getPhysicalTableName());
			List<DatabaseIndex> databaseIndexes = this.databaseTableRepository.findIndexes(project, table.getPhysicalTableName());
			List<Index> indexes = databaseIndexes.stream()
					.map(e -> {
						Index index = new Index();
						index.tableId = t.getId();
						index.indexName = e.getIndexName();
						index.isUnique = e.getIsUnique();
						return index;
					}).collect(Collectors.toList());
			for(Index index : indexes) {
				this.indexRepository.insert(index);
				List<DatabaseIndexColumn> databaseIndexColumns = this.databaseTableRepository.findColumnIndexes(project, table.getPhysicalTableName(), index.getIndexName());
				for(DatabaseIndexColumn databaeIndexColumn : databaseIndexColumns) {
					Column column = this.columnRepository.findSingle(project.getId(), table.getPhysicalTableName(), databaeIndexColumn.getColumnName());
					IndexColumn indexColumn = new IndexColumn();
					indexColumn.setIndexId(index.getId());
					indexColumn.setColumnId(column.getId());
					this.indexColumnRepository.insert(indexColumn);
				}
			}
		}
	}
	
	/**
	 * 対象のプロジェクトのテーブル・カラム・インデックス・外部キーの情報を削除する
	 * @param projectId
	 */
	@Transactional(rollbackForClassName={"Exception"})
	public void delete(Long projectId) {
		List<Referential> referentials = this.referentialRepository.findByProjectId(projectId);
		// 外部キー削除
		for(Referential referential : referentials) {
			List<ReferentialColumn> referentialColumns = this.referentialColumnRepository.findByReferentialId(referential.getId());
			this.referentialColumnRepository.deleteAll(referentialColumns);
		}
		this.referentialRepository.deleteAll(referentials);
		
		List<Table> tables = this.tableRepository.findByProjectId(projectId);
		// インデックス削除
		for(Table table : tables) {
			List<Index> indexes = this.indexRepository.findByTableId(table.getId());
			for(Index index : indexes) {
				List<IndexColumn> indexColumns = this.indexColumnRepository.findByIndexId(index.getId());
				this.indexColumnRepository.deleteAll(indexColumns);
			}
			this.indexRepository.deleteAll(indexes);
		}
		// カラム削除
		for(Table table : tables) {
			List<Column> columns = this.columnRepository.findByTableId(table.getId());
			this.columnRepository.deleteAll(columns);
		}
		this.tableRepository.deleteAll(tables);
	}
}
