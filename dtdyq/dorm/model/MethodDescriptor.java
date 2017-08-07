package dtdyq.dorm.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import dtdyq.dorm.enums.DataType;

/**
 * 
 * @author dtdyq
 * 
 * get the method's result type、parameter etc.
 *
 */
public class MethodDescriptor {
	private Method method;
	private String name;
	private Object arg;//参数对象
	private DataType argType;
	private DataType resultType;
	private Class<?> result;
	public MethodDescriptor(Method method,Object[] args) throws InstantiationException, IllegalAccessException {
		this.method = method;
		this.name = method.getName();
		if(args.length == 0){
			this.arg = null;
			this.argType = DataType.NULL;
		}
		arg = args[0];
		this.argType = getDataType(arg.getClass());
		this.result = method.getReturnType();
		this.resultType = getDataType(result);

	}
	public Method getMethod(){
		return this.method;
	}
	public static DataType getDataType(Class<?> clazz) throws InstantiationException, IllegalAccessException{
		String tmp = clazz.getTypeName();
		DataType res = null;
		switch(tmp.trim()){
		case "java.util.HashMap":
		case "java.util.Map":
		case "java.util.TreeMap":res = DataType.MAP;break;
		case "java.util.list":
		case "java.util.ArrayList":
		case "java.util.LinkedList":res = DataType.LIST;break;
		case "java.lang.Integer":
		case "byte": case "short":
		case "java.lang.Short":
		case "int":
		case "java.lang.Byte":res = DataType.INTEGER;break;
		case "java.lang.Float":
		case "java.lang.Double":
		 case "float": case "double":res = DataType.DOUBLE;break;
		case "void":res = DataType.NULL;break;
		case "java.sql.ResultSet":res = DataType.RESULTSET;break;
		default:res = DataType.POJO;
		}
		return res;
	}
	public String getName(){
		return this.name;
	}
	public Object getArgObject(){
		return this.arg;
	}
	public DataType getArgType(){
		return this.argType;
	}
	public Class<?> getResultClass(){
		return this.result;
	}
	public DataType getResultType(){
		return this.resultType;
	}
}








