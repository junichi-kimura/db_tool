package db_tool.domain.validate.project;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.core.GenericTypeResolver;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import db_tool.application.repository.BaseRepository;

public class IdValidator implements ConstraintValidator<IdExists, Long>, Validator {

	private AutowireCapableBeanFactory factory;
	
	@Autowired
	public IdValidator(AutowireCapableBeanFactory factory) {
		this.factory = factory;
	}
	
	private BaseRepository<?> repository;
	
	@Override
	public void initialize(IdExists idExists) {
		Class<? extends BaseRepository<?>> repositoryClassType = idExists.repository();
		this.repository = factory.createBean(repositoryClassType);
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Long.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Long id = (Long) target;
		Class<?>[] classTypes = GenericTypeResolver.resolveTypeArguments(BaseRepository.class, repository.getClass());
		Class<?> clazz = classTypes[0];
		clazz = (Class<?>) this.repository.findById(id);
		if (clazz == null) {
			errors.reject("project.projectId.invalid.format");
		}
	}

	@Override
	public boolean isValid(Long id, ConstraintValidatorContext context) {
		if (id == null) {
			return true;
		}
		return this.repository.findById(id) != null;
	}

}
