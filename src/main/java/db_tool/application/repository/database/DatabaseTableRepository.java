package db_tool.application.repository.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.miragesql.miragesql.ClasspathSqlResource;
import com.miragesql.miragesql.SqlManager;
import com.miragesql.miragesql.SqlResource;
import com.miragesql.miragesql.session.Session;

import db_tool.domain.model.database.DatabaseColumn;
import db_tool.domain.model.database.DatabaseIndex;
import db_tool.domain.model.database.DatabaseIndexColumn;
import db_tool.domain.model.database.DatabaseReferential;
import db_tool.domain.model.database.DatabaseReferentialColumn;
import db_tool.domain.model.database.DatabaseTable;
import db_tool.domain.type.DatabaseType;
import db_tool.infrastructure.database.DatabaseInfo;
import db_tool.infrastructure.database.DynamicSessionFactory;

@Repository
public class DatabaseTableRepository {

	@SuppressWarnings("serial")
	public List<DatabaseTable> findAll(DatabaseInfo databaseInfo) {
		Session session = DynamicSessionFactory.getSession(databaseInfo);
		SqlManager sqlManager = session.getSqlManager();
		session.begin();
		List<DatabaseTable> databaseTables = new ArrayList<>();
		try {
			SqlResource selectSql = null;
			switch(databaseInfo.getEnumDatabaseType()) {
			case MySQL:
				selectSql = new ClasspathSqlResource("sql/database_table/findAll.mysql.sql");
				break;
			case H2_MEM:
			case H2_FILE:
			case H2_TCP:
				selectSql = new ClasspathSqlResource("sql/database_table/findAll.h2.sql");
				break;
			default:
				break;
			}
			if (databaseInfo.getEnumDatabaseType() == DatabaseType.MySQL) {
				selectSql = new ClasspathSqlResource("sql/database_table/findAll.mysql.sql");
			}
			databaseTables = sqlManager.getResultList(DatabaseTable.class, selectSql, new HashMap<String, Object>(){{
				put("database_name", databaseInfo.getDatabaseName());
				put("database_schema", databaseInfo.getDatabaseSchema());
			}});
			session.commit();
		} catch (Exception e) {
			session.rollback();
		} finally {
			session.release();
		}
		return databaseTables;
	}
	
	@SuppressWarnings("serial")
	public List<DatabaseColumn> findColumnsByTable(DatabaseInfo databaseInfo, String tableName) {
		Session session = DynamicSessionFactory.getSession(databaseInfo);
		SqlManager sqlManager = session.getSqlManager();
		session.begin();
		List<DatabaseColumn> databaseColumns = new ArrayList<>();
		try {
			SqlResource selectSql = null;
			switch(databaseInfo.getEnumDatabaseType()) {
			case MySQL:
				selectSql = new ClasspathSqlResource("sql/database_table/findColumnsByTable.mysql.sql");
				break;
			case H2_MEM:
			case H2_FILE:
			case H2_TCP:
				selectSql = new ClasspathSqlResource("sql/database_table/findColumnsByTable.h2.sql");
				break;
			default:
				break;
			}
			databaseColumns = sqlManager.getResultList(DatabaseColumn.class, selectSql, new HashMap<String, Object>(){{
				put("table_schema", databaseInfo.getDatabaseSchema());
				put("table_name", tableName);
			}});
			session.commit();
		} catch (Exception e) {
			session.rollback();
		} finally {
			session.release();
		}
		return databaseColumns;
	}
	
	@SuppressWarnings("serial")
	public List<DatabaseReferential> findReferentials(DatabaseInfo databaseInfo) {
		Session session = DynamicSessionFactory.getSession(databaseInfo);
		SqlManager sqlManager = session.getSqlManager();
		session.begin();
		List<DatabaseReferential> databaseReferentials = new ArrayList<>();
		try {
			SqlResource selectSql = null;
			switch(databaseInfo.getEnumDatabaseType()) {
			case MySQL:
				selectSql = new ClasspathSqlResource("sql/database_table/findReferentials.mysql.sql");
				break;
			case H2_MEM:
			case H2_FILE:
			case H2_TCP:
				selectSql = new ClasspathSqlResource("sql/database_table/findReferentials.h2.sql");
				break;
			default:
				break;
			}
			databaseReferentials = sqlManager.getResultList(DatabaseReferential.class, selectSql, new HashMap<String, Object>(){{
				put("table_schema", databaseInfo.getDatabaseSchema());
			}});
			session.commit();
		} catch (Exception e) {
			session.rollback();
		} finally {
			session.release();
		}
		return databaseReferentials;
	}
	
	@SuppressWarnings("serial")
	public List<DatabaseReferentialColumn> findReferentialColumns(DatabaseInfo databaseInfo, String constraintName, String fromTableName) {
		Session session = DynamicSessionFactory.getSession(databaseInfo);
		SqlManager sqlManager = session.getSqlManager();
		session.begin();
		List<DatabaseReferentialColumn> databaseReferentialColumns = new ArrayList<>();
		try {
			SqlResource selectSql = null;
			switch(databaseInfo.getEnumDatabaseType()) {
			case MySQL:
				selectSql = new ClasspathSqlResource("sql/database_table/findReferentialColumns.mysql.sql");
				break;
			case H2_MEM:
			case H2_FILE:
			case H2_TCP:
				selectSql = new ClasspathSqlResource("sql/database_table/findReferentialColumns.h2.sql");
				break;
			default:
				break;
			}
			databaseReferentialColumns = sqlManager.getResultList(DatabaseReferentialColumn.class, selectSql, new HashMap<String, Object>(){{
				put("table_schema", databaseInfo.getDatabaseSchema());
				put("constraint_name", constraintName);
				put("from_table_name", fromTableName);
			}});
			session.commit();
		} catch (Exception e) {
			session.rollback();
		} finally {
			session.release();
		}
		return databaseReferentialColumns;
	}
	
	@SuppressWarnings("serial")
	public List<DatabaseIndex> findIndexes(DatabaseInfo databaseInfo, String tableName) {
		Session session = DynamicSessionFactory.getSession(databaseInfo);
		SqlManager sqlManager = session.getSqlManager();
		session.begin();
		List<DatabaseIndex> databaseIndexes = new ArrayList<>();
		try {
			SqlResource selectSql = null;
			switch(databaseInfo.getEnumDatabaseType()) {
			case MySQL:
				selectSql = new ClasspathSqlResource("sql/database_table/findIndexesByTable.mysql.sql");
				break;
			case H2_MEM:
			case H2_FILE:
			case H2_TCP:
				selectSql = new ClasspathSqlResource("sql/database_table/findIndexesByTable.h2.sql");
				break;
			default:
				break;
			}
			databaseIndexes = sqlManager.getResultList(DatabaseIndex.class, selectSql, new HashMap<String, Object>(){{
				put("table_schema", databaseInfo.getDatabaseSchema());
				put("table_name", tableName);
			}});
			session.commit();
		} catch (Exception e) {
			session.rollback();
		} finally {
			session.release();
		}
		return databaseIndexes;
	}
	
	@SuppressWarnings("serial")
	public List<DatabaseIndexColumn> findColumnIndexes(DatabaseInfo databaseInfo, String tableName, String indexName) {
		Session session = DynamicSessionFactory.getSession(databaseInfo);
		SqlManager sqlManager = session.getSqlManager();
		session.begin();
		List<DatabaseIndexColumn> databaseIndexColumns = new ArrayList<>();
		try {
			SqlResource selectSql = null;
			switch(databaseInfo.getEnumDatabaseType()) {
			case MySQL:
				selectSql = new ClasspathSqlResource("sql/database_table/findIndexColumns.mysql.sql");
				break;
			case H2_MEM:
			case H2_FILE:
			case H2_TCP:
				selectSql = new ClasspathSqlResource("sql/database_table/findIndexColumns.h2.sql");
				break;
			default:
				break;
			}
			databaseIndexColumns = sqlManager.getResultList(DatabaseIndexColumn.class, selectSql, new HashMap<String, Object>(){{
				put("table_schema", databaseInfo.getDatabaseSchema());
				put("table_name", tableName);
				put("index_name", indexName);
			}});
			session.commit();
		} catch (Exception e) {
			session.rollback();
		} finally {
			session.release();
		}
		return databaseIndexColumns;
	}
}
