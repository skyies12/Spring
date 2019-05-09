package com.study.spring20.dao;

import java.util.ArrayList;
import com.study.spring20.MemberDTO;
import com.study.spring20.dto.BDto;
import com.study.spring20.dto.BPageInfo;;

public interface IDao {
	public BDto contentView(String strID, String kind, String intbNum);
	public void write(String bNum, String bName, String bTitle, String bFile, String bContent, String id, String nextvar, String currvar);
	public ArrayList<BDto> list(int nStart, int nEnd, String intNum);
	public ArrayList<MemberDTO> Memberlist(int nStart, int nEnd);
	public void upHit(String bId);
	public void delete(String bId, String bNum);
	public BDto reply_view(String str, String intNum);
	public void reply(String bNum, String bId, String bName, String bTitle, String bContent, String bGroup,
			int bStep, int bIndent, String checkid, String nextvar);
	public void replyShape(String strGroup, String strStep);
	public BPageInfo articlePage(int curPage);
	public BPageInfo articlePage(int curPage, String bNum);
	public ArrayList<BDto> selectlist(int nStart, int nEnd, String strTitle, String intNum, String selectNum, String search);
	public void modify(String bNum, String bId, String bName, String bTitle, String bContent, String bFile);
	public int articleCount(String bNum);
	public int memberarticleCount();
	
	public int confirmId(String id);
}
