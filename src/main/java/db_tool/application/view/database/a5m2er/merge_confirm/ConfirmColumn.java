package db_tool.application.view.database.a5m2er.merge_confirm;

import org.apache.commons.lang3.StringUtils;

import lombok.Data;

@Data
public class ConfirmColumn {

	public String a5PhysicalColumnName;
	
	public String dbPhysicalColumnName;
	
	public String a5LogicalColumnName;
	
	public String dbLogicalColumnName;
	
	public String a5ColumnType;
	
	public String dbColumnType;
	
	public String a5IsNotNull;
	
	public String dbIsNotNull;
	
	public String a5PrimaryKey;
	
	public String dbPrimaryKey;
	
	public String a5ColumnDefault;
	
	public String dbColumnDefault;
	
	public String a5ColumnComment;
	
	public String dbColumnComment;
	
	public boolean isEqual() {
		return StringUtils.equals(a5PhysicalColumnName, dbPhysicalColumnName)
			&& StringUtils.equals(a5LogicalColumnName, dbLogicalColumnName)
			&& StringUtils.equals(a5ColumnType, dbColumnType)
			&& StringUtils.equals(a5IsNotNull, dbIsNotNull)
			&& StringUtils.equals(a5PrimaryKey, dbPrimaryKey)
			&& StringUtils.equals(a5ColumnDefault, dbColumnDefault)
			&& StringUtils.equals(a5ColumnComment, dbColumnComment);
	}
}
