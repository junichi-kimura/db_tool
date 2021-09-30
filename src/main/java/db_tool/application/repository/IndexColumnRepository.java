package db_tool.application.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.miragesql.miragesql.ClasspathSqlResource;
import com.miragesql.miragesql.SqlManager;
import com.miragesql.miragesql.SqlResource;

import db_tool.domain.model.IndexColumn;
import db_tool.application.view.database.table.IndexColumnDetail;
import lombok.Getter;

@Repository
public class IndexColumnRepository extends BaseRepository<IndexColumn> {

	@Getter
	private SqlManager sqlManager;
	
	@Autowired
	public IndexColumnRepository(SqlManager sqlManager) {
		this.sqlManager = sqlManager;
	}
	
	@SuppressWarnings("serial")
	public List<IndexColumn> findByIndexId(Long indexId) {
		SqlResource selectSql = new ClasspathSqlResource("sql/index_column/findByIndexId.sql");
		List<IndexColumn> indexColumns = this.sqlManager.getResultList(IndexColumn.class, selectSql, new HashMap<String, Object>(){{
			put("index_id", indexId);
		}});
		return indexColumns;
	}
	
	@SuppressWarnings("serial")
	public List<IndexColumnDetail> findByTableId(Long tableId) {
		SqlResource selectSql = new ClasspathSqlResource("sql/index_column/findByTableId.sql");
		List<IndexColumnDetail> indexColumnDetails = this.sqlManager.getResultList(IndexColumnDetail.class, selectSql, new HashMap<String, Object>(){{
			put("table_id", tableId);
		}});
		return indexColumnDetails;
	}
}
