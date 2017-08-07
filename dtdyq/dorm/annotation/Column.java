package dtdyq.dorm.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
/**
 * 
 * @author dtdyq
 * pojo���Զ�Ӧ�ı����ֶ�
 *
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface Column {
	String name();//���ֶ�����
	boolean key() default false;//�Ƿ�������
	boolean auto() default false;//�Ƿ�����
	String value() default "";//�������ʱ��Ĭ��ֵ
}





