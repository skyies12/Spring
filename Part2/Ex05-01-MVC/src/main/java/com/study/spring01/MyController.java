package com.study.spring01;

import java.util.Locale;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyController {
	
	@RequestMapping(value = "/myhome2", method = RequestMethod.GET)
	public String home2(Locale locale, Model model) {		
		model.addAttribute("name", "orange" );
		
		return "orange";
	}
}
