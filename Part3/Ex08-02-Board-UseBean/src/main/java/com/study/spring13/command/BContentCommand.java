package com.study.spring13.command;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import com.study.spring13.dao.BDao;
import com.study.spring13.dto.BDto;

@Component("contentHandler")
public class BContentCommand implements BCommand {
	@Override
	public void execute(HttpServletRequest request, Model model)  {		
		String bId = request.getParameter("bId");
		String bkind = request.getParameter("kind");
		BDao dao = BDao.getInstance();
		BDto dto = dao.contentView(bId, bkind);
		
		model.addAttribute("content_view", dto);		
	}
}
