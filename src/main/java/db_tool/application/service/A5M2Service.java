package db_tool.application.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import db_tool.application.repository.ColumnRepository;
import db_tool.application.repository.TableRepository;
import db_tool.domain.model.Column;
import db_tool.domain.model.Table;
import db_tool.application.view.database.a5m2er.A5M2erDiffDetail;
import db_tool.application.view.database.a5m2er.analyze.Analyzer;
import db_tool.application.view.database.a5m2er.analyze.Entity;
import db_tool.application.view.database.a5m2er.analyze.TableColumns;
import db_tool.application.view.database.a5m2er.merge_confirm.A5M2ErMergeConfirmOutput;
import db_tool.application.view.database.a5m2er.merge_confirm.ConfirmColumn;
import db_tool.application.view.database.a5m2er.merge_confirm.ConfirmTable;

@Service
public class A5M2Service {

	private TableRepository tableRepository;
	private ColumnRepository columnRepository;
	
	@Autowired
	public A5M2Service(TableRepository tableRepository,
			ColumnRepository columnRepository) {
		this.tableRepository = tableRepository;
		this.columnRepository = columnRepository;
	}
	
	@Transactional(readOnly = true)
	public A5M2ErMergeConfirmOutput getConfirmOutput(String fileName, Long projectid) {
		// A5M2のER図を解析
		String path = System.getProperty("user.home") + File.separator + ".db_tool" + File.separator
				+ fileName;
		File f = new File(path);
		Analyzer analyzer = new Analyzer(f);
		analyzer.analyze();
		A5M2ErMergeConfirmOutput confirmOutput = new A5M2ErMergeConfirmOutput();
		for (Entity entity : analyzer.entities) {
			confirmOutput.addA5(entity);
		}

		List<TableColumns> tableColumns = this.getTableColumns(projectid);
		for (TableColumns tableColumn : tableColumns) {
			confirmOutput.addDb(tableColumn);
		}
		return confirmOutput;	
	}
	
	@Transactional(readOnly = true)
	public ConfirmTable getDiffConfirmTable(A5M2erDiffDetail form) {
		A5M2ErMergeConfirmOutput confirmOutput = this.getConfirmOutput(form.getFileName(), form.getProjectId());
		return confirmOutput.getConfirmTables().get(0);
	}
	
	@Transactional(readOnly = true)
	public ConfirmColumn getDiffConfirmColumn(A5M2erDiffDetail form) {
		A5M2ErMergeConfirmOutput confirmOutput = this.getConfirmOutput(form.getFileName(), form.getProjectId());
		return confirmOutput.getConfirmTables().get(0).getConfirmColumns().stream()
				.filter(e -> StringUtils.equals(e.getA5PhysicalColumnName(), form.getColumnName()) || StringUtils.equals(e.getDbPhysicalColumnName(), form.getColumnName()))
				.findFirst().get();
	}
	
	/**
	 * プロジェクトのDBからテーブル・カラム一覧を取得する
	 * @param projectId
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<TableColumns> getTableColumns(Long projectId) {
		List<TableColumns> tableColumns = new ArrayList<>();
		List<Table> tables = this.tableRepository.findByProjectId(projectId);
		for(Table table : tables) {
			TableColumns tableColumn = new TableColumns();
			tableColumn.setTable(table);
			List<Column> columns = this.columnRepository.findByTableId(table.getId());
			tableColumn.columns.addAll(columns);
			tableColumns.add(tableColumn);
		}
		return tableColumns;
	}
}
