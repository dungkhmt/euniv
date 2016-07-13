/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.validation;

/**
 *
 * @author Tonytran
 */
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.beanutils.BeanUtils;

public final class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object>
{
    private String firstField;
    private String secondField;
    private String errorMessagename;

    @Override
    public void initialize(final FieldMatch constraintAnnotation) {
    	firstField = constraintAnnotation.first();
    	secondField = constraintAnnotation.second();
        errorMessagename = constraintAnnotation.errorMessage();
        //System.out.println("firstFieldName = "+firstFieldName+"   secondFieldName = "+secondFieldName);
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext cvc){
    boolean toReturn = false;

    try{
        final Object firstObj = BeanUtils.getProperty(value, firstField );
        final Object secondObj = BeanUtils.getProperty(value, secondField );

        //System.out.println("firstObj = "+firstObj+"   secondObj = "+secondObj);

        toReturn = firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
    }
    catch (final Exception e){
        System.out.println(e.toString());
    }
    //If the validation failed
    if(!toReturn) {
        cvc.disableDefaultConstraintViolation();
        //In the initialiaze method you get the errorMessage: constraintAnnotation.message();
        cvc.buildConstraintViolationWithTemplate(errorMessagename).addNode(firstField).addConstraintViolation();
    }
    return toReturn;
}
}
