package com.study.spring20;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.study.spring20.MemberDAO;
import com.study.spring20.MemberDTO;

public class modifyOk implements Service {
	public modifyOk() {
		
	}

	public void execute(HttpServletRequest request, HttpServletResponse response, Model model) 
			throws ServletException, IOException {
		System.out.println("modifyOk");
		
		request.setCharacterEncoding("UTF-8");
		
		String pw = request.getParameter("pw");
		String eMail = request.getParameter("eMail");
		String gender = request.getParameter("gender");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO dto = new MemberDTO();
		
		HttpSession session = request.getSession();
		
		dto.setPw(pw);
		dto.seteMail(eMail);
		dto.setGender(gender);
		dto.setBirth(year + "/" + month + "/" + day);
		dto.setYear(year);
		dto.setMonth(month);
		dto.setDay(day);
		
		String id = (String)session.getAttribute("id");
		dto.setId(id);
		
		dao = MemberDAO.getInstance();
		int ri = dao.updateMember(dto);
		
		String json_data = "";
		if (ri == 1) {
			json_data = "{\"code\":\"success\", \"desc\":\"정보수정 완료\"}";
		} else {
			json_data = "{\"code\":\"success\", \"desc\":\"정보수정 실패\"}";
		}
		System.out.println(ri);

		response.setContentType("application/json; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		writer.println(json_data);
		writer.close();
	}
}
