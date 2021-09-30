package db_tool.application.view.database.a5m2er.merge_confirm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import db_tool.domain.model.Column;
import db_tool.application.view.database.a5m2er.analyze.Entity;
import db_tool.application.view.database.a5m2er.analyze.Field;
import db_tool.application.view.database.a5m2er.analyze.TableColumns;
import lombok.Data;

@Data
public class ConfirmTable {

	public String a5PhysicalTableName;
	
	public String dbPhysicalTableName;
	
	public String a5LogicalTableName;
	
	public String dbLogicalTableName;
	
	public String a5Comment;
	
	public String dbComment;
	
	private Map<String, ConfirmColumn> confirmColumnMap = new HashMap<>();
	
	public List<ConfirmColumn> getConfirmColumns() {
		return new ArrayList<ConfirmColumn>(confirmColumnMap.values());
	}
	
	public void setA5(Entity entity) {
		this.a5PhysicalTableName = entity.PName;
		this.a5LogicalTableName = entity.LName;
		this.a5Comment = entity.Comment;
	}
	
	public void setDb(TableColumns tableColumn) {
		this.dbPhysicalTableName = tableColumn.physicalTableName;
		this.dbLogicalTableName = tableColumn.logicalTableName;
		this.dbComment = tableColumn.tableComment;
	}
	
	public void addA5Column(Field field) {
		ConfirmColumn confirmColumn;
		if (confirmColumnMap.containsKey(field.columnName)) {
			confirmColumn = confirmColumnMap.get(field.columnName);
		} else {
			confirmColumn = new ConfirmColumn();
		}
		confirmColumn.a5PhysicalColumnName = field.columnName;
		confirmColumn.a5LogicalColumnName = field.logicalColumnName;
		confirmColumn.a5ColumnType = field.columnType;
		confirmColumn.a5IsNotNull = field.isNotNull;
		confirmColumn.a5PrimaryKey = field.primaryKey;
		confirmColumn.a5ColumnDefault = field.columnDefault;
		confirmColumn.a5ColumnComment = field.columnComment;
		confirmColumnMap.put(field.columnName, confirmColumn);
	}
	
	public void addDbColumn(Column column, Integer primaryKey) {
		ConfirmColumn confirmColumn;
		if (confirmColumnMap.containsKey(column.physicalColumnName)) {
			confirmColumn = confirmColumnMap.get(column.physicalColumnName);
		} else {
			confirmColumn = new ConfirmColumn();
		}
		confirmColumn.dbPhysicalColumnName = column.physicalColumnName;
		confirmColumn.dbLogicalColumnName = column.logicalColumnName;
		confirmColumn.dbColumnType = column.columnType;
		confirmColumn.dbIsNotNull = column.isNullable ? "" : "NOT NULL";
		confirmColumn.dbPrimaryKey = column.columnKey.equals(Column.PRIMARY_KEY) ? primaryKey.toString() : "";
		confirmColumn.dbColumnDefault = column.columnDefault;
		confirmColumn.dbColumnComment = column.columnComment;
		confirmColumnMap.put(column.physicalColumnName, confirmColumn);
	}
	
	public boolean isEqual() {
		return StringUtils.equals(a5PhysicalTableName, dbPhysicalTableName)
			&& StringUtils.equals(a5LogicalTableName, dbLogicalTableName)
			&& StringUtils.equals(a5Comment, dbComment);
	}
}
