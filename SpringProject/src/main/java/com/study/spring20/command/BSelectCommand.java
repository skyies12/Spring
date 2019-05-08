package com.study.spring20.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.study.spring20.dao.BDao;
import com.study.spring20.dto.BDto;
import com.study.spring20.dto.BPageInfo;

public class BSelectCommand implements BCommand {
	@Override
	public void execute(HttpServletRequest request, Model model)  {
		int nPage = 1;
		try {
			String sPage = request.getParameter("page");
			nPage = Integer.parseInt(sPage);
		} catch(Exception e) {}
		
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bTitle");
		String bName = request.getParameter("bTitle");
		String selectNum = request.getParameter("serchcheck");
		
		HttpSession session = null;
		session = request.getSession();
		BDao dao = new BDao();
		String bNum = (String)session.getAttribute("bNum");
		session.setAttribute("cpage", nPage);
		session.setAttribute("selectNum", selectNum);
		
		BPageInfo pinfo = dao.articlePage(nPage, bNum);
		request.setAttribute("page", pinfo);
		
		nPage = pinfo.getCurPage();
	
		ArrayList<BDto> dtos = dao.selectlist(nPage, bTitle, bNum, bContent, bName, selectNum);
		model.addAttribute("selectlist", dtos);
	}
}
