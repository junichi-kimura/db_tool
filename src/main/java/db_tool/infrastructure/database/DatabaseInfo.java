package db_tool.infrastructure.database;

import db_tool.domain.type.DatabaseType;

public interface DatabaseInfo {

	public DatabaseType getEnumDatabaseType();
	
	public String getDatabaseHostName();
	
	public String getDatabaseName();
	
	public String getDatabaseUser();
	
	public String getDatabasePass();
	
	public String getDatabasePort();
}
