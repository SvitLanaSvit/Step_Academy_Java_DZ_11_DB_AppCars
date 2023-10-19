<%--
  Created by IntelliJ IDEA.
  User: Svitlana
  Date: 19.10.2023
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manufacturer of min car</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>VEHICLE NAME</th>
                <th>COUNT OF CARS</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${manufacturer.manufacturer}</td>
                <td>${manufacturer.countCars}</td>
            </tr>
            </tbody>
        </table>
        <a href="table-cars" class="btn btn-outline-secondary">BACK</a>
    </div>
</body>
</html>
