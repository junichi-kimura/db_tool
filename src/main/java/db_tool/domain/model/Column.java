package db_tool.domain.model;

import java.sql.Timestamp;

import com.miragesql.miragesql.annotation.PrimaryKey;
import com.miragesql.miragesql.annotation.PrimaryKey.GenerationType;
import com.miragesql.miragesql.annotation.Table;
import com.miragesql.miragesql.annotation.Transient;

import lombok.Data;

@Data
@Table(name="columns")
public class Column {

	@PrimaryKey(generationType=GenerationType.IDENTITY)
	@com.miragesql.miragesql.annotation.Column(name="id")
	public Long id;
	
	@com.miragesql.miragesql.annotation.Column(name="table_id")
	public Long tableId;
	
	@com.miragesql.miragesql.annotation.Column(name="physical_column_name")
	public String physicalColumnName;
	
	@com.miragesql.miragesql.annotation.Column(name="logical_column_name")
	public String logicalColumnName;
	
	@com.miragesql.miragesql.annotation.Column(name="column_comment")
	public String columnComment;
	
	@com.miragesql.miragesql.annotation.Column(name="ordinal")
	public Integer ordinal;
	
	@com.miragesql.miragesql.annotation.Column(name="column_type")
	public String columnType;
	
	@com.miragesql.miragesql.annotation.Column(name="character_maximum_length")
	public Integer characterMaximumLength;
	
	@com.miragesql.miragesql.annotation.Column(name="is_nullable")
	public Boolean isNullable;
	
	@com.miragesql.miragesql.annotation.Column(name="column_default")
	public String columnDefault;
	
	@com.miragesql.miragesql.annotation.Column(name="column_key")
	public String columnKey;
	
	@com.miragesql.miragesql.annotation.Column(name="extra")
	public String extra;
	
	@com.miragesql.miragesql.annotation.Column(name="note")
	public String note;
	
	@com.miragesql.miragesql.annotation.Column(name="created_at")
	public Timestamp createdAt;
	
	@com.miragesql.miragesql.annotation.Column(name="updated_at")
	public Timestamp updatedAt;
	
	@Transient
	public static final String PRIMARY_KEY = "PRI";
}
