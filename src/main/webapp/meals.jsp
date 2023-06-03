<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Meals</title>
    <style>
        TABLE {
            border-collapse: collapse;
        }

        TD, TH {
            padding: 6px;
            border: 2px solid black;
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table>
    <thead>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="meals" items="${requestScope.mealTo}">
        <tr style="color: ${meals.isExcess() ? 'red' : 'green'}">
            <td>${meals.dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))}</td>
            <td>${meals.description}</td>
            <td>${meals.calories}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
