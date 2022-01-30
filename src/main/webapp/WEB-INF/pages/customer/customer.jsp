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
    <a class="active" href="/allCustomers"> Компании </a>
    <a href="/allMachines"> Mашины </a>
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
<h2>Информация о заказчике</h2>

    <br><br>

    <a href="/customer/updateCustomer/${customer.id}" style="bottom: auto"> Редактировать</a>
    &nbsp; &nbsp;
    <a href="/customer/machine/createMachine/${customer.id}" style="bottom: auto"> Добавить машину</a>
    &nbsp; &nbsp;
    <a href="/customer/deleteCustomer/${customer.id}"
       onclick="return confirm('!!!Вы уверены что хатите удалить компанию!!!');"
       style="color: crimson;">Удалить компанию</a>
    <br> <br>

<table class="mainTables">
    <tr>
        <th>Название Компании: </th>
        <td>${customer.name}</td>
    </tr>
    <tr>
        <th>Kонтакты:</th>
        <td>${customer.contacts}</td>
    </tr>
    <tr>
        <th>Наличие договора:</th>
        <td>${customer.contract}</td>
    </tr>
       <tr>
            <th>Другие информации</th>
        <td>${customer.otherInfo}</td>

    </tr>

</table>
    <h3>Парк машин</h3>
    <table class="mainTables">
        <tr>
            <th>N</th>
            <th>Модель</th>
            <th>Серийный номер</th>
            <th>Год производства</th>
            <th>Последняя инфо</th>
            <th>Дата информации</th>
            <th>Наработка</th>

              </tr>

        <% int i = 1; %>
        <c:forEach items="${machineList}" var="machine">
            <tr>
                <td><%= i++%> </td>
                <td>${machine.model}</td>
                <td><a href="/customer/machine/${machine.id}"> ${machine.serialNumber} </a></td>
                <td>${machine.productionYear}</td>
                <td> <a href="/customer/machine/historyRecord/${machine.lastInfoId}"> ${machine.lastInfo} </a> </td>
                <td> ${machine.lastInfoDate}</td>
                <td>${machine.lastSMR}</td>
            </tr>
        </c:forEach>
        </tr>
    </table>
</div>

</body>
</html>
