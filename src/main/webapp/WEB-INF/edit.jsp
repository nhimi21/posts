<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Edit or Delete</title>
    <style>
        .form-control{
            border: 1px solid black;
        }
        .btn{
            border-radius: 10px;
        }
    </style>
</head>
<body>

<div class="container">
    <h3>Edit Post</h3>
    <form:form method="POST" action="/posts/${post.id}/edit" modelAttribute="post">
        <input type="hidden" name="_method" value="put">
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
        </table>
        <input type="submit" class="btn btn-primary" value="Edit"/>
    </form:form>
    <form action="/posts/${post.id}/edit" method="post">
        <input type="submit" class="btn btn-danger" value="Delete"/>
        <input type="hidden" name="_method" value="delete">
        <a href="/posts">Back</a>
    </form>
</div>
</body>
</html>
