package db_tool.domain.type;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;

public enum ColumnType implements DefaultType<String>{

	INT("int", "int", "Integer"),
	INTEGER("integer", "INTEGER", "Integer"),
	BIGINT("bigint", "bigint", "Long"),
	VARCHAR("varchar", "varchar", "String"),
	TIMESTAMP("timestamp", "timestamp", "Timestamp"),
	LONG("long", "long", "Long"),
	CHAR("char", "char", "String"),
	DATETIME("datetime", "datetime", "Timestamp"),
	DATE("date", "date", "Date"),
	DECIMAL("decimal", "decimal", "DOUBLE"),
	FLOAT("float", "float", "Float"),
	TEXT("text", "text", "String"),
	TIME("time", "time", "Timestamp"),
	TINYINT("tinyint", "tinyint", "Integer"),
	BOOLEAN("boolean", "boolean", "Boolean"),
	CLOB("clob", "clob", "String");
	
	@Getter
	private String id;
	
	@Getter
	private String name;
	
	@Getter
	private String javaObject;
	
	ColumnType(String id, String name, String javaObject) {
		this.id = id;
		this.name = name;
		this.javaObject = javaObject;
	}
	
	public static ColumnType of(String id) {
		for(ColumnType columnType : ColumnType.values()) {
			if (StringUtils.equals(id, columnType.getId()) || StringUtils.equals(id, columnType.getId().toUpperCase())) {
				return columnType;
			}
		}
		return null;
	}

	@Override
	public String getValue() {
		return this.id;
	}
	
	
}
