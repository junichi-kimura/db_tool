package db_tool.domain.model;

import java.sql.Timestamp;

import com.miragesql.miragesql.annotation.Column;
import com.miragesql.miragesql.annotation.PrimaryKey;
import com.miragesql.miragesql.annotation.PrimaryKey.GenerationType;
import com.miragesql.miragesql.annotation.Table;
import com.miragesql.miragesql.annotation.Transient;

import db_tool.domain.type.DatabaseType;
import db_tool.infrastructure.database.DatabaseInfo;
import lombok.Data;

@Data
@Table(name="projects")
public class Project implements DatabaseInfo{

	@PrimaryKey(generationType=GenerationType.IDENTITY)
	@Column(name="id")
	public Long id;
	
	@Column(name="project_name")
	public String projectName;
	
	@Column(name="database_type")
	public Integer databaseType;
	
	@Column(name="database_host_name")
	public String databaseHostName;
	
	@Column(name="database_name")
	public String databaseName;
	
	@Column(name="database_schema")
	public String databaseSchema;
	
	@Column(name="database_user")
	public String databaseUser;
	
	@Column(name="database_pass")
	public String databasePass;
	
	@Column(name="database_port")
	public String databasePort;
	
	@Column(name="created_at")
	public Timestamp createdAt;
	
	@Column(name="updated_at")
	public Timestamp updatedAt;

	@Override
	@Transient
	public DatabaseType getEnumDatabaseType() {
		return DatabaseType.of(this.databaseType);
	}
	
}
