package db_tool.domain.model.database;

import lombok.Data;

@Data
public class DatabaseIndexColumn {

	@com.miragesql.miragesql.annotation.Column(name = "column_name")
	public String columnName;
}
