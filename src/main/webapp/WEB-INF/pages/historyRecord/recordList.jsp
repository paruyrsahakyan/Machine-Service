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
    <a href="/machineType/allMachineTypes"> Типы Машин</a>
    <a href="/wareHouse"> Склад </a>
    <a href="/price/mainPage"> Прайс </a>
</div>
<div class="mainContent">
<h2>История Машины</h2>
<br>

    <a href="/customer/machine/${machine.id}" >
        <H3> ${machine.model} : sn${machine.serialNumber} </H3>
    </a>

    <form action="/customer/machine/historyRecord/recordList/${machine.id}">
        <input type="date" name="startDate" value="${startDate}">
        <input type="date" name="endDate" value = "${endDate}">
        <input type="submit" value="Фильтр по датам">
        <input type="button" onclick="location.href='/customer/machine/historyRecord/createHistoryRecord/${machine.id}';" value="Создать Новый" />
           </form>

    <br> <br>
    <table class="mainTables">
        <tr>
            <th>N</th>
            <th>Дата</th>
            <th>Описание работы</th>
            <th>Моточасы</th>
            <th>Потраченное время</th>
        </tr>

        <% int i = 1; %>
        <c:forEach items="${recordList}" var="record">
            <tr>
                <td><%= i++%> </td>
                <td>${record.recordDate}</td>
                <td><a href="/customer/machine/historyRecord/${record.id}"> ${record.title} </a></td>
                <td>${record.SMR}</td>
                <td>${record.laborHour}</td>
            </tr>
        </c:forEach>
        </tr>
    </table>
    </div>
</body>
</html>

