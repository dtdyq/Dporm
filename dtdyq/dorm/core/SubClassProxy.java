package dtdyq.dorm.core;

import java.lang.reflect.Method;
import java.util.Map;

import dtdyq.dorm.annotation.Select;
import dtdyq.dorm.model.MethodDescriptor;
import dtdyq.dorm.model.SqlConfig;
import dtdyq.dorm.tool.AnnotationDetail;
import dtdyq.dorm.tool.AnnotationUtil;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
/**
 * 
 * @author dtdyq
 * generate the subclass from given interface
 * implement the interface's method according to the method's annotation
 *
 */
public class SubClassProxy implements MethodInterceptor {
	private SqlConfig sqlConfig;
	public SubClassProxy(SqlConfig sqlConfig) {
		this.sqlConfig = sqlConfig;
	}
	@Override
	public Object intercept(Object target, Method method, Object[] methodArgs, MethodProxy proxy) throws Throwable {
		//get sql string from the method's annotation
		Map<String,AnnotationDetail> annots = AnnotationUtil.getMethodAnnotation(method);
		AnnotationDetail detail= annots.get(Select.class.getName());
		String sql = detail.getValue("value");
		SqlResolver resolver = new SqlResolver().reslove(sql);
		MethodDescriptor descriptor = new MethodDescriptor(method, methodArgs);
		SqlExecutor executor = new DefaultSqlExecutor(sqlConfig);
		Object res = executor.execute(resolver,descriptor);
		return res;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getProxy(Class<T> clazz) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(this);
		return (T) enhancer.create();
	}
}
