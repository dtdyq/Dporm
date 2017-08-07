package dtdyq.dorm.core;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dtdyq.dorm.enums.DataType;
import dtdyq.dorm.enums.SqlType;
import dtdyq.dorm.model.ClassTableMapper;
import dtdyq.dorm.model.MethodDescriptor;
import dtdyq.dorm.model.SqlConfig;
import dtdyq.dorm.tool.StringUtil;
import dtdyq.dpool.core.DPoolSource;

public class DefaultSqlExecutor implements SqlExecutor {
	private Connection connection;
	private SqlConfig sqlConfig;
	public DefaultSqlExecutor(SqlConfig sqlConfig){
		this.sqlConfig = sqlConfig;
		try {
			Class.forName(sqlConfig.getDriverName());
			this.connection = DriverManager.getConnection(
					sqlConfig.getUrl(),
					sqlConfig.getUser(),
					sqlConfig.getPassword());
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public DefaultSqlExecutor(DPoolSource dPoolSource) throws SQLException {
		this.connection = dPoolSource.getConnection();
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T> T execute(SqlResolver resolver, MethodDescriptor descriptor) throws SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		String sql = resolver.getSql();
		PreparedStatement statement = connection.prepareStatement(sql);
		List<String> names = resolver.getNames();
		Object object = descriptor.getArgObject();
		DataType dataType =  descriptor.getArgType();
		switch(dataType){
			case DOUBLE:statement.setDouble(1, Double.parseDouble(object.toString()));break;
			case INTEGER:statement.setInt(1, Integer.parseInt(object.toString()));break;
			case LIST:statement.setArray(1, (Array)object);break;//可能有问题
			case NULL:break;
			case MAP:{
				int tmp = 1;
				for(String name : names){
					statement.setString(tmp++, ((Map)object).get(name).toString());
				}
			}
			case POJO:{
				int tmp = 1;
				for(String name : names){
					Class<?> clazz = object.getClass();
					try {
						Method method = clazz.getDeclaredMethod("get"+StringUtil.initial2Upcase(name), null);
						Object string = method.invoke(object, null);
						statement.setString(tmp++,string.toString());
					} catch (NoSuchMethodException | SecurityException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
					
				}
			}
			case STRING:
			default:statement.setString(1, object.toString());break;
		}
		SqlType sqlType = resolver.getSqlType(); 
		ResultSet set = null;
		switch(sqlType){
			case SELECT:set  = statement.executeQuery();
			case UPDATE:
			case DELETE:
			case INSERT:statement.execute();break;
		}
		
		DataType resultType = descriptor.getResultType();
		switch(resultType){
		case DOUBLE:
		case STRING:
		case INTEGER:{
			Integer res = null;
			if(set.next()){
				res = set.getInt(1);
			}
			return (T)res;
		}
		case MAP:{
			  Map<String,String> map = new HashMap<>();
			  if(set.next()){
				  ResultSetMetaData data = set.getMetaData();
				  int cnt = data.getColumnCount();
				  for(int i = 1; i <= cnt;i++){
					  map.put(data.getColumnName(i), set.getString(i));
				  }
			  }
			  return (T)map;
		}
		case NULL:{
		
			return (T)null;
		}
		case  LIST:{
			Class<?> clz = null;
			Method method = descriptor.getMethod();
			Type type2 = method.getGenericReturnType();
			if(type2 instanceof ParameterizedType){
				ParameterizedType paramType = (ParameterizedType) type2;
				Type[] actualTypes = paramType.getActualTypeArguments();     
				   for (Type aType : actualTypes) {         
				       if (aType instanceof Class) {         
				           clz = (Class) aType;             
				       }     
				   }
			}
			List<Object> res = new ArrayList<>();
			
			
			while(set.next()){
				Object obj = clz.newInstance();
				ResultSetMetaData data = set.getMetaData();
				int cnt = data.getColumnCount();
				for(int i = 1;i<=cnt;i++){
					 ClassTableMapper mapper = new ClassTableMapper(clz);
					 String cname = data.getColumnName(i);
					 Field field = mapper.getFieldByColumnName(cname);
					 String temp = set.getString(i);
					 Method methd = clz.getMethod("set"+StringUtil.initial2Upcase(field.getName()), field.getType());
					 methd.invoke(obj,method.getParameterTypes()[0].cast(temp));//可能有问题
				}
				res.add(obj);
			}
			return (T)res;
		}
		case POJO:{
			Object res = descriptor.getResultClass().newInstance();
			Class<?> clazz = descriptor.getResultClass();
			if(set.next()){
				ResultSetMetaData data = set.getMetaData();
				int cnt = data.getColumnCount();
				for(int i = 1;i<=cnt;i++){
					 ClassTableMapper mapper = new ClassTableMapper(clazz);
					 String cname = data.getColumnName(i);
					 Field field = mapper.getFieldByColumnName(cname);
					 
					 Method method = clazz.getMethod("set"+StringUtil.initial2Upcase(field.getName()), field.getType());
					 method.invoke(res,(Integer)4323);//可能有问题
				}
			}
			return (T)res; 
		}
		case RESULTSET:
		default:
			return (T)set; 
		}
	}

}
