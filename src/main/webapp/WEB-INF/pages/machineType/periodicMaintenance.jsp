<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title> Техническое Обслуживание</title>
    <style>
        table, th, td {
            border: 1px solid black;
            align-self: center;
        }

    </style>
</head>
<body>
<div style="text-align: center">
    <h2> Техническое Обслуживание</h2> <br>

    <h3>Тип машины &nbsp; ${machineType.typeDescription}&nbsp; </h3>

    <a href="/"> Главное меню</a>
    <a href="/machineType/allMachineTypes">   Типы машин</a>
    <br><br>
    <a href="/machineType/${machineType.id}/periodicMaintenance/${periodicMaintenance.id}/update"> Редактировать</a>
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
