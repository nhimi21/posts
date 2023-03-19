<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <meta charset="UTF-8">
    <title>Posts</title>
</head>
<body>
<div class="container">
    <h1>Hello, <c:out value="${user.fullName}"/></h1>
    <a href="/logout">Logout</a>
    <table class="table">
        <thead>
        <tr>
            <th>Headline</th>
            <th>Post</th>
            <th>Number of Likes</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${posts}" var="post">
            <tr>
                <td><a href="/post/${post.id}/details">${post.headline}</a></td>
                <td>${post.description}</td>
                <td>${post.nrOfLikes}</td>
                <td>
                    <c:choose>
                        <c:when test="${post.userID == user}">
                            <a href="/post/${post.id}">edit</a>
                        </c:when>
                        <c:otherwise>
                            <span>Created by ${post.userID.fullName} </span>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="/new">New Post</a>
</div>
</body>
</html>

