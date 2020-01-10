<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html><head><title>Login Page</title>
<link href="resources/style.css" rel="stylesheet" type="text/css">
</head>
<body onload='document.f.username.focus();'>
<div id="contents">

        <div id="sidebar">
               
        <c:if test="${loggedInUser!=null}">
        	<p align="right">Authenticated as 
        		<b><a href="portfolio/signout">${loggedInUser}</a></b>.
        	</p>
        </c:if>
        
        </div>
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
<div class="main">
<h1>Login</h1>
<form name='login' action='login' method='POST' class="message">
<!-- /spring-security-jdbc-authentication-example/login -->
<p>
    <label for="username">Username: </label><input type='text' name='username' value=''>
</p>
<p>
	<label for="password">Password: </label><input type='password' name='password'  value=''/>
</p>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<input name="submit" type="submit" value="Login"/>
</form>
</div>
</div>
</body></html>