package db_tool.infrastructure.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import com.miragesql.miragesql.SqlManager;
import com.miragesql.miragesql.SqlManagerImpl;
import com.miragesql.miragesql.dialect.H2Dialect;
import com.miragesql.miragesql.integration.spring.SpringConnectionProvider;
import com.miragesql.miragesql.provider.ConnectionProvider;

@Configuration
public class MirageSqlConfig {
	
	@Bean
	public ConnectionProvider getConnectionProvider(DataSource dataSource) {
		SpringConnectionProvider connectionProveder = new SpringConnectionProvider();
		connectionProveder.setDataSource(new TransactionAwareDataSourceProxy(dataSource));
		return connectionProveder;
	}
	
	@Bean
	public SqlManager getSqlManager(ConnectionProvider connectionProvider) {
		SqlManagerImpl sqlManager = new SqlManagerImpl();
		sqlManager.setConnectionProvider(connectionProvider);
		sqlManager.setDialect(new H2Dialect());
		return sqlManager;
	}
}
