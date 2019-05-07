package com.study.spring07;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping("/index")
	public String goIndex() {
		return "index";
	}
	
	@RequestMapping(value = "/student", method = RequestMethod.GET)
	public String goStudent1(HttpServletRequest httpServletRequest, Model model) {
		System.out.println("GET");
		String id = httpServletRequest.getParameter("id");
		
		model.addAttribute("studentId", id);
		
		return "student/studentId";
	}
	
	@RequestMapping(value = "/student", method = RequestMethod.POST)
	public String goStudent2(HttpServletRequest httpServletRequest, Model model) {
		System.out.println("POST");
		String id = httpServletRequest.getParameter("id");
		
		model.addAttribute("studentId", id);
		
		return "student/studentId";
	}
}
