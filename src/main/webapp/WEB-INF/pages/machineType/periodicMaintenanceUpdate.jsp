<%--
  Created by IntelliJ IDEA.
  User: Paruyr
  Date: 1/10/2019
  Time: 12:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <style><%@include file="/WEB-INF/pages/CSS/topNavigation.css"%></style>
    <style><%@include file="/WEB-INF/pages/CSS/tables.css"%></style>

        <style>
            table, th, td {
                border: 1px solid black;
                align-self: center;
            }

        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Редактирование ТО</title>
    </head>
<body>
<div class="topnav">
    <a href="/"> Главное меню</a>
    <a class="active" href="/allCustomers"> Компании </a>
    <a href="/allMachines"> Mашины </a>
    <a href="/analysis/worker/jobs"> Анализ работ</a>
    <a href="/workOrder/home"> Наряды </a>
    <a href="/admin/workers"> Работники </a>
    <a href="/admin/allServiceMachines"> Сервисные Mашины</a>
    <a href="/machineType/allMachineTypes"> Типы Машин</a>
    <a href="/wareHouse"> Склад </a>
    <a href="/price/mainPage"> Прайс </a>
</div>

<div class="mainContent">
    <h2>Редактирование  ТО</h2> <br>
    <h3>Тип машины &nbsp; ${machineType.typeDescription}</h3>
    <form:form action="/machineType/${machineType.id}/periodicMaintenance/${periodicMaintenance.id}/updated" method="post">
        Периодичность ТO(мч)  <input name ="smr" type="number" value="${periodicMaintenance.smr}">
        <input type="submit" value="Сохранить">
        <button> <a href="/machineType/${machineType.id}" style="text-decoration:none">Отменить </a> </button>
        <br> <br>
        <table id = "table" align="center">
            <tr>
                <th>Артикул</th>
                <th>Описание</th>
                <th>Единица</th>
                <th>Количесто</th>
            </tr>
            <% int i = 1; %>
            <c:forEach items="${periodicMaintenance.maintenanceParts}" var="maintenancePart" >
                <tr id="${maintenancePart.id}">
                    <td> <input type='text' name= 'partNumber[]' value="${maintenancePart.partNumber}"> </td>
                    <td> <input type='text' name= 'description[]' value="${maintenancePart.partType}"> </td>
                    <td> <input type='text' name= 'unit[]' value="${maintenancePart.unit}"> </td>
                    <td> <input type='number' name= 'quantity[]' value="${maintenancePart.quantity}"> </td>
                    <td> <button onclick="deleteRow(${maintenancePart.id})"> Удалить строку </button></td>
                </tr>
            </c:forEach>
                </table>
    </form:form>
        <button id ="addPart"  onclick="addRow()">Добавить строку</button>
</div>
<script>
    function deleteRow(rowId) {
        document.getElementById(rowId).innerHTML="";
    }
        function addRow(){
        var table=document.getElementById("table");
        var row = table.insertRow();
        var partNumberCell =row.insertCell(0);
        var descriptionCell =row.insertCell(1);
        var unitCell =row.insertCell(2);
        var quantityCell =row.insertCell(3);
       partNumberCell.innerHTML ="<input type='text' name= 'partNumber[]' required>";
        descriptionCell.innerHTML ="<input type='text' name= 'description[]' required>";
        unitCell.innerHTML ="<input type='text' name= 'unit[]'> required";
        quantityCell.innerHTML ="<input type='number' name= 'quantity[]' required>";
               }
    </script>
</body>
</html>
