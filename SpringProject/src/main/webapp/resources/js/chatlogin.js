/**
 * 
 */

history.pushState(null, null, location.href);
    window.onpopstate = function () {
        history.go(1);
};
    $('input[type="text"]').keydown(function() {
        if (event.keyCode === 13) {
            event.preventDefault();
        }
    });

    	$(document).ready(function() {
    		var regExp = /[0-9]/;
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
        		$("#idwrap1").css("border-color","#d30923");
        		

        	});
            $('#id').keyup(function(){
      			 if($("#id").val().length == 0) {
      				 $('font[name=id]').text('');
      				  $('font[name=id]').html("필수 정보입니다.");
      			}  else if($("#id").val().length > 3) {
      				$('font[name=id]').text('');
      				$('font[name=id]').html("닉네임은 4글자 미만이어야 합니다.");
      				$('font[name=id]').css("color", "red");
      			} else if(regExp.test($("#id").val())) {
      				$('font[name=id]').text('');
      				$('font[name=id]').html("닉네임에 숫자가 포함되어서는 안됩니다.");
      				$('font[name=id]').css("color", "red");
       		}  			
      			else {
      				$('font[name=id]').text('');
      			}
      		 });
    		
    	});
    	function form_check() {
    		if($("#id").val().length == 0) {
    			alert("닉네임 설정해주세요");
    			return;
    		}
    		if($("#id").val().length > 3) {
    			alert("닉네임은 4글자 미만이어야 합니다.");
    			return;
    		}
    		var regExp = /[0-9]/;
    		if(regExp.test($("#id").val())) {
    			alert("닉네임에 숫자가 포함되어서는 안됩니다.");
    			return;
    		}
    		
    		submit_ajax();
    	}
    	function submit_ajax() {
    		var queryString = $("#chatform").serialize();
    	    $.ajax({
    	        // url : "/jsp19/lginOK.jsp",
    	        url : "nickname",
    	        type : "post",
    	        dataType : "text",
    	        data : queryString,
    			success : function(json) {
    				//console.log(json);
    				var result = JSON.parse(json);

    				if(result.code == "success") {
    	               alert(result.desc)
    	                window.location.replace("client");
    	           } else {
    	                alert(result.desc);
    	           }
                }
    		}); 
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