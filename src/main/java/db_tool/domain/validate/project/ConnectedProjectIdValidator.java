package db_tool.domain.validate.project;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import db_tool.application.repository.ProjectRepository;
import db_tool.application.repository.database.DatabaseConnectRepository;
import db_tool.domain.model.Project;

public class ConnectedProjectIdValidator implements ConstraintValidator<ConnectedProjectId, Long>, Validator {

	private ProjectRepository projectRepository;
	private DatabaseConnectRepository databaseConnectRepository;
	
	@Autowired
	public ConnectedProjectIdValidator(ProjectRepository projectRepository, DatabaseConnectRepository databaseConnectRepository) {
		this.projectRepository = projectRepository;
		this.databaseConnectRepository = databaseConnectRepository;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Integer.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Long projectId = (Long) target;
		Project project = this.projectRepository.findById(projectId);
		if (project != null && this.databaseConnectRepository.isConnect(project)) {
			errors.reject("project.connectedProjectId.invalid.format");
		}
	}

	@Override
	public boolean isValid(Long projectId, ConstraintValidatorContext context) {
		if (projectId == null) {
			return true;
		}
		Project project = this.projectRepository.findById(projectId);
		if (project == null) {
			return true;
		}
		return this.databaseConnectRepository.isConnect(project);
	}

}
