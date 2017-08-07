package dtdyq.dorm.tool;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 
 * @author dtdyq
 * 
 * 描述annotation信息的类
 *
 */
public class AnnotationDetail {
	
	//原始的Annotation对象
	private Object obj;
	//annotation全限定名称
	private String name;
	//annotation中的键值对
	private Map<String,String> data = new HashMap<>();
	
	public AnnotationDetail() {
	}

	public AnnotationDetail(Object obj, String name, Map<String, String> data) {
		this.obj = obj;
		this.name = name;
		this.data = data;
	}
	/**
	 * 根据传入的Annotation进行解析
	 * @param annotation
	 */
	public AnnotationDetail(Annotation annotation){
		this.obj = annotation;
		String tmp = annotation.toString();
		this.name = tmp.substring(1,tmp.indexOf("("));
		
		for (Method method : annotation.annotationType().getDeclaredMethods()) {  
            if (!method.isAccessible()) {  
                method.setAccessible(true);  
            }  
            Object invoke = null;
			try {
				invoke = method.invoke(annotation);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}  
            data.put(method.getName(), invoke.toString());
            if (invoke.getClass().isArray()) {  
                Object[] obj = (Object[]) invoke;  
                data.put(method.getName(), Arrays.toString(obj));  
            }  
        } 
	}
	public Object getObj() {
		return obj;
	}

	public AnnotationDetail setObj(Object obj) {
		this.obj = obj;
		return this;
	}

	public String getName() {
		return name;
	}

	public AnnotationDetail setName(String name) {
		this.name = name;
		return this;
	}

	public Map<String, String> getData() {
		return data;
	}

	public AnnotationDetail setData(Map<String, String> data) {
		this.data = data;
		return this;
	}
	//根据给定的属性名称获取对应的值
	public String getValue(String key){
		return data.get(key);
	}

}
/**
 * tool：confirm the value of name according to giving numbers(beg,end:
 * the value's begin index and end index in string)
 *
 */
class IndexTool implements Comparable<IndexTool>{
	String name;
	int beg;
	int end;
	public IndexTool(String name, int beg, int end) {
		this.name = name;
		this.beg = beg;
		this.end = end;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBeg() {
		return beg;
	}
	public void setBeg(int beg) {
		this.beg = beg;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	@Override
	public int compareTo(IndexTool o) {
		
		return this.beg - o.beg;
	}
	
}









