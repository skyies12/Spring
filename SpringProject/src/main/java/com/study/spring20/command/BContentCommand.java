package com.study.spring20.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.study.spring20.dao.BDao;
import com.study.spring20.dto.BDto;

public class BContentCommand implements BCommand {
	@Override
	public void execute(HttpServletRequest request, Model model)  {
		String bId = request.getParameter("bId");
		String bkind = request.getParameter("kind");
		String checkname = request.getParameter("name");
		String bNum = request.getParameter("bNum");
		String check = request.getParameter("check");
		BDao dao = BDao.getInstance();
		BDto dto = dao.contentView(bId, bkind, bNum);
		
		HttpSession session = null;
		session = request.getSession();
		session.setAttribute("checkname", checkname);
		
		request.setAttribute("content_view", dto);
	}
}
