package pl.mczapiewski.translator.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Adnotacja umozliwiajaca okreslenia rodzaju pola obiektu DTO
 * 
 * @author mczapiewski
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface Dto {
	public enum DtoType {
		FIELD,  //pole - które jest obiektem 
		LIST,   //lista - 
		SET,    //set
		TRANSIENT //pole - które jest obiektem lub listą ale ma być ignorowane w procesie mapowania danych 
	};

	DtoType type() default DtoType.FIELD;
}
