<%--
  Created by IntelliJ IDEA.
  User: pichk
  Date: 04.06.2023
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Edit meal</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Edit meal</h2>
<form method="post" action="<c:url value="/edit"/>">

    <label for="dateTime">dateTime:</label>
    <input type="datetime-local" name="dateTime" id="dateTime" required><br>

    <label for="description">description:</label>
    <input type="text" name="description" id="description" required><br>

    <label for="calories">calories:</label>
    <input type="number" name="calories" id="calories" required><br>

    <button type="submit" name="action" value="insert">Save</button>
    <button formnovalidate onclick="window.location.href='meals.jsp'">Cancel</button>
</form>
</body>
</html>
