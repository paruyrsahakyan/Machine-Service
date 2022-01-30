<%--
  Created by IntelliJ IDEA.
  User: Paruyr
  Date: 1/6/2019
  Time: 11:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Типы Машин</title>
    <style><%@include file="/WEB-INF/pages/CSS/topNavigation.css"%></style>
    <style>
        table, th, td {
            border: 1px solid black;

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
    <a  class="active" href="/machineType/allMachineTypes"> Типы Машин</a>
    <a href="/wareHouse"> Склад </a>
    <a href="/price/mainPage"> Прайс </a>
     <a href="/offer/mainPage">  Заказы </a>


</div>
<div style="text-align: center">
    <h2 style="text-align: center">Типы Машин</h2>
     <a href="/machineType/newTypeCreation"> Добавить Новый Тип</a>
    <br>
    <br>

    <table align="center">
        <tr>
            <th>N</th>
            <th>Тып Машины</th>
             </tr>
        <% int i = 1; %>

        <c:forEach items="${machineTypeList}" var="machineType">
            <tr>
                <td> <%= i++%> </td>
                <td> <a href="/machineType/${machineType.id}"> ${machineType.typeDescription} </a> </td>

            </tr>
        </c:forEach>

    </table>

</div>
</body>
</html>

