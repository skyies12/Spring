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

public class loginOk implements Service {
	public loginOk() {
		
	}

	public void execute(HttpServletRequest request, HttpServletResponse response, Model model) 
			throws ServletException, IOException {
		System.out.println("loginOk");
		
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO dto = new MemberDTO();
		
		String json_data = "";
		
		int checkNum = dao.userCheck(id, pw);
		if(checkNum == -1) {
			json_data = "{\"code\":\"fail\", \"desc\":\"아이디가 존재하지 않습니다.\"}";
		} else if(checkNum == 0) {
			json_data = "{\"code\":\"fail\", \"desc\":\"비밀번호가 틀립니다.\"}";
		} else if(checkNum == 1) {
			dto = dao.getMember(id);
			
			if(dto == null) {
				json_data = "{\"code\":\"fail\", \"desc\":\"존재하지 않는 회원입니다.\"}";
			} else {
				HttpSession session = request.getSession();
				String name = dto.getName();
				session.setAttribute("id", id);
				session.setAttribute("name", name);
				session.setAttribute("ValidMem", "yes");
				json_data = "{\"code\":\"success\", \"desc\":\"로그인\"}";
			}
		}
		
		
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		writer.println(json_data);
		writer.close();
	}
}
