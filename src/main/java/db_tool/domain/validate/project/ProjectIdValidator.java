package db_tool.domain.validate.project;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import db_tool.application.repository.ProjectRepository;
import db_tool.domain.model.Project;

public class ProjectIdValidator implements ConstraintValidator<ProjectId, Long>, Validator {

	private ProjectRepository projectRepository;
	
	@Autowired
	public ProjectIdValidator(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Long.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Long projectId = (Long) target;
		Project project = this.projectRepository.findById(projectId);
		if (project == null) {
			errors.reject("project.projectId.invalid.format");
		}
	}

	@Override
	public boolean isValid(Long projectId, ConstraintValidatorContext context) {
		if (projectId == null) {
			return true;
		}
		return this.projectRepository.findById(projectId) != null;
	}

}
