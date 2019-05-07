package com.study.spring.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.study.spring.dao.BDao;
import com.study.spring.dto.BDto;

public class BListCommand implements BCommand {
	@Override
	public void execute(HttpServletRequest request, Model model)  {
		BDao dao = BDao.getInstance();
		ArrayList<BDto> dtos = dao.list();
		model.addAttribute("list", dtos);
	}
}
