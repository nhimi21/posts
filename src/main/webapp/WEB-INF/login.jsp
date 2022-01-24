<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <meta charset="UTF-8">
    <title>LogIn</title>
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
        <h1>Log In</h1>
        <p><c:out value="${error}"/></p>
        <form method="post" action="/login">
            <table>
                <tr>
                    <td><label type="email" for="email">Email</label></td>
                    <td><input type="text" id="email" name="email" class="form-control" placeholder="email"/></td>
                </tr>
                <tr>
                    <td><label for="password">Password</label></td>
                    <td><input type="password" id="password" name="password" class="form-control" placeholder="password"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" class="btn btn-primary" value="Log In"/></td>
                </tr>
            </table>
        </form>
        <a href="/">Register</a>
    </div>

</body>
</html>
