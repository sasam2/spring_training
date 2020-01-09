<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring Security JDBC Authentication Example</title>
</head>
<body>
<c:if test="${loggedInUser!=null}">Logged in as ${loggedInUser}.</c:if>
<nav class="floating-menu">
    <h3>Menu</h3>
    <c:choose>
    <c:when test="${loggedInUser!=null}">
       <a href="logout">Sign out</a>
    </c:when>    
    <c:otherwise>
        <a href="login">Sign in</a>
        <a href="signup">Sign up</a>
    </c:otherwise>
	</c:choose>
    <a href="post_view">View Posts</a>
    <a href="post_create">New Post</a>
</nav>
<h1>Logout</h1>
<h2>${message}</h2>
<div class="main">
<form action="logout" method="post" class="message">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="submit" value="Logout">
</form>
</div>
</body>
</html>