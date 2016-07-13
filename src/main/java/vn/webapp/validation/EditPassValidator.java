package vn.webapp.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EditPassValidator implements ConstraintValidator<EditPass, String> {

	
	private int min;
    private int max;
    
    @Override
    public void initialize(EditPass paramA) {
    	min = paramA.min();
        max = paramA.max();
    }

    @Override
    public boolean isValid(String passWord, ConstraintValidatorContext ctx) {
        if (passWord == null || passWord.isEmpty() ) {
            return true;
        }
        
        int length = passWord.length();
        return length >= min && length <= max;
    }
}
