package dtdyq.dorm.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.lang.model.element.Element;

@Documented
@Retention(RUNTIME)
@Target(ElementType.METHOD)
public @interface Select {
	String value();
}
