<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
	<title>The Posts</title>
	<link href="resources/style.css" rel="stylesheet" type="text/css">
</head>

<body>
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
	<h1>The Posts</h1>
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
		  <div class="buttonHolder">
        <button type="button" id="loadPostsButton" onclick="loadPosts('{{ user.get_username }}')">Load More</button>
    </div>
	</div>
</div>

</body>
</html>