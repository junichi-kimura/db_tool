package db_tool.application.view.database.table;

import java.util.List;

import db_tool.domain.model.Project;
import db_tool.domain.validate.project.ProjectId;
import lombok.Data;

@Data
public class TableSearchInfo {
	
	private List<Project> projects;
	
	@ProjectId
	public Long projectId;
	
	public String tableName;
	
	public String columnName;
}
