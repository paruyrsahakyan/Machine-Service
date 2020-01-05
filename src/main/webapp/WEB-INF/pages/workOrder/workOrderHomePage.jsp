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
    <style><%@include file="/WEB-INF/pages/CSS/topNavigation.css"%></style>
    <style><%@include file="/WEB-INF/pages/CSS/tables.css"%></style>

    </head>
</head>
<body>
<div class="topnav">
    <a href="/"> Главное меню</a>
    <a href="/allCustomers"> Компании </a>
    <a href="/allMachines"> Mашины </a>
    <a href="/analysis/worker/jobs"> Анализ работ</a>
    <a class="active" href="/workOrder/home"> Наряды </a>
    <a href="/admin/workers"> Работники </a>
    <a href="/admin/allServiceMachines"> Сервисные Mашины</a>
    <a href="/machineType/allMachineTypes"> Типы Машин</a>
    <a href="/wareHouse"> Склад </a>
</div>
<div class="mainContent">

    <h2>Наряды</h2>
    <br> <br>
    <a href="/workOrder/new"> Создать Новый наряд </a>
        <h3 style="color: #dc161c"> ${workOrderList.size()} невыполненных нарядов  &nbsp;&nbsp;&nbsp;&nbsp;
    </h3>

   <table class="mainTables">
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
    <th><a href="/workOrder/${workOrder.id}"> <%= i++%> </a>
    </th>
    <td> ${workOrder.machine.customer}</td>
    <td>${workOrder.machine.model} &nbsp; sn ${workOrder.machine.serialNumber}</td>
    <td> TO${workOrder.periodicMaintenance.smr} </td>
    <td>${workOrder.worker}</td>
    </tr>
    </c:forEach>
</div>
</body>
</html>
