package com.study.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.springboot.jdbc.UserDAO;
import com.study.springboot.jdbc.UserDTO;

@Controller
public class MyController {
	@Autowired
	private UserDAO dao;
	
	@RequestMapping("/")
	public @ResponseBody String root() throws Exception {
		return "jdbcTemplate 사용하기";
	}
	
	@RequestMapping("/sign") 
	public String user() {
		
		return "/user";
	}
	
	@RequestMapping(value="/user", method = RequestMethod.POST) 
	public String userAdd(UserDTO dto) {
		dao.insert(dto);
		
		return "redirect:userlist";
	}
	
	@RequestMapping(value="/userlist", method = RequestMethod.GET) 
	public String userlistPage(Model model) {
		model.addAttribute("users", dao.list());
		
		return "/userlist";
	}

}
