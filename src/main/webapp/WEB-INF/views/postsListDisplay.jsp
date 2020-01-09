<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
	<title>The Posts</title>
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
<h1>The Posts</h1>
<div class="main">
	<ul class="news"  id="posts">
	    <c:forEach items="${posts}" var="p">
		    <li id="${p.id}">
		    <h2> ${p.title}</h2>
		   	<img src="${p.photo}">
		    <p> ${p.content}</p>
		    <p> ${p.date}</p>
		    <p> ${p.authorName}</p>
		</c:forEach>
	</ul>
</div>
<!-- h2>All Posts in System</h2>
<p>${message}</p>
		<table border="1">
		<tr>
			<th>Post Id</th>
			<th>Title</th>
			<th>Content</th>
		</tr>
		<c:forEach items="${posts}" var="p">
			<tr>
				<td>${p.id}</td>
				<td>${p.title}</td>
				<td>${p.content}</td>
			</tr>
		</c:forEach>
</table -->

</body>
</html>