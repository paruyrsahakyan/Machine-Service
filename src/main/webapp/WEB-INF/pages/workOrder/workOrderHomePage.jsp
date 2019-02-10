<%--
  Created by IntelliJ IDEA.
  User: Paruyr
  Date: 2/10/2019
  Time: 12:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Наряды</title>
    <head>
        <style>
            table {
                font-family: arial, sans-serif;
                border-collapse: collapse;
                width: auto;

            }

            td, th {
                border: 1px solid #dddddd;
                text-align: left;
                padding: 8px;
            }

            tr:nth-child(even) {
                background-color: #dddddd;
            }
        </style>
    </head>
</head>
<body>
<div style="text-align: center">

    <h2>Наряды</h2>

    <a href="/"> Главное меню</a>
    <br> <br>
    <h5 style="color: #dc161c"> ${workOrderList.size()} невыполненных нарядов  &nbsp;&nbsp;&nbsp;&nbsp;
    <a href="/workOrder/new"> Создать Новый наряд </a>
    </h5>
    <br>
    <H3 Невыполненные нарады>

<table style="width: auto" align="center">
<tr>
    <th>N</th>
    <th>Заказчик</th>
    <th>Машина</th>
    <th>Работа</th>
    <th>Ответственный</th>
    </tr>

<% int i = 1; %>
<c:forEach items="${workOrderList}" var="workOrder">
    <tr>
    <th><a href="/workOrder/${workOrder.id}}"> <%= i++%> </a>
    </th>
    <td> ${workOrder.machine.customer}</td>
    <td>${workOrder.machine.model} &nbsp; sn ${workOrder.machine.serialNumber}</td>
    <td>${workOrder.worker}</td>
    </tr>
    </c:forEach>
</div>
</body>
</html>
