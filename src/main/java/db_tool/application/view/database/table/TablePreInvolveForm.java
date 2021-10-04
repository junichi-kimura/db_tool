package db_tool.application.view.database.table;

import db_tool.application.repository.ProjectRepository;
import db_tool.domain.validate.project.ConnectedProjectId;
import db_tool.domain.validate.project.IdExists;
import lombok.Data;

@Data
public class TablePreInvolveForm {

	@ConnectedProjectId
	@IdExists(repository = ProjectRepository.class, message = "存在しないプロジェクトIDです")
	private Long projectId;
}
