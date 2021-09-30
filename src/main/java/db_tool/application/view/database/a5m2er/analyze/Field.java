package db_tool.application.view.database.a5m2er.analyze;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;

import db_tool.domain.model.Column;

public class Field {

	public String Field;
	
	public String logicalColumnName;
	
	public String columnName;
	
	public String columnType;
	
	public String isNotNull;
	
	public String primaryKey;
	
	public String columnDefault;
	
	public String columnComment;
	
	public String color;
	
	public String unknown; // よくわからないカラム
	
	public void addField(String field) {
		this.Field = field;
		
		// csv形式の文字列を分解して各フィールドに落とし込む
		StringReader sr = new StringReader(field);
		try {
			CSVParser parse = CSVFormat.DEFAULT.parse(sr);
			List<CSVRecord> records = parse.getRecords();
			if (records.get(0).size() >= 9) {
				this.logicalColumnName = records.get(0).get(0).toString();
				this.columnName = records.get(0).get(1).toString();
				this.columnType = records.get(0).get(2).toString();
				this.isNotNull = records.get(0).get(3).toString();
				this.primaryKey = records.get(0).get(4).toString();
				this.columnDefault = records.get(0).get(5).toString();
				this.columnComment = records.get(0).get(6).toString();
				this.color = records.get(0).get(7).toString();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void createAndDelete(Column column, Integer primaryKeyNum) {
		if (Strings.isEmpty(this.logicalColumnName) && Strings.isNotEmpty(column.logicalColumnName)) {
			this.logicalColumnName = column.logicalColumnName;
		}
		if (Strings.isEmpty(this.columnName) && Strings.isNotEmpty(column.logicalColumnName)) {
			this.columnName = column.logicalColumnName;
		}
		if (Strings.isEmpty(this.columnType) && Strings.isNotEmpty(column.columnType)) {
			this.columnType = column.columnType;
		}
		if (Strings.isEmpty(this.isNotNull) && column.isNullable != null) {
			this.isNotNull = column.isNullable ? "" : "NOT NULL";
		}
		if (Strings.isEmpty(this.primaryKey) && Strings.isNotEmpty(column.columnKey)) {
			this.primaryKey = column.columnKey.equals(Column.PRIMARY_KEY) ? primaryKeyNum.toString() : "";
		}
		if (Strings.isEmpty(this.columnDefault) && Strings.isNotEmpty(column.columnDefault)) {
			this.columnDefault = column.columnDefault;
		}
	}
	
	public void createAndDeleteAndUpdate(Column column, Integer primaryKeyNum) {
		this.logicalColumnName = column.logicalColumnName;
		this.columnName = column.physicalColumnName;
		this.columnType = column.columnType;
		this.isNotNull = column.isNullable ? "" : "NOT NULL";
		this.primaryKey = column.columnKey.equals(Column.PRIMARY_KEY) ? primaryKeyNum.toString() : "";
		this.columnDefault = column.columnDefault;
	}
	
	public String output() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("\"" + this.logicalColumnName + "\",");
		sb.append("\"" + this.columnName + "\",");
		sb.append("\"" + this.columnType + "\",");
		if (StringUtils.isEmpty(this.isNotNull)) {
			sb.append(",");
		} else {
			sb.append("\"" + this.isNotNull + "\",");
		}
		
		sb.append(this.primaryKey + ",");
		sb.append("\"" + (this.columnDefault != null ? this.columnDefault : "") + "\",");
		sb.append("\"" + (this.columnComment != null ? this.columnComment : "") + "\",");
		if (this.color != null) {
			sb.append(this.color + ",");
		} else {
			sb.append("$FFFFFFFF,");
		}
		
		sb.append("\"" + (this.unknown != null ? this.unknown : "") + "\"");
		sb.append("\n");
		return sb.toString();
	}
}
