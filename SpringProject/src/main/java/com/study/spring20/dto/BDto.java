package com.study.spring20.dto;

import java.sql.*;

public class BDto {
	int bNum;
	int bId;
	String bName;
	String bTitle;
	String bContent;
	String bFile;
	Date bDate;
	int bHit;
	int bGroup;
	int bStep;
	int bIndent;
	String id;
	
	public BDto() {
		
	}

	public BDto(int bNum, int bId, String bName, String bTitle, String bContent, String bFile, Date bDate, int bHit,
			int bGroup, int bStep, int bIndent, String id) {
		super();
		this.bNum = bNum;
		this.bId = bId;
		this.bName = bName;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bFile = bFile;
		this.bDate = bDate;
		this.bHit = bHit;
		this.bGroup = bGroup;
		this.bStep = bStep;
		this.bIndent = bIndent;
		this.id = id;
	}




	public BDto(int bNum, int bId, String bName, String bTitle, String bContent, String bFile, Date bDate, int bHit,
			int bGroup, int bStep, int bIndent) {
		super();
		this.bNum = bNum;
		this.bId = bId;
		this.bName = bName;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bFile = bFile;
		this.bDate = bDate;
		this.bHit = bHit;
		this.bGroup = bGroup;
		this.bStep = bStep;
		this.bIndent = bIndent;
	}

	public BDto(int bNum, int bId, String bName, String bTitle, String bContent, Date bDate, int bHit, int bGroup, int bStep,
			int bIndent) {
		super();
		this.bNum = bNum;
		this.bId = bId;
		this.bName = bName;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bDate = bDate;
		this.bHit = bHit;
		this.bGroup = bGroup;
		this.bStep = bStep;
		this.bIndent = bIndent;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getbFile() {
		return bFile;
	}

	public void setbFile(String bFile) {
		this.bFile = bFile;
	}

	public int getbNum() {
		return bNum;
	}

	public void setbNum(int bNum) {
		this.bNum = bNum;
	}

	public int getbId() {
		return bId;
	}

	public void setbId(int bId) {
		this.bId = bId;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public String getbTitle() {
		return bTitle;
	}

	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}

	public String getbContent() {
		return bContent;
	}

	public void setbContent(String bContent) {
		this.bContent = bContent;
	}

	public Date getbDate() {
		return bDate;
	}

	public void setbDate(Date bDate) {
		this.bDate = bDate;
	}

	public int getbHit() {
		return bHit;
	}

	public void setbHit(int bHit) {
		this.bHit = bHit;
	}

	public int getbGroup() {
		return bGroup;
	}

	public void setbGroup(int bGroup) {
		this.bGroup = bGroup;
	}

	public int getbStep() {
		return bStep;
	}

	public void setbStep(int bStep) {
		this.bStep = bStep;
	}

	public int getbIndent() {
		return bIndent;
	}

	public void setbIndent(int bIndent) {
		this.bIndent = bIndent;
	}
	
	
}
