package com.study.spring20;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public class deleteOk implements Service  {
	public deleteOk() {
		
	}
	
	public void execute(HttpServletRequest request, HttpServletResponse response, Model model) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		System.out.println("아이디 : " + id);
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO dto = new MemberDTO();
		
		dto.setId(id);
		
		String json_data = "";
		
		if(dao.delete(dto.getId()) == MemberDAO.MEMBER_EXISTENT) {
			json_data = "{\"code\":\"success\", \"desc\":\"탈퇴가 완료되었습니다.\"}";
		} else {

			json_data = "{\"code\":\"fail\", \"desc\":\"에러가 발생하여 탈퇴에 실패했습니다.\"}";
		}
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println(json_data);
		writer.close();
	}
}
