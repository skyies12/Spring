<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.*, java.io.*"
%><%
    // 생성된 토큰 받음
    String g_recaptcha_response = request.getParameter("g-recaptcha-response");
    System.out.println(g_recaptcha_response);
    
    // 토큰과 보안키를 가지고 성공 여부를 확인 함
    HttpURLConnection conn = (HttpURLConnection) new URL("https://www.google.com/recaptcha/api/siteverify").openConnection();
    String params = "secret=6LcdI5gUAAAAAJHYyA_QwBzQQSjXTkia2JhDXKGW" + "&response=" + g_recaptcha_response;
    conn.setRequestMethod("POST");
    conn.setDoOutput(true);
    DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
    wr.writeBytes(params);
    wr.flush();
    wr.close();
    
    // 결과코드 확인(200 : 성공)
    int responseCode = conn.getResponseCode();
    StringBuffer responseBody = new StringBuffer();
    if (responseCode == 200) {
        
        // 데이터 추출
        BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(bis));
        String line;
        while ((line = reader.readLine()) != null) {
            responseBody.append(line);
        }
        bis.close();
        
        // JSON으로 변환 하여야 하지만 기본 모듈에서 처리하기위하여 아래와 같이 진행 합니다
        if(responseBody.toString().indexOf("\"success\": true") > -1)
            System.out.println("인증 되었습니다...");
        	
    }
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="resources/css/normalize.css" rel="stylesheet">
    <link href="resources/css/style.css" rel="stylesheet">
    <link href="resources/css/join.css" rel="stylesheet">
    <title>K.SW Project</title>
</head>
<body>
      <div id="wrap">
       <!-- header -->
        <header>
          <div class="nav-wrap">
            <div class="nav-bar">
            <div id="lsbtn">
             <button onclick="JavaScript:location = 'login'" type="button" class="btn btn-outline-danger"><a href="#">로그인</a></button>
             <button onclick="JavaScript:location = 'join'" type="button" class="btn btn-outline-danger"><a href="#">회원가입</a></button>
         </div>
            <!-- 메뉴 -->
              <button type="button" class="button-wrap"  style="display:none;">
                <span></span>
                <span></span>
                <span></span>
              </button>
            <div class="nav">
            <h2 class="blind">메뉴</h2>
                <div class="menu">
                    <ul>
                        <li><a href="list.do?bNum=0">공지사항</a></li>
                        <li><a href="list.do?bNum=1">자유게시판</a></li>
                        <li><a href="list.do?bNum=2">자료실</a></li>
                        <li><a href="chatlogin.jsp">다중 채팅방</a></li>
                    </ul>
                    <button type="button" class="pc-button" style="display:none;">
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
		<div class="font">아이디</div>
		<div id="idwrap" class="input"><input type="text" name="id" id="id"></div>
		<div class="text"><font name="id" color="red"></font></div>
		<div class="font">비밀번호</div>
		<div id="pwwrap" class="input"><input type="password" name="pw" id="pw"></div>
		<div class="text"><font name="pw"  color="red"></font></div>
		<div class="font">비밀번호 재확인</div>
		<div id="pwchwrap" class="input"><input type="password" name="pw_check" id="pw_check"></div>
		<div class="text"><font name="pw_check" color="red"></font> </div>
		<div class="font">이름</div>
		<div id="namewrap" class="input"><input type="text" name="name" id="name"></div>
		<div class="text"><font name="name" color="red"></font></div>
		<div class="font">생년월일</div>
		<div id="birthwrap">
			<div id="yearwrap"><input class="year" type="text" name="year" placeholder="년(4자)"></div> 
				<div id="monthwrap"><select class="month" name="month">
        			<option selected value="month">월</option>
        			<option value="01">1</option>
        			<option value="02">2</option>
        			<option value="03">3</option>
        			<option value="04">4</option>
        			<option value="05">5</option>
        			<option value="06">6</option>
        			<option value="07">7</option>
        			<option value="08">8</option>
        			<option value="09">9</option>
        			<option value="10">10</option>
        			<option value="11">11</option>
        			<option value="12">12</option>
        		</select> 
        		</div>
      	 <div id="daywrap"><input  class="day" type="text" name="day" placeholder="일"></div>
    	</div>
		<div class="text"><font name="birth"  color="red"></font></div>
		<div class="font">성별</div>
		<div class="input" id="genderwrap">
        	<select id="gender" name="gender">
                <option value="gen" selected>성별</option>
        		<option value="man">남자</option>
        		<option value="woman">여자</option>
        	</select>
        </div>
		<div class="text"><font name="gender" color="red"></font></div>
		<div class="font">본인 확인 이메일</div>
		<div id="eMailwrap" class="input"><input type="text" name="eMail" id="eMail"></div>
		<div class="text"><font name="eMail" color="red"></font></div>
		<input type="text" name="rating" value="준회원" style="display:none;">
        <div id="html_element"></div>
        <input id="join" type="button" value="가입하기" onclick="form_check()">

    <!-- JSON String 찍어보기 -->
 

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
    <script src="resources/js/script.js"></script>
    <script src="resources/js/join.js"></script>
    <script src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&render=explicit" async defer></script>
</body>
</html>