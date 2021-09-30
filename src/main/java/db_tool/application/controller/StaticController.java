package db_tool.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StaticController {

	@RequestMapping("/reloadCloseBox")
	public String reload(Model model) {
		return "reloadCloseBox";
	}
}
