package db_tool.application.view.database.a5m2er.analyze;

import java.util.ArrayList;
import java.util.List;

import db_tool.domain.model.Column;
import db_tool.domain.model.Table;

public class TableColumns {

	public String physicalTableName;
	
	public String logicalTableName;
	
	public String tableComment;
	
	public String note;
	
	public void setTable(Table table) {
		this.physicalTableName = table.physicalTableName;
		this.logicalTableName = table.logicalTableName;
		this.tableComment = table.tableComment;
		this.note = table.note;
	}
	
	public List<Column> columns = new ArrayList<>();
}
