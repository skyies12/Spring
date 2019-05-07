/**
 * 
 */


var text = "<h1>Welcome K.SW Project</h1>";
var cnt = 0;
var speed = 80; //글자가 찍히는 속도
var timer1 = null;
 
function gogogo(){
    document.getElementById('texts').innerHTML = text.substring(0, cnt) + "_";
    cnt++;
    timer1 = setTimeout('gogogo()', speed);
 
    if(text.length < cnt){
        // 아래 주석으로 처리된 부분을 지우면 한번만 실행됩니다.
        clearTimeout(timer1)
        cnt = 0;
    }
}
 
gogogo();

// 네이버 
var naverLogin = new naver.LoginWithNaverId(
		{

			clientId: "IEzLjz3gsY3kDLocAh4B",
			callbackUrl: "http://54.180.95.221:8081/project/login.jsp",
			isPopup: false,
		}
	);
window.addEventListener('load', function () {
	naverLogin.getLoginStatus(function (status) {
		if (status) {
			$("#fblogout").css("display","none");
			$("#dblogout").css("display","none");
			$("#googlelogout").css("display","none");
			$("#gnbLogin").click(function () {
				naverLogin.logout();		
			});
		} else {
			
		}
	});
});
// 페북
(function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "https://connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
  }(document, 'script', 'facebook-jssdk'));

function statusChangeCallback(response) {
    if (response.status === 'connected') {
		
    } else {
    	
    }
  }

function fbLogout () {
    FB.logout(function(response) {
      statusChangeCallback(response);
    });
  }
history.pushState(null, null, location.href);
window.onpopstate = function () {
    history.go(1);
};


