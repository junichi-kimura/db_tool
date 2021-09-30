package db_tool.domain.model.database;

import lombok.Data;

@Data
public class DatabaseTable {

	@com.miragesql.miragesql.annotation.Column(name="table_name")
	public String tableName;
	
	@com.miragesql.miragesql.annotation.Column(name="table_comment")
	public String tableComment;
}
