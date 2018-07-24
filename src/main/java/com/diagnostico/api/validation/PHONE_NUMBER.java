package com.diagnostico.api.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
//@Pattern(regexp = "^\\([1-9]{2}\\) (?:[2-8]|9[1-9])[0-9]{3}\\-[0-9]{4}$")	FORMATADO
@Pattern(regexp = "^[1-9]{2}(?:[2-8]|9[1-9])[0-9]{2,3}[0-9]{4}$") //SEM FORMATAÇÃO
public @interface PHONE_NUMBER {

	@OverridesAttribute(constraint = Pattern.class, name = "message")
	String message() default "Telefone inválido";
	
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

}
