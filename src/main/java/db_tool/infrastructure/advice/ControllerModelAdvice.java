package db_tool.infrastructure.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import db_tool.domain.type.DefaultType;
import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.Configuration;
import freemarker.template.TemplateHashModel;

@ControllerAdvice
public class ControllerModelAdvice {

	@ModelAttribute("ENUM_PACKAGE")
	public String addEnumPackage() {
		return DefaultType.class.getPackage().getName();
	}
	
	@ModelAttribute("enum")
	public TemplateHashModel addEnum() {
		// Modelに追加するオブジェクトを返却する
		BeansWrapper beansWrapper = new BeansWrapperBuilder(Configuration.VERSION_2_3_21).build();
		return beansWrapper.getEnumModels();
	}
}
