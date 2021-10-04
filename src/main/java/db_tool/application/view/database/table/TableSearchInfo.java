package db_tool.application.view.database.table;

import java.util.List;

import db_tool.application.repository.ProjectRepository;
import db_tool.domain.model.Project;
import db_tool.domain.validate.project.IdExists;
import lombok.Data;

@Data
public class TableSearchInfo {
	
	private List<Project> projects;
	
	@IdExists(repository = ProjectRepository.class, message = "存在しないプロジェクトIDです")
	public Long projectId;
	
	public String tableName;
	
	public String columnName;
}
