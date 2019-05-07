package com.study.spring20;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.study.spring20.MemberDAO;
import com.study.spring20.MemberDTO;

public class joinOk implements Service {
	public joinOk() {
		
	}
	
	public void execute(HttpServletRequest request, HttpServletResponse response, Model model) 
			throws ServletException, IOException {
		System.out.println("joinOk");
		
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String eMail = request.getParameter("eMail");
		String gender = request.getParameter("gender");
		String rating = request.getParameter("rating");
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO dto = new MemberDTO();
		
		dto.setYear(year);
		dto.setMonth(month);
		dto.setDay(day);
		dto.setId(id);
		dto.setPw(pw);
		dto.setName(name);
		dto.seteMail(eMail);
		dto.setGender(gender);
		dto.setBirth(dto.getYear() + "/" + dto.getMonth() + "/" + dto.getDay());
		dto.setrDate(new Timestamp(System.currentTimeMillis()));
		dto.setRating(rating);
		
		String json_data = "";
		if(dao.confirmId(dto.getId()) == MemberDAO.MEMBER_EXISTENT) {
			json_data = "{\"code\":\"fail\",\"desc\":\"아이디가 이미 존재합니다.\"}";
		} else {
			int ri = dao.insertMember(dto);
			if(ri == MemberDAO.MEMBER_JOIN_SUCCESS) {
				HttpSession session = request.getSession();
				session.setAttribute("id", dto.getId());
				session.setAttribute("pw", dto.getPw());
				session.setAttribute("name", dto.getName());
				session.setAttribute("birth", dto.getBirth());
				session.setAttribute("eMail", dto.geteMail());
				session.setAttribute("year", dto.getYear());
				session.setAttribute("month", dto.getMonth());
				session.setAttribute("day", dto.getDay());
				session.setAttribute("rating", dto.getRating());
			
				json_data = "{\"code\":\"success\", \"desc\":\"회원가입을 축하합니다.\"}";
			} else {
				json_data = "{\"code\":\"fail\", \"desc\":\"에러가 발생하여 회원가입에 실패했습니다.\"}";
			}
		}
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		writer.println(json_data);
		writer.close();
	}
}
