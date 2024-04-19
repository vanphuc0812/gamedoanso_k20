<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="utils.UrlUtils" %>
<%@ page import="model.Player" %>
<%@ page import="model.Game" %>
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
                    <a class="nav-link " aria-current="page"
                       href="<%=request.getContextPath() +UrlUtils.HOME%>">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link " href="<%=request.getContextPath() +UrlUtils.GAME%>">Game</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath() +UrlUtils.RANKING%>">Rank</a>
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
<div class="container mt-5">
    <div class="row justify-content-between">
        <div class="col-2">
            <h4>${game.getId()}</h4>
        </div>
        <div class="col-5">
            <h1 class="text-center text-primary mb-5">GAME ĐOÁN SỐ</h1>
            <c:choose>
                <c:when test="${game.getIsComplete() == 0}">
                    <form class="d-flex flex-column"
                          action="<%=request.getContextPath() + UrlUtils.GAME + "?gameid=" + ((Game) request.getAttribute("game")).getId() %>"
                          method="post">
                        <div class="mb-3">
                            <input required name="guessNumber" type="text" class="form-control" placeholder="Guess !!!">
                        </div>
                        <button type="submit" class="btn btn-primary mb-5 fs-5">GUESS</button>
                    </form>
                </c:when>
                <c:otherwise>
                    <h1 class="text-success text-center mb-4">BINGO !!!</h1>
                </c:otherwise>
            </c:choose>

            <table class="table">
                <tbody>
                <c:forEach var="guess" items="${game.getReverseGuessList()}" varStatus="loop">
                    <c:choose>
                        <c:when test="${guess.getGuessResult() == 1}">
                            <tr class="table-danger">
                                <th scope="row">${guess.getGuessNumber()}</th>
                                <td colspan="2">Your number is higher than target</td>
                                <td>${guess.getGuessTime()}</td>
                            </tr>
                        </c:when>
                        <c:when test="${guess.getGuessResult() == -1}">
                            <tr class="table-warning">
                                <th scope="row">${guess.getGuessNumber()}</th>
                                <td colspan="2">Your number is lower than target</td>
                                <td>${guess.getGuessTime()}</td>
                            </tr>
                        </c:when>
                        <c:when test="${guess.getGuessResult() == 0}">
                            <tr class="table-success">
                                <th scope="row">${guess.getGuessNumber()}</th>
                                <td colspan="2">Bingo</td>
                                <td>${guess.getGuessTime()}</td>
                            </tr>
                        </c:when>
                    </c:choose>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col-2 text-end">
            <form class="d-flex flex-column"
                  action="<%=request.getContextPath() + UrlUtils.NEW_GAME + "?username=" + ((Player) session.getAttribute("currentuser")).getUsername() %>"
                  method="post">
                <button type="submit" class="btn btn-primary mb-5 fs-5">New Game</button>
            </form>
        </div>
    </div>


</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>