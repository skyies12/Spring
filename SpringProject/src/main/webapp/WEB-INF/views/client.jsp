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
    <link href="resources/css/client.css" rel="stylesheet">
    <script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
        <script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script>
    <title>K.SW Project</title>
</head>
<body>

<% request.setCharacterEncoding("UTF-8"); %>

<% 

String bNum = request.getParameter("bNum");
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
String nickname = (String)session.getAttribute("nickname");
%>
      <div id="wrap">
       <!-- header -->
        <header>
          <div class="nav-wrap">
            <div class="nav-bar">
            <div id="lsbtn">
            		<button class="btn btn-outline-danger" type="button" onclick="closeSocket();">퇴장</button>
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
                        <li><a href="list?bNum=0&num=4">공지사항</a></li>
                        <li><a href="list?bNum=1">자유게시판</a></li>
                        <li><a href="list?bNum=2">자료실</a></li>
                        <li><a href="chatlogin">다중 채팅방</a></li>
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
            	<div class="container">
            		<div id="cha_wrap" class="row">
            		<div class="col" id="papago">
            			<div class="row">
            				<textarea placeholder="번역" id="result_text" class="form_control" name="content" style="resize: none;"></textarea>
            			</div>
            			<div class="row">
            				<textarea placeholder="텍스트 입력" id="send_text" class="form_control" name="content" style="resize: none;" onkeyup="papagoenterkey();"></textarea>
            			</div>
            		</div>
            		<div class="col" id="chatwrap">
            			<div>
						닉네임 : <%= nickname %>
						</div>
						<div id="messages"></div>
						<div>
						<div>
							<input onkeyup="enterkey();" type="text" id="messageinput">
							<button id="send" type="button" onclick="send();">보내기</button>
						</div>
							<button style="display:none;" type="button" onclick="openSocket();">참여</button>
						</div>
            		</div>
				</div>
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
    <script type="text/javascript">    
    history.pushState(null, null, location.href);
    window.onpopstate = function () {
        history.go(1);
  };  
		var webSocket;
		var messages = document.getElementById("messages");
		window.onload=function(){
			openSocket();
		}
		
		function openSocket() {
			var id = "<%= nickname%>";	
		
			if(webSocket != undefined && webSocket.readyState != WebSocket.CLOSED) {
				writeResponse("이미 채팅방에 참여 중입니다.");
				return;
			}
			
			webSocket = new WebSocket("ws://localhost:8081/spring20/websocketendpoint2");
			webSocket.onopen = function(event) {
				if(event.data == undefined) {
					return
				}
				writeResponse(event.data);
			};
			webSocket.onmessage = function(event) {
				writeResponse(event.data);
			};
			
			webSocket.onclose = function(event) {
				writeResponse(id + "님이 퇴장하셨습니다.");
			};
		}
		
		function send() {
			var id = "<%= nickname%>";
			var text = document.getElementById("messageinput").value;
			webSocket.send(id + " : " + text);
			$("#messageinput").val("");
			
		}
		
		function closeSocket() {
			var nickname = "<%= nickname%>";

			var queryString = {"nickname": nickname};
    	    $.ajax({
    	        // url : "/jsp19/lginOK.jsp",
    	        url : "nicknamedeleteOk",
    	        type : "post",
    	        dataType : "text",
    	        data : queryString,
    			success : function(json) {
    				//console.log(json);
    				var result = JSON.parse(json);

    				if(result.code == "success") {
    	               alert(result.desc)
    	               webSocket.close();
    	               window.location.replace("chatlogin");
    	           } else {
    	                alert(result.desc);
    	           }
                }
    		}); 
		}
		function enterkey() {
	        if (window.event.keyCode == 13) {       
	             // 엔터키가 눌렸을 때 실행할 내용
	        	var test = {
	    				"original_str": $("#messageinput").val()
	    			};
	    			jsonSend(test);
	        }
		}
		
		function writeResponse(text) {
			var id = "<%= nickname%>";
			console.log($("#messages")[0].scrollHeight-510);
			console.log($("#messages").scrollTop());

			var ostr = $("#messageinput").val();
			var str = text.substring(0,id.length);
			var str1 = text.substring(id.length+3,text.length+1);
			console.log(str);
			console.log(str1);
			
			if (str.indexOf(id) != -1) {
				messages.innerHTML += "<div class='mes aad' style='width:100%; height:auto; text-align:right;'><p style='text-align:left; display:inline-block; padding:5px; background-color:yellow; max-width:200px; border-radius:5px;'>" + str1 + "</p></div>";
				$("#messages").scrollTop($("#messages")[0].scrollHeight-510);
			} else {
				if(text=="") {
					messages.innerHTML += "<div style='text-align:center;'>채팅방에 입장하셨습니다.</div>"
				} else {
					messages.innerHTML += "<div>"+ str +"</div><div style='width:100%; height:auto; text-align:left;'><p style=' display:inline-block; padding:5px; background-color:#fff; max-width:200px; border-radius:5px;'>" + str1 + "</p></div>";
					$("#messages").scrollTop($("#messages")[0].scrollHeight-510);		
				}
			}
		}
		
		
		function jsonSend(test) {
			$.ajax({
				type : "post",
				url : "/spring20/anmt",
				data : test,
				success : function(data) {
					console.log(data);
					var test1 = test;
					var test2 = test;
					var result_obj = JSON.parse(data);
					var langCode = result_obj.langCode;
					console.log(langCode);
					if(langCode=="ko") {
						send();
					} else {
						var alldata = {"test":$("#messageinput").val() , "langCode":langCode}
						$.ajax({
							type : "post",
							url : "/spring20/nmt",
							data : alldata,
							success : function(data) {
								var id = "<%= nickname%>";		
								var text = $("#messageinput").val();
								var result_obj = JSON.parse(data);
								console.log(text);
								webSocket.send(id + " : " + result_obj.message.result.translatedText + "<br/>" +text);
								$("#messageinput").val("");
							},
							error : function(e) {
								alert("실패");
							}
						});
					}
				},
				error : function(e) {
					alert("실패");
				}
			})
		}
	</script>
    <script>
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
    		$("#id").focus(); 
            
            $("#id").focus(function() {
        		$("#idwrap").css("border-color","#d30923");

        	});
    		
    	});
    	function form_check() {
    		if($("#id").val().length == 0) {
    			alert("닉네임 설정해주세요");
    			return;
    		}

    		submit();
    	}
    	function submit() {
    		document.login_form.submit();
    	}
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
			        url : "/spring20/param.jsp",
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
							location.href="login.jsp";
						});
					}
		      });
		}
	</script>
	<script>

	function papagoenterkey() {
       	if (window.event.keyCode == 13) {       
       		var test = {
    				"original_str": $("#send_text").val()
    			};
    		jsonSend1(test);
        }
	}	
		function jsonSend1(test) {
			$.ajax({
				type : "post",
				url : "/spring20/Result",
				data : test,
				success : function(data) {
					console.log(data);
					
					var result_obj = JSON.parse(data);
					
					$("#result_text").val(result_obj.message.result.translatedText);
					
				},
				error : function(e) {
					alert("실패");
				}
			})
		}

		
	</script>
</body>
</html>