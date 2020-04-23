<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title> Техническое Обслуживание</title>
    <style><%@include file="/WEB-INF/pages/CSS/topNavigation.css"%></style>
    <style><%@include file="/WEB-INF/pages/CSS/tables.css"%></style
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
    <a class="active" href="/machineType/allMachineTypes"> Типы Машин</a>
    <a href="/wareHouse"> Склад </a>
    <a href="/price/mainPage"> Прайс </a>
</div>
<div class="mainContent">
    <h2> Техническое Обслуживание</h2> <br>

    <h3>Тип машины &nbsp; ${machineType.typeDescription}&nbsp; </h3>

    <a href="/machineType/${machineType.id}"> Тип машины</a>
    <br><br>
    <a href="/machineType/${machineType.id}/periodicMaintenance/${periodicMaintenance.id}/updatePage"> Редактировать</a>
    &nbsp; &nbsp;
    <a href="/machineType/${machineType.id}/periodicMaintenance/${periodicMaintenance.id}/deleted"
       onclick="!!!! Вы уверяны что хатите удалить? !!!" > Удалить </a>
       <br> <br>

    <table style="width: auto" align="center">
        <tr>
            <th colspan="5">
                TO &nbsp; ${periodicMaintenance.smr}
            </th>
        </tr>
        <tr>
            <td> N </td>
            <td> Артикул </td>
            <td> Описание </td>
            <td> Единица </td>
            <td> Количество </td>
        </tr>
            <% int i = 1; %>
        <c:forEach items="${periodicMaintenance.maintenanceParts}" var="maintenancePart" >
        <tr>
            <td><%= i++%> </td>
            <td>${maintenancePart.partNumber}</td>
            <td>${maintenancePart.partType}</td>
            <td>${maintenancePart.unit}</td>
            <td>${maintenancePart.quantity}</td>
        </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
