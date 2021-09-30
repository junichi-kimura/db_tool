package db_tool.application.repository;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.miragesql.miragesql.SqlManager;
import com.miragesql.miragesql.StringSqlResource;
import com.miragesql.miragesql.annotation.Table;

@Transactional(readOnly = true)
public abstract class BaseRepository<T> {
	
	public abstract SqlManager getSqlManager();
	
	@SuppressWarnings({"serial" })
	public T findById(Long id) {
		Table tableClass = (Table) this.getTClass().getAnnotation(Table.class);
		T t = this.getSqlManager().getSingleResult(this.getTClass(), new StringSqlResource("SELECT * FROM " + tableClass.name() + " WHERE id = /*id*/0"),  new HashMap<String, Object>(){{
			put("id", id);
		}});
		return t;
	}
	
	public List<T> findAll() {
		Table tableClass = (Table) this.getTClass().getAnnotation(Table.class);
		List<T> list = this.getSqlManager().getResultList(this.getTClass(), new StringSqlResource("SELECT * FROM " + tableClass.name() + " ORDER BY id"));
		return list;
	}
	
	@SuppressWarnings("unchecked")
    public Class<T> getTClass() {
		Type type = getClass().getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] argTypes = ((ParameterizedType) type).getActualTypeArguments();
			return (Class<T>)argTypes[0];
		} else {
			return null;
			//throw new IllegalStateException();
		}
	}
	
	@Transactional
	public void update(T t) {
		try {
			Method method = t.getClass().getDeclaredMethod("setUpdatedAt", Timestamp.class);
			if (method != null) {
				method.setAccessible(true);
				method.invoke(t, new Timestamp(System.currentTimeMillis()));
			}
			this.getSqlManager().updateEntity(t);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Transactional
	public void insert(T t) {
		try {
			Method method = t.getClass().getDeclaredMethod("setCreatedAt", Timestamp.class);
			if (method != null) {
				method.setAccessible(true);
				method.invoke(t, new Timestamp(System.currentTimeMillis()));
			}
			this.getSqlManager().insertEntity(t);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Transactional
	public void delete(T t) {
		this.getSqlManager().deleteEntity(t);
	}
	
	@Transactional
	public void deleteAll(List<T> t) {
		this.getSqlManager().deleteBatch(t);
	}
}
