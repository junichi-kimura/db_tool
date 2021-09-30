package db_tool.application.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.miragesql.miragesql.ClasspathSqlResource;
import com.miragesql.miragesql.SqlManager;
import com.miragesql.miragesql.SqlResource;

import db_tool.domain.model.Column;
import lombok.Getter;

@Repository
public class ColumnRepository extends BaseRepository<Column>{

	@Getter
	private SqlManager sqlManager;
	
	@Autowired
	public ColumnRepository(SqlManager sqlManager) {
		this.sqlManager = sqlManager;
	}
	
	@SuppressWarnings("serial")
	public Column findSingle(Long projectId, String tableName, String columnName) {
		SqlResource selectSql = new ClasspathSqlResource("sql/column/findSingle.sql");
		Column column = this.sqlManager.getSingleResult(Column.class, selectSql, new HashMap<String, Object>(){{
			put("project_id", projectId);
			put("table_name", tableName);
			put("column_name", columnName);
		}});
		return column;
	}
	
	@SuppressWarnings("serial")
	public List<Column> findByTableId(Long tableId) {
		SqlResource selectSql = new ClasspathSqlResource("sql/column/findByTableId.sql");
		List<Column> columns = this.sqlManager.getResultList(Column.class, selectSql, new HashMap<String, Object>(){{
			put("table_id", tableId);
		}});
		return columns;
	}
}
