package db_tool.application.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.miragesql.miragesql.SqlManager;

import db_tool.domain.model.Project;
import db_tool.application.view.master.project.ProjectInputForm;
import lombok.Getter;

@Repository
public class ProjectRepository extends BaseRepository<Project> {
	
	@Getter
	private SqlManager sqlManager;
	
	@Autowired
	public ProjectRepository(SqlManager sqlManager) {
		this.sqlManager = sqlManager;
	}
	
	@Transactional
	public void update(ProjectInputForm form) {
		Project project = this.findById(form.getId());
		project.setProjectName(form.getProjectName());
		project.setDatabaseType(form.toDatabaseType().getId());
		project.setDatabaseHostName(form.getDatabaseHostName());
		project.setDatabaseName(form.getDatabaseName());
		project.setDatabaseSchema(form.getDatabaseSchema());
		project.setDatabaseUser(form.getDatabaseUser());
		project.setDatabasePass(form.getDatabasePass());
		project.setDatabasePort(form.getDatabasePort());
		super.update(project);
	}
}
