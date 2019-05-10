package com.study.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController2 {

	@RequestMapping("/2")
	public @ResponseBody String test1() throws Exception {
		return "MyController 2(/)";
	}
	
	@RequestMapping("/demo2")
	public @ResponseBody String test2() throws Exception {
		// Contents 자체를 return - ajax 데이터 등
		return "MyController 2(/demo)";
	}
	
	@RequestMapping("/test1")
	public String test3() {
		return "test1";
	}
	
	@RequestMapping("/test2")
	public String test4() {
		return "sub/test2";
	}
}
