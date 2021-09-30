package db_tool.application.controller.master;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import db_tool.application.repository.ProjectRepository;
import db_tool.application.repository.database.DatabaseConnectRepository;
import db_tool.domain.model.Project;
import db_tool.application.view.master.project.ProjectConnectForm;
import db_tool.application.view.master.project.ProjectInputForm;
import db_tool.application.view.master.project.ProjectIsConnect;

@Controller
@RequestMapping("/master/project")
public class ProjectController {

	private ProjectRepository projectRepository;
	private DatabaseConnectRepository databaseConnectRepository;
	
	@Autowired
	public ProjectController(ProjectRepository projectRepository, 
			DatabaseConnectRepository databaseConnectRepository) {
		this.projectRepository = projectRepository;
		this.databaseConnectRepository = databaseConnectRepository;
	}
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("menu", "master.project");
		model.addAttribute("projects", this.projectRepository.findAll());
		return "/master/project/index";
	}
	
	@RequestMapping("/input")
	public String input(Model model, @RequestParam("id") Optional<Long> id) {
		ProjectInputForm form = new ProjectInputForm();
		if (id.isPresent()) {
			Project project = this.projectRepository.findById(id.get());
			if (project != null) {
				form.setProject(project);
			}
		}
		model.addAttribute("form", form);
		return "/master/project/input";
	}
	
	@RequestMapping("/register")
	public String register(Model model, @ModelAttribute("form") @Valid ProjectInputForm form, BindingResult result) {
		if (result.hasErrors()) {
			return "/master/project/input";
		}
		Project project = form.getProject();
		if (project.getId() != null) {
			this.projectRepository.update(form);
		} else {
			this.projectRepository.insert(project);
		}
		return "redirect:/reloadCloseBox";
	}
	
	@PostMapping("/is_connect")
	@ResponseBody
	public ProjectIsConnect isConnect(Model model, @ModelAttribute @Validated ProjectConnectForm form, BindingResult result) {
		ProjectIsConnect projectIsConnect = new ProjectIsConnect();
		if (result.hasErrors()) {
			projectIsConnect.status = false;
			projectIsConnect.error = "入力エラー";
			projectIsConnect.connect = false;
			return projectIsConnect;
		}
		projectIsConnect.status = true;
		projectIsConnect.error = null;
		projectIsConnect.connect = this.databaseConnectRepository.isConnect(form);
		return projectIsConnect;
	}
}
