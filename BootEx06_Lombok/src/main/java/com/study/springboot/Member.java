package com.study.springboot;

import lombok.Data;

@Data
public class Member {
	private String id;
	private String name;
	
	public Member() {}
	
	public Member(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
}