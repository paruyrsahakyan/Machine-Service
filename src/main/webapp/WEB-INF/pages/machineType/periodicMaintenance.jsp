
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: Paruyr
  Date: 1/10/2019
  Time: 12:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title> Техническое Обслуживание</title>
</head>
<body>
<div style="text-align: center">
    <h2> Техническое Обслуживание</h2> <br>

    <h3>Тип машины &nbsp; ${machineType.typeDescription}&nbsp; </h3>

    <a href="/"> Главное меню</a>
    <br><br>
    <a href="/machineType/${machineType.id}/periodicMaintenance/${periodicMaintenance.id}"
       style="bottom: auto" > Редактировать</a>
    &nbsp; &nbsp;
    <a href="/machineType/${machineType.id}/periodicMaintenance/${periodicMaintenance.id}/maintenanceDeleted"
       onclick="!!!! Вы уверяны что хатите удалить? !!!" style="bottom: auto"> Удалить </a>
       <br> <br>

    <table style="width: auto" align="center">
        <tr>
            <th colspan="4">
                <a href="/admin/machineType/${machineType.id}/periodicMaintenance/${periodicMaintenance.id}">
                  TO &nbsp; ${periodicMaintenance.smr}
                </a>
            </th>
        </tr>
        <tr>
            <td> N </td>
            <td> Описание </td>
            <td> Артикул </td>
            <td> Единица </td>
            <td> Количество </td>
        </tr>
            <% int i = 1; %>
        <c:forEach items="${periodicMaintenance.maintenanceParts}" var="maintenancePart" >
        <tr>
            <td><%= i++%> </td>
            <td>${maintenancePart.partType}</td>
            <td>${maintenancePart.partNumber}</td>
            <td>${maintenancePart.unit}</td>
            <td>${maintenancePart.quantity}</td>
        </tr>
        </c:forEach>

</div>
</body>
</html>
