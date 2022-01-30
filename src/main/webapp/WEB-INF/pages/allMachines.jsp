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
    <a class="active" href="/allMachines"> Mашины </a>
    <a href="/analysis/worker/jobs"> Анализ работ</a>
    <a href="/workOrder/home"> Наряды </a>
    <a href="/admin/workers"> Работники </a>
    <a href="/admin/allServiceMachines"> Сервисные Mашины</a>
    <a href="/machineType/allMachineTypes"> Типы Машин</a>
    <a href="/wareHouse"> Склад </a>
    <a href="/price/mainPage"> Прайс </a>
      <a href="/offer/mainPage">  Заказы </a>
</div>

<div class="mainContent">

    <h2 style="text-align: center">Машины</h2>

    <form action="/allMachines/filtered" method="get">
        <B>Фильтры | </B> модел: <input type="text" name="model" value="${model}"> &nbsp;&nbsp;
        серийный номер։ <input type="text" name="serialNumber" value="${serialNumber}">
        <input type="submit" value="обновить список">
    </form>
    <br>
    <a href="/allMachines/maintainedByIKO"> Обслуживаемые Дистрибютором</a>
    <br>
    <br>
    <table class="mainTables">
        <tr>
            <th>N</th>
            <th>Модель</th>
            <th>Серийный номер</th>
            <th>Владелец</th>
            <th>Год производства</th>
            <th>Последняя инфо</th>
            <th>Дата инфо</th>
            <th>Наработка</th>
            <th>Обслуж. ДБ</th>

        </tr>

        <% int i = 1; %>
        <c:forEach items="${machineList}" var="machine">
            <tr>
                <td><%= i++%>
                </td>
                <td>${machine.model}</td>
                <td><a href="/customer/machine/${machine.id}"> ${machine.serialNumber} </a></td>
                <td>${machine.customer}</td>
                <td>${machine.productionYear}</td>
                <td> <a href="/customer/machine/historyRecord/${machine.lastInfoId}"> ${machine.lastInfo} </a> </td>
                <td>${machine.lastInfoDate}</td>
                <td>${machine.lastSMR}</td>
                <td>${machine.maintainedByIko}</td>
            </tr>
        </c:forEach>
        </tr>
    </table>
</div>
</body>
</html>
