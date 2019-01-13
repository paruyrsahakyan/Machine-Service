<%--
  Created by IntelliJ IDEA.
  User: Paruyr
  Date: 1/9/2019
  Time: 11:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
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
<body>
<div style="text-align: center">
    <h2>Тип Машины: ${machineType.description} </h2>
    <a href="/"> Главное меню</a>
    <br><br>

    <a href="/admin/machineType/{$machineType.id}/update" style="bottom: auto"> Редактировать</a>
    &nbsp; &nbsp;
    <a href="/admin/machineType/{$machineType.id}/addPeriodicMaintenance" style="bottom: auto"> Добавить TO</a>
    &nbsp; &nbsp;
    <a href="/admin/machineType/{$machineType.id}/deleteMachineType"
       onclick="return confirm('!!!Вы уверены что хатите удалить тип машины!!!');"
       style="color: crimson;">Удалить Тип Машины</a>
    <br> <br>

            <c:forEach items="${periodicMaintenanceList}" var="periodicMaintenance" >
            <table style="width: auto" align="center">
                <tr>
                    <th colspan="4">
                      <a href="/admin/machineType/${machineType.id}/periodicMaintenance/${periodicMainteance.id}">
                                   ${periodicMaintenance.smr}
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
            <c:forEach items="${periodicMaintenance.maintenancePartList}" var="maintenancePart" >

            <tr>
                <td><%= i++%> </td>
                <td>${maintenancePart.description}</td>
                <td>${maintenancePart.partNumber}</td>
                <td>${maintenancePart.unit}</td>
                <td>${maintenancePart.quantity}</td>
                </tr>
            </c:forEach>
            </table>
            <br><br>
        </c:forEach>

    </div>
</body>
</html>