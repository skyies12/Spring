package com.study.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Hello {
	@Value("루피")
	private String name;
	@Value("고무고무")
	private String nickname;
	@Autowired // 자동 주입
	@Qualifier("printerA") // 자동 주입 가능한 bean이 두개일 때 지정하기
	private Printer printer;

	public Hello() {
		super();
	}

	public Hello(String name, String nickname, Printer printer) {
		super();
		this.name = name;
		this.nickname = nickname;
		this.printer = printer;
	}

	public void setPrinter(Printer printer) {
		this.printer = printer;
	}
	
	public String sayHello() {
		return "Hello " + name + " : " + nickname;
	}
	
	public void print() {
		printer.print(sayHello());
	}
}