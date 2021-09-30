package db_tool.application.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import db_tool.application.repository.ColumnRepository;
import db_tool.application.repository.IndexColumnRepository;
import db_tool.application.repository.IndexRepository;
import db_tool.domain.model.Index;
import db_tool.application.view.database.table.ColumnDetail;
import db_tool.application.view.database.table.IndexColumnDetail;
import db_tool.application.view.database.table.IndexDetail;

@Service
public class TableService {

	private ColumnRepository columnRepository;
	private IndexRepository indexRepository;
	private IndexColumnRepository indexColumnRepository;
	
	@Autowired
	public TableService(ColumnRepository columnRepository, 
			IndexRepository indexRepository,
			IndexColumnRepository indexColumnRepository) {
		this.columnRepository = columnRepository;
		this.indexRepository = indexRepository;
		this.indexColumnRepository = indexColumnRepository;
	}
	
	public List<ColumnDetail> getColumnDetails(Long tableId) {
		return this.columnRepository.findByTableId(tableId).stream().map(c -> {
			ColumnDetail columnDetail = new ColumnDetail();
			columnDetail.setPhysicalColumnName(c.physicalColumnName);
			columnDetail.setLogicalColumnName(c.logicalColumnName);
			columnDetail.setColumnComment(c.columnComment);
			columnDetail.setColumnType(c.columnType);
			columnDetail.setCharacterMaximumLength(c.characterMaximumLength);
			columnDetail.setIsNullable(c.isNullable);
			columnDetail.setColumnDefault(c.columnDefault);
			columnDetail.setColumnKey(c.columnKey);
			columnDetail.setExtra(c.extra);
			columnDetail.setNote(c.note);
			return columnDetail;
		}).collect(Collectors.toList());
	}
	
	public List<IndexDetail> getIndexDetails(Long tableId) {
		Map<Long, Index> indexMap = this.indexRepository.findByTableId(tableId).stream()
				.collect(Collectors.toMap(Index::getId, i -> i));
		Map<Long, List<IndexColumnDetail>> indexColumnMap = this.indexColumnRepository.findByTableId(tableId).stream()
				.collect(Collectors.groupingBy(IndexColumnDetail::getIndexId));
		return indexMap.entrySet().stream()
				.map(i -> {
					IndexDetail indexDetail = new IndexDetail();
					indexDetail.setIndexName(i.getValue().getIndexName());
					indexDetail.setIsUnique(i.getValue().getIsUnique());
					indexDetail.setNote(i.getValue().getNote());
					indexDetail.setIndexColumnName(indexColumnMap.get(i.getKey()));
					return indexDetail;
				}).collect(Collectors.toList());
	}
}
