package de.ts.shoppinglist.view.dynamicgui;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface View {

	boolean editable() default true;
	
	ContentType contentType() default ContentType.String;

	String labelName() default "";
	
	int length() default 10;
	
}
