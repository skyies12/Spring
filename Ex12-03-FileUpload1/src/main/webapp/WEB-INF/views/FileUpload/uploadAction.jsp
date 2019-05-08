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
	<h2>파일업로드 결과보기</h2>
	
	<a href="./uploadForm">파일업로드폼 바로가기</a>
	
	<c:forEach begin="0" end="${returnObj.files.size()-1 }" var="i">
		<ul>
			<li>제목${i+1 } : ${returnObj.files[i].title }</li>
			<li>원본파일명${i+1 } : ${returnObj.files[i].originalName }</li>
			<li>저장된파일명${i+1 } : ${returnObj.files[i].saveFileName }</li>
			<li>전체경로${i+1 } : ${returnObj.files[i].serverFullName }</li>
			<li><img src="resources/upload/${returnObj.files[i].originalName }" width="300"></li>
		</ul>
	</c:forEach>
	
</body>
</html>