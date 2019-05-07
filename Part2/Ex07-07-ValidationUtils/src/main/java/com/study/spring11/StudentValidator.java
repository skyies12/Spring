package com.study.spring11;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class StudentValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Student.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Student student = (Student)target;
		
//		String studentName = student.getName();
//		if(studentName == null || studentName.trim().isEmpty()) {
//			errors.rejectValue("name", "trouble");
//		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "trouble");
		
		int studentId = student.getId();
		if(studentId == 0) {
			errors.rejectValue("id", "trouble");
		}
		
	}

}
