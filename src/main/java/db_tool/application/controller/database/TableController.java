package db_tool.application.controller.database;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import db_tool.application.repository.ColumnRepository;
import db_tool.application.repository.ProjectRepository;
import db_tool.application.repository.ReferentialColumnRepository;
import db_tool.application.repository.TableRepository;
import db_tool.application.service.TableInvolveService;
import db_tool.application.service.TableService;
import db_tool.application.view.database.table.MirageSqlEntity;
import db_tool.application.view.database.table.TablePreInvolveForm;
import db_tool.application.view.database.table.TableSearchInfo;
import db_tool.domain.model.Column;
import db_tool.domain.model.Table;

@Controller
@RequestMapping("/database/table")
public class TableController {

	private ProjectRepository projectRepository;
	private TableRepository tableRepository;
	private ColumnRepository columnRepository;
	private ReferentialColumnRepository referentialColumnRepository;
	
	private TableService tableService;
	private TableInvolveService tableInvolveService;
	
	@Autowired
	public TableController(ProjectRepository projectRepository, 
			TableRepository tableRepository,
			ColumnRepository columnRepository,
			ReferentialColumnRepository referentialColumnRepository,
			TableService tableService,
			TableInvolveService tableInvolveService) {
		this.projectRepository = projectRepository;
		this.tableRepository = tableRepository;
		this.columnRepository = columnRepository;
		this.referentialColumnRepository = referentialColumnRepository;
		this.tableService = tableService;
		this.tableInvolveService = tableInvolveService;
	}
	
	@RequestMapping("/")
	public String index(Model model, @ModelAttribute("searchInfo") @Validated TableSearchInfo searchInfo, BindingResult result) {
		searchInfo.setProjects(this.projectRepository.findAll());
		model.addAttribute("searchInfo", searchInfo);
		if (result.hasErrors()) {
			return "/database/table/index";
		}
		model.addAttribute("tables", this.tableRepository.find(searchInfo));
		return "/database/table/index";
	}
	
	@RequestMapping("/detail")
	public String detail(Model model, @RequestParam("table_id") Optional<Long> tableId) {
		if (!tableId.isPresent()) {
			return "/database/table/detail";
		}
		Table table = this.tableRepository.findById(tableId.get());
		model.addAttribute("table", table);
		model.addAttribute("columns", this.tableService.getColumnDetails(table.getId()));
		model.addAttribute("indexes", this.tableService.getIndexDetails(table.getId()));
		model.addAttribute("referenceDetails", this.referentialColumnRepository.findReferenceDetails(table.getProjectId(), table.getPhysicalTableName()));
		model.addAttribute("referencedDetails", this.referentialColumnRepository.findReferencedDetails(table.getProjectId(), table.getPhysicalTableName()));
		return "/database/table/detail";
	}
	
	@RequestMapping("/mirageSql")
	public String mirageSql(Model model, @ModelAttribute("tableId") Long tableId) {
		Table table = this.tableRepository.findById(tableId);
		List<Column> columns = this.columnRepository.findByTableId(tableId);
		MirageSqlEntity mirageSqlEntity = new MirageSqlEntity(table, columns);
		model.addAttribute("mirageSqlEntity", mirageSqlEntity);
		return "/database/table/mirageSql";
	}
	
	@RequestMapping("/preInvolve")
	public String preInvolve(Model model, @ModelAttribute("form") @Valid TablePreInvolveForm form, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("error", "DBに接続できません");
			return "/database/table/notConnected";
		}
		model.addAttribute("projectId", form.getProjectId());
		model.addAttribute("tableInvolves", this.tableInvolveService.getTableInvolve(form.getProjectId()));
		return "/database/table/preInvolve";
	}
	
	@RequestMapping("/involve")
	public String involve(Model model, @ModelAttribute("projectId") Long projectId) {
		this.tableInvolveService.delete(projectId);
		this.tableInvolveService.involve(projectId);
		return "redirect:/reloadCloseBox";
	}
}
