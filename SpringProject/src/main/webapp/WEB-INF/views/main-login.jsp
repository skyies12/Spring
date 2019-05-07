<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
<%
	String name = (String)session.getAttribute("name");
	String id = (String)session.getAttribute("id");
	// db 로그인
	String nickname = request.getParameter("nickname");
	// 네이버 로그인
	String nname = request.getParameter("nname");
	String nimage = request.getParameter("nimage");
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
	
	session.setAttribute("nname", nname);
	session.setAttribute("fname", fname);
	session.setAttribute("kname", kname);
	session.setAttribute("num", num);
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
      <link href="resources/css/normalize.css" rel="stylesheet">
   	<script src="https://apis.google.com/js/api:client.js"></script>
    <link href="resources/css/style.css" rel="stylesheet">
    <link href="resources/css/main-login.css" rel="stylesheet">
    <title>K.SW Project</title>
</head>
<body>
      <div id="wrap">
       <!-- header -->
        <header>
         <div id="lsbtn">
         	 <form id="form" action="logout" method="post">
         	 <%if(name.equals("관리자")) { %>
         	 	<input id="dblogout" class="btn btn-outline-danger" type="submit" value="로그아웃">
         	 	<button class="btn btn-outline-danger" type="button" onclick="javascript:window.location='member'">멤버 관리</button>
         	 <%} else { %>
         	 	<input id="dblogout" class="btn btn-outline-danger" type="submit" value="로그아웃">
         	 	<button onclick="javascript:window.location='membermodify'" type="button" class="btn btn-outline-danger">정보수정</button>
         	 <%} %>
			</form>
         </div>
          <div class="nav-wrap">
            <div class="nav-bar">
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
            <div id="content">
				<div id="texts">
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
        <script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script>
    <script src="resources/js/script.js"></script>  
    <script src="resources/js/main-login.js"></script>  
	<script>
	var admin = "<%=name%>";
	if(admin == "관리자") {
		alert("관리자로 로그인하셨습니다.");
	} else {
		alert(admin + "님 환영합니다.");
	}
	</script>
</body>
</html>