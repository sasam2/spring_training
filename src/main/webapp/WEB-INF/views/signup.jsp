<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
<h1>Sign up</h1>
<div class="main">
<form action="signup" method="post" class="message">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <p>
    <label for="username">Username:</label>
    <input type="text" name="username" required id="username"/>
    </p>
    <p>
    <label for="password">Password:</label>
    <input type="password" name="password" required id="password"/>
    </p>
    <input type="submit" value="Sign up">
</form>
</div>


</body>
</html>