<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String name = (String)session.getAttribute("name");
	String id = (String)session.getAttribute("id");
	// db 로그인
	String nickname = request.getParameter("nickname");
	// 네이버 로그인
	String nname = request.getParameter("nname");
	String nimage = request.getParameter("nimage");
	String nrating = request.getParameter("nrating");
	// 페북 로그인
	String fname = request.getParameter("fname");
	String fimage = request.getParameter("fimage");
	// 카카오 로그인
	String kname = request.getParameter("kname");
	String kimage = request.getParameter("kimage");
	// 구글 로그인
	String gname = request.getParameter("gname");
	String gimage = request.getParameter("gimage");
	
	String num = request.getParameter("num");
	
	String face = request.getParameter("face");
	
	session.setAttribute("face", face);
	session.setAttribute("nname", nname);
	session.setAttribute("fname", fname);
	session.setAttribute("kname", kname);
	session.setAttribute("gname", gname);
	session.setAttribute("num", num);
	
	session.setAttribute("nrating", nrating);
%>
</body>
</html>