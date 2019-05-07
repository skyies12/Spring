package com.study.spring20.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.study.spring20.dao.BDao;
import com.study.spring20.dto.BDto;

public class BReplyViewCommand implements BCommand {
	@Override
	public void execute(HttpServletRequest request, Model model)  {
		String bId = request.getParameter("bId");
		String bNum = request.getParameter("bNum");
		BDao dao = new BDao();
		BDto dto = dao.reply_view(bId, bNum);
		
		request.setAttribute("reply_view", dto);
		
	}
}
