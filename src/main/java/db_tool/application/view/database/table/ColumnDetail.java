package db_tool.application.view.database.table;

import db_tool.domain.model.Column;
import db_tool.domain.type.ColumnType;
import lombok.Data;

@Data
public class ColumnDetail {

	public String physicalColumnName;
	
	public String logicalColumnName;
	
	public String columnComment;
	
	public String columnType;
	
	public Integer characterMaximumLength;
	
	public Boolean isNullable;
	
	public String columnDefault;
	
	public String columnKey;
	
	public String extra;
	
	public String note;
	
	public boolean isPrimaryKey() {
		return this.columnKey != null && Column.PRIMARY_KEY.equals(this.columnKey);
	}
	
	public String getColumnType() {
		ColumnType ct = ColumnType.of(this.columnType);
		if (ct == null) {
			return this.columnType;
		}
		switch(ct) {
		case VARCHAR:
		case CHAR:
			return this.columnType + "(" + this.characterMaximumLength + ")";
		default:
			return this.columnType;
		}
		
	}
}
