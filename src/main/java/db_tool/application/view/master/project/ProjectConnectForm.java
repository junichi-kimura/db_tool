package db_tool.application.view.master.project;

import javax.validation.constraints.NotBlank;

import db_tool.domain.type.DatabaseType;
import db_tool.infrastructure.database.DatabaseInfo;
import lombok.Data;

@Data
public class ProjectConnectForm implements DatabaseInfo{

	@NotBlank
	private String databaseType;
	
	private String databaseHostName;
	
	@NotBlank
	private String databaseName;
	
	private String databaseSchema;
	
	@NotBlank
	private String databaseUser;
	
	private String databasePass;
	
	private String databasePort;
	
	public DatabaseType toDatabaseType() {
		return DatabaseType.of(Integer.parseInt(this.databaseType));
	}

	@Override
	public DatabaseType getEnumDatabaseType() {
		return DatabaseType.of(Integer.parseInt(this.databaseType));
	}
}
