package db_tool.domain.model;

import java.sql.Timestamp;

import com.miragesql.miragesql.annotation.Column;
import com.miragesql.miragesql.annotation.PrimaryKey;
import com.miragesql.miragesql.annotation.PrimaryKey.GenerationType;
import com.miragesql.miragesql.annotation.Table;

import lombok.Data;

@Data
@Table(name="referentials")
public class Referential {

	@PrimaryKey(generationType=GenerationType.IDENTITY)
	@Column(name="id")
	public Long id;
	
	@Column(name="project_id")
	public Long projectId;
	
	@Column(name="referential_type")
	public Integer referentialType;
	
	@Column(name="constraint_name")
	public String constraintName;
	
	@Column(name="from_table_name")
	public String fromTableName;
	
	@Column(name="from_table_id")
	public Long fromTableId;
	
	@Column(name="from_cardinari")
	public Integer fromCardinari;
	
	@Column(name="to_table_name")
	public String toTableName;
	
	@Column(name="to_table_id")
	public Long toTableId;
	
	@Column(name="to_cardinari")
	public Integer toCardinari;
	
	@Column(name="created_at")
	public Timestamp createdAt;
	
	@Column(name="updated_at")
	public Timestamp updatedAt;
}
