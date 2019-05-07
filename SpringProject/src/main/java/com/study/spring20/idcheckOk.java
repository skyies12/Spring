package com.study.spring20;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

public class idcheckOk implements Service  {
	public idcheckOk() {
		
	}
	
	public void execute(HttpServletRequest request, HttpServletResponse response, Model model) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO dto = new MemberDTO();
		
		dto.setId(id);
		
		String json_data = "";
		
		if(dao.checkId(dto.getId()) == MemberDAO.MEMBER_EXISTENT) {
			json_data = "{\"code\":\"fail\", \"desc\":\"아이디가 이미 존재합니다.\"}";
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("id", dto.getId());
			json_data = "{\"code\":\"success\", \"desc\":\"멋진 아이디네요!\"}";
			System.out.println(dto.getId());
		}
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println(json_data);
		writer.close();
	}
}
