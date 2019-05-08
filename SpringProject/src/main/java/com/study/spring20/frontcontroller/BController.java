package com.study.spring20.frontcontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.study.spring20.NicknamedeleteOk;
import com.study.spring20.NiknameOk;
import com.study.spring20.Service;
import com.study.spring20.deleteOk;
import com.study.spring20.idcheckOk;
import com.study.spring20.joinOk;
import com.study.spring20.loginOk;
import com.study.spring20.modifyOk;
import com.study.spring20.ratingmodifyOk;
import com.study.spring20.command.BCommand;
import com.study.spring20.command.BContentCommand;
import com.study.spring20.command.BDeleteCommand;
import com.study.spring20.command.BListCommand;
import com.study.spring20.command.BModifyCommand;
import com.study.spring20.command.BReplyCommand;
import com.study.spring20.command.BReplyViewCommand;
import com.study.spring20.command.BSelectCommand;
import com.study.spring20.command.BWriteCommand;
import com.study.spring20.command.FileModifyCommand;
import com.study.spring20.command.FileUploadCommand;
import com.study.spring20.command.MemberListCommand;
import com.study.spring20.util.Constant;

@Controller
public class BController {
	
	@Autowired
	private ApplicationContext context;
	
	String viewPage = null;
	BCommand command = null;
	Service service = null;
	
	public JdbcTemplate template;
	
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
		Constant.template = this.template;
	}

	@RequestMapping("/login")
	public String login(HttpServletRequest request,HttpServletResponse response, Model model) {
		return "login";
	}
	
	@RequestMapping(value = "/loginOk", method = RequestMethod.POST)
	public void loginOk(HttpServletRequest request,HttpServletResponse response, Model model) throws ServletException, IOException {
		service = new loginOk();
		service.execute(request, response, model);
	}
	
	@RequestMapping(value = "/idcheckOk", method = RequestMethod.POST)
	public void idcheckOk(HttpServletRequest request,HttpServletResponse response, Model model) throws ServletException, IOException {
		service = new idcheckOk();
		service.execute(request, response, model);
	}
	
	@RequestMapping(value = "/joinOk", method = RequestMethod.POST)
	public void joinOk(HttpServletRequest request,HttpServletResponse response, Model model) throws ServletException, IOException {
		service = new joinOk();
		service.execute(request, response, model);
	}
	
	@RequestMapping(value = "/param", method = RequestMethod.POST)
	public String param(HttpServletRequest request,HttpServletResponse response, Model model) {
		return "param";
	}
	
	@RequestMapping("/main-login")
	public String main(HttpServletRequest request,HttpServletResponse response, Model model) {
		
		return "main-login";
	}
	
	@RequestMapping("/join")
	public String join(HttpServletRequest request,HttpServletResponse response, Model model) {
		return "join";
	}
	
	@RequestMapping("/membermodify")
	public String modify(HttpServletRequest request,HttpServletResponse response, Model model) {
		return "modify";
	}
	
	@RequestMapping(value = "/modifyOk", method = RequestMethod.POST)
	public void modifyOk(HttpServletRequest request,HttpServletResponse response, Model model) throws ServletException, IOException {
		service = new modifyOk();
		service.execute(request, response, model);
	}
	
	@RequestMapping(value = "/ratingmodifyOk", method = RequestMethod.POST)
	public void ratingmodifyOk(HttpServletRequest request,HttpServletResponse response, Model model) throws ServletException, IOException {
		service = new ratingmodifyOk();
		service.execute(request,response, model);
	}
	
	@RequestMapping(value = "/deleteOk", method = RequestMethod.POST)
	public void deletekOk(HttpServletRequest request,HttpServletResponse response, Model model) throws ServletException, IOException {
		service = new deleteOk();
		service.execute(request,response, model);
	}
	
	@RequestMapping(value = "/nickname", method = RequestMethod.POST)
	public void nickname(HttpServletRequest request,HttpServletResponse response, Model model) throws ServletException, IOException {
		service = new NiknameOk();
		service.execute(request,response, model);
	}
	
	@RequestMapping(value = "/nicknamedeleteOk", method = RequestMethod.POST)
	public void nicknamedeleteOk(HttpServletRequest request,HttpServletResponse response, Model model) throws ServletException, IOException {
		service = new NicknamedeleteOk();
		service.execute(request,response, model);
	}
	
	@RequestMapping(value = "/member")
	public String member(HttpServletRequest request,Model model)  {
		command = new MemberListCommand();
		command.execute(request, model);
		
		return "memberlist";
	}

	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model) {
		command = new BListCommand();
		command.execute(request, model);
	
		return "list";
	}
	
	@RequestMapping("/write_view")
	public String write_view(HttpServletRequest request,HttpServletResponse response, Model model) {
		return "write_view";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		command = new BWriteCommand();
		command.execute(request, model);
		String bNum = request.getParameter("bNum");
	
		return "redirect:list?bNum=" + bNum;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, Model model) {
		HttpSession session = null;
		session = request.getSession();
		session.invalidate();
		return "login";
	}
	
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request, Model model) {
		command = new BContentCommand();
		command.execute(request, model);
		return "content_view";
	}	
	@RequestMapping(value = "/select",produces="text/plain;charset=UTF-8")
	public String select(HttpServletRequest request, Model model) {
		command = new BSelectCommand();
		command.execute(request, model);
		return "select";
	}	
	
	@RequestMapping("/modify_view")
	public String modify_view(HttpServletRequest request, Model model) {
		command = new BContentCommand();
		command.execute(request, model);
		return "modify_view";
	}	
	
	@RequestMapping("/modify")
	public String modify(HttpServletRequest request, Model model) {
		command = new BModifyCommand();
		command.execute(request, model);
		
		command = new BContentCommand();
		command.execute(request, model);
		String bNum = request.getParameter("bNum");
		String name = request.getParameter("name");
		String bId = request.getParameter("bId");
		String bkind = request.getParameter("kind");
		
		return "redirect:content_view?name=" + name + "&bNum=" + bNum + "&bId=" + bId + "&kind=" + bkind;
	}	
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		command = new BDeleteCommand();
		command.execute(request, model);
		String bNum = request.getParameter("bNum");
		
		return "redirect:list?bNum=" + bNum;
	}
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request, Model model) {
		command = new BReplyViewCommand();
		command.execute(request, model);
		
		return "reply_view";
	}
	
	@RequestMapping("/reply")
	public String reply(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		command = new BReplyCommand();
		command.execute(request, model);
		
		String bNum = request.getParameter("bNum");
		
		return "redirect:list?bNum=" + bNum;
	}
	
	@RequestMapping("/upload_view")
	public String upload_view(HttpServletRequest request, Model model) {
		
		return "upload_view";
	}

	@RequestMapping(value = "/filesUpload", method = RequestMethod.POST)
	public String filesUpload(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		command = new FileUploadCommand();
		command.execute(request, model);
		String bNum = request.getParameter("bNum");
	
		return "redirect:list?bNum=" + bNum;
	}
	
	@RequestMapping("/filecontent_view")
	public String filecontent_view(HttpServletRequest request, Model model) {
		command = new BContentCommand();
		command.execute(request, model);
		return "file_view";
	}	
	
	@RequestMapping("/fileDown")
	public String fileDown(HttpServletRequest request, Model model) {
		
		return "fileDown";
	}
	
	@RequestMapping("/filemodify_view")
	public String filemodify_view(HttpServletRequest request, Model model) {
		command = new BContentCommand();
		command.execute(request, model);
		return "filemodify_view";
	}	
	
	@RequestMapping("/filemodify")
	public String filemodify(HttpServletRequest request, Model model) {
		command = new FileModifyCommand();
		command.execute(request, model);
		
		String bNum = request.getParameter("bNum");
		
		return "redirect:list?bNum=" + bNum;
	}
	
	@RequestMapping("/chatlogin")
	public String chatlogin(HttpServletRequest request, Model model) {

		return "chatlogin";
	}	
	
	@RequestMapping("/client")
	public String client(HttpServletRequest request, Model model) {

		return "client";
	}
}


