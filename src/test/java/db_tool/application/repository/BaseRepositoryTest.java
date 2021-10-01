package db_tool.application.repository;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.core.GenericTypeResolver;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.miragesql.miragesql.SqlManager;
import com.miragesql.miragesql.test.MockSqlManager;

import db_tool.AbstractTest;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BaseRepositoryTest<T, R extends BaseRepository<T>> extends AbstractTest {
	
	protected SqlManager sqlManager = new MockSqlManager();
	
	@MockBean
	protected T result;
	
	@MockBean
	protected List<T> results;
	
	@SpyBean
	public R repository;
	
	@SuppressWarnings("unchecked")
	@BeforeEach
	public void setUp() {
		Mockito.when(repository.getSqlManager()).thenReturn(sqlManager);
		Class<?>[] classTypes = GenericTypeResolver.resolveTypeArguments(getClass(), BaseRepositoryTest.class);
		Mockito.when(repository.getTClass()).thenReturn((Class<T>) classTypes[0]);
	}
}
