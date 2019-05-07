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

public class NiknameOk implements Service {
	public NiknameOk() {
		
	}

	public void execute(HttpServletRequest request, HttpServletResponse response, Model model) 
			throws ServletException, IOException {
		System.out.println("joinOk");
		
		request.setCharacterEncoding("UTF-8");
		String nickname = request.getParameter("nickname");
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO dto = new MemberDTO();
		
		
		dto.setNickname(nickname);

		String json_data = "";
		if(dao.confirmNickname(dto.getNickname()) == MemberDAO.MEMBER_EXISTENT) {
			json_data = "{\"code\":\"fail\",\"desc\":\"닉네임 이미 존재합니다.\"}";
		} else {
			int ri = dao.insertNciname(dto);
			if(ri == MemberDAO.MEMBER_JOIN_SUCCESS) {
				HttpSession session = request.getSession();
				session.setAttribute("nickname", dto.getNickname());

				json_data = "{\"code\":\"success\", \"desc\":\"채팅방에 입장하셨습니다.\"}";
			} else {
				json_data = "{\"code\":\"fail\", \"desc\":\"에러가 발생하여 채팅방 참여에 실패했습니다.\"}";
			}
		}
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		writer.println(json_data);
		writer.close();
	}
}
