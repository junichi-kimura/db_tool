package db_tool.application.repository;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.miragesql.miragesql.SqlManager;
import com.miragesql.miragesql.test.MockSqlManager;

import db_tool.AbstractTest;
import db_tool.application.repository.BaseRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public abstract class BaseRepositoryTest<T, R extends BaseRepository<T>> extends AbstractTest {
	
	protected SqlManager sqlManager = new MockSqlManager();
	
	@MockBean
	protected T result;
	
	@MockBean
	protected List<T> results;
	
	@SpyBean
	public R repository;
	
	@BeforeEach
	public void setUp() {
		Mockito.when(repository.getSqlManager()).thenReturn(sqlManager);
		Mockito.when(repository.getTClass()).thenReturn(getClassType());
	}
	
	protected abstract Class<T> getClassType();
}
