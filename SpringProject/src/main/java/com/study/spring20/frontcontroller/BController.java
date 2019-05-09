package com.study.spring20.frontcontroller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.study.spring20.MemberDTO;
import com.study.spring20.NicknamedeleteOk;
import com.study.spring20.NiknameOk;
import com.study.spring20.Service;
import com.study.spring20.deleteOk;
import com.study.spring20.idcheckOk;
import com.study.spring20.joinOk;
import com.study.spring20.loginOk;
import com.study.spring20.modifyOk;
import com.study.spring20.ratingmodifyOk;
import com.study.spring20.dao.IDao;
import com.study.spring20.dto.BPageInfo;
import com.study.spring20.util.Constant;

@Controller
public class BController {
	
	@Autowired
	private SqlSession sqlSession;
	
	private HttpSession session = null;
	
	int listCount = 10;	// 한페이지당 보여줄 게시물의 갯수
	int pageCount = 10; // 하단에 보여줄 페이지 리스트이 갯수
	
	String viewPage = null;
	Service service = null;
	
	public JdbcTemplate template;
	
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
		Constant.template = this.template;
	}

	@RequestMapping("/member/welcome")
	public String login(HttpServletRequest request,HttpServletResponse response, Model model, Locale locale) {
		return "member/main-login";
	}
	
	@RequestMapping("/loginForm")
	public String loginForm(Locale locale, Model model) {
		return "security/login";
	}
	
	@RequestMapping("/loginError")
	public String loginError(Locale locale, Model model) {
		return "security/loginError";
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
		session = request.getSession();
		
		int nPage = 1;
		try {
			if(request.getParameter("page") != null) {
				String sPage = request.getParameter("page");
				nPage = Integer.parseInt(sPage);
			} else {
				if(session.getAttribute("cpage") != null) {
					nPage = (Integer)session.getAttribute("cpage");
				}
			}

		} catch(Exception e) {}

		IDao dao = sqlSession.getMapper(IDao.class);

		String bNum = request.getParameter("bNum");
		String naver = request.getParameter("naver");
		String face = request.getParameter("face");
		String kakao = request.getParameter("kakao");
		String google = request.getParameter("google");
		String db = request.getParameter("db");

		int nTotalPage = dao.memberarticleCount();
		
		BPageInfo pinfo = articlePage(nPage, nTotalPage);
		session.setAttribute("page", pinfo);
		
		nPage = pinfo.getCurPage();
		
		int nStart = (nPage - 1) * listCount + 1;
		int nEnd = (nPage - 1) * listCount + listCount;
		
		session = request.getSession();
		session.setAttribute("cpage", nPage);
		session.setAttribute("bNum", bNum);
		session.setAttribute("naver", naver);
		session.setAttribute("face", face);
		session.setAttribute("kakao", kakao);
		session.setAttribute("google", google);
		session.setAttribute("db", db);

		model.addAttribute("memberlist", dao.Memberlist(nStart, nEnd));
		
		return "memberlist";
	}

	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model) {
		session = request.getSession();
		
		int nPage = 1;
		try {
			if(request.getParameter("page") != null) {
				String sPage = request.getParameter("page");
				nPage = Integer.parseInt(sPage);
			} else {
				if(session.getAttribute("cpage") != null) {
					nPage = (Integer)session.getAttribute("cpage");
				}
			}

		} catch(Exception e) {}

		IDao dao = sqlSession.getMapper(IDao.class);

		String bNum = request.getParameter("bNum");
		String naver = request.getParameter("naver");
		String face = request.getParameter("face");
		String kakao = request.getParameter("kakao");
		String google = request.getParameter("google");
		String db = request.getParameter("db");

		int nTotalPage = dao.articleCount(bNum);
		
		BPageInfo pinfo = articlePage(nPage, nTotalPage, bNum);
		session.setAttribute("page", pinfo);
		
		nPage = pinfo.getCurPage();
		
		int nStart = (nPage - 1) * listCount + 1;
		int nEnd = (nPage - 1) * listCount + listCount;
		
		session = request.getSession();
		session.setAttribute("cpage", nPage);
		session.setAttribute("bNum", bNum);
		session.setAttribute("naver", naver);
		session.setAttribute("face", face);
		session.setAttribute("kakao", kakao);
		session.setAttribute("google", google);
		session.setAttribute("db", db);
	
		model.addAttribute("list", dao.list(nStart, nEnd, bNum));
		
		return "list";
	}
	
	@RequestMapping("/write_view")
	public String write_view(HttpServletRequest request,HttpServletResponse response, Model model) {
		return "write_view";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(HttpServletRequest request, Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		String bNum = request.getParameter("bNum");
		String id = request.getParameter("id");
		String bName = request.getParameter("bName");
		String bFile = request.getParameter("bTitle");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		
		if(bNum.equals("0")) {
			dao.write(bNum, bName, bFile, bTitle, bContent, id, "MVC_BOARD_SEQ1.NEXTVAL", "MVC_BOARD_SEQ1.CURRVAL");
		} else if(bNum.equals("1")) {
			dao.write(bNum, bName, bFile, bTitle, bContent, id, "MVC_BOARD_SEQ2.NEXTVAL", "MVC_BOARD_SEQ2.CURRVAL");
		} else if(bNum.equals("2")) {
			dao.write(bNum, bName, bFile, bTitle, bContent, id, "MVC_BOARD_SEQ3.NEXTVAL", "MVC_BOARD_SEQ3.CURRVAL");
		} else if(bNum.equals("3")) {
			dao.write(bNum, bName, bFile, bTitle, bContent, id, "MVC_BOARD_SEQ4.NEXTVAL", "MVC_BOARD_SEQ4.CURRVAL");
		}
	
		return "redirect:list?bNum=" + bNum;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, Model model) {
		session = request.getSession();
		session.invalidate();
		return "login";
	}
	
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request, Model model) {
		String bId = request.getParameter("bId");
		String bkind = request.getParameter("kind");
		String checkname = request.getParameter("name");
		String bNum = request.getParameter("bNum");

		IDao dao = sqlSession.getMapper(IDao.class);
		
		if(bkind.equals("view")) {
			dao.upHit(bId);
		} 
		
		session = request.getSession();
		session.setAttribute("checkname", checkname);
		
		model.addAttribute("content_view", dao.contentView(bId, bkind, bNum));
		
		return "content_view";
	}	
	@RequestMapping(value = "/select",produces="text/plain;charset=UTF-8")
	public String select(HttpServletRequest request, Model model) {
		session = request.getSession();
		
		int nPage = 1;
		try {
			if(request.getParameter("page") != null) {
				String sPage = request.getParameter("page");
				nPage = Integer.parseInt(sPage);
			} else {
				if(session.getAttribute("cpage") != null) {
					nPage = (Integer)session.getAttribute("cpage");
				}
			}

		} catch(Exception e) {}

		IDao dao = sqlSession.getMapper(IDao.class);

		String bNum = request.getParameter("bNum");
		String naver = request.getParameter("naver");
		String face = request.getParameter("face");
		String kakao = request.getParameter("kakao");
		String google = request.getParameter("google");
		String db = request.getParameter("db");
		String str = request.getParameter("bTitle");
		String selectNum = request.getParameter("serchcheck");
		
		int nTotalPage = dao.articleCount(bNum);
		
		BPageInfo pinfo = articlePage(nPage, nTotalPage, bNum);
		session.setAttribute("page", pinfo);
		session.setAttribute("selectNum", selectNum);
		
		nPage = pinfo.getCurPage();
		
		int nStart = (nPage - 1) * listCount + 1;
		int nEnd = (nPage - 1) * listCount + listCount;
		
		session = request.getSession();
		session.setAttribute("cpage", nPage);
		session.setAttribute("bNum", bNum);
		session.setAttribute("naver", naver);
		session.setAttribute("face", face);
		session.setAttribute("kakao", kakao);
		session.setAttribute("google", google);
		session.setAttribute("db", db);
		
		System.out.println(selectNum);
		
		if(selectNum.equals("0")) {
			model.addAttribute("selectlist", dao.selectlist(nStart, nEnd, str, bNum, selectNum, "BTITLE"));
		} else if(selectNum.equals("1")) {
			model.addAttribute("selectlist", dao.selectlist(nStart, nEnd, str, bNum, selectNum, "BCONTENT"));
		} else if(selectNum.equals("2")) {
			model.addAttribute("selectlist", dao.selectlist(nStart, nEnd, str, bNum, selectNum, "BNAME"));
		}

		return "select";
	}	
	
	@RequestMapping("/modify_view")
	public String modify_view(HttpServletRequest request, Model model) {
		String bId = request.getParameter("bId");
		String bkind = request.getParameter("kind");
		String checkname = request.getParameter("name");
		String bNum = request.getParameter("bNum");

		IDao dao = sqlSession.getMapper(IDao.class);
		
		if(bkind.equals("view")) {
			dao.upHit(bId);
		} 
		
		session = request.getSession();
		session.setAttribute("checkname", checkname);
		
		model.addAttribute("content_view", dao.contentView(bId, bkind, bNum));
		return "modify_view";
	}	
	
	@RequestMapping("/modify")
	public String modify(HttpServletRequest request, Model model) {
		String bNum = request.getParameter("bNum");
		String bId = request.getParameter("bId");
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		String bFile = request.getParameter("bTitle");
		String name = request.getParameter("name");
		String bkind = request.getParameter("kind");
		String checkname = request.getParameter("name");
		
		IDao dao = sqlSession.getMapper(IDao.class);
		dao.modify(bNum, bId, bName, bTitle, bContent, bFile);
		
		if(bkind.equals("view")) {
			dao.upHit(bId);
		} 
		
		session = request.getSession();
		session.setAttribute("checkname", checkname);
		
		model.addAttribute("content_view", dao.contentView(bId, bkind, bNum));
		
		return "redirect:content_view?name=" + name + "&bNum=" + bNum + "&bId=" + bId + "&kind=" + bkind;
	}	
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		IDao dao = sqlSession.getMapper(IDao.class);
		
		String bId = request.getParameter("bId");
		String bNum = request.getParameter("bNum");
		
		dao.delete(bId, bNum);
		
		return "redirect:list?bNum=" + bNum;
	}
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request, Model model) {
		IDao dao = sqlSession.getMapper(IDao.class);
		
		String bId = request.getParameter("bId");
		String bNum = request.getParameter("bNum");
		
		model.addAttribute("reply_view", dao.reply_view(bId, bNum));
		
		return "reply_view";
	}
	
	@RequestMapping("/reply")
	public String reply(HttpServletRequest request, Model model) {
		IDao dao = sqlSession.getMapper(IDao.class);
		
		String bNum = request.getParameter("bNum");
		String bId = request.getParameter("bId");
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		String bGroup = request.getParameter("bGroup");
		String bStep = request.getParameter("bStep");
		String bIndent = request.getParameter("bIndent");
		String id = request.getParameter("id");
		
		dao.replyShape(bGroup, bStep);
		
		if(bNum.equals("0")) {
			dao.reply(bNum, bId, bName, bTitle, bContent, bGroup, Integer.parseInt(bStep) + 1, Integer.parseInt(bIndent) + 1, id, "MVC_BOARD_SEQ1.NEXTVAL");
		} else if(bNum.equals("1")) {
			dao.reply(bNum, bId, bName, bTitle, bContent, bGroup, Integer.parseInt(bStep) + 1, Integer.parseInt(bIndent) + 1, id, "MVC_BOARD_SEQ2.NEXTVAL");
		} else if(bNum.equals("2")) {
			dao.reply(bNum, bId, bName, bTitle, bContent, bGroup, Integer.parseInt(bStep) + 1, Integer.parseInt(bIndent) + 1, id, "MVC_BOARD_SEQ3.NEXTVAL");
		} else if(bNum.equals("3")) {
			dao.reply(bNum, bId, bName, bTitle, bContent, bGroup, Integer.parseInt(bStep) + 1, Integer.parseInt(bIndent) + 1, id, "MVC_BOARD_SEQ4.NEXTVAL");
		}
		
		return "redirect:list?bNum=" + bNum;
	}
	
	@RequestMapping("/upload_view")
	public String upload_view(HttpServletRequest request, Model model) {
		
		return "upload_view";
	}

	@RequestMapping(value = "/filesUpload", method = RequestMethod.POST)
	public String filesUpload(HttpServletRequest request, Model model) {
		
		String uploadPath = request.getRealPath("resources/fileFolder");
		
		int maxSize = 1024 * 1024 * 10; // 한번에 올릴 수 있는 파일 용량 : 10M로 제한
		
		String name = "";
		String subject = "";
		String bNum = "";
		String fileName1 = ""; // 중복처리된 이름
		String originalName1 = ""; // 중복 처리전 실제 원본 이름
		long fileSize = 0; // 파일 사이즈
		String fileType = ""; // 파일 타입
		String bContent = "";
		String id = "";
		
		MultipartRequest multi = null;
		
		try{
			// request,파일저장경로,용량,인코딩타입,중복파일명에 대한 기본 정책
			multi = new MultipartRequest(request,uploadPath,maxSize,"utf-8",new DefaultFileRenamePolicy());
			// form내의 input name="name" 인 녀석 value를 가져옴
			bNum = multi.getParameter("bNum");
			name = multi.getParameter("bName");
			id = multi.getParameter("id");
			// name="subject" 인 녀석 value를 가져옴
			subject = multi.getParameter("bTitle");
			bContent = multi.getParameter("bContent");
			// 전송한 전체 파일이름들을 가져옴
			Enumeration files = multi.getFileNames();
			
			while(files.hasMoreElements()){
				// form 태그에서 <input type="file" name="여기에 지정한 이름" />을 가져온다.
				String file1 = (String)files.nextElement(); // 파일 input에 지정한 이름을 가져옴
				// 그에 해당하는 실재 파일 이름을 가져옴
				originalName1 = multi.getOriginalFileName(file1);
				// 파일명이 중복될 경우 중복 정책에 의해 뒤에 1,2,3 처럼 붙어 unique하게 파일명을 생성하는데
				// 이때 생성된 이름을 filesystemName이라 하여 그 이름 정보를 가져온다.(중복에 대한 처리)
				fileName1 = multi.getFilesystemName(file1);	
				// 파일 타입 정보를 가져옴
				fileType = multi.getContentType(file1);
				// input file name에 해당하는 실재 파일을 가져옴
				File file = multi.getFile(file1);
				// 그 파일 객체의 크기를 알아냄
				fileSize = file.length();			
				
			}
			System.out.println("파일이름 : " + fileName1);
			IDao dao = sqlSession.getMapper(IDao.class);
			dao.write(bNum, name, subject, fileName1, bContent, id, "MVC_BOARD_SEQ4.NEXTVAL", "MVC_BOARD_SEQ4.CURRVAL");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return "redirect:list?bNum=" + bNum;
	}
	
	@RequestMapping("/filecontent_view")
	public String filecontent_view(HttpServletRequest request, Model model) {
		String bId = request.getParameter("bId");
		String bkind = request.getParameter("kind");
		String checkname = request.getParameter("name");
		String bNum = request.getParameter("bNum");

		IDao dao = sqlSession.getMapper(IDao.class);
		
		if(bkind.equals("view")) {
			dao.upHit(bId);
		} 
		
		session = request.getSession();
		session.setAttribute("checkname", checkname);
		
		model.addAttribute("content_view", dao.contentView(bId, bkind, bNum));
		return "file_view";
	}	
	
	@RequestMapping("/fileDown")
	public String fileDown(HttpServletRequest request, Model model) {
		
		return "fileDown";
	}
	
	@RequestMapping("/filemodify_view")
	public String filemodify_view(HttpServletRequest request, Model model) {
		String bId = request.getParameter("bId");
		String bkind = request.getParameter("kind");
		String checkname = request.getParameter("name");
		String bNum = request.getParameter("bNum");

		IDao dao = sqlSession.getMapper(IDao.class);
		
		if(bkind.equals("view")) {
			dao.upHit(bId);
		} 
		
		session = request.getSession();
		session.setAttribute("checkname", checkname);
		
		model.addAttribute("content_view", dao.contentView(bId, bkind, bNum));
		return "filemodify_view";
	}	
	
	@RequestMapping("/filemodify")
	public String filemodify(HttpServletRequest request, Model model) {
		String uploadPath = request.getRealPath("/resources/fileFolder");
		
		int maxSize = 1024 * 1024 * 10; // 한번에 올릴 수 있는 파일 용량 : 10M로 제한
		
		String bId = "";
		String name = "";
		String subject = "";
		String bNum = "";
		String fileName1 = ""; // 중복처리된 이름
		String originalName1 = ""; // 중복 처리전 실제 원본 이름
		long fileSize = 0; // 파일 사이즈
		String fileType = ""; // 파일 타입
		String bContent = "";
		
		MultipartRequest multi = null;
		
		try{
			// request,파일저장경로,용량,인코딩타입,중복파일명에 대한 기본 정책
			multi = new MultipartRequest(request,uploadPath,maxSize,"utf-8",new DefaultFileRenamePolicy());
			// form내의 input name="name" 인 녀석 value를 가져옴
			bNum = multi.getParameter("bNum");
			name = multi.getParameter("bName");
			// name="subject" 인 녀석 value를 가져옴
			bContent = multi.getParameter("bContent");
			bId = multi.getParameter("bId");
			subject = multi.getParameter("bTitle");
			// 전송한 전체 파일이름들을 가져옴
			Enumeration files = multi.getFileNames();
			
			while(files.hasMoreElements()){
				// form 태그에서 <input type="file" name="여기에 지정한 이름" />을 가져온다.
				String file1 = (String)files.nextElement(); // 파일 input에 지정한 이름을 가져옴
				// 그에 해당하는 실재 파일 이름을 가져옴
				originalName1 = multi.getOriginalFileName(file1);
				// 파일명이 중복될 경우 중복 정책에 의해 뒤에 1,2,3 처럼 붙어 unique하게 파일명을 생성하는데
				// 이때 생성된 이름을 filesystemName이라 하여 그 이름 정보를 가져온다.(중복에 대한 처리)
				fileName1 = multi.getFilesystemName(file1);	
				// 파일 타입 정보를 가져옴
				fileType = multi.getContentType(file1);
				// input file name에 해당하는 실재 파일을 가져옴
				File file = multi.getFile(file1);
				// 그 파일 객체의 크기를 알아냄
				fileSize = file.length();
			}
			IDao dao = sqlSession.getMapper(IDao.class);
			dao.modify(bNum, bId, name, subject, bContent, fileName1);
		}catch(Exception e){
			e.printStackTrace();
		}	
		
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
	
	public BPageInfo articlePage(int curPage, int nTotalPage, String bNum) {
		
		// 총 페이지 수
		int totalPage = nTotalPage / listCount;
		if(nTotalPage % listCount > 0) {
			totalPage++;
		}
		int myCurPage = curPage;
		if(myCurPage > totalPage) {
			myCurPage = totalPage;
		}
		if(myCurPage < 1) {
			myCurPage = 1;
		}
		
		// 시작 페이지
		int startPage = ((myCurPage - 1) / pageCount) * pageCount + 1;
		
		// 끝 페이지
		int endPage = startPage + pageCount - 1;
		if(endPage > totalPage) {
			endPage = totalPage;
		}
		
		BPageInfo pinfo = new BPageInfo();
		pinfo.setTotalCount(nTotalPage);
		pinfo.setListCount(listCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(myCurPage);
		pinfo.setPageCount(pageCount);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);
		
		return pinfo;
	}
	
	public BPageInfo articlePage(int curPage, int nTotalPage) {
		
		// 총 페이지 수
		int totalPage = nTotalPage / listCount;
		if(nTotalPage % listCount > 0) {
			totalPage++;
		}
		int myCurPage = curPage;
		if(myCurPage > totalPage) {
			myCurPage = totalPage;
		}
		if(myCurPage < 1) {
			myCurPage = 1;
		}
		
		// 시작 페이지
		int startPage = ((myCurPage - 1) / pageCount) * pageCount + 1;
		
		// 끝 페이지
		int endPage = startPage + pageCount - 1;
		if(endPage > totalPage) {
			endPage = totalPage;
		}
		
		BPageInfo pinfo = new BPageInfo();
		pinfo.setTotalCount(nTotalPage);
		pinfo.setListCount(listCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(myCurPage);
		pinfo.setPageCount(pageCount);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);
		
		return pinfo;
	}
}


