<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>
<%@ page session="false" %>
<html>
<head>
	<title>Welcome</title>
</head>
<body>
<h1>
	welcome : Admin  
</h1>
<!-- 
<c:if test="${not empty pageContext.request.userPrincipal }">
	<p>is LogIn</p>
</c:if>

<c:if test="${empty pageContext.request.userPrincipal }">
	<p>is LogOut</p>
</c:if>
USER ID = ${pageContext.request.userPrincipal.name }<br/>
  -->
  
  <s:authorize ifAnyGranted="ROLE_USER">
  	<p>LogIn</p>
  </s:authorize>
  <s:authorize ifNotGranted="ROLE_USER">
  	<p>LogOut</p>
  </s:authorize>
USER ID : <s:authentication property="name"/><br/>
<c:url value="/j_spring_security_logout" var="logoutUrl"/>
<a href="${logoutUrl }">LogOut</a>
</body>
</html>
