package db_tool.infrastructure.database;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import com.miragesql.miragesql.exception.ConfigurationException;
import com.miragesql.miragesql.session.JDBCSessionImpl;
import com.miragesql.miragesql.session.Session;
import com.miragesql.miragesql.util.StringUtil;

public class DynamicSessionFactory {

    /**
     * Returns a session configured with the properties specified by the properties parameter.
     * If no <code>session.class</code> key is present in the properties, the session will use the {@link JDBCSessionImpl}.
     *
     * @param properties properties with settings to create a {@link Session} with.
     *
     * @return {@link Session}
     */
    public synchronized static Session getSession(DatabaseInfo databaseInfo) {
    	Session session = null;
    	Properties properties = databaseInfo.getEnumDatabaseType().getProperties(databaseInfo);
        if(properties == null){
            throw new ConfigurationException("jdbc.properties is not found!");
        }

        try {
            String sessionClass = properties.getProperty("session.class");
            if(StringUtil.isEmpty(sessionClass)){
                sessionClass = JDBCSessionImpl.class.getName();
            }

            Class<?> clazz = Class.forName(sessionClass);
            Constructor<?> constructor = clazz.getConstructor(Properties.class);
            session = (Session) constructor.newInstance(properties);
        } catch (ClassNotFoundException e) {
            throw new ConfigurationException("Driver class not found.", e);

        } catch (NoSuchMethodException e) {
            throw new ConfigurationException(
                    "sessionClass does not have constructor with argument type of Properties.", e);

        } catch (ClassCastException e) {
            throw new ConfigurationException("sessionClass does not implements Session interface ", e);
            
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException| InvocationTargetException e) {
        	throw new ConfigurationException(e);
		}

        return session;
    }
}
