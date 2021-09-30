package db_tool.application.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.miragesql.miragesql.ClasspathSqlResource;
import com.miragesql.miragesql.SqlManager;
import com.miragesql.miragesql.SqlResource;

import db_tool.domain.model.Index;
import lombok.Getter;

@Repository
public class IndexRepository extends BaseRepository<Index> {

	@Getter
	private SqlManager sqlManager;
	
	@Autowired
	public IndexRepository(SqlManager sqlManager) {
		this.sqlManager = sqlManager;
	}

	@SuppressWarnings("serial")
	public List<Index> findByTableId(Long tableId) {
		SqlResource selectSql = new ClasspathSqlResource("sql/index/findByTableId.sql");
		List<Index> indexes = this.sqlManager.getResultList(Index.class, selectSql, new HashMap<String, Object>(){{
			put("table_id", tableId);
		}});
		return indexes;
	}
}
