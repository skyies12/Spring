<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>loginForm</title>
</head>
<body>
<h1>
	loginForm
</h1>
<table>

</table>
<form action="<c:url value='j_spring_security_check'/>" method="post">
	<table>
		<tr>
			<td>ID</td>
			<td><input type="text" name="j_username"></td>
		</tr>
		<tr>
			<td>PW</td>
			<td><input type="password" name="j_password"></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="LOGIN"></td>
		</tr>
	</table>
</form>
</body>
</html>