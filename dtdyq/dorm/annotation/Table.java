package dtdyq.dorm.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * pojoӳ��ı���
 * @author dtdyq
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
	String value();
}
