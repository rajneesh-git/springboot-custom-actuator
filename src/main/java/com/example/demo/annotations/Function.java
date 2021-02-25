package com.example.demo.annotations;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

@Documented
@Retention(RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface Function {
	
 
	String id() default "Unknown";	
	

	String about() default "";	

}
