<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Svitlana
  Date: 19.10.2023
  Time: 18:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All cars</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>MANUFACTURER</th>
                    <th>VEHICLE NAME</th>
                    <th>ENGINE VOLUME</th>
                    <th>YEAR OF ISSUE</th>
                    <th>CAR COLOR</th>
                    <th>CAR TYPE</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${cars}" var="car">
                <tr>
                    <td>${car.car_id}</td>
                    <td>${car.manufacturer}</td>
                    <td>${car.vehicle_name}</td>
                    <td>${car.engine_volume}</td>
                    <td>${car.year_of_issue}</td>
                    <td>${car.car_color}</td>
                    <td>${car.car_type}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <a href="table-cars" class="btn btn-outline-secondary">BACK</a>
    </div>
</body>
</html>
