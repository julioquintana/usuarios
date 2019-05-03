package com.qs.telotengo.user.dto.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {
	
	@Override
    public void initialize(Phone contactNumber) {
    }
 
    @Override
    public boolean isValid(String contactField,
      ConstraintValidatorContext cxt) {
    	System.out.println("Entro a la validacion");
        return !contactField.isEmpty() && contactField.matches("[0-9]+")
          && (contactField.length() > 8) && (contactField.length() < 14);
    }
}
