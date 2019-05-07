/**
 * 
 */

 var text = "<h1>Welcome K.SW Project</h1>";
	var cnt = 0;
	var speed = 80; //글자가 찍히는 속도
	var timer1 = null; 
    
	function ngogogo(){
	    document.getElementById('navertext').innerHTML = text.substring(0, cnt) + "_";
	    cnt++;
	    timer1 = setTimeout('ngogogo()', speed);
	 
	    if(text.length < cnt){
	        // 아래 주석으로 처리된 부분을 지우면 한번만 실행됩니다.
	        clearTimeout(timer1)
	        cnt = 0;
	    }
	}
	function fgogogo(){
	    document.getElementById('facetext').innerHTML = text.substring(0, cnt) + "_";
	    cnt++;
	    timer1 = setTimeout('fgogogo()', speed);
	 
	    if(text.length < cnt){
	        // 아래 주석으로 처리된 부분을 지우면 한번만 실행됩니다.
	        clearTimeout(timer1)
	        cnt = 0;
	    }
	}
	function kgogogo(){
	    document.getElementById('kakaotext').innerHTML = text.substring(0, cnt) + "_";
	    cnt++;
	    timer1 = setTimeout('kgogogo()', speed);
	 
	    if(text.length < cnt){
	        // 아래 주석으로 처리된 부분을 지우면 한번만 실행됩니다.
	        clearTimeout(timer1)
	        cnt = 0;
	    }
	}
	function gogogo(){
	    document.getElementById('googletext').innerHTML = text.substring(0, cnt) + "_";
	    cnt++;
	    timer1 = setTimeout('gogogo()', speed);
	 
	    if(text.length < cnt){
	        // 아래 주석으로 처리된 부분을 지우면 한번만 실행됩니다.
	        clearTimeout(timer1)
	        cnt = 0;
	    }
	}
	
	
	$("#id").focus(); 
    
    $("#id").focus(function() {
		$("#idwrap").css("border-color","#d30923");
		$("#pwwrap").css("border-color","#dadada");
	});
    $("#pw").focus(function() {
   	 $("#idwrap").css("border-color","#dadada");
 		$("#pwwrap").css("border-color","#d30923");
    });
function submit_ajax() {
	var queryString = $("#reg_frm").serialize();
	$.ajax({
	       // url : "/jsp19/lginOK.jsp",
	       url : "loginOk",
	       type : "post",
	       dataType : "text",
	       data : queryString,
		   success : function(json) {
				console.log(json);
				var result = JSON.parse(json);
				console.log(result);
				if(result.code == "success") {
	              window.location='main-login?num=4'
	              //window.location='main.jsp?num=0'
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
		
		var allData = { "nname": uName, "num": 0, "nrating": "준회원"};

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
					$('#glogin').css('display', 'none');
			    	$('#googlelogout').css('display', 'none');
			    	$('#googlelogout').css('display', 'none');
			    	$('.pc-button').css('display', 'block');
			    	$('.button-wrap').css('display', 'block');
			    	$('#naverIdLogin_loginButton').css('background-color', '#fff'); 	
			    	$('#naverIdLogin').css('display', 'none'); 	
			    	$('#naverIdLogin_loginButton').css('display', 'none'); 	
					//$("#naverIdLogin_loginButton").html(
					//		'<br><br><img src="' + profileImage + 
					//		'" height=50 /> <p style="color:#000">' + uName + '님 반갑습니다.</p>');
					$("#gnbLogin").html("로그아웃");
					$("#gnbLogin").css("display", "inline-block");
					$("#gnbLogin").attr("href", "#");
					$("#gnbLogin").attr("href", "#");
					$('#jbtn').css('display', 'none');
					
					ngogogo();
					/* (7) 로그아웃 버튼을 설정하고 동작을 정의합니다. */
					$("#gnbLogin").click(function () {
						naverLogin.logout();
						location.href = "login.jsp";
					});
				}
	      });
	}
	
	
	
	window.fbAsyncInit = function() {
	    FB.init({
	      appId      : '370624353781669',
	      cookie     : true,
	      xfbml      : true,
	      version    : 'v3.2'
	    });
	  };

	  // Load the SDK asynchronously
	  (function(d, s, id) {
	    var js, fjs = d.getElementsByTagName(s)[0];
	    if (d.getElementById(id)) return;
	    js = d.createElement(s); js.id = id;
	    js.src = "https://connect.facebook.net/en_US/sdk.js";
	    fjs.parentNode.insertBefore(js, fjs);
	  }(document, 'script', 'facebook-jssdk'));

	  function statusChangeCallback(response) {
	    if (response.status === 'connected') {
	      getINFO();
	    } else {
	    	$("#idwrap").css("display", "block");
			$("#pwwrap").css("display", "block");
			$("#join").css("display", "block");
			$("#naverIdLogin").css("display", "block");
	        $('#kklogin').css('display', 'block');
	      $('#login').css('display', 'none');
	      $('#login').css('display', 'block');
	      $('#logout').css('display', 'none');
	      $('#fblogout').css('display', 'none');
	      $('#glogin').css('display', 'block');
	      $('.pc-button').css('display', 'none');
	  	$('.button-wrap').css('display', 'none');
	  	$('#googlelogout').css('display', 'none');
	      $('#upic').attr('src', '');
	      $('#uname').html('');
	      $('#jbtn').css('display', 'block');
	    }
	  }
		  
	  function fbLogin () {
	    FB.login(function(response){
	      statusChangeCallback(response);
	    }, {scope: 'public_profile, email'});
	  }

	  function fbLogout () {
	    FB.logout(function(response) {
	      statusChangeCallback(response);
	    });
	  }

	  function getINFO() {
	    FB.api('/me?fields=id,name,picture.width(100).height(100).as(picture_small)', function(response) {
	    	
	      console.log(response);
	      var allData = { "fname": response.name, "num": 1};

	      $.ajax({
		        url : "param",
		        type : "post",
		        dataType : "text",
		        data : allData,
				success : function(data) {
					
					 $("#idwrap").css("display", "none");
						$("#pwwrap").css("display", "none");
						$("#join").css("display", "none");
						$("#naverIdLogin").css("display", "none");
				        $('#kklogin').css('display', 'none');
				      $('#login').css('display', 'none');
				      $('#logout').css('display', 'block');
				      $('#fblogout').css('display', 'inline-block');
				      $('#glogin').css('display', 'none');
				    	$('#googlelogout').css('display', 'none');
				    	$('.pc-button').css('display', 'block');
				    	$('.button-wrap').css('display', 'block');
				      //$('#upic').attr('src', response.picture_small.data.url );
				      //$('#uname').html('[ ' + response.name + ' ]');
				      $('#jbtn').css('display', 'none');
				      $('#facetext').css('display', 'block');
				      fgogogo();
				}
	      });
	    });
	  }
	  
	  
	  Kakao.init('59d239eb996c1e775ce748202bc61a0a');
	    function loginWithKakao() {
	      // 로그인 창을 띄웁니다.
	      Kakao.Auth.login({
	        success: function(authObj) {
	          //alert(JSON.stringify(authObj));
	          signIn(authObj);
	        },
	        fail: function(err) {
	          alert(JSON.stringify(err));
	        }
	      });
	    };

	    function signIn(authObj) {
	        //console.log(authObj);
	        Kakao.API.request({
	            url: '/v2/user/me',
	            success: function(res) {
	                //console.log(res);
	                console.log(res.id);
	                
	                var allData = { "kname": res.properties.nickname, "num": 2 };
	            

	                $.ajax({
	        	        // url : "/jsp19/lginOK.jsp",
	        	        url : "/project/param.jsp",
	        	        type : "post",
	        	        dataType : "text",
	        	        data : allData,
	        			success : function(data) {
	        				 $("#idwrap").css("display", "none");
	        	        		$("#pwwrap").css("display", "none");
	        	        		$("#join").css("display", "none");
	        	        		$("#naverIdLogin").css("display", "none");
	        	              	$('#login').css('display', 'none');
	        	              	$('#logout').css('display', 'block');
	        	             	 $('#fblogout').css('display', 'none');
	        	                $('#kklogin').css('display', 'none');
	        	               	$('#kklogout').css('display', 'block');
	        	               	$('#kakaologout').css('display', 'inline-block');
	        	               	$('#glogin').css('display', 'none');
	        			    	$('#googlelogout').css('display', 'none');
	        			    	$('.pc-button').css('display', 'block');
	        			    	$('.button-wrap').css('display', 'block');
	        			    	$('#facetext').css('display', 'none');
	        			    	$('#kakaotext').css('display', 'block');
	        			    	
	        			    	kgogogo();
	        	                //$('#kkupic').attr('src', res.properties.thumbnail_image );
	        	               //	$('#kkuname').html('[ ' + res.properties.nickname + ' ]');
	        	               	$('#jbtn').css('display', 'none');
	        	               	
	                    }
	        		});
	             }
	         })
		}

	    function signOut() {
		    Kakao.Auth.logout(function () {
		    	$("#idwrap").css("display", "block");
	    		$("#pwwrap").css("display", "block");
	    		$("#join").css("display", "block");
	    		$("#naverIdLogin").css("display", "block");
	          	$('#login').css('display', 'block');
	          	$('#logout').css('display', 'none');
	         	 $('#fblogout').css('display', 'none');
		    	$('#kklogin').css('display', 'block');
		    	$('#kklogout').css('display', 'none');
		    	$('#kakaologout').css('display', 'none');
		    	$('#glogin').css('display', 'block');
		    	$('#googlelogout').css('display', 'none');
		    	$('#kkupic').attr('src', '');
		    	$('#kkuname').html('');
		    	$('#jbtn').css('display', 'block');
		    	$('.pc-button').css('display', 'none');
		    	$('.button-wrap').css('display', 'none');
		    });
		}    
	    
	    var googleUser = {};
		var startApp = function() {
			gapi.load('auth2', function() {
				// Retrieve the singleton for the GoogleAuth library and set up the client.
				auth2 = gapi.auth2.init({
					client_id: '1043167905219-uo1hrq33hv0tjq2r2odmhlbn48ed5tpm.apps.googleusercontent.com',
					cookiepolicy: 'single_host_origin',
	        		// Request scopes in addition to 'profile' and 'email'
	        		//scope: 'additional_scope'
				});
				
				attachSignin(document.getElementById('glogin'));
			});
		};
			
		function attachSignin(element) {
			auth2.attachClickHandler(element, {},
				function(googleUser) {
					var profile = googleUser.getBasicProfile();
					var allData = { "gname": profile.getName(), "num": 3 };
					

	                $.ajax({
	        	        // url : "/jsp19/lginOK.jsp",
	        	        url : "param",
	        	        type : "post",
	        	        dataType : "text",
	        	        data : allData,
	        			success : function(data) {
	        				$("#idwrap").css("display", "none");
	    	        		$("#pwwrap").css("display", "none");
	    	        		$("#join").css("display", "none");
	    	        		$("#naverIdLogin").css("display", "none");
	    	              	$('#login').css('display', 'none');
	    	              	$('#logout').css('display', 'block');
	    	             	 $('#fblogout').css('display', 'none');
	    	                $('#kklogin').css('display', 'none');
	    	               	$('#kklogout').css('display', 'block');
	    	               	$('#kakaologout').css('display', 'none');
	    	               	$('#jbtn').css('display', 'none');
	    				$('#glogin').css('display', 'none');
	    		    	$('#googlelogout').css('display', 'inline-block');
	    		    	//$('#upic').attr('src', profile.getImageUrl());
	    		    	//$('#uname').html('[ ' +profile.getName() + ' ]');
	    		    	$('.pc-button').css('display', 'block');
	    		    	$('.button-wrap').css('display', 'block');
	    		    	$('#kakaotext').css('display', 'none');
	    		    	$('#facetext').css('display', 'block');
	    		    	fgogogo();
	        			}
	                });
				}, function(error) {
					alert(JSON.stringify(error, undefined, 2));
				});
		}
		function gsignOut() {
		    var auth2 = gapi.auth2.getAuthInstance();
		    auth2.signOut().then(function () {
		    	$("#idwrap").css("display", "block");
	    		$("#pwwrap").css("display", "block");
	    		$("#join").css("display", "block");
	    		$("#naverIdLogin").css("display", "block");
	          	$('#login').css('display', 'block');
	          	$('#logout').css('display', 'none');
	         	$('#fblogout').css('display', 'none');
		    	$('#kklogin').css('display', 'block');
		    	$('#kklogout').css('display', 'none');
		    	$('#kakaologout').css('display', 'none');
		    	$('#glogin').css('display', 'block');
		    	$('#googlelogout').css('display', 'none');
		    	$('#jbtn').css('display', 'block');
		    	$('#upic').attr('src', '');
		    	$('#uname').html('');
		    	$('.pc-button').css('display', 'none');
		    	$('.button-wrap').css('display', 'none');
		    });
		}
		history.pushState(null, null, location.href);
	    window.onpopstate = function () {
	        history.go(1);
	};