package com.study.spring20.dao;

import java.util.ArrayList;

import com.study.spring20.MemberDTO;
import com.study.spring20.dto.BDto;
import com.study.spring20.dto.BPageInfo;;

public interface IDao {
	public BDto contentView(String strID, String kind, String intbNum);
	public void write(String bNum, String bName, String bTitle, String bFile, String bContent, String id);
	public ArrayList<BDto> list(int curPage, String intNum);
	public ArrayList<MemberDTO> Memberlist(int curPage);
	public void upHit(String bId);
	public void delete(String bId, String bNum);
	public BDto reply_view(String str, String intNum);
	public void reply(String bNum, String bId, String bName, String bTitle, String bContent, String bGroup,
			String bStep, String bIndent, String checkid);
	public void replyShape(String strGroup, String strStep);
	public BPageInfo articlePage(int curPage);
	public BPageInfo articlePage(int curPage, String bNum);
	public ArrayList<BDto> selectlist(int curPage, String strTitle, String intNum, String strContent, String strName, String selectNum);
}
