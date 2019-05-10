<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
	
	var wsocket;
	
	function connect() {
		wsocket = new WebSocket("ws://localhost:8081/wsocket/chat-ws");
		wsocket.onopen = onOpen;
		wsocket.onmessage = onMessage;
		wsocket.onclose = onClose;
	}
	function disconnect() {
		wsocket.close();
	}
	
	function onOpen(evt) {
		appendMessage("연결되었습니다.")
	}
	
	function onMessage(evt) {
		var data = evt.data;
		if(data.substring(0,6) == "msg : ") {
			appendMessage(data.substring(6));
		}
	}
	
	function onClose(evt) {
		appendMessage("연결이 끊어졌습니다.")
	}
	
	function send() {
		var nickname = $("#nickname").val();
		var msg = $("#message").val();
		wsocket.send("msg : " + nickname + " : " + msg);
		$("#message").val("");
	}
	
	function appendMessage(msg) {
		$("#chatMessageArea").append(msg + "<br/>");
		var chatAreaHeight = $("#chatArea").height();
		var maxScroll = $("#chatMessageArea").height() - chatAreaHeight;
		$("#chatArea").scrollTop(maxScroll);
	}
	
	$(document).ready(function() {
		$("#message").keypress(function(event) {
			var keycode = (event.keyCode ? event.keyCode : event.which);
			if(keycode == "13") {
				send();
			}
			event.stopPropagation();
		});
		$("#sendBtn").click(function() { send(); });
		$("#endterBtn").click(function() { connect(); });
		$("#exitdBtn").click(function() { disconnect(); });
	});
</script>
<style>
	#chatArea {
		width : 200px;
		height : 100px;
		overflow-y : auto;
		border : 1px solid black;
	}
</style>
</head>
<body>
	이름 : <input type="text" id="nickname">
	<input type="button" id="endterBtn" value="입장">
	<input type="button" id="exitdBtn" value="나가기">
	
	<h1>대화 영역</h1>
	<div id="chatArea">
		<div id="chatMessageArea"></div>
	</div>
	<br/>
	<input type="text" id="message">
	<input type="button" id="sendBtn" value="전송">
</body>
</html>