package com.cd.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.cd.persistence.service.AccountDto;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

	@Override
	public void initialize(PasswordMatches arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {
		AccountDto user = (AccountDto) obj;
		return user.getPassword().equals(user.getConfirmPassword());
	}

}
