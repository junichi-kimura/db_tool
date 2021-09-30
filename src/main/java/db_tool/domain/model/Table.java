package db_tool.domain.model;

import java.sql.Timestamp;

import com.miragesql.miragesql.annotation.Column;
import com.miragesql.miragesql.annotation.PrimaryKey;
import com.miragesql.miragesql.annotation.PrimaryKey.GenerationType;

import lombok.Data;

@Data
@com.miragesql.miragesql.annotation.Table(name="tables")
public class Table {

	@PrimaryKey(generationType=GenerationType.IDENTITY)
	@Column(name="id")
	public Long id;
	
	@Column(name="project_id")
	public Long projectId;
	
	@Column(name="physical_table_name")
	public String physicalTableName;
	
	@Column(name="logical_table_name")
	public String logicalTableName;
	
	@Column(name="table_comment")
	public String tableComment;
	
	@Column(name="note")
	public String note;
	
	@Column(name="created_at")
	public Timestamp createdAt;
	
	@Column(name="updated_at")
	public Timestamp updatedAt;
}
