package db_tool.domain.validate.project;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ConnectedProjectIdValidator.class)
public @interface ConnectedProjectId {
	String message() default "{jp.itokuro.db_tool.constraints.id.NotExists.message}";
	  
	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
