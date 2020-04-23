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
    <a class="active"  href="/workOrder/home"> Наряды </a>
    <a href="/admin/workers"> Работники </a>
    <a href="/admin/allServiceMachines"> Сервисные Mашины</a>
    <a href="/machineType/allMachineTypes"> Типы Машин</a>
    <a href="/wareHouse"> Склад </a>
    <a href="/price/mainPage"> Прайс </a>
</div>

<div class="mainContent">

    <h2>Наряд</h2>
    <br> <br>
    <table class="mainTables">
        <tr>
             <th>Заказчик:</th>
            <td>${workOrder.machine.customer}</td>
        </tr>
        <tr>
        <th>Машина:</th>
        <td>${workOrder.machine.model} &nbsp; sn ${workOrder.machine.serialNumber}</td>
    </tr>
         <tr>
            <th>Работа:</th>
            <td>ТО ${workOrder.periodicMaintenance.smr}</td>
        </tr>
        <tr>

            <th>Наработка:</th>
            <td>${workOrder.smr}</td>
        </tr>
        <tr>
            <th>Дата:</th>
            <td>${workOrder.orderDate}</td>
        </tr>
        <tr>
            <th>Ответственный:</th>
            <td>${workOrder.worker}</td>
        </tr>
        <tr>
            <th>Сервисная Машина:</th>
            <td> ${workOrder.serviceMachine}</td>
        </tr>

    </table>

    <br> <br>
    <a href="/workOrder/${workOrder.id}/update" style="bottom: auto"> Редактировать</a> &nbsp;<b>|</b>
    <a href="/workOrder/${workOrder.id}/completeWorkOrder">Закрыть Наряд</a>
    &nbsp;<b>|</b>
       <a href="/workOrder/${workOrder.id}/deleted"
       onclick="return confirm('!!!Вы уверены что хатите удалить машину!!!');"
       style="color: crimson;">Удалить Наряд</a>
    <br><br>
     <a href="/workOrder/${workOrder.id}/serviceReport" style="color: darkgreen"> Сервисный отчет</a> &nbsp;<b>|</b>
    <a href="/workOrder/${workOrder.id}/wareHouseRequest" style="color: sienna"> Заявка на склад</a> &nbsp;<b>|</b>
    <a href="/workOrder/${workOrder.id}/maintenanceRequest" style="color: darkblue"> Заявка на TO</a>


 </div>
</body>
</html>
