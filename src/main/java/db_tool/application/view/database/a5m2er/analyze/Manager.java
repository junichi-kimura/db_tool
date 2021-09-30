package db_tool.application.view.database.a5m2er.analyze;

import java.util.ArrayList;
import java.util.List;

public class Manager {

	public String ProjectName;
	
	public String Author;
	
	public String MaxEntityRowShow;
	public String ReadOnlyRecommend;
	
	public Page page;
	
	public List<Page> Pages = new ArrayList<>();
	
	public String PageInfo;
	
	public String LogicalView;
	public String DecodeDomain;
	public String ViewModePageIndividually;
	public String ViewMode;
	public String ViewFormat;
	public String UseNondependenceDashLine;
	public String FontName;
	public String FontSize;
	public String PaperSize;
	public String HeaderLeft;
	public String HeaderCenter;
	public String HeaderRight;
	public String FooterLeft;
	public String FooterCenter;
	public String FooterRight;
	public String ShowPageoutRelation;
	public String RDBMSType;
	public String RDBMSTypeName;
	public String DefaultPkName;
	public String DefaultPkIndexName;
	public String DefaultIndexName;
	public String DefaultFkName;
	public String SqlSeparator;
	public String ShowTag;
	public String ShowCommonAttributes;
	
	public void add(String s) {
		if (s.startsWith("ProjectName")) {
			this.ProjectName = s.replaceAll("ProjectName=", "");
		} else if (s.startsWith("Author")) {
			this.Author = s.replaceAll("Author=", "");
		} else if (s.startsWith("MaxEntityRowShow")) {
			this.MaxEntityRowShow = s.replaceAll("MaxEntityRowShow=", "");
		} else if (s.startsWith("ReadOnlyRecommend")) {
			this.ReadOnlyRecommend = s.replaceAll("ReadOnlyRecommend=", "");
		} else if (s.startsWith("Page=")) {
			this.page = new Page();
			this.page.Page = s.replaceAll("Page=", "");
		} else if (s.startsWith("PageInfo")) {
			this.page.PageInfo = s.replaceAll("PageInfo=", "");
			this.Pages.add(this.page);
			this.page = new Page();
		}  else if (s.startsWith("LogicalView")) {
			this.LogicalView = s.replaceAll("LogicalView=", "");
		} else if (s.startsWith("DecodeDomain")) {
			this.DecodeDomain = s.replaceAll("DecodeDomain=", "");
		} else if (s.startsWith("ViewModePageIndividually")) {
			this.ViewModePageIndividually = s.replaceAll("ViewModePageIndividually=", "");
		} else if (s.startsWith("ViewMode")) {
			this.ViewMode = s.replaceAll("ViewMode=", "");
		} else if (s.startsWith("ViewFormat")) {
			this.ViewFormat = s.replaceAll("ViewFormat=", "");
		} else if (s.startsWith("UseNondependenceDashLine")) {
			this.UseNondependenceDashLine = s.replaceAll("UseNondependenceDashLine=", "");
		} else if (s.startsWith("FontName")) {
			this.FontName = s.replaceAll("FontName=", "");
		} else if (s.startsWith("FontSize")) {
			this.FontSize = s.replaceAll("FontSize=", "");
		} else if (s.startsWith("PaperSize")) {
			this.PaperSize = s.replaceAll("PaperSize=", "");
		} else if (s.startsWith("HeaderLeft")) {
			this.HeaderLeft = s.replaceAll("HeaderLeft=", "");
		} else if (s.startsWith("HeaderCenter")) {
			this.HeaderCenter = s.replaceAll("HeaderCenter=", "");
		} else if (s.startsWith("HeaderRight")) {
			this.HeaderRight = s.replaceAll("HeaderRight=", "");
		} else if (s.startsWith("FooterLeft")) {
			this.FooterLeft = s.replaceAll("FooterLeft=", "");
		} else if (s.startsWith("FooterCenter")) {
			this.FooterCenter = s.replaceAll("FooterCenter=", "");
		} else if (s.startsWith("FooterRight")) {
			this.FooterRight = s.replaceAll("FooterRight=", "");
		} else if (s.startsWith("ShowPageoutRelation")) {
			this.ShowPageoutRelation = s.replaceAll("ShowPageoutRelation=", "");
		} else if (s.startsWith("RDBMSType=")) {
			this.RDBMSType = s.replaceAll("RDBMSType=", "");
		} else if (s.startsWith("RDBMSTypeName")) {
			this.RDBMSTypeName = s.replaceAll("RDBMSTypeName=", "");
		} else if (s.startsWith("DefaultPkName")) {
			this.DefaultPkName = s.replaceAll("DefaultPkName=", "");
		} else if (s.startsWith("DefaultPkIndexName")) {
			this.DefaultPkIndexName = s.replaceAll("DefaultPkIndexName=", "");
		} else if (s.startsWith("DefaultIndexName")) {
			this.DefaultIndexName = s.replaceAll("DefaultIndexName=", "");
		} else if (s.startsWith("DefaultFkName")) {
			this.DefaultFkName = s.replaceAll("DefaultFkName=", "");
		} else if (s.startsWith("SqlSeparator")) {
			this.SqlSeparator = s.replaceAll("SqlSeparator=", "");
		} else if (s.startsWith("ShowTag")) {
			this.ShowTag = s.replaceAll("ShowTag=", "");
		} else if (s.startsWith("ShowCommonAttributes")) {
			this.ShowCommonAttributes = s.replaceAll("ShowCommonAttributes=", "");
		}
	}
	
	public String output() {
		StringBuilder sb = new StringBuilder();
		sb.append("[Manager]\n");
		sb.append("ProjectName=" + this.ProjectName + "\n");

		sb.append("Author=" + this.Author + "\n");
		sb.append("MaxEntityRowShow=" + this.MaxEntityRowShow + "\n");
		sb.append("ReadOnlyRecommend=" + this.ReadOnlyRecommend + "\n");
		
		for(Page page : this.Pages) {
			sb.append("Page=" + page.Page + "\n");
			sb.append("PageInfo=" + page.PageInfo + "\n");
		}

		sb.append("LogicalView=" + this.LogicalView + "\n");
		sb.append("DecodeDomain=" + this.DecodeDomain + "\n");
		sb.append("ViewModePageIndividually=" + this.ViewModePageIndividually + "\n");
		sb.append("ViewMode=" + this.ViewMode + "\n");
		sb.append("ViewFormat=" + this.ViewFormat + "\n");
		sb.append("UseNondependenceDashLine=" + this.UseNondependenceDashLine + "\n");
		sb.append("FontName=" + this.FontName + "\n");
		sb.append("FontSize=" + this.FontSize + "\n");
		sb.append("PaperSize=" + this.PaperSize + "\n");
		sb.append("HeaderLeft=" + this.HeaderLeft + "\n");
		sb.append("HeaderCenter=" + this.HeaderCenter + "\n");
		sb.append("HeaderRight=" + this.HeaderRight + "\n");
		sb.append("FooterLeft=" + this.FooterLeft + "\n");
		sb.append("FooterCenter=" + this.FooterCenter + "\n");
		sb.append("FooterRight=" + this.FooterRight + "\n");
		sb.append("ShowPageoutRelation=" + this.ShowPageoutRelation + "\n");
		sb.append("RDBMSType=" + this.RDBMSType + "\n");
		sb.append("RDBMSTypeName=" + this.RDBMSTypeName + "\n");
		sb.append("DefaultPkName=" + this.DefaultPkName + "\n");
		sb.append("DefaultPkIndexName=" + this.DefaultPkIndexName + "\n");
		sb.append("DefaultIndexName=" + this.DefaultIndexName + "\n");
		sb.append("DefaultFkName=" + this.DefaultFkName + "\n");
		sb.append("SqlSeparator=" + this.SqlSeparator + "\n");
		sb.append("ShowTag=" + this.ShowTag + "\n");
		sb.append("ShowCommonAttributes=" + this.ShowCommonAttributes + "\n");
		return sb.toString();
	}
}
