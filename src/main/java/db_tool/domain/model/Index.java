package db_tool.domain.model;

import java.sql.Timestamp;

import com.miragesql.miragesql.annotation.PrimaryKey;
import com.miragesql.miragesql.annotation.PrimaryKey.GenerationType;
import com.miragesql.miragesql.annotation.Table;

import lombok.Data;

@Data
@Table(name="indexes")
public class Index {

	@PrimaryKey(generationType=GenerationType.IDENTITY)
	@com.miragesql.miragesql.annotation.Column(name="id")
	public Long id;
	
	@com.miragesql.miragesql.annotation.Column(name="table_id")
	public Long tableId;
	
	@com.miragesql.miragesql.annotation.Column(name="index_name")
	public String indexName;
	
	@com.miragesql.miragesql.annotation.Column(name="is_unique")
	public Boolean isUnique;
	
	@com.miragesql.miragesql.annotation.Column(name="note")
	public String note;
	
	@com.miragesql.miragesql.annotation.Column(name="created_at")
	public Timestamp createdAt;
	
	@com.miragesql.miragesql.annotation.Column(name="updated_at")
	public Timestamp updatedAt;
}
