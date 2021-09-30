package db_tool.application.view.database.a5m2er;

import java.util.List;

import db_tool.domain.model.Project;
import lombok.Data;

@Data
public class A5M2ErSearchInfo {

	private List<Project> projects;
	
	public Long projectId;
}
