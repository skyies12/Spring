<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="com.study.spring20.dto.BDto"%>
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
  <link href="resources/css/reply_view.css" rel="stylesheet">
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
String contid = (String)pageContext.getAttribute("contid");
String id = (String)session.getAttribute("id");
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
                        <li><a href="list.do?bNum=0">공지사항</a></li>
                        <li><a href="list.do?bNum=1">등업게시판</a></li>
                        <li><a href="list.do?bNum=2">자유게시판</a></li>
                        <li><a href="list.do?bNum=3">자료실</a></li>
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
		<form name="reg_frm" action="reply?bNum=<%=bNum %>" method="post">	
			<input type="hidden" name="bId" value="${reply_view.bId }">
			<input type="hidden" name="bGroup" value="${reply_view.bGroup }">
			<input type="hidden" name="bStep" value="${reply_view.bStep }">
			<input type="hidden" name="bIndent" value="${reply_view.bIndent }">
		<tr style="display:none;">
				<td>게시판</td>
				<td><input type="text" name="id" value="<%=id %>" size="50"></td>
			</tr>
		<tr style="display:none">
			<td>번호</td>
			<td>${reply_view.bId }</td>
		</tr>
		<tr style="display:none">
			<td>히트</td>
			<td>${reply_view.bHit }</td>
		</tr>
			<% if(num.equals("0")) {%>
				<td class="cname">이름</td>
				<td><input type="text" name="bName" size="40" value="<%=nname%>" readonly="readonly"></td>
			<%  } else if(num.equals("1")) {%>
				<td  class="cname">이름</td>
				<td><input type="text" name="bName" size="40" value="<%=fname%>" readonly="readonly"></td>
			<% } else if(num.equals("2")) {%>
				<td  class="cname">이름</td>
				<td><input type="text" name="bName" size="40" value="<%=kname%>" readonly="readonly"></td>
			<% } else if(num.equals("3")) {%>
				<td  class="cname">이름</td>
				<td><input type="text" name="bName" size="40" value="<%=gname%>" readonly="readonly"></td>
			<%} else if(num.equals("4")) {%>
				<td  class="cname">이름</td>
				<td><input type="text" name="bName" size="40" value="<%=name%>" readonly="readonly"></td>
			<%}%>
			</tr>
			<tr>
				<td  class="cname">제목</td>
				<td><input id="context" type="text" name="bTitle" size="40" placeholder="게시글 제목을 입력하세요"></td>
			</tr>
			<tr>
				<td  class="cname">내용</td>
				<td><textarea style="resize: none;" name="bContent" id="summernote" rows="10" cols="100" placeholder="내용을 입력하세요"></textarea></td>
			<script>
		      $('#summernote').summernote({
		    	  placeholder : "내용을 입력하세요",
		    	  tabsize: 2,
		        height: 300
		      });
		    </script>
            </td>
			</tr>
			<tr>
				<td id="td" colspan="2">
					<div id="wbtn">
						<input  class="wbtn" type="button" value="답변" onclick="form_check()">&nbsp;&nbsp;
						<a  class="wbtn" href="javascript:history.go(-1)">취소</a>
					</div>
				</td>
			</tr>
		</form>
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
    <script src="resources/js/reply_view.js"></script> 
</body>
</html>