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

        <style>
            table, th, td {
                border: 1px solid black;
                align-self: center;
            }

        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Редактирование ТО</title>

<body>
<div style="text-align: center">
    <h2>Редактирование  ТО</h2> <br>
    <h3>Тип машины &nbsp; ${machineType.typeDescription}</h3>
    <form:form action="/machineType/${machineType.id}/maintenance/${periodicMaintenance.id}/updated" method="post">
        Периодичность ТO(мч)  <input name ="smr" type="number" value="${periodicMaintenance.smr}">
        <input type="submit" value="Сохранить">
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
                <tr>
                    <td><%= i++%> </td>
                    <td> <input type='text' name= 'partNumber[]' value="${maintenancePart.partNumber}"> </td>
                    <td> <input type='text' name= 'description[]' value="${maintenancePart.partType}"> </td>
                    <td> <input type='text' name= 'unit[]' value="${maintenancePart.unit}"> </td>
                    <td> <input type='number' name= 'quantity[]' value="${maintenancePart.quantity}"> </td>

            </c:forEach>
                </table>
    </form:form>
</div>
</body>
</html>
