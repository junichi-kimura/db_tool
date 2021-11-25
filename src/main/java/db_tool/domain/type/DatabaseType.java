package db_tool.domain.type;

import java.util.Properties;

import db_tool.infrastructure.database.DatabaseInfo;
import lombok.Getter;

public enum DatabaseType implements DefaultType<Integer>{
	MySQL(1, "MySQL", "3306", "com.mysql.cj.jdbc.Driver") {
		@Override
		public Properties getProperties(DatabaseInfo databaseInfo) {
			Properties prop = new Properties();
			prop.setProperty("jdbc.driver", this.getJdbcDriver());
			if (!this.getDefaultPort().equals(databaseInfo.getDatabasePort())) {
				prop.setProperty("jdbc.url", "jdbc:mysql://" + databaseInfo.getDatabaseHostName() + ":" + databaseInfo.getDatabasePort() + "/" + databaseInfo.getDatabaseName());
			} else {
				prop.setProperty("jdbc.url", "jdbc:mysql://" + databaseInfo.getDatabaseHostName() + "/" + databaseInfo.getDatabaseName());
			}
			prop.setProperty("jdbc.user", databaseInfo.getDatabaseUser());
			prop.setProperty("jdbc.password", databaseInfo.getDatabasePass());
			return prop;
		}
	},
	H2_MEM(2, "H2(メモリー)", "8082", "org.h2.Driver") {
		@Override
		public Properties getProperties(DatabaseInfo databaseInfo) {
			Properties prop = new Properties();
			prop.setProperty("jdbc.driver", this.getJdbcDriver());
			prop.setProperty("jdbc.url", "jdbc:h2:mem:" + databaseInfo.getDatabaseHostName());
			prop.setProperty("jdbc.user", databaseInfo.getDatabaseUser());
			prop.setProperty("jdbc.password", databaseInfo.getDatabasePass());
			return prop;
		}
	},
	H2_FILE(3, "H2(ファイル)", "8082", "org.h2.Driver") {
		@Override
		public Properties getProperties(DatabaseInfo databaseInfo) {
			Properties prop = new Properties();
			prop.setProperty("jdbc.driver", this.getJdbcDriver());
			prop.setProperty("jdbc.url", "jdbc:h2:file:" + databaseInfo.getDatabaseHostName());
			prop.setProperty("jdbc.user", databaseInfo.getDatabaseUser());
			prop.setProperty("jdbc.password", databaseInfo.getDatabasePass());
			return prop;
		}
	},
	H2_TCP(4, "H2(TCP)", "8082", "org.h2.Driver") {
		@Override
		public Properties getProperties(DatabaseInfo databaseInfo) {
			Properties prop = new Properties();
			prop.setProperty("jdbc.driver", this.getJdbcDriver());
			prop.setProperty("jdbc.url", "jdbc:h2:tcp://" + databaseInfo.getDatabaseHostName());
			prop.setProperty("jdbc.user", databaseInfo.getDatabaseUser());
			prop.setProperty("jdbc.password", databaseInfo.getDatabasePass());
			return prop;
		}
	},;
	
	@Getter
	private final Integer id;
	
	@Getter
	private final String name;
	
	@Getter
	private String defaultPort;
	
	@Getter
	private String jdbcDriver;
	
	public abstract Properties getProperties(DatabaseInfo databaseInfo);
	
	DatabaseType(Integer id, String name, String defaultPort, String jdbcDriver) {
		this.id = id;
		this.name = name;
		this.defaultPort = defaultPort;
		this.jdbcDriver = jdbcDriver;
	}
	
	public static DatabaseType of(Integer id) {
		for(DatabaseType databaseType : DatabaseType.values()) {
			if (databaseType.getId().equals(id)) {
				return databaseType;
			}
		}
		return null;
	}
	
	@Override
	public Integer getValue() {
		return this.id;
	}
}
