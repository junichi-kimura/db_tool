package db_tool.domain.validate.project;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import db_tool.application.repository.BaseRepository;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IdValidator.class)
public @interface IdExists {
	String message() default "{jp.itokuro.db_tool.constraints.id.NotExists.message}";

	Class<? extends BaseRepository<?>> repository();
	
	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
