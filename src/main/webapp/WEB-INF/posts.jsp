<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Posts</title>
    <style>
        .form-control{
            border: 1px solid black;
        }
        .btn{
            border-radius: 10px;
            width: 100%;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Hello, <c:out value="${user.fullName}"/></h1>
    <form:form method="POST" action="/posts/new" modelAttribute="post">
        <table>
            <tr>
                <td><form:label path="headline">Headline:</form:label></td>
                <td>
                    <form:input type="text" path="headline" class="form-control"/>
                    <form:errors path="headline" cssStyle="color: red"/>
                </td>
            </tr>
            <tr>
                <td><form:label path="description">Description:</form:label></td>
                <td>
                    <form:textarea type="text" path="description" class="form-control"/>
                    <form:errors path="description" cssStyle="color: red"/>
                </td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" class="btn btn-info" value="Post"/></td>
            </tr>
        </table>
    </form:form>
    <a href="/posts">Back</a>
</div>
</body>
</html>
