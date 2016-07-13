/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.validation;

/**
 *
 * @author Tonytran
 */
import vn.webapp.validation.FieldMatchValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

/**
 *
 * @author Tonytran
 */
@Documented
@Constraint(validatedBy = FieldMatchValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldMatch {

    String message() default "{constraintdescriptor.fieldmatch}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * @return The first field
     */
    String first();

    /**
     * @return The second field
     */
    String second();

    /**
     * @return the Error Message
     */
    String errorMessage();

    /**
     * Defines several
     * <code>@FieldMatch</code> annotations on the same element
     *
     * @see FieldMatch
     */
    @Target({TYPE, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    public @interface List {

        FieldMatch[] value();
    }
}
