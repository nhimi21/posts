<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <meta charset="UTF-8">
    <title>Register</title>
    <style>
        input{
            margin: 7px;
        }
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
    <h1>Register</h1>
    <%--        <p><form:errors path="user.*"/></p>--%>
    <%--@elvariable id="user" type="java"--%>
    <form:form method="POST" action="/register" modelAttribute="user">
        <table>
            <tr>
                <td><form:label path="fullName">Full Name:</form:label></td>
                <td>
                    <form:input type="text" path="fullName" class="form-control" placeholder="Enter full name.."/>
                    <form:errors path="fullName" cssStyle="color: red"/>
                </td>
            </tr>
            <tr>
                <td><form:label path="email">Email:</form:label></td>
                <td>
                    <form:input type="email" path="email" class="form-control" placeholder="Enter email.."/>
                    <form:errors path="email" cssStyle="color: red"/>
                </td>
            </tr>
            <tr>
                <td><form:label path="password">Password:</form:label></td>
                <td>
                    <form:password path="password" class="form-control" placeholder="Enter password.."/>
                    <form:errors path="password" cssStyle="color: red"/>
                </td>
            </tr>
            <tr>
                <td><form:label path="confirmPassword">Confirm Password:</form:label></td>
                <td>
                    <form:password path="confirmPassword" class="form-control" placeholder="Again password.."/>
                    <form:errors path="confirmPassword" cssStyle="color: red"/>
                </td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" class="btn btn-info" value="Register"/></td>
            </tr>
        </table>
    </form:form>
    <a href="/login">Log In</a>
</div>
</body>
</html>
