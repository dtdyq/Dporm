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
 * @author dtdyq ��ȡ����annotation��Ϣ�Ĺ�����
 *
 */
public class AnnotationUtil {

	private AnnotationUtil() {
	};

	/**
	 * ����ĳ���������з�����ע��
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
	 * ����ĳ��ָ��������ע��
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
	 * ��ȡָ�������������Ե�ע��
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
	 * ��ȡĳ��ָ�������ϵ�ע��
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
	 * ��ȡָ�����ϵ�ע��
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
	 * �ж������Ƿ���ע��
	 * 
	 * @return boolean
	 */
	public static boolean hasClassAnnotation(Class<?> clazz) {
		return clazz.getAnnotations().length != 0;

	}
}
