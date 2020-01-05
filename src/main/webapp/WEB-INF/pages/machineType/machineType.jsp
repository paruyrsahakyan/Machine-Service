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
    <style><%@include file="/WEB-INF/pages/CSS/topNavigation.css"%></style>
    <style><%@include file="/WEB-INF/pages/CSS/tables.css"%></style>
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
    <a class="active"  href="/machineType/allMachineTypes"> Типы Машин</a>
    <a href="/wareHouse"> Склад </a>
</div>

<div class="mainContent">
    <h2>Тип Машины: ${machineType.typeDescription} </h2>
    <a href="/"> Главное меню</a>
    <br><br>

    <a href="/machineType/${machineType.id}/update" style="bottom: auto"> Редактировать</a>
    &nbsp; &nbsp;
    <a href="/machineType/${machineType.id}/maintenance/newMaintenanceCreation" style="bottom: auto"> Добавить TO</a>
    &nbsp; &nbsp;
    <a href="/machineType/${machineType.id}/deleted"
       onclick="return confirm('!!!Вы уверены что хатите удалить тип машины!!!');"
       style="color: crimson;">Удалить Тип Машины</a>
    <br> <br>

            <c:forEach items="${maintenanceList}" var="maintenance" >
            <table class="mainTables">
                <tr>
                    <th colspan="5">
                      <a href="/machineType/${machineType.id}/periodicMaintenance/${maintenance.id}" style="text-align: center">
                               TO ${maintenance.smr} мч
                      </a>
                    </th>
                </tr>
                <tr>
                    <td><b> N </b>  </td>
                    <td><b> Артикул </b> </td>
                    <td><b> Описание </b> </td>
                    <td><b> Единица </b> </td>
                    <td><b> Количество </b> </td>
                </tr>
            <% int i = 1; %>
            <c:forEach items="${maintenance.maintenanceParts}" var="maintenancePart" >

            <tr>
                <td><%= i++%> </td>
                <td>${maintenancePart.partNumber}</td>
                <td>${maintenancePart.partType}</td>
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