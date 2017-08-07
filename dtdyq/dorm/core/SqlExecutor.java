package dtdyq.dorm.core;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import dtdyq.dorm.model.MethodDescriptor;

/**
 * Statement执行器，对外提供的操作数据库接口
 * @author dtdyq
 *
 */
public interface SqlExecutor {
	
	<T> T execute(SqlResolver resolver, MethodDescriptor descriptor) throws SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException;
}
