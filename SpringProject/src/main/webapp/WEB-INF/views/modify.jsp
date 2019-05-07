<%@page import="com.study.spring20.MemberDTO"%>
<%@page import="com.study.spring20.MemberDAO"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	String id = (String)session.getAttribute("id");
	MemberDAO dao = MemberDAO.getInstance();
	MemberDTO dto = dao.getMember(id);
	
	String birth = dto.getBirth();
	String gender = dto.getGender();

	String year = birth.substring(0,4);
	String month = birth.substring(5,7);
	String day = birth.substring(8,10);
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
    <link href="resources/css/modify.css" rel="stylesheet">
    <title>K.SW Project</title>
</head>
<body>
      <div id="wrap">
       <!-- header -->
        <header>
         <div id="lsbtn">
             <button onclick="JavaScript:location = 'logout'" type="button" class="btn btn-outline-danger">로그아웃</button>
             <button onclick="JavaScript:location = 'membermodify'" type="button" class="btn btn-outline-danger">정보수정</button>
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
            <div id="wrap1">
	<form id="reg_frm">
		<div class="font">아이디</div>
		<div id="idwrap" class="input"><input type="text" name="id" id="id" value="<%= dto.getId() %>" readonly="readonly"></div>
		<div class="text"><font name="id" color="red"></font></div>
		<div class="font">비밀번호</div>
		<div id="pwwrap" class="input"><input type="password" name="pw" id="pw"></div>
		<div class="text"><font name="pw"  color="red"></font></div>
		<div class="font">비밀번호 재확인</div>
		<div id="pwchwrap" class="input"><input type="password" name="pw_check" id="pw_check"></div>
		<div class="text"><font name="pw_check" color="red"></font> </div>
		<div class="font">이름</div>
		<div id="namewrap" class="input"><input type="text" name="name" id="name" value="<%= dto.getName() %>" readonly="readonly"></div>
		<div class="text"><font name="name" color="red"></font></div>
		<div class="font">생년월일</div>
		<div id="birthwrap">
			<div id="yearwrap"><input class="year" type="text" name="year" value="<%= year %>"></div>
			<%
        	if(month.equals("month")) {
      		  %>
        		<div id="monthwrap">
					<select class="month" name="month">
        				<option value="month" selected>월</option>
        				<option value="01" >1</option>
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
        	<% 
       		 } 
        	else if(month.equals("01")) {
      		  %>
        		<div id="monthwrap">
					<select class="month" name="month">
        				<option value="month">월</option>
        				<option value="01" selected>1</option>
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
        	<% 
       		 } else if(month.equals("02")) {
        	%>
				<div id="monthwrap">
					<select class="month" name="month">
        				<option value="month">월</option>
        				<option value="01" >1</option>
        				<option value="02" selected>2</option>
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
        	<%
       		 } else if(month.equals("03")) {
      		  %>
        		<div id="monthwrap">
					<select class="month" name="month">
        				<option value="month">월</option>
        				<option value="01">1</option>
        				<option value="02">2</option>
        				<option value="03" selected>3</option>
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
        	<% 
       		 } else if(month.equals("04")) {
        	%>
				<div id="monthwrap">
					<select class="month" name="month">
        				<option value="month">월</option>
        				<option value="01" >1</option>
        				<option value="02">2</option>
        				<option value="03">3</option>
        				<option value="04" selected>4</option>
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
        	<%
       		 } else if(month.equals("05")) {
        	%>
        	<div id="monthwrap">
					<select class="month" name="month">
        				<option value="month">월</option>
        				<option value="01" >1</option>
        				<option value="02">2</option>
        				<option value="03">3</option>
        				<option value="04">4</option>
        				<option value="05" selected>5</option>
        				<option value="06">6</option>
        				<option value="07">7</option>
        				<option value="08">8</option>
        				<option value="09">9</option>
        				<option value="10">10</option>
        				<option value="11">11</option>
        				<option value="12">12</option>
        			</select> 
        		</div>
        	<%
       		 } else if(month.equals("06")) {
        	%>
        	<div id="monthwrap">
					<select class="month" name="month">
        				<option value="month">월</option>
        				<option value="01" >1</option>
        				<option value="02">2</option>
        				<option value="03">3</option>
        				<option value="04">4</option>
        				<option value="05">5</option>
        				<option value="06" selected>6</option>
        				<option value="07">7</option>
        				<option value="08">8</option>
        				<option value="09">9</option>
        				<option value="10">10</option>
        				<option value="11">11</option>
        				<option value="12">12</option>
        			</select> 
        		</div>
        	<%
       		 } else if(month.equals("07")) {
        	%>
        	<div id="monthwrap">
					<select class="month" name="month">
        				<option value="month">월</option>
        				<option value="01" >1</option>
        				<option value="02">2</option>
        				<option value="03">3</option>
        				<option value="04">4</option>
        				<option value="05">5</option>
        				<option value="06">6</option>
        				<option value="07" selected>7</option>
        				<option value="08">8</option>
        				<option value="09">9</option>
        				<option value="10">10</option>
        				<option value="11">11</option>
        				<option value="12">12</option>
        			</select> 
        		</div>
        	<%
       		 } else if(month.equals("08")) {
        	%>
        	<div id="monthwrap">
					<select class="month" name="month">
        				<option value="month">월</option>
        				<option value="01" >1</option>
        				<option value="02">2</option>
        				<option value="03">3</option>
        				<option value="04">4</option>
        				<option value="05">5</option>
        				<option value="06">6</option>
        				<option value="07">7</option>
        				<option value="08" selected>8</option>
        				<option value="09">9</option>
        				<option value="10">10</option>
        				<option value="11">11</option>
        				<option value="12">12</option>
        			</select> 
        		</div>
        	<%
       		 } else if(month.equals("09")) {
        	%>
        	<div id="monthwrap">
					<select class="month" name="month">
        				<option value="month">월</option>
        				<option value="01" >1</option>
        				<option value="02">2</option>
        				<option value="03">3</option>
        				<option value="04">4</option>
        				<option value="05">5</option>
        				<option value="06">6</option>
        				<option value="07">7</option>
        				<option value="08">8</option>
        				<option value="09" selected>9</option>
        				<option value="10">10</option>
        				<option value="11">11</option>
        				<option value="12">12</option>
        			</select> 
        		</div>
        	<%
       		 } else if(month.equals("10")) {
        	%>
        	<div id="monthwrap">
					<select class="month" name="month">
        				<option value="month">월</option>
        				<option value="01" >1</option>
        				<option value="02">2</option>
        				<option value="03">3</option>
        				<option value="04">4</option>
        				<option value="05">5</option>
        				<option value="06">6</option>
        				<option value="07">7</option>
        				<option value="08">8</option>
        				<option value="09">9</option>
        				<option value="10" selected>10</option>
        				<option value="11">11</option>
        				<option value="12">12</option>
        			</select> 
        		</div>
        	<%
       		 } else if(month.equals("11")) {
        	%>
        	<div id="monthwrap">
					<select class="month" name="month">
        				<option value="month">월</option>
        				<option value="01" >1</option>
        				<option value="02">2</option>
        				<option value="03">3</option>
        				<option value="04">4</option>
        				<option value="05">5</option>
        				<option value="06">6</option>
        				<option value="07">7</option>
        				<option value="08">8</option>
        				<option value="09">9</option>
        				<option value="10">10</option>
        				<option value="11" selected>11</option>
        				<option value="12">12</option>
        			</select> 
        		</div>
        	<%
       		 } else if(month.equals("12")) {
        	%>
        	<div id="monthwrap">
					<select class="month" name="month">
        				<option value="month">월</option>
        				<option value="01" >1</option>
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
        				<option value="12" selected>12</option>
        			</select> 
        		</div>
        	<%
       		 }
			%>
      	 	<div id="daywrap"><input  class="day" type="text" name="day" value="<%= day %>"></div>
    	</div>
		<div class="text"><font name="birth"  color="red"></font></div>
		<div class="font">성별</div>
			<%
        	if(gender.equals("man")) {
      		  %>
        	<div class="input" id="genderwrap">
        		<select id="gender" name="gender">
             	   <option value="gen">성별</option>
        			<option value="man" selected>남자</option>
        			<option value="woman">여자</option>
        		</select>
        	</div>
        	<% 
       		 } else {
        	%>
			<div class="input" id="genderwrap">
        		<select id="gender" name="gender">
             	   	<option value="gen">성별</option>
        			<option value="man">남자</option>
        			<option value="woman" selected>여자</option>
        		</select>
        	</div>
        	<%
       		 }
        	%>
		<div class="text"><font name="gender" color="red"></font></div>
		<div class="font">본인 확인 이메일</div>
		<div id="eMailwrap" class="input"><input type="text" name="eMail" id="eMail" value="<%= dto.geteMail() %>"></div>
		<div class="text"><font name="eMail" color="red"></font></div>
		<input class="bbbtn" id="join" type="button" value="정보수정" onclick="updateInfoConfirm()">
		<a style="display:block;" class="bbbtn" id="join" href="javascript:history.go(-1)">취소</a>
		<input class="bbbtn" id="out" type="button" value="회원탈퇴" onclick="deleteConfirm();">
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
    <script src="resources/js/modify.js"></script>
</body>
</html>