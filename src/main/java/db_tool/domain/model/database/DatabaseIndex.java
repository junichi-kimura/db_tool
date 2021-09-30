package db_tool.domain.model.database;

import lombok.Data;

@Data
public class DatabaseIndex {

	@com.miragesql.miragesql.annotation.Column(name = "index_name")
	public String indexName;
	
	@com.miragesql.miragesql.annotation.Column(name = "is_unique")
	public Boolean isUnique;
}
