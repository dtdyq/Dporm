package dtdyq.dorm.tool;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 
 * @author dtdyq 获取类中annotation信息的工具类
 *
 */
public class AnnotationUtil {

	private AnnotationUtil() {
	};

	/**
	 * 解析某个类中所有方法的注解
	 * 
	 * @param clazz
	 * @return Map<String, Map<String,AnnotationDetail>>
	 */
	public static Map<String, Map<String,AnnotationDetail>> getMethodsAnnotation(Class<?> clazz) {
		Method[] methods = clazz.getDeclaredMethods();
		Map<String, Map<String,AnnotationDetail>> methodsAnnotation = new HashMap<>();
		for (Method method : methods) {
			methodsAnnotation.put(method.getName(), getMethodAnnotation(method));
		}
		return methodsAnnotation;
	}

	/**
	 * 解析某个指定方法的注解
	 * 
	 * @param method
	 * @return Map<String,AnnotationDetail>
	 */
	public static Map<String,AnnotationDetail> getMethodAnnotation(Method method) {
		Annotation[] annots = method.getAnnotations();
		if (annots.length == 0) {
			return null;
		}
		Map<String,AnnotationDetail> res = new HashMap<>();
		for (Annotation a : annots) {
			res.put(a.annotationType().getName().toString(),new AnnotationDetail(a));
		}
		return res;
	}

	/**
	 * 获取指定类上所有属性的注解
	 * 
	 * @param clazz
	 * @return Map<String, Map<String,AnnotationDetail>>
	 */
	public static Map<String, Map<String,AnnotationDetail>> getFieldsAnnotation(Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		Map<String, Map<String,AnnotationDetail>> fieldsAnnotation = new HashMap<>();
		for (Field field : fields) {
			fieldsAnnotation.put(field.getName(), getFieldAnnotation(field));
		}
		return fieldsAnnotation;
	}

	/**
	 * 获取某个指定属性上的注解
	 * 
	 * @param field
	 * @return Optional<List<AnnotationDetail>>
	 */
	public static Map<String,AnnotationDetail> getFieldAnnotation(Field field) {
		Annotation[] annots = field.getAnnotations();
		if (annots.length == 0) {
			return null;
		}
		Map<String,AnnotationDetail> res = new HashMap<>();
		for (Annotation a : annots) {
			res.put(a.annotationType().getName().toString(),new AnnotationDetail(a));
		}
		return res;
	}

	/**
	 * 获取指定类上的注解
	 * 
	 * @param clazz
	 */
	public static Map<String,AnnotationDetail> getClassAnnotation(Class<?> clazz) {
		Annotation[] annot = clazz.getAnnotations();
		if (annot.length == 0) {
			return null;
		}

		Map<String,AnnotationDetail> classAnnotations = new HashMap<>();
		for (Annotation a : annot) {
			classAnnotations.put(a.annotationType().getName().toString(),new AnnotationDetail(a));
		}
		return classAnnotations;
	}

	/**
	 * 判断类上是否有注解
	 * 
	 * @return boolean
	 */
	public static boolean hasClassAnnotation(Class<?> clazz) {
		return clazz.getAnnotations().length != 0;

	}
}
