package db_tool.domain.model.database;

import lombok.Data;

@Data
public class DatabaseReferential {

	@com.miragesql.miragesql.annotation.Column(name = "constraint_name")
	public String constraintName;
	
	@com.miragesql.miragesql.annotation.Column(name = "from_table_name")
	public String fromTableName;
	
	@com.miragesql.miragesql.annotation.Column(name = "to_table_name")
	public String toTableName;
	
	
}
