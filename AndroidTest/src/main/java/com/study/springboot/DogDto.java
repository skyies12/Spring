package com.study.springboot;

import lombok.Data;

@Data
public class DogDto {
	private String kindCd;
	private String colorCd;
	private String age;
	private String weight;
	private String noticeNo;
	
	public DogDto() {
		
	}
	
	public DogDto(String kindCd, String colorCd, String age, String weight, String noticeNo) {
		super();
		this.kindCd = kindCd;
		this.colorCd = colorCd;
		this.age = age;
		this.weight = weight;
		this.noticeNo = noticeNo;
	}
	
	
}
