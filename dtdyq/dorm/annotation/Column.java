package dtdyq.dorm.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
/**
 * 
 * @author dtdyq
 * pojo属性对应的表中字段
 *
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface Column {
	String name();//表字段名称
	boolean key() default false;//是否是主键
	boolean auto() default false;//是否自增
	String value() default "";//插入操作时的默认值
}





