package com.study.spring14.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.spring14.command.BCommand;
import com.study.spring14.command.BContentCommand;
import com.study.spring14.command.BDeleteCommand;
import com.study.spring14.command.BListCommand;
import com.study.spring14.command.BModifyCommand;
import com.study.spring14.command.BReplyCommand;
import com.study.spring14.command.BReplyViewCommand;
import com.study.spring14.command.BWriteCommand;
import com.study.spring14.util.Constant;


@Controller
public class BController {
	@Autowired
	private ApplicationContext context;

	BCommand command = null;

	public JdbcTemplate template;

	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
		Constant.template = this.template;
	}

	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model) {
		command = new BListCommand();
		command.execute(request, model);

		return "list";
	}

	@RequestMapping("/write_view")
	public String write_view(Model model) {

		return "write_view";
	}

	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		command = new BWriteCommand();
		command.execute(request, model);

		return "redirect:list";
	}

	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request, Model model) {

		model.addAttribute("request", request);
		command = new BContentCommand();
		command.execute(request, model);

		return "content_view";
	}

	@RequestMapping("/modify_view")
	public String modify_view(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		command = new BContentCommand();
		command.execute(request, model);
		return "modify_view";
	}

	@RequestMapping("/modify")
	public String modify(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		command = new BModifyCommand();
		command.execute(request, model);

		command = new BContentCommand();
		command.execute(request, model);

		return "content_view";
	}

	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		command = new BDeleteCommand();
		command.execute(request, model);

		return "redirect:list";
	}

	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request, Model model) {

		model.addAttribute("request", request);
		command = new BReplyViewCommand();
		command.execute(request, model);

		return "reply_view";
	}

	@RequestMapping("/reply")
	public String reply(HttpServletRequest request, Model model) {

		model.addAttribute("request", request);
		command = new BReplyCommand();
		command.execute(request, model);

		return "redirect:list";
	}
}
