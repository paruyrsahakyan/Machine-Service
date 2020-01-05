<%--
  Created by IntelliJ IDEA.
  User: Paruyr
  Date: 2/16/2019
  Time: 11:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>Взаимозаменяемые детали</title>
    <style><%@include file="/WEB-INF/pages/CSS/topNavigation.css"%></style>
    <style><%@include file="/WEB-INF/pages/CSS/tables.css"%></style>
    <style>
        table, th, td {
            border: 1px solid black;
            align-self: center;
        }

    </style>
</head>
<body>
<div class="topnav">
    <a href="/"> Главное меню</a>
    <a  href="/allCustomers"> Компании </a>
    <a href="/allMachines"> Mашины </a>
    <a href="/analysis/worker/jobs"> Анализ работ</a>
    <a href="/workOrder/home"> Наряды </a>
    <a href="/admin/workers"> Работники </a>
    <a href="/admin/allServiceMachines"> Сервисные Mашины</a>
    <a href="/machineType/allMachineTypes"> Типы Машин</a>
    <a class="active" href="/wareHouse"> Склад </a>
</div>
<div style="text-align: center">
<h3>Взаимозаменяемые детали</h3>
    <br>
<a href="/wareHouse/interChangeableParts/createNew"> Создать взаимозаменяемую запчасть </a>
    <br> <br>
<table style="width: auto" align="center">
       <tr>
        <th> N </th>
        <th> Основной артикул </th>
        <th> Замена1 </th>
        <th> Замена2 </th>
        <th> Замена3 </th>
        <th> Замена4 </th>
        <th> Замена5 </th>
        <th> Замена6 </th>
    </tr>
    <% int i = 1; %>
    <c:forEach items="${interChangeableGroupList}" var="interChangeableGroup" >
        <tr>
            <td><%= i++%> </td>
            <th>
                <a href="/wareHouse/interChangeableGroup/${interChangeableGroup.basicPartNumber}">
                        ${interChangeableGroup.basicPartNumber}
                            </a>
            </th>
            <c:forEach items="${interChangeableGroup.interChangeablePartsList}" var="interChangeablePart">
            <td>${interChangeablePart}</td>
            </c:forEach>
        </tr>
    </c:forEach>
    </div>
</body>
</html>
