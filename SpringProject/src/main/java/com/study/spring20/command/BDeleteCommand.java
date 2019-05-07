package com.study.spring20.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.study.spring20.dao.BDao;

public class BDeleteCommand implements BCommand {
	@Override
	public void execute(HttpServletRequest request, Model model)  {
		String bId = request.getParameter("bId");
		String bNum = request.getParameter("bNum");
		BDao dao = BDao.getInstance();
		dao.delete(bId, bNum);
	}
}
