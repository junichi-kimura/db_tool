package db_tool.application.view.database.table;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;

@Data
public class IndexDetail {

	public String indexName;
	
	public Boolean isUnique;
	
	public String indexColumnName;
	
	public String note;
	
	public void setIndexColumnName(List<IndexColumnDetail> indexColumnDetails) {
		this.indexColumnName = indexColumnDetails.stream()
				.map(i -> i.getColumnName())
				.collect(Collectors.joining(","));
	}
}
