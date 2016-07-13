/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.validation;

/**
 *
 * @author Tonytran
 */
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author Tonytran
 */
@Documented
@Constraint(validatedBy = YearConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Year {

    int value();

    String message() default "{Year}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
