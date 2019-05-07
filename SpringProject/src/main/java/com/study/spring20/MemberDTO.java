package com.study.spring20;

import java.sql.Date;
import java.sql.Timestamp;

public class MemberDTO {
	private String id;
	private String pw;
	private String name;
	private String eMail;
	private Timestamp rDate;
	private Date Date;
	private String gender;
	private String birth;
	private String year;
	private String month;
	private String day;
	private String nickname;
	private String rating;
	
	public MemberDTO() {
		
	}
	
	
	

	
	
	public MemberDTO(String id, String pw, String name, String eMail, java.sql.Date date, String gender, String birth, String rating) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.eMail = eMail;
		Date = date;
		this.gender = gender;
		this.birth = birth;
		this.rating = rating;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public Date getDate() {
		return Date;
	}

	public void setDate(Date date) {
		Date = date;
	}

	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public Timestamp getrDate() {
		return rDate;
	}
	public void setrDate(Timestamp rDate) {
		this.rDate = rDate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	
}