<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Meal list</title>
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
<p><a href="addMeal.jsp">Add Meal</a></p>
<h2>Meals</h2>
<table>
    <thead>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="meals" items="${requestScope.mealTo}">
        <tr style="color: ${meals.isExcess() ? 'red' : 'green'}">
            <c:set var="dateTime" value="${meals.dateTime}"/>
            <td>${fn:replace(dateTime,"T"," ")}</td>
            <td>${meals.description}</td>
            <td>${meals.calories}</td>
            <td><a href="editMeal.jsp">Update</a></td>
            <td><a href="MealServlet?action=delete&id=<c:out value="${meals.id}"/>">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
