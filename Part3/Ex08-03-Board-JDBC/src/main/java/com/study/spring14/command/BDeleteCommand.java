package com.study.spring14.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.study.spring14.dao.BDao;

public class BDeleteCommand implements BCommand {
	@Override
	public void execute(HttpServletRequest request, Model model)  {
		String bId = request.getParameter("bId");
		BDao dao = new BDao();
		dao.delete(bId);
	}
}
