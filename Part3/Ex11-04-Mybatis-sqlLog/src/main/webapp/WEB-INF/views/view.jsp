<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
list.jsp
<table width="500" cellpadding="0" border="1">
		<tr>
			<td>번호</td>
			<td>${content_view.mId }</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${content_view.mWriter }</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>${content_view.mContent }</td>
		</tr>
	</table>
	<p><a href="list">목록보기</a></p>
</body>
</html>