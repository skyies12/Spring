/**
 * 
 */

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
    	function form_check() {
			if($("#context").val().length == 0) {
				alert("제목을 입력해주세요");
				return
			}
			if($("#summernote").val().length == 0) {
				alert("내용을 입력해주세요");
				return
			}
			if($("#fileName").val().length == 0) {
				alert("첨부하실 파일을 올려주세요");
				return
			}
			document.modify_form.submit();
		}
    	
    	var naverLogin = new naver.LoginWithNaverId(
    			{

    				clientId: "IEzLjz3gsY3kDLocAh4B",
    				callbackUrl: "http://54.180.95.221:8081/project/login.jsp",
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
    			        url : "/project/param.jsp",
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