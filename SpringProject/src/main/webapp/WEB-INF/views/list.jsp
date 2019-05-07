<%@page import="com.study.spring20.MemberDTO"%>
<%@page import="com.study.spring20.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="com.study.spring20.dto.BDto"%>
<%@page import="com.study.spring20.dao.BDao"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date" />

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap CSS -->
   
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="resources/css/normalize.css" rel="stylesheet">
    <link href="resources/css/style.css" rel="stylesheet">
     <link href="resources/css/list.css" rel="stylesheet">
    <script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
     <script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script>
         <script src="http://code.jquery.com/jquery.js"></script>
    <title>K.SW Project</title>
</head>
<body>

<% String bNum = request.getParameter("bNum");
String naver = (String)session.getAttribute("naver");
String selectNum = (String)session.getAttribute("selectNum");
String bNum1 = (String)session.getAttribute("bNum");
String face = (String)session.getAttribute("face");
String kakao = (String)session.getAttribute("kakao");
String google = (String)session.getAttribute("google");
String db = (String)session.getAttribute("db");
String num = (String)session.getAttribute("num");

String kname = (String)session.getAttribute("kname");
String name = (String)session.getAttribute("name");
String nname = (String)session.getAttribute("nname");
String fname = (String)session.getAttribute("fname");
String gname = (String)session.getAttribute("gname");
String bDate = (String)pageContext.getAttribute("date");
String id = (String)session.getAttribute("id");
String nrating = (String)session.getAttribute("nrating");

MemberDAO dao = MemberDAO.getInstance();
MemberDTO dto = dao.getMember(id);

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
                        <li><a href="list?bNum=0&num=4">공지사항</a></li>
                        <li><a href="list?bNum=1">등업게시판</a></li>
                        <li><a href="list?bNum=2">자유게시판</a></li>
                        <li><a href="list?bNum=3">자료실</a></li>
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
            <div class="container">
		<div id="title1">
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
		</div>
			<form id="select" action="select?bNum=<%=bNum1 %>" method="post">
				<select name="serchcheck">
					<option value="0">제목</option>				
					<option value="1">내용</option>				
					<option value="2">작성자</option>				
				</select>
				<input id="inputtext" type="text" name="bTitle"><input id="search" type="submit" value="검색">
			</form>
		<table class="table">
		<thead class="thead">
		<tr>
		<%if(bNum.equals("0")) { %>
			<th scope="col">공지</th>
		<%} else { %>
			<th scope="col">번호</th>
		<%} %>
             <th scope="col">작성자</th>
             <th scope="col">제목</th>
              <th scope="col">작성일</th>
              <th scope="col">조회</th>
		</tr>
		</thead>
		<tbody>
		<% if(bNum.equals("0")) { %>
			<c:forEach items="${list }" var="dto">
				<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today" />  
				<fmt:formatDate value="${dto.bDate}" pattern="yyyy-MM-dd" var="write_dt"/>
			<tr>
			<td>공지</td>
			<td>${dto.bName }</td>
			<td class="btttd">
				<c:forEach begin="1" end="${dto.bIndent }">→</c:forEach>
					<% if(num.equals("0")) {%>
						<a style="font-weight:700;" id="title" href="content_view?name=<%= nname %>&bNum=<%=bNum %>&bId=${dto.bId }&kind=view">${dto.bTitle }<c:if test="${today <= write_dt }"> &nbsp;<span class="badge badge-danger">New</span></c:if></a>
					<%  } else if(num.equals("1")) {%>
						<a style="font-weight:700;" id="title" href="content_view?name=<%= fname %>&bNum=<%=bNum %>&bId=${dto.bId }&kind=view">${dto.bTitle }<c:if test="${today <= write_dt }"> &nbsp;<span class="badge badge-danger">New</span></c:if></a>
					<% } else if(num.equals("2")) {%>
						<a style="font-weight:700;" id="title" href="content_view?name=<%= kname %>&bNum=<%=bNum %>&bId=${dto.bId }&kind=view">${dto.bTitle }<c:if test="${today <= write_dt }"> &nbsp;<span class="badge badge-danger">New</span></c:if></a>
					<% } else if(num.equals("3")) {%>
						<a style="font-weight:700;" id="title" href="content_view?name=<%= gname %>&bNum=<%=bNum %>&bId=${dto.bId }&kind=view">${dto.bTitle }<c:if test="${today <= write_dt }"> &nbsp;<span class="badge badge-danger">New</span></c:if></a>
					<%} else if(num.equals("4")) {%>
						<a style="font-weight:700;" id="title" href="content_view?name=<%= name %>&bNum=<%=bNum %>&bId=${dto.bId }&kind=view">${dto.bTitle }<c:if test="${today <= write_dt }"> &nbsp;<span class="badge badge-danger">New</span></c:if></a>
					<%}%>
			</td>
			<td>${dto.bDate}</td>
			<td>${dto.bHit}</td>
		</tr>
		</c:forEach>
		<% } else if(bNum.equals("1")) { %>
			<c:forEach items="${list}" var="dto">
				<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today" />  
				<fmt:formatDate value="${dto.bDate}" pattern="yyyy-MM-dd" var="write_dt"/>
		<tr>
			<td>${dto.bId }</td>
			<td>${dto.bName }</td>
			<td class="btttd">
				<c:forEach begin="1" end="${dto.bIndent }">→</c:forEach>
					<% if(num.equals("0")) {%>
						<a id="title" href="content_view?name=<%= nname %>&bNum=<%=bNum %>&bId=${dto.bId }&kind=view">${dto.bTitle }<c:if test="${today <= write_dt }"> &nbsp;<span class="badge badge-danger">New</span></c:if></a>
					<%  } else if(num.equals("1")) {%>
						<a id="title" href="content_view?name=<%= fname %>&bNum=<%=bNum %>&bId=${dto.bId }&kind=view">${dto.bTitle }<c:if test="${today <= write_dt }"> &nbsp;<span class="badge badge-danger">New</span></c:if></a>
					<% } else if(num.equals("2")) {%>
						<a id="title" href="content_view?name=<%= kname %>&bNum=<%=bNum %>&bId=${dto.bId }&kind=view">${dto.bTitle }<c:if test="${today <= write_dt }"> &nbsp;<span class="badge badge-danger">New</span></c:if></a>
					<% } else if(num.equals("3")) {%>
						<a id="title" href="content_view?name=<%= gname %>&bNum=<%=bNum %>&bId=${dto.bId }&kind=view">${dto.bTitle }<c:if test="${today <= write_dt }"> &nbsp;<span class="badge badge-danger">New</span></c:if></a>
					<%} else if(num.equals("4")) {%>
						<a id="title" href="content_view?name=<%= name %>&bNum=<%=bNum %>&bId=${dto.bId }&kind=view">${dto.bTitle }<c:if test="${today <= write_dt }"> &nbsp;<span class="badge badge-danger">New</span></c:if></a>
					<%}%>
			</td>
			<td>${dto.bDate}</td>
			<td>${dto.bHit}</td>
		</tr>
		</c:forEach>
		<% } else if(bNum.equals("2")) { %>
			<c:forEach items="${list }" var="dto">
				<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today" />  
				<fmt:formatDate value="${dto.bDate}" pattern="yyyy-MM-dd" var="write_dt"/>
			<tr>
			<td>${dto.bId }</td>
			<td>${dto.bName }</td>
			<td class="btttd">
				<c:forEach begin="1" end="${dto.bIndent }">→</c:forEach>
					<% if(num.equals("0")) {%>
						<a id="title" href="content_view?name=<%= nname %>&bNum=<%=bNum %>&bId=${dto.bId }&kind=view">${dto.bTitle }<c:if test="${today <= write_dt }"> &nbsp;<span class="badge badge-danger">New</span></c:if></a>
					<%  } else if(num.equals("1")) {%>
						<a id="title" href="content_view?name=<%= fname %>&bNum=<%=bNum %>&bId=${dto.bId }&kind=view">${dto.bTitle }<c:if test="${today <= write_dt }"> &nbsp;<span class="badge badge-danger">New</span></c:if></a>
					<% } else if(num.equals("2")) {%>
						<a id="title" href="content_view?name=<%= kname %>&bNum=<%=bNum %>&bId=${dto.bId }&kind=view">${dto.bTitle }<c:if test="${today <= write_dt }"> &nbsp;<span class="badge badge-danger">New</span></c:if></a>
					<% } else if(num.equals("3")) {%>
						<a id="title" href="content_view?name=<%= gname %>&bNum=<%=bNum %>&bId=${dto.bId }&kind=view">${dto.bTitle }<c:if test="${today <= write_dt }"> &nbsp;<span class="badge badge-danger">New</span></c:if></a>
					<%} else if(num.equals("4")) {%>
						<a id="title" href="content_view?name=<%= name %>&bNum=<%=bNum %>&bId=${dto.bId }&kind=view">${dto.bTitle }<c:if test="${today <= write_dt }"> &nbsp;<span class="badge badge-danger">New</span></c:if></a>
					<%}%>
			</td>
			<td>${dto.bDate}</td>
			<td>${dto.bHit}</td>
		</tr>
		</c:forEach>
		<% } else if(bNum.equals("3")) { %>
			<c:forEach items="${list }" var="dto">
				<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today" />  
				<fmt:formatDate value="${dto.bDate}" pattern="yyyy-MM-dd" var="write_dt"/>
			<tr>
			<td>${dto.bId }</td>
			<td>${dto.bName }</td>
			<td class="btttd">
				<c:forEach begin="1" end="${dto.bIndent }">→</c:forEach>
					<% if(num.equals("0")) {%>
					<a class="bTitle" id="title" href="filecontent_view?name=<%= nname %>&fileName1=${dto.bFile }&bNum=<%=bNum %>&bId=${dto.bId }&kind=view">${dto.bTitle }<c:if test="${today <= write_dt }"> &nbsp;<span class="badge badge-danger">New</span></c:if></a>
				<%  } else if(num.equals("1")) {%>
					<a class="bTitle" id="title" href="filecontent_view?name=<%= fname %>&fileName1=${dto.bFile }&bNum=<%=bNum %>&bId=${dto.bId }&kind=view">${dto.bTitle }<c:if test="${today <= write_dt }"> &nbsp;<span class="badge badge-danger">New</span></c:if></a>
				<% } else if(num.equals("2")) {%>
					<a class="bTitle" id="title" href="filecontent_view?name=<%= kname %>&fileName1=${dto.bFile }&bNum=<%=bNum %>&bId=${dto.bId }&kind=view">${dto.bTitle }<c:if test="${today <= write_dt }"> &nbsp;<span class="badge badge-danger">New</span></c:if></a>
				<% } else if(num.equals("3")) {%>
					<a class="bTitle" id="title" href="filecontent_view?name=<%= gname %>&fileName1=${dto.bFile }&bNum=<%=bNum %>&bId=${dto.bId }&kind=view">${dto.bTitle }<c:if test="${today <= write_dt }"> &nbsp;<span class="badge badge-danger">New</span></c:if></a>
				<%} else if(num.equals("4")) {%>
					<a class="bTitle" id="title" href="filecontent_view?name=<%= name %>&fileName1=${dto.bFile }&bNum=<%=bNum %>&bId=${dto.bId }&kind=view">${dto.bTitle }<c:if test="${today <= write_dt }"> &nbsp;<span class="badge badge-danger">New</span></c:if></a>
				<%}%>
			</td>
			<td>${dto.bDate}</td>
			<td>${dto.bHit}</td>
		</tr>
		</c:forEach>
		<% } %>
		<tr>
			<td colspan="5">
			<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
			<div class="btn-group mr-2" role="group" aria-label="First group">
				<!-- 처음 -->
				<c:choose>
					<c:when test="${(page.curPage - 1) < 1}">
						<button type="button" class="btn btn-secondary check">처음</button>
					</c:when>
					<c:otherwise>
						<a class="btn btn-secondary" href="list?bNum=<%= bNum %>&page=1">처음</a>
					</c:otherwise>
				</c:choose>
				<!-- 이전 -->
				<c:choose>
					<c:when test="${(page.curPage - 1) < 1}">
					<button type="button" class="btn btn-secondary check">&lt; 이전</button>
					</c:when>
					<c:otherwise>
						<a class="btn btn-secondary" href="list?bNum=<%= bNum %>&page=${page.curPage - 1}">&lt; 이전</a>
					</c:otherwise>
				</c:choose>
				</div>
				<!-- 개별 페이지 -->
				<div class="btn-group mr-2" role="group" aria-label="Second group">
				<c:forEach var="fEach" begin="${page.startPage }" end="${page.endPage }" step="1">
					<c:choose>
						<c:when test="${page.curPage == fEach }">
							<button type="button" class="btn btn-secondary">${fEach}</button>
						</c:when>
						
						<c:otherwise>
							<a class="btn btn-secondary" href="list?bNum=<%= bNum %>&page=${fEach }">${fEach }</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				</div>
				
				<!-- 다음 -->
				  <div class="btn-group" role="group" aria-label="Third group">
				<c:choose>
					<c:when test="${(page.curPage + 1) > page.totalPage }">
						<button type="button" class="btn btn-secondary check">다음 &gt;</button>
					</c:when>
					<c:otherwise>
						<a class="btn btn-secondary" href="list?bNum=<%= bNum %>&page=${page.curPage + 1}">다음 &gt;</a>
					</c:otherwise>
				</c:choose>
				
				<!-- 끝 -->
				<c:choose>
					<c:when test="${page.curPage == page.totalPage }">
						<button type="button" class="btn btn-secondary check">끝</button>
					</c:when>
					<c:otherwise>
						<a class="btn btn-secondary" href="list?bNum=<%= bNum %>&page=${page.totalPage }">끝</a>
					</c:otherwise>
				</c:choose>
				</div>
				</div>
			</td>
		</tr>
		<tr>
			<%if(bNum.equals("3")) { %>
				<td colspan="5"><a id="write" href="upload_view?bNum=<%= bNum %>" class="btn btn-outline-danger write">작성</a></td>
			<%} else if(bNum.equals("1")) { %>
				<td colspan="5"><a id="write" href="write_view?bNum=<%= bNum %>" class="btn btn-outline-danger write">작성</a></td>
			<%} else if(bNum.equals("2")) { %>
				<td colspan="5"><a id="write" href="write_view?bNum=<%= bNum %>" class="btn btn-outline-danger write">작성</a></td>
			<%} else if(bNum.equals("0")) { %>
				<td colspan="5"><a id="write" href="write_view?bNum=<%= bNum %>" class="btn btn-outline-danger write">작성</a></td>	
			<% }  %>
		</tr>
		</tbody>
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
    <script src="resources/js/login.js"></script>
    <script>
    var num = "<%=num%>";
    var name = "<%=name%>";
    var bNum = "<%=bNum%>";
    console.log(name);
		if(num === "0") {
			 $(".bTitle").click(function() {
				alert("회원가입 후 사용이 가능합니다.");
				$(".bTitle").attr("href","#");
			 });
			 $("#write").click(function() {
				alert("회원가입 후 사용이 가능합니다.");
				$("#write").attr("href","#");
			});
		} else if(num === "1") {
			 $(".bTitle").click(function() {
				alert("회원가입 후 사용이 가능합니다.");
				$(".bTitle").attr("href","#");
			});
			 $("#write").click(function() {
					alert("회원가입 후 사용이 가능합니다.");
					$("#write").attr("href","#");
				});
		} else if(num === "2") {
			 $(".bTitle").click(function() {
				alert("회원가입 후 사용이 가능합니다.");
				$(".bTitle").attr("href","#");
			});
			 $("#write").click(function() {
					alert("회원가입 후 사용이 가능합니다.");
					$("#write").attr("href","#");
				});
		} else if(num === "3") {
			 $(".bTitle").click(function() {
				alert("회원가입 후 사용이 가능합니다.");
				$(".bTitle").attr("href","#");
			});
			 $("#write").click(function() {
				alert("회원가입 후 사용이 가능합니다.");
				$("#write").attr("href","#");
			});
		} else {
			if(bNum == "0") {
				if(name != "관리자") {
					$("#write").click(function() {
						alert("관리자만 작성이 가능합니다.");
						$("#write").attr("href","#");
					});	
				}		
			}
		}

    	$(document).ready(function() {	
    		$("#serchcheck").focus(function() {
    			if($("#serchcheck option:selected").val() == "0") {
					$("#bbcontent").css("display", "none");
					$("#bbname").css("display", "none");
					$("#select").css("display", "block");
				} else if($("#serchcheck option:selected").val() == "1") {
					$("#bbcontent").css("display", "block");
					$("#bbname").css("display", "none");
					$("#select").css("display", "none");
				} else if($("#serchcheck option:selected").val() == "2") {
					$("#bbcontent").css("display", "none");
					$("#bbname").css("display", "block");
					$("#select").css("display", "none");
				}
    		});
    	});
    	
    </script>
	<script>	
		var naverLogin = new naver.LoginWithNaverId(
			{

				clientId: "IEzLjz3gsY3kDLocAh4B",
				callbackUrl: "http://localhost:8081/project/login.jsp",
				isPopup: false,
			}
		);
		/* (4) 네아로 로그인 정보를 초기화하기 위하여 init을 호출 */
		naverLogin.init();
		
		/* (4-1) 임의의 링크를 설정해줄 필요가 있는 경우 */
		$("#gnbLogin").attr("href", naverLogin.generateAuthorizeUrl());

		/* (5) 현재 로그인 상태를 확인 */
		window.addEventListener('load', function () {
			naverLogin.getLoginStatus(function (status) {
				if (status) {
					/* (6) 로그인 상태가 "true" 인 경우 로그인 버튼을 없애고
					   사용자 정보를 출력합니다. */
					setLoginStatus();
				} else {
				}
			});
		});

		/* (6) 로그인 상태가 "true" 인 경우 로그인 버튼을 없애고
		   사용자 정보를 출력합니다. */
		function setLoginStatus() {
			console.log(naverLogin.user);
			var uid = naverLogin.user.getId();
			var profileImage = naverLogin.user.getProfileImage();
			var uName = naverLogin.user.getName();
			var nickName = naverLogin.user.getNickName();
			var eMail = naverLogin.user.getEmail();
			
			var allData = { "nname": uName, "num": 0};

		      $.ajax({
			        url : "param",
			        type : "post",
			        dataType : "text",
			        data : allData,
					success : function(data) {
						$("#idwrap").css("display", "none");
						$("#pwwrap").css("display", "none");
						$("#join").css("display", "none");
						$("#login").css("display", "none");
						$("#kklogin").css("display", "none");
						$("#form").css("display", "none");
						$("#naverIdLogin_loginButton").html(
								'<br><br><img src="' + profileImage + 
								'" height=50 /> <p>' + uName + '님 반갑습니다.</p>');
						$("#gnbLogin").html("로그아웃");
						$("#gnbLogin").css("display", "inline-block");
						$("#gnbLogin").css("line-height", "40px");
						$("#gnbLogin").attr("href", "#");
						/* (7) 로그아웃 버튼을 설정하고 동작을 정의합니다. */
						$("#gnbLogin").click(function () {
							naverLogin.logout();
							location.href="login";
						});
					}
		      });
		}
		history.pushState(null, null, location.href);
	    window.onpopstate = function () {
	        history.go(1);
	};
	
	</script>
</body>
</html>