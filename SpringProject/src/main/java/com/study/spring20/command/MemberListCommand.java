package com.study.spring20.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.study.spring20.MemberDTO;
import com.study.spring20.dao.BDao;
import com.study.spring20.dto.BDto;
import com.study.spring20.dto.BPageInfo;

public class MemberListCommand implements BCommand {
	@Override
	public void execute(HttpServletRequest request, Model model)  {
		int nPage = 1;
		try {
			String sPage = request.getParameter("page");
			nPage = Integer.parseInt(sPage);
		} catch(Exception e) {}
		
		BDao dao = BDao.getInstance();	

		String bNum = request.getParameter("bNum");
		String naver = request.getParameter("naver");
		String face = request.getParameter("face");
		String kakao = request.getParameter("kakao");
		String google = request.getParameter("google");
		String db = request.getParameter("db");
		
		BPageInfo pinfo = dao.articlePage(nPage);
		request.setAttribute("page", pinfo);
		
		nPage = pinfo.getCurPage();
		
		HttpSession session = null;
		session = request.getSession();
		session.setAttribute("cpage", nPage);
		session.setAttribute("bNum", bNum);
		session.setAttribute("naver", naver);
		session.setAttribute("face", face);
		session.setAttribute("kakao", kakao);
		session.setAttribute("google", google);
		session.setAttribute("db", db);
		
		ArrayList<MemberDTO> dtos = dao.Memberlist(nPage);
		request.setAttribute("memberlist", dtos);

	}
}
