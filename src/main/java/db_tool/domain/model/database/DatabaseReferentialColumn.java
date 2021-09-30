package db_tool.domain.model.database;

import lombok.Data;

@Data
public class DatabaseReferentialColumn {
	
	@com.miragesql.miragesql.annotation.Column(name = "from_table_name")
	public String fromTableName;
	
	@com.miragesql.miragesql.annotation.Column(name = "from_column_name")
	public String fromColumnName;
	
	@com.miragesql.miragesql.annotation.Column(name = "to_table_name")
	public String toTableName;
	
	@com.miragesql.miragesql.annotation.Column(name = "to_column_name")
	public String toColumnName;
}
