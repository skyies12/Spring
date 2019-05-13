package com.study.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Controller
public class MyController {

	@RequestMapping("/")
	public @ResponseBody String root() throws Exception {
		return "jdbcTemplate 사용하기";
	}
	
	@RequestMapping("/db") 
	public String user() {
		
		return "/test1";
	}
	
	@RequestMapping("/test") 
	public String test(String[] args) {

		XmlParsing_test.main(args);
		
		return "/main";
	}
	
}
