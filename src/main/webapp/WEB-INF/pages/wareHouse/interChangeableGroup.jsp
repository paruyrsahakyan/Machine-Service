<%--
  Created by IntelliJ IDEA.
  User: Paruyr
  Date: 2/17/2019
  Time: 11:28 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>Взаимозаменяемая  группа </title>
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
    <a href="/allCustomers"> Компании </a>
    <a href="/allMachines"> Mашины </a>
    <a href="/analysis/worker/jobs"> Анализ работ</a>
    <a href="/workOrder/home"> Наряды </a>
    <a href="/admin/workers"> Работники </a>
    <a href="/admin/allServiceMachines"> Сервисные Mашины</a>
    <a href="/machineType/allMachineTypes"> Типы Машин</a>
    <a  class="active" href="/wareHouse"> Склад </a>
    <a href="/price/mainPage"> Прайс </a>
</div>
<div style="text-align: center">
    <h3>Взаимозаменяемая  группа </h3>
    <br> <br>

    <table style="width: auto" align="center">
        <tr>
            <th> ${basicPartNumber} </th>
            <th> <a href="/wareHouse/interChangeableGroup/${basicPartNumber}/deleteGroup"  style="color: darkred">
                Удалить группу </a>
            </tr>
           <c:forEach items="${interChangeablePartList}" var="interChangeablePart" >
        <tr>
               <td>
                ${interChangeablePart.partNumber}
                </td>
                <td>
                    <a href="/wareHouse/interChangeablePart/${interChangeablePart.id}/deleted">
                    Удалить </a>
                </td>
        </tr>
                        </c:forEach>
       </div>
</body>
</html>
