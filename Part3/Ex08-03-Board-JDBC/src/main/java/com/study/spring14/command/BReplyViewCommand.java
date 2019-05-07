package com.study.spring14.command;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.study.spring14.dao.BDao;
import com.study.spring14.dto.BDto;

public class BReplyViewCommand implements BCommand {
	@Override
	public void execute(HttpServletRequest request, Model model)  {
		
		String bId = request.getParameter("bId");
		BDao dao = new BDao();
		BDto dto = dao.reply_view(bId);
		
		model.addAttribute("reply_view", dto);
		
	}
}
