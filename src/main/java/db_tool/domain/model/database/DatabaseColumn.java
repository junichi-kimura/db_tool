package db_tool.domain.model.database;

import lombok.Data;

@Data
public class DatabaseColumn {

	@com.miragesql.miragesql.annotation.Column(name = "table_schema")
	public String tableSchema;
	
	@com.miragesql.miragesql.annotation.Column(name = "table_name")
	public String tableName;
	
	@com.miragesql.miragesql.annotation.Column(name = "column_name")
	public String columnName;
	
	@com.miragesql.miragesql.annotation.Column(name = "column_comment")
	public String columnComment;
	
	@com.miragesql.miragesql.annotation.Column(name = "ordinal_position")
	public Integer ordinalPosition;
	
	@com.miragesql.miragesql.annotation.Column(name = "column_type")
	public String columnType;
	
	@com.miragesql.miragesql.annotation.Column(name = "character_maximum_length")
	public Integer characterMaximumLength;
	
	@com.miragesql.miragesql.annotation.Column(name = "is_nullable")
	public Boolean isNullable;
	
	@com.miragesql.miragesql.annotation.Column(name = "column_default")
	public String columnDefault;
	
	@com.miragesql.miragesql.annotation.Column(name = "column_key")
	public String columnKey;
	
	@com.miragesql.miragesql.annotation.Column(name = "extra")
	public String extra;
	
	public db_tool.domain.model.Column toColumn(Long tableId) {
		db_tool.domain.model.Column column = new db_tool.domain.model.Column();
		column.setTableId(tableId);
		column.setPhysicalColumnName(this.columnName);
		column.setLogicalColumnName(this.columnComment);
		column.setColumnComment(this.columnComment);
		column.setOrdinal(this.ordinalPosition);
		column.setColumnType(this.columnType);
		column.setCharacterMaximumLength(this.characterMaximumLength);
		column.setIsNullable(this.isNullable);
		column.setColumnDefault(this.columnDefault);
		column.setColumnKey(this.columnKey);
		column.setExtra(this.extra);
		return column;
	}
}
