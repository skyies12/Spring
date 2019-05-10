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
	<h2>파일 목록보기</h2>
	<ul>
	<c:forEach items="${fileMap }" var="file">
		<li>
			파일명 : <a href="download?fileName=${file.key }&oriFileName=${file.key }">${file.key }</a>
			&nbsp;&nbsp;
			파일크기 : ${file.value }KB
		</li>
	</c:forEach>
	</ul>
</body>
</html>