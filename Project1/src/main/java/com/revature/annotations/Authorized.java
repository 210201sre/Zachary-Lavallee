package com.revature.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import com.revature.models.Role;

@Retention(RUNTIME)
@Target(METHOD)
public @interface Authorized {
	public Role[] allowedRoles() default {};
}