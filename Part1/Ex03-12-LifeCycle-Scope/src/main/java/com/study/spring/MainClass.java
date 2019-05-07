package com.study.spring;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		// 생성
		 AbstractApplicationContext context = new GenericXmlApplicationContext("classpath:beans.xml");
		
		// 사용
		Student student1 = context.getBean("student", Student.class);
		System.out.println("이름 : " + student1.getName());
		System.out.println("나이 : " + student1.getAge());
		
		System.out.println("========================================");
		
		Student student2 = context.getBean("student", Student.class);
		student2.setName("관우");
		student2.setAge(20);
		
		System.out.println("이름 : " + student1.getName());
		System.out.println("나이 : " + student1.getAge());
		
		System.out.println("========================================");
		
		// 객체가 저장하는 value
		if(student1.equals(student2)) {
			System.out.println("student1 == student2");
		} else {
			System.out.println("student1 != student2");
		}
		
		// 객체 자체 비고
		System.out.println(student1 == student2);
		
		// 종료
		context.close();
		
//		별도의 scope를 지정하지 않으면 스프링에서 default는 singleton 이다.
//		singleton : 기본 싱글톤 스코프
		
//		prototype : 어플리케이션에서 요청시 (getBean()) 마다 스프링이 새 인스턴스를 생성
//
//		request : HTTP 요청별로 인스턴스화 되며 요청이 끝나면 소멸 
//		(spring mvc webapplication 용도)
		
//		session : HTTP 세션별로 인스턴스화되며 세션이 끝나며 소멸 
//		(spring mvc webapplication 용도)
		
//		global session : 포틀릿 기반의 웹 어플리케이션 용도. 전역 세션 스코프의 빈은 
//		같은 스프링 MVC를 사용한 포탈 어플리케이션 내의 모든 포틀릿 사이에서 공유할 수 있다
		
//		thread : 새 스레드에서 요청하면 새로운 bean 인스턴스를 생성, 
//		같은 스레드에 대해서는 항상 같은 bean 인스턴스가 반환
		
//		custom : org.pringframework.beans.factory.config.Scope를 구현하고 
//		커스텀 스코프를 스프링의 설정에 등록하여 사용

	}
}