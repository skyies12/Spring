package com.study.spring20;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

public class NicknamedeleteOk implements Service  {
	public NicknamedeleteOk() {
		
	}

	public void execute(HttpServletRequest request, HttpServletResponse response, Model model) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String nickname = request.getParameter("nickname");
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO dto = new MemberDTO();
		
		dto.setNickname(nickname);
		
		String json_data = "";
		
		if(dao.deletenickname(dto.getNickname()) == MemberDAO.MEMBER_EXISTENT) {
			json_data = "{\"code\":\"success\", \"desc\":\"" + dto.getNickname() + "님이 퇴장하셨습니다.\"}";
		} else {

			json_data = "{\"code\":\"fail\", \"desc\":\"에러가 발생하여 탈퇴에 실패했습니다.\"}";
		}
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println(json_data);
		writer.close();
	}
}
