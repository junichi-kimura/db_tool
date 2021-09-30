package db_tool.application.repository;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.miragesql.miragesql.ClasspathSqlResource;
import com.miragesql.miragesql.SqlManager;
import com.miragesql.miragesql.SqlResource;

import db_tool.domain.model.Table;
import db_tool.application.view.database.table.TableSearchInfo;
import lombok.Getter;

@Repository
public class TableRepository extends BaseRepository<Table> {

	@Getter
	private SqlManager sqlManager;
	
	@Autowired
	public TableRepository(SqlManager sqlManager) {
		this.sqlManager = sqlManager;
	}
	
	@SuppressWarnings("serial")
	public List<Table> findByProjectId(Long projectId) {
		SqlResource selectSql = new ClasspathSqlResource("sql/table/findByProjectId.sql");
		List<Table> tables = this.sqlManager.getResultList(Table.class, selectSql, new HashMap<String, Object>(){{
			put("project_id", projectId);
		}});
		return tables;
	}
	
	@SuppressWarnings("serial")
	public Table findByTableName(Long projectId, String tableName) {
		SqlResource selectSql = new ClasspathSqlResource("sql/table/findByTableName.sql");
		Table table = this.sqlManager.getSingleResult(Table.class, selectSql, new HashMap<String, Object>(){{
			put("project_id", projectId);
			put("table_name", tableName);
		}});
		return table;
	}
	
	@SuppressWarnings("serial")
	public List<Table> find(TableSearchInfo searchInfo) {
		SqlResource selectSql = new ClasspathSqlResource("sql/table/find.sql");
		List<Table> tables = this.sqlManager.getResultList(Table.class, selectSql, new HashMap<String, Object>(){{
			put("project_id", searchInfo.getProjectId());
			
			put("table_name", StringUtils.isNotEmpty(searchInfo.getTableName()) ? "%" + searchInfo.getTableName() + "%" : null);
			put("column_name", StringUtils.isNotEmpty(searchInfo.getColumnName()) ? "%" + searchInfo.getColumnName() + "%" : null);
		}});
		return tables;
	}
}
