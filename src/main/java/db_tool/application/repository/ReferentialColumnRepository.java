package db_tool.application.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.miragesql.miragesql.ClasspathSqlResource;
import com.miragesql.miragesql.SqlManager;
import com.miragesql.miragesql.SqlResource;

import db_tool.domain.model.ReferentialColumn;
import db_tool.application.view.database.table.ReferenceDetail;
import lombok.Getter;

@Repository
public class ReferentialColumnRepository extends BaseRepository<ReferentialColumn> {

	@Getter
	private SqlManager sqlManager;
	
	@Autowired
	public ReferentialColumnRepository(SqlManager sqlManager) {
		this.sqlManager = sqlManager;
	}
	
	@SuppressWarnings("serial")
	public List<ReferentialColumn> findByReferentialId(Long referentialId) {
		SqlResource selectSql = new ClasspathSqlResource("sql/referential_column/findByReferentialId.sql");
		List<ReferentialColumn> referentialColumns = this.sqlManager.getResultList(ReferentialColumn.class, selectSql, new HashMap<String, Object>(){{
			put("referential_id", referentialId);
		}});
		return referentialColumns;
	}
	
	@SuppressWarnings("serial")
	public List<ReferenceDetail> findReferenceDetails(Long projectId, String fromTableName) {
		SqlResource selectSql = new ClasspathSqlResource("sql/referential_column/findReferenceDetail.sql");
		List<ReferenceDetail> referentialDetails = this.sqlManager.getResultList(ReferenceDetail.class, selectSql, new HashMap<String, Object>(){{
			put("project_id", projectId);
			put("from_table_name", fromTableName);
			put("to_table_name", null);
		}});
		return referentialDetails;
	}
	
	@SuppressWarnings("serial")
	public List<ReferenceDetail> findReferencedDetails(Long projectId, String toTableName) {
		SqlResource selectSql = new ClasspathSqlResource("sql/referential_column/findReferenceDetail.sql");
		List<ReferenceDetail> referentialDetails = this.sqlManager.getResultList(ReferenceDetail.class, selectSql, new HashMap<String, Object>(){{
			put("project_id", projectId);
			put("from_table_name", null);
			put("to_table_name", toTableName);
		}});
		return referentialDetails;
	}
}
