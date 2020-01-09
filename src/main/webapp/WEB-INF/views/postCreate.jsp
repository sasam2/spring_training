<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Post</title>
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
<h1>New Post</h1>
<div class="main">
<form action="post_create" method="post" class="message"> <!--   enctype="multipart/form-data" -->  
    <table>
    <tr>
    	<th><label for="title">Title:</label></th>
    	<td><input type="text" name="title" required id="title" /></td>
    </tr>
	<tr>
		<th><label for="content">Content:</label></th>
		<td><textarea name="content" id="content" rows="10" cols="40" required></textarea></td>
	</tr>
<!-- tr><th><label for="id_image">Image:</label></th><td><input type="file" name="image" required id="id_image" /></td></tr>
 --> 
    </table>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="submit" value="Logout">
</form>
</div>
</body>
</html>