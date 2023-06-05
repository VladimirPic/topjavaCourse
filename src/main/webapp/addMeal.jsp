<%--
  Created by IntelliJ IDEA.
  User: pichk
  Date: 04.06.2023
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Add meal</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Add Meal</h2>
<form method="post" action="<c:url value="/create"/>">
    <label for="dateTime">dateTime:</label>
    <input type="datetime-local" name="dateTime" id="dateTime" required><br>

    <label for="description">description:</label>
    <input type="text" name="description" id="description" required><br>

    <label for="calories">calories:</label>
    <input type="number" name="calories" id="calories" required><br>

    <button type="submit" name="action" value="insert">Add Meal</button>
</form>
</body>
</html>
