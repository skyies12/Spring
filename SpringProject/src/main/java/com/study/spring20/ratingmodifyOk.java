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

public class ratingmodifyOk implements Service {
	public ratingmodifyOk() {
		
	}

	public void execute(HttpServletRequest request, HttpServletResponse response, Model model) 
			throws ServletException, IOException {
		System.out.println("ratingmodifyOk");
		
		request.setCharacterEncoding("UTF-8");
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO dto = new MemberDTO();
		
		HttpSession session = request.getSession();
	
		String id = request.getParameter("id");
		String rating = request.getParameter("rating");
		dto.setId(id);
		dto.setRating(rating);
		
		System.out.println(id);
		System.out.println(rating);
		dao = MemberDAO.getInstance();
		int ri = dao.update(dto);
		
		String json_data = "";
		if (ri == 1) {
			json_data = "{\"code\":\"success\", \"desc\":\"등업 완료\"}";
			session.setAttribute("rating1", rating);
		} else {
			json_data = "{\"code\":\"fail\", \"desc\":\"등업 실패\"}";
		}
		System.out.println(ri);

		response.setContentType("application/json; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		writer.println(json_data);
		writer.close();
	}
}
