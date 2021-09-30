package db_tool.application.repository.database;

import org.springframework.stereotype.Repository;

import com.miragesql.miragesql.SqlManager;
import com.miragesql.miragesql.StringSqlResource;
import com.miragesql.miragesql.session.Session;

import db_tool.infrastructure.database.DatabaseInfo;
import db_tool.infrastructure.database.DynamicSessionFactory;

@Repository
public class DatabaseConnectRepository {

	public boolean isConnect(DatabaseInfo databaseInfo) {
		Session session = DynamicSessionFactory.getSession(databaseInfo);
		SqlManager sqlManager = session.getSqlManager();
		try {
			session.begin();
			sqlManager.getCount(new StringSqlResource("SELECT 1"));
			session.commit();
		} catch (Exception e1) {
			try {
				session.rollback();
			} catch (Exception e2) {
				return false;
			}
			return false;
		} finally {
			session.release();
		}
		return true;
	}
}
