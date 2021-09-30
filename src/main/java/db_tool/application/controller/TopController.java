package db_tool.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TopController {

	@RequestMapping("/")
	public ModelAndView index() {
		return new ModelAndView("index");
	}
}
