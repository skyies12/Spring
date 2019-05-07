package com.study.spring04;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class HomeController {
	
	@RequestMapping("/")
	public String root(Model model) {
		model.addAttribute("name", "홍길동");
		
		return "board/home";
	}
	
	@RequestMapping("/write")
	public String write(Model model) {
		model.addAttribute("id", 30);
		
		return "board/write";
	}
}
