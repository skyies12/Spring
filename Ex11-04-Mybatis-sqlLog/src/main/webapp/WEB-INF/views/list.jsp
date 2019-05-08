<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table width="500" cellpadding="0" border="1">
		<tr>
			<td>번호</td>
			<td>작성자</td>
			<td>내용</td>
			<td>삭제</td>
		</tr>
		<c:forEach items="${list }" var="dto">
		<tr>
			<td>${dto.mId }</td>
			<td>${dto.mWriter }</td>
			<td><a href="view?mId=${dto.mId }">${dto.mContent }</a></td>
			<td><a href="delete?mId=${dto.mId }">X</a></td>			
		</tr>
		</c:forEach>
	</table>
	<p><a href="writeForm">글 작성</a></p>
</body>
</html>