<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="utils.UrlUtils" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!doctype html>
<html lang="en">
<head>
    <title>Example</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<div class="container col-md-4 ">
    <h1 class="text-center text-primary mt-5">LOGIN</h1>
    <form action="<%=request.getContextPath() + UrlUtils.LOGIN %>" method="post">
        <div class="mb-3">
            <label for="exampleInputEmail1" class="form-label">Username</label>
            <input name="username" type="text" class="form-control" id="exampleInputEmail1">
        </div>
        <div class="mb-3">
            <label for="exampleInputPassword1" class="form-label">Password</label>
            <input name="password" type="password" class="form-control" id="exampleInputPassword1">
        </div>
        <div class="mb-3">
            <a class="form-check-label" href="<%=request.getContextPath() + UrlUtils.REGISTER%>">Have not account?
                Register now !</a>
        </div>
        <button type="submit" class="btn btn-primary">Login</button>
    </form>
    <div class="mt-5 alert alert-danger <%=request.getAttribute("errors") == null ? "d-none" : ""%>" role="alert">
        <%=request.getAttribute("errors")%>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>