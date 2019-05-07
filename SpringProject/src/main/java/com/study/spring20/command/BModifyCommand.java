package com.study.spring20.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.study.spring20.dao.BDao;

public class BModifyCommand implements BCommand {
	@Override
	public void execute(HttpServletRequest request, Model model)  {
		String bNum = request.getParameter("bNum");
		String bId = request.getParameter("bId");
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		String bFile = request.getParameter("bTitle");
		
		BDao dao = new BDao();
		dao.modify(bNum, bId, bName, bTitle, bContent, bFile);
		
	}

}
