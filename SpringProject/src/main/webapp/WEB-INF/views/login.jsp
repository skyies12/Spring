<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
    <script src="https://apis.google.com/js/api:client.js"></script>
    <link href="resources/css/normalize.css" rel="stylesheet">
    <link href="resources/css/login.css" rel="stylesheet">
    <title>K.SW Project</title>
    <style>
       
</style>
</head>
<body>
      <div id="wrap">
       <!-- header -->
        <header>
          <div class="nav-wrap">
            <div class="nav-bar">
             <div id="lsbtn">
         	<button class="btn btn-outline-danger " id="fblogout" onclick="fbLogout();" style="display:none; ">로그아웃</button>
         	<button class="btn btn-outline-danger" id="kakaologout" onclick="signOut();" style="display:none; ">로그아웃</button>
         	<button style="display:none; " id="googlelogout" type="button" class="btn btn-outline-danger" onclick="gsignOut();">로그아웃</button>
            <a id="gnbLogin" href="#" class="btn btn-outline-danger" style="display:none;">로그인</a>
            <button id="jbtn" onclick="JavaScript:location = 'join'"class="btn btn-outline-danger">회원가입</button>
         </div>
            <!-- 메뉴 -->
              <button type="button" class="button-wrap" style="display:none">
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
                    <button type="button" class="pc-button" style="display:none">
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
			<form id="reg_frm">
				<div id="idwrap" class="input"><input placeholder="아이디" type="text" name="id" id="id" value="<% if(session.getAttribute("id") != null) {
                																out.println(session.getAttribute("id"));
          																			}%>"></div>
				<div id="pwwrap" class="input"><input placeholder="비밀번호" type="password" name="pw" id="pw"></div>
				<input id="join" type="button" name="id" value="로그인" onclick="submit_ajax()">
				<div id="naverIdLogin">
					<a id="naverIdLogin_loginButton" href="#" role="button">
					<img src="" width=320>네이버 로그인</a>
				</div>
				<div class="texts" id="navertext"></div>
				<div id="login" style="display: block;">
   					 <input type="button" onclick="fbLogin();" value="페이스북 로그인" />
				</div>
				<div id="logout" style="display: none;">	
   					<img id="upic" src=""><br>
   					<span id="uname"></span>
   					<div class="texts" id="facetext"></div>
				</div>
				<div id="kklogin" style="display: block">
					 <input type="button" onclick="loginWithKakao();" value="카카오 로그인" />
				</div>

				<div id="kklogout" style="display: none;">
    					<img id="kkupic" src=""><br>
   						<div class="texts" id="kakaotext"></div>
				</div>
				<div id="glogin" >
					<input type="button" onclick="startApp();" value="Google 로그인" />
				</div>
   				 <div id="glogout" style="display: none;">
    				<div class="texts" id="googletext"></div>
   				 </div>
			</form>
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
    <script src="resources/js/login.js"></script>
</body>
</html>