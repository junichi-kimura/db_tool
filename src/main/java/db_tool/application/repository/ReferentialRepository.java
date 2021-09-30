package db_tool.application.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.miragesql.miragesql.ClasspathSqlResource;
import com.miragesql.miragesql.SqlManager;
import com.miragesql.miragesql.SqlResource;

import db_tool.domain.model.Referential;
import lombok.Getter;

@Repository
public class ReferentialRepository extends BaseRepository<Referential> {

	@Getter
	private SqlManager sqlManager;
	
	@Autowired
	public ReferentialRepository(SqlManager sqlManager) {
		this.sqlManager = sqlManager;
	}
	
	@SuppressWarnings("serial")
	public List<Referential> findByProjectId(Long projectId) {
		SqlResource selectSql = new ClasspathSqlResource("sql/referential/findByProjectId.sql");
		List<Referential> referentials = this.sqlManager.getResultList(Referential.class, selectSql, new HashMap<String, Object>(){{
			put("project_id", projectId);
		}});
		return referentials;
	}
}
