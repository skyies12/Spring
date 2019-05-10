package com.study.spring31.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.study.spring31.dto.ContentDto;

public interface IDao {
	public ArrayList<ContentDto> listDao();
	public void writeDao(String mWriter, String mContent);
	public ContentDto viewDao(String strID);
	public void deleteDao(@Param("_id") String bId);
	public int articleCount();
}
