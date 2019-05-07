<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="com.study.spring20.dto.BDto"%>
<%@page import="com.study.spring20.dao.BDao"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap CSS -->
   
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="resources/css/normalize.css" rel="stylesheet">
    <link href="resources/css/style.css" rel="stylesheet">
    <script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
        <script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script>
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
  <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
  <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
  <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote.css" rel="stylesheet">
  <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote.js"></script>
  <link href="resources/css/file_view.css" rel="stylesheet">
    <title>K.SW Project</title>
</head>
<body>
<c:set var="contid" value="${content_view.id }"/>
<% String bNum = request.getParameter("bNum"); 
String kname = (String)session.getAttribute("kname");
String name = (String)session.getAttribute("name");
String nname = (String)session.getAttribute("nname");
String fname = (String)session.getAttribute("fname");
String gname = (String)session.getAttribute("gname");
String num = (String)session.getAttribute("num");
String checkname = (String)session.getAttribute("checkname");
String contid = (String)pageContext.getAttribute("contid");
String id = (String)session.getAttribute("id");
%>
<%
		// post방식에 대한 한글 인코딩 방식 지정 get방식은 서버의 server.xml에서 Connector태그에 URIEncoding="UTF-8" 추가
		request.setCharacterEncoding("UTF-8");
		
		// input type="name" 의 value값을 가져옴
		String filename = request.getParameter("name");
		// input type="subject" 의 value값을 가져옴
		String subject = request.getParameter("subject");
		// 중복방지용으로 만들어져 넘겨진 파일명을 가져옴
		String fileName1 = request.getParameter("fileName1");
		// 본래의 파일명을 가져옴
		String originalName1 = request.getParameter("originalName1");
	%>
      <div id="wrap">
       <!-- header -->
        <header>
          <div class="nav-wrap">
            <div class="nav-bar">
            <div id="lsbtn">
            	 <a id="gnbLogin" href="#" class="btn btn-outline-danger" style="display:none;">로그인</a>
             	<form id="form" action="logout" method="post">
             		<input class="btn btn-outline-danger" type="submit" value="로그아웃">
             		<%if(num.equals("4")) { %>
             			<%if(name.equals("관리자")) { %>
         	 				<button class="btn btn-outline-danger" type="button" onclick="javascript:window.location='member'">멤버 관리</button>
         				<%} else { %>
         	 				<button onclick="javascript:window.location='membermodify'" type="button" class="btn btn-outline-danger">정보수정</button>
         	 			<%} %>
             		<%}%>
				</form>
         </div>

            <!-- 메뉴 -->
              <button type="button" class="button-wrap">
                <span></span>
                <span></span>
                <span></span>
              </button>
            <div class="nav">
            <h2 class="blind">메뉴</h2>
                <div class="menu">
                    <ul>
                        <li><a href="list?bNum=0">공지사항</a></li>
                        <li><a href="list?bNum=1">자유게시판</a></li>
                        <li><a href="list?bNum=2">자료실</a></li>
                        <li><a href="chatlogin">채팅방</a></li>
                    </ul>
                    <button type="button" class="pc-button">
                        <span></span>
                        <span></span>
                        <span></span>
                    </button>
                </div>
            </div>
          </div>
          <div class="sns-wrap">
            <ul>
              <li><a href="#"></a></li>
              <li><a href="#"></a></li>
              <li><a href="#"></a></li>
              <li><a href="#"></a></li>
            </ul>
          </div>
            </div>
        </header>

        <!-- header end -->
        <div class="contents">
        <div id="wrap1">
           	<div class="container" >
				<table class="table table-striped">
				<thead class="thead">
		<tr>
			<th colspan="2" scope="col">
		<% if(bNum.equals("0")) { %>
			<h2>공지사항 게시판</h2>	
		<% 
		} else if(bNum.equals("1")) {%>
			<h2>등업게시판</h2>
		<%} else if(bNum.equals("2")) { %>
			<h2>자유게시판</h2>
		<%} else {%>
			<h2>자료실</h2>
		<%}%>
			</th>
		</tr>
		</thead>
		<tr>
			<td>${content_view.bTitle }</td>
			<td>${content_view.bName }</td>
		</tr>
		<tr>
			<td style="height : 300px; text-align:left; padding:40px 0 0 40px;" colspan="2">${content_view.bContent }</td>
		</tr>
		<tr>
			
		</tr>
		<tr>
			<td>조회수 ${content_view.bHit }</td>
			<td>첨부파일 <a id="downA" href="#"><%=fileName1%></a></td>
		</tr>
		<tr>
			<td colspan="2">
				<div id="wbtn">
		
		<%if(checkname.equals("관리자")) {%> 
			<a class="wbtn" href="filemodify_view?bNum=<%=bNum %>&bId=${content_view.bId }&kind=modify">수정</a> &nbsp;&nbsp;
			<a class="wbtn" href="delete?bNum=<%=bNum %>&bId=${content_view.bId }">삭제</a> &nbsp;&nbsp;
			<a class="wbtn" href="list?bNum=<%=bNum %>&page=<%= session.getAttribute("cpage")%>">목록</a> &nbsp;&nbsp;
			<a class="wbtn" href="reply_view?bNum=<%=bNum %>&bId=${content_view.bId }">답변</a>	
		<%} else { %>
			<%if(bNum.equals("0")) { %>
				<a class="wbtn" href="list.do?bNum=<%=bNum %>&page=<%= session.getAttribute("cpage")%>">목록</a> &nbsp;&nbsp;
			<%} else { %>
				<%if(num.equals("0")) {%>
					<a class="wbtn" href="list?bNum=<%=bNum %>&page=<%= session.getAttribute("cpage")%>">목록</a> &nbsp;&nbsp;
				<%} else if(num.equals("1")) {%>
					<a class="wbtn" href="list?bNum=<%=bNum %>&page=<%= session.getAttribute("cpage")%>">목록</a> &nbsp;&nbsp;
				<%} else if(num.equals("2")) {%>
					<a class="wbtn" href="list?bNum=<%=bNum %>&page=<%= session.getAttribute("cpage")%>">목록</a> &nbsp;&nbsp;
				<%} else if(num.equals("3")) {%>
					<a class="wbtn" href="list?bNum=<%=bNum %>&page=<%= session.getAttribute("cpage")%>">목록</a> &nbsp;&nbsp;
				<%} else if(num.equals("4")) {%>
					<%if(id.equals(contid)) {%>
						<a class="wbtn" href="filemodify_view?bNum=<%=bNum %>&bId=${content_view.bId }&kind=modify">수정</a> &nbsp;&nbsp;
						<a class="wbtn" href="delete?bNum=<%=bNum %>&bId=${content_view.bId }">삭제</a> &nbsp;&nbsp;
					<%}%>
					<a class="wbtn" href="list?bNum=<%=bNum %>&page=<%= session.getAttribute("cpage")%>">목록</a> &nbsp;&nbsp;
					<a class="wbtn" href="reply_view?bNum=<%=bNum %>&bId=${content_view.bId }">답변</a>		
				<%} %>
			<%} %>
		<%} %>		

	
				</div>
			</td>
		</tr>
	</table>
			</div>
        </div>
        </div>
        
        
        <!-- footer -->
        <footer>
          <div class="ft-sns-wrap">
              <ul class="clear">
                <li><a href="#"><img src="resources/image/facebook-on.png" alt="페이스북"></a></li>
                <li><a href="#"><img src="resources/image/instagram-on.png" alt="인스타그램"></a></li>
                <li><a href="#"><img src="resources/image/twiter-on.png" alt="트위터"></a></li>
                <li><a href="#"><img src="resources/image/youtube-on.png" alt="유튜브"></a></li>
              </ul>
            </div>
            <div class="copy-wrap">
             <h3 class="blind">회사정보</h3>
              <p><a href="#">이용약관<span>l</span>법적고지<span>l</span>개인정보처리방침</a></p>
              <p>Copyrightⓒ 2018 K.SW KOREA COMPANY.<br>ALL RIGHTS RESERVED</p>
            </div>
        </footer>
        </div>
        <!-- footer end -->
    <!-- home end -->
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="resources/js/script.js"></script> 
    <script src="resources/js/file_view.js"></script> 
    <script>
 // 영문파일은 그냥 다운로드 클릭시 정상작동하지만 한글파일명을 쿼리문으로 날릴경우 인코딩 문제가 발생할 수 있다. 한글이 깨져 정상작동하지 않을 수 있음
	// 따라서, 쿼리문자열에 한글을 보낼 때는 항상 인코딩을 해서 보내주도록 하자.
	document.getElementById("downA").addEventListener("click", function(event) {
		event.preventDefault(); // a 태그의 기본 동작을 막음
		event.stopPropagation(); // 이벤트의 전파를 막음
		// fileName1을 utf-8로 인코딩한다.
		var fName = encodeURIComponent("<%=fileName1%>");
		// 인코딩된 파일이름을 쿼리문자열에 포함시켜 다운로드 페이지로 이동
		window.location.href = "fileDown?file_name="+fName;
	});
    </script>
</body>
</html>