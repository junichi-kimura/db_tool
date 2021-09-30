package db_tool.application.view.database.table;

import db_tool.domain.validate.project.ConnectedProjectId;
import db_tool.domain.validate.project.ProjectId;
import lombok.Data;

@Data
public class TablePreInvolveForm {

	@ConnectedProjectId
	@ProjectId
	private Long projectId;
}
