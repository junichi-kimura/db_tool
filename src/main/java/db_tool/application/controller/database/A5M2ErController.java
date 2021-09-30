package db_tool.application.controller.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import db_tool.application.repository.ColumnRepository;
import db_tool.application.repository.ProjectRepository;
import db_tool.application.repository.TableRepository;
import db_tool.application.service.A5M2Service;
import db_tool.domain.model.Column;
import db_tool.domain.model.Table;
import db_tool.domain.type.DatabaseMergeType;
import db_tool.application.view.database.a5m2er.A5M2ErMergeDownload;
import db_tool.application.view.database.a5m2er.A5M2ErSearchInfo;
import db_tool.application.view.database.a5m2er.A5M2ErUpload;
import db_tool.application.view.database.a5m2er.A5M2erDiffDetail;
import db_tool.application.view.database.a5m2er.analyze.Analyzer;

@Controller
@RequestMapping("/database/a5m2_er")
public class A5M2ErController {

	private ProjectRepository projectRepository;
	private TableRepository tableRepository;
	private ColumnRepository columnRepository;
	private A5M2Service a5M2Service;
	
	@Autowired
	public A5M2ErController(ProjectRepository projectRepository,
			TableRepository tableRepository, 
			ColumnRepository columnRepository,
			A5M2Service a5M2Service) {
		this.projectRepository = projectRepository;
		this.tableRepository = tableRepository;
		this.columnRepository = columnRepository;
		this.a5M2Service = a5M2Service;
	}
	
	@RequestMapping("/")
	public String index(Model model, @ModelAttribute @Validated A5M2ErSearchInfo searchInfo, BindingResult result) {
		searchInfo.setProjects(this.projectRepository.findAll());
		model.addAttribute("searchInfo", searchInfo);
		if (result.hasErrors()) {
			return "/database/a5m2er/index";
		}
		model.addAttribute("isInvolved", (this.tableRepository.findByProjectId(searchInfo.getProjectId()).size() > 0));
		return "/database/a5m2er/index";
	}
	
	@RequestMapping("/preInvolve")
	public String preInvolve(Model model, @ModelAttribute("projectId") Integer projectId) {
		model.addAttribute("projectId", projectId);
		return "/database/a5m2er/preInvolve";
	}
	
	@RequestMapping("/upload")
	@ResponseBody
	public A5M2ErUpload upload(@RequestParam("file") MultipartFile file) throws IOException {
		String path = System.getProperty("user.home") + File.separator + ".db_tool";
		File dir = new File(path);
		if (dir.exists() || dir.mkdir()) {
			try {
				File f = new File(path + File.separator + file.getOriginalFilename());
				f.createNewFile();
				FileOutputStream fos = new FileOutputStream(f);
				fos.write(file.getBytes());
				fos.close();
				
				A5M2ErUpload upload = new A5M2ErUpload();
				upload.status = true;
				upload.fileName = file.getOriginalFilename();
				return upload;
			} catch (IOException e) {
				A5M2ErUpload upload = new A5M2ErUpload();
				upload.status = false;
				upload.error = "ファイルアップロードに失敗しました";
				return upload;
			}
		}
		return null;
	}
	
	@RequestMapping("/mergeConfirm")
	public String mergeConfirm(Model model, @ModelAttribute("form") @Validated A5M2ErMergeDownload form, BindingResult result) {
		if (result.hasErrors()) {
			return "/database/a5m2er/preInvolve";
		}
		DatabaseMergeType databaseMergeType = DatabaseMergeType.of(form.getMergeType());
		if (databaseMergeType == null) {
			return "/database/a5m2er/preInvolve";
		}
		
		model.addAttribute("confirmOutput", this.a5M2Service.getConfirmOutput(form.getFileName(), form.getProjectId()));
		
		A5M2ErMergeDownload downloadForm = new A5M2ErMergeDownload();
		downloadForm.setProjectId(form.getProjectId());
		downloadForm.setFileName(form.getFileName());
		downloadForm.setMergeType(DatabaseMergeType.CREATE_DELETE.getId());
		model.addAttribute("downloadForm", downloadForm);
		
		return "database/a5m2er/mergeConfirm";
	}
	
	@RequestMapping("/diffTable")
	public String diffTable(Model model, @ModelAttribute("form") @Validated A5M2erDiffDetail form, BindingResult result) {
		if (result.hasErrors()) {
			return "redirect:/reloadCloseBox";
		}
		model.addAttribute("confirmTable", this.a5M2Service.getDiffConfirmTable(form));
		return "database/a5m2er/diffTable";
	}
	
	@RequestMapping("/diffColumn")
	public String diffColumn(Model model, @ModelAttribute("form") @Validated A5M2erDiffDetail form, BindingResult result) {
		if (result.hasErrors()) {
			return "redirect:/reloadCloseBox";
		}
		model.addAttribute("confirmColumn", this.a5M2Service.getDiffConfirmColumn(form));
		return "database/a5m2er/diffColumn";
	}
	
	@RequestMapping("/mergeDownload")
	@ResponseBody
	public String mergeDownload(HttpServletResponse response, @ModelAttribute("downloadForm") @Validated A5M2ErMergeDownload a5M2ErMergeDownload) throws IOException {
		DatabaseMergeType databaseMergeType = DatabaseMergeType.of(a5M2ErMergeDownload.getMergeType());
		if (databaseMergeType == null) {
			return null;
		}
		String path = System.getProperty("user.home") + File.separator + ".db_tool" + File.separator + a5M2ErMergeDownload.getFileName();
		File f = new File(path);
		Analyzer analyzer = new Analyzer(f);
		analyzer.analyze();
		
		List<Table> tables = this.tableRepository.findByProjectId(a5M2ErMergeDownload.getProjectId());
		analyzer.mergeTables(tables, a5M2ErMergeDownload.getEnumMergeType());
		for(Table table : tables) {
			List<Column> columns = this.columnRepository.findByTableId(table.getId());
			analyzer.mergeColumns(table.getPhysicalTableName(), columns, a5M2ErMergeDownload.getEnumMergeType());
		}
		
		String fileName = System.currentTimeMillis() + a5M2ErMergeDownload.getFileName();
		File outputFile = new File(System.getProperty("user.home") + File.separator + ".db_tool" + File.separator + fileName);
		outputFile = analyzer.output(outputFile);
		
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + a5M2ErMergeDownload.fileName);
		response.setContentLength((int) outputFile.length());
		outputStreamWrite(response, outputFile);
		return null;
	}
	
	private void outputStreamWrite(HttpServletResponse response, File f) throws IOException {
		ServletOutputStream os = null;
		InputStream in = null;
		try {
			in = new FileInputStream(f.toString());
			os = response.getOutputStream();
			
			byte[] buff = new byte[1024];
			int len = 0;
			while ((len = in.read(buff, 0, buff.length)) != -1) {
				os.write(buff, 0, len);
			}
			os.flush();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (os != null) {
				os.close();
			}
			if (in != null) {
				in.close();
			}
		}
		
	}
}
