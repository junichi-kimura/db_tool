package db_tool.application.view.database.table;

import lombok.Data;

@Data
public class IndexColumnDetail {

	@com.miragesql.miragesql.annotation.Column(name = "index_id")
	public Long indexId;
	
	@com.miragesql.miragesql.annotation.Column(name = "column_name")
	public String columnName;
}
