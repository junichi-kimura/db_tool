package db_tool.application.view.master.project;


import javax.validation.constraints.NotBlank;

import db_tool.domain.model.Project;
import db_tool.domain.type.DatabaseType;
import db_tool.domain.validate.project.ProjectId;
import lombok.Data;

@Data
public class ProjectInputForm {

	@ProjectId
	private Long id;
	
	@NotBlank
	private String projectName;
	
	@NotBlank(message = "選択してください")
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
		return DatabaseType.of(Integer.parseInt(this.getDatabaseType()));
	}
	
	public void setProject(Project project) {
		this.id = project.getId();
		this.projectName = project.getProjectName();
		this.databaseType = project.getDatabaseType().toString();
		this.databaseHostName = project.getDatabaseHostName();
		this.databaseName = project.getDatabaseName();
		this.databaseSchema = project.getDatabaseSchema();
		this.databaseUser = project.getDatabaseUser();
		this.databasePass = project.getDatabasePass();
		this.databasePort = project.getDatabasePort();
	}
	
	public Project getProject() {
		Project project = new Project();
		project.setId(this.getId());
		project.setProjectName(this.getProjectName());
		project.setDatabaseType(this.toDatabaseType().getId());
		project.setDatabaseHostName(this.getDatabaseHostName());
		project.setDatabaseName(this.getDatabaseName());
		project.setDatabaseSchema(this.getDatabaseSchema());
		project.setDatabaseUser(this.getDatabaseUser());
		project.setDatabasePass(this.getDatabasePass());
		project.setDatabasePort(this.getDatabasePort());
		return project;
	}
}
