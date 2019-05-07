package com.study.spring;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		// 생성
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		
		// 설정
		context.load("classpath:beans.xml");
		
		context.refresh();
		
		// 사용
		Student student = context.getBean("student", Student.class);
		System.out.println("이름 : " + student.getName());
		System.out.println("나이 : " + student.getAge());
		
		// 종료
		context.close();
	}
}