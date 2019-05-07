package com.study.spring20.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.study.spring20.dao.BDao;
import com.study.spring20.dto.BDto;

public class BReplyCommand implements BCommand {
	@Override
	public void execute(HttpServletRequest request, Model model)  {
		String bNum = request.getParameter("bNum");
		String bId = request.getParameter("bId");
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		String bGroup = request.getParameter("bGroup");
		String bStep = request.getParameter("bStep");
		String bIndent = request.getParameter("bIndent");
		String id = request.getParameter("id");
		
		BDao dao = BDao.getInstance();
		dao.reply(bNum, bId, bName, bTitle, bContent, bGroup, bStep, bIndent, id);
		
	}
}
