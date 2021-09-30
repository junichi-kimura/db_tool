package db_tool.domain.model;

import java.sql.Timestamp;

import com.miragesql.miragesql.annotation.PrimaryKey;
import com.miragesql.miragesql.annotation.PrimaryKey.GenerationType;
import com.miragesql.miragesql.annotation.Table;

import lombok.Data;

@Data
@Table(name="index_columns")
public class IndexColumn {

	@PrimaryKey(generationType=GenerationType.IDENTITY)
	@com.miragesql.miragesql.annotation.Column(name="id")
	public Long id;
	
	@com.miragesql.miragesql.annotation.Column(name="index_id")
	public Long indexId;
	
	@com.miragesql.miragesql.annotation.Column(name="column_id")
	public Long columnId;
	
	@com.miragesql.miragesql.annotation.Column(name="created_at")
	public Timestamp createdAt;
	
	@com.miragesql.miragesql.annotation.Column(name="updated_at")
	public Timestamp updatedAt;
}
