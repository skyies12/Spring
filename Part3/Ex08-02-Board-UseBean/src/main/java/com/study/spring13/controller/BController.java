package com.study.spring13.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.spring13.command.BCommand;
import com.study.spring13.command.BContentCommand;
import com.study.spring13.command.BDeleteCommand;
import com.study.spring13.command.BListCommand;
import com.study.spring13.command.BModifyCommand;
import com.study.spring13.command.BReplyCommand;
import com.study.spring13.command.BReplyViewCommand;
import com.study.spring13.command.BWriteCommand;

@Controller
public class BController {

	@Autowired
	private ApplicationContext context;
	BCommand command = null;

	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model) {
		command = context.getBean(BListCommand.class);
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
		// command = new BWriteCommand();
		command = (BWriteCommand)context.getBean("writeHandler");
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
		command = (BModifyCommand)context.getBean("modifyHandler");
		command.execute(request, model);

		command = new BContentCommand();
		command.execute(request, model);

		return "content_view";
	}

	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		command = (BDeleteCommand)context.getBean("deleteHandler");
		command.execute(request, model);

		return "redirect:list";
	}

	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request, Model model) {

		model.addAttribute("request", request);
		command = (BReplyViewCommand)context.getBean("replayViewHandler");
		command.execute(request, model);

		return "reply_view";
	}

	@RequestMapping("/reply")
	public String reply(HttpServletRequest request, Model model) {

		model.addAttribute("request", request);
		command = (BReplyCommand)context.getBean("replyHandler");
		command.execute(request, model);

		return "redirect:list";
	}
}
