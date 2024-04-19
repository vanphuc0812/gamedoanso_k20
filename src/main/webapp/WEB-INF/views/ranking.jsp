<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="utils.UrlUtils" %>
<%@ page import="model.Player" %>
<%@ page import="java.time.Duration" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="utils.JspUtils" %>

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
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="<%=request.getContextPath() + UrlUtils.HOME%>">Game Đoán Số</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link <%=request.getServletPath().equals(JspUtils.HOME)?"active" : ""%>"
                       aria-current="page" href="<%=request.getContextPath() +UrlUtils.HOME%>">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <%=request.getServletPath().equals(JspUtils.GAME)?"active" : ""%>"
                       href="<%=request.getContextPath() +UrlUtils.GAME%>">Game</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <%=request.getServletPath().equals(JspUtils.RANK)?"active" : ""%>"
                       href="<%=request.getContextPath() +UrlUtils.RANKING%>">Rank</a>
                </li>
            </ul>
        </div>
        <div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown"
                    aria-expanded="false">
                <%--                <%= ((Player) session.getAttribute("currentuser")).getUsername()%>--%>
                <%= ((Player) session.getAttribute("currentuser")).getUsername()%>
            </button>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="<%=request.getContextPath() + UrlUtils.LOGOUT%>">Logout</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="mt-5">
    <div class="row justify-content-around">
        <div class="col-4 justify-content-between">
            <h1 class="text-primary text-center mb-5">Ranking By Play Time</h1>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">GameID</th>
                    <th scope="col">Target Number</th>
                    <th scope="col">Player</th>
                    <th scope="col">Start Time</th>
                    <th scope="col">End Time</th>
                    <th scope="col">Play Time</th>
                </tr>
                </thead>
                <tbody class="table-group-divider">
                <c:forEach var="game" items="${listRankByPlayTime}" varStatus="loop">
                    <tr>
                        <th scope="row">${listRankByPlayTime.indexOf(game) + 1}</th>
                        <td>${game.getId()}</td>
                        <td>${game.getTargetNumber()}</td>
                        <td>${game.getUsername()}</td>
                        <td>${game.getStartTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))}</td>
                        <td>${game.getEndTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))}</td>
                        <td>${Duration.between(game.getStartTime(),game.getEndTime()).getSeconds()}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col-4 justify-content-between">
            <h1 class="text-primary text-center mb-5">Ranking By Guess Times</h1>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">GameID</th>
                    <th scope="col">Target Number</th>
                    <th scope="col">Player</th>
                    <th scope="col">Start Time</th>
                    <th scope="col">End Time</th>
                    <th scope="col">Guess Times</th>
                </tr>
                </thead>
                <tbody class="table-group-divider">
                <c:forEach var="game" items="${listRankByGuessTime}" varStatus="loop">
                    <tr>
                        <th scope="row">${listRankByGuessTime.indexOf(game) + 1}</th>
                        <td>${game.getId()}</td>
                        <td>${game.getTargetNumber()}</td>
                        <td>${game.getUsername()}</td>
                        <td>${game.getStartTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))}</td>
                        <td>${game.getEndTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))}</td>
                        <td>${game.getGuessTimes()}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>