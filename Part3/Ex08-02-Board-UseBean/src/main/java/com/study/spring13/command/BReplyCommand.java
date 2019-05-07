package com.study.spring13.command;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.study.spring13.dao.BDao;
import com.study.spring13.dto.BDto;

public class BReplyCommand implements BCommand {
	@Override
	public void execute(HttpServletRequest request, Model model)  {
		
		String bId = request.getParameter("bId");
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		String bGroup = request.getParameter("bGroup");
		String bStep = request.getParameter("bStep");
		String bIndent = request.getParameter("bIndent");
		
		BDao dao = BDao.getInstance();
		dao.reply(bId, bName, bTitle, bContent, bGroup, bStep, bIndent);
		
	}
}
