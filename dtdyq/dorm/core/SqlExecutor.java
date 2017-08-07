package dtdyq.dorm.core;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import dtdyq.dorm.model.MethodDescriptor;

/**
 * Statementִ�����������ṩ�Ĳ������ݿ�ӿ�
 * @author dtdyq
 *
 */
public interface SqlExecutor {
	
	<T> T execute(SqlResolver resolver, MethodDescriptor descriptor) throws SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException;
}
