package com.study.spring14.command;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.study.spring14.dao.BDao;
import com.study.spring14.dto.BDto;

public class BContentCommand implements BCommand {
	@Override
	public void execute(HttpServletRequest request, Model model)  {		
		String bId = request.getParameter("bId");
		String bkind = request.getParameter("kind");
		BDao dao = new BDao();
		BDto dto = dao.contentView(bId, bkind);
		
		model.addAttribute("content_view", dto);		
	}
}
