package com.study.springboot;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ContentValidator implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		return ContentDto.class.isAssignableFrom(arg0);
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		ContentDto dto = (ContentDto)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "writer", "Writer is empty.");
		String sWriter = dto.getWriter();
		if(sWriter.length() < 3) {
			errors.rejectValue("writer", "Writer is too short.");
		}
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "Content is empty.");
//		String sContent = dto.getContent();
//		if(sContent == null || sContent.trim().isEmpty()) {
//			System.out.println("Content is null or empty");
//			errors.rejectValue("content", "trouble");
//		}
		
	}

}
