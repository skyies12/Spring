package com.study.spring31;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.study.spring31.dao.IDao;
import com.study.spring31.dto.ContentDto;

@Controller
public class HomeController {
//	private ContentDao dao;
//	
//	@Autowired
//	public void setDao(ContentDao dao) {
//		this.dao = dao;
//	}
	
	@Autowired
	private SqlSession sqlSession;
	
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
	
	@RequestMapping("/list")
	public String list(Model model) {
//		ArrayList<ContentDto> dtos = dao.listDao();
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		model.addAttribute("list", dao.listDao());
		
		int nTotalCount = dao.articleCount();
		System.out.println("Count : " + nTotalCount);
		
		return "/list";
	}
	
	@RequestMapping("/writeForm")
	public String writeForm() {
		
		return "/writeForm";
	}
	
	@RequestMapping("/write")
	
	public String write(HttpServletRequest request, Model model) {
		String mWriter = request.getParameter("mWriter");
		String mContent = request.getParameter("mContent");
		
//		dao.writeDao(mWriter, mContent);
		Map<String, String> map = new HashMap<String, String>();
		map.put("item1", mWriter);
		map.put("item2", mContent);
		
		int nResult = sqlSession.update("writeDao", map);
		System.out.println("Write : " + nResult);
		
		return "redirect:list";
	}
	

	@RequestMapping("/view")
	public String view(HttpServletRequest request, Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		String strId = request.getParameter("mId");
		
		model.addAttribute("content_view", dao.viewDao(strId));
		
		return "/view";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		IDao dao = sqlSession.getMapper(IDao.class);
		String mId = request.getParameter("mId");
		
		int nResult = sqlSession.update("deleteDao", mId);
		System.out.println("Delete : " + nResult);
		
		return "redirect:list";
	}
}
