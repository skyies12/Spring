package com.study.spring13.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.study.spring13.dao.BDao;

public class BWriteCommand implements BCommand {
	@Override
	public void execute(HttpServletRequest request, Model model) {

		
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		
		BDao dao = BDao.getInstance();
		dao.write(bName, bTitle, bContent);
	}
}
