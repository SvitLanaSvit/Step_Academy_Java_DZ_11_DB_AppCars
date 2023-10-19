<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Svitlana
  Date: 18.10.2023
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cars</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
    <div class="container" style="margin-top: 20px">
        <c:if test="${not empty message}">
            <div id="message" class="alert alert-success">${message}</div>
            <script>
                setTimeout(function(){
                    document.querySelector(".alert-success").style.display = "none"
                }, 3000);
            </script>
        </c:if>
        <form action="table-cars" method="post">
            <input type="hidden" name="param" value="connect" />
            <input type="submit" class="btn btn-success" value="Connect to DB">
        </form>
        <form action="table-cars" method="post">
            <input type="hidden" name="param" value="insert" />
            <input type="submit" class="btn btn-success" value="Insert data to DB">
        </form>
        <form action="table-cars" method="post">
            <input type="hidden" name="param" value="allCars" />
            <input type="submit" class="btn btn-success" value="Show all cars">
        </form>
        <form action="table-cars" method="post">
            <input type="hidden" name="param" value="allVehicle" />
            <input type="submit" class="btn btn-success" value="Show all vehicle name">
        </form>
        <form action="table-cars" method="post">
            <input type="hidden" name="param" value="countCars" />
            <input type="submit" class="btn btn-success" value="Show vehicles with count of cars">
        </form>
        <form action="table-cars" method="post">
            <input type="hidden" name="param" value="maxCars" />
            <input type="submit" class="btn btn-success" value="Show vehicle with max count of cars">
        </form>
        <form action="table-cars" method="post">
            <input type="hidden" name="param" value="minCars" />
            <input type="submit" class="btn btn-success" value="Show vehicle with min count of cars">
        </form>
        <form action="table-cars" method="post">
            <div class="mb-3 w-25">
                <label for="year" class="form-label">Year of issue</label>
                <input type="hidden" name="param" value="searchByYear" />
                <input type="number" class="form-control" id="year" name="year" min="2000" max="2050"/>
                <input type="submit" class="btn btn-success" value="Search cars by year" style="margin-top: 20px">
            </div>
        </form>
    </div>
</body>
</html>
