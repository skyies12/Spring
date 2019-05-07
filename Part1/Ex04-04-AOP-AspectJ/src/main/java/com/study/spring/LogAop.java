package com.study.spring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogAop {
	// Execution
//	@Pointcut("execution(public void get*(..)");
//  public void인 모든 get메소드
	
//	@Pointcut("execution(* com.study.spring.*.*())");
//  com.study.spring 패키지에 파라미터가 없는 모든 메소드
	
//	@Pointcut("execution(* com.study.spring..*.*())");
//  com.study.spring 패키지 & com.study.spring 하위 패키지에
//  파라미터가 없는 모든 메소드
	
//	@Pointcut("execution(* com.study.spring.Worker.*())");	
//  com.study.spring.Worker 안의 모든 메소드

	
	// within
//  @Pointcut("within(com.study.spring.*)")	
//  com.study.spring 패키지 안에 있는 모든 메소드	
	
//  @Pointcut("within(com.study.spring..*)")
//  com.study.spring 패키지 & com.study.spring 하위 패키지에
//  파라미터가 없는 모든 메소드
	
//  @Pointcut("within(com.study.spring.Worker)")	
//  com.study.spring.Worker 안의 모든 메소드
	
	
	// bean
//  @Pointcut("bean(student)")
//  student bean에만 적용
	
	@Pointcut("within(com.study.spring.*)")
	private void pointcutMethod() {
		
	}
	
	@Around("pointcutMethod()")
	public Object loggerAop(ProceedingJoinPoint joinpoint) throws Throwable {
		String signatureStr = joinpoint.getSignature().toShortString();
		System.out.println(signatureStr + " is start.");
		
		long st = System.currentTimeMillis();
		
		try {
			Object obj = joinpoint.proceed();
			return obj;
		} finally {
			long et = System.currentTimeMillis();
			
			System.out.println(signatureStr + " is finished.");
			System.out.println(signatureStr + " 경과시간 : " + (et - st));
		}
	}
	
	@Before("within(com.study.spring.*)")
	public void beforeAdvice() {
		System.out.println("beforeAdvice()");
	}
}
