<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Details</title>
    <style>
        .container h1{
            text-align: center;
        }
        .cont{
            display: flex;
            justify-content: space-around;
            align-items: center;
            margin-top: 20px;
        }
        .bord{
            width:300px;
            height: auto;
            border:1px solid black;
            padding: 20px;
        }
        .bord h3{
            text-align: center;
        }
        .bord p{
            color:blue;
            text-align: center;
        }

    </style>
</head>
<body>
<div class="container">
    <h1>Post Details</h1><hr>
    <div class="cont">
        <div class="bord">
            <h3>Headline</h3><hr>
            <p><c:out value="${post.headline}"/></p></div>
        <div class="bord">
            <h3>Description</h3><hr>
            <p><c:out value="${post.description}"/></p>
        </div>
    </div>
    ${post.userLikes.size()} |
    <c:choose>
        <c:when test="${post.userLikes.contains(user)}">
            <a href="/unlike/${post.id}">Unlike</a>
        </c:when>
        <c:otherwise>
            <a href="/like/${post.id}">Like</a>
        </c:otherwise>
    </c:choose>
     | <a href="/posts">Back</a>
</div>
</body>
</html>
