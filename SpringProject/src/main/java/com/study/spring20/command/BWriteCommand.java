package com.study.spring20.command;

import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import com.study.spring20.dao.BDao;

public class BWriteCommand implements BCommand {
	@Override
	public void execute(HttpServletRequest request, Model model) {	
		String bNum = request.getParameter("bNum");
		String id = request.getParameter("id");
		String bName = request.getParameter("bName");
		String bFile = request.getParameter("bTitle");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		BDao dao = new BDao();
		dao.write(bNum, bName, bFile, bTitle, bContent, id);
	}
}
