package db_tool.domain.model;

import java.sql.Timestamp;

import com.miragesql.miragesql.annotation.Column;
import com.miragesql.miragesql.annotation.PrimaryKey;
import com.miragesql.miragesql.annotation.PrimaryKey.GenerationType;
import com.miragesql.miragesql.annotation.Table;

import lombok.Data;

@Data
@Table(name="referential_columns")
public class ReferentialColumn {

	@PrimaryKey(generationType=GenerationType.IDENTITY)
	@Column(name="id")
	public Long id;
	
	@Column(name="referential_id")
	public Long referentialId;
	
	@Column(name="from_column_id")
	public Long fromColumnId;
	
	@Column(name="from_column_name")
	public String fromColumnName;
	
	@Column(name="to_column_id")
	public Long toColumnId;
	
	@Column(name="to_column_name")
	public String toColumnName;
	
	@Column(name="created_at")
	public Timestamp createdAt;
	
	@Column(name="updated_at")
	public Timestamp updatedAt;
}
