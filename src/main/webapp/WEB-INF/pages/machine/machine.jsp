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
</div>

<div class="mainContent">
    <h2>Информация о машине</h2>
        <br>
    <table class="mainTables">
        <tr>
            <th>Тип Машины:</th>
            <td>${machine.machineType.typeDescription}</td>
        </tr>
        <tr>
            <th>Обслуживает ДБ:</th>
            <td>${machine.maintainedByIko}</td>
        </tr>
        <tr>
            <th>Модель:</th>
            <td>${machine.model}</td>
        </tr>
        <tr>
            <th>Серийный номер:</th>
            <td>${machine.serialNumber}</td>
        </tr>
        <tr>
            <th>Модель ДВС</th>
            <td>${machine.engineModel}</td>

        </tr>
        <tr>
            <th>Серийный номер ДВС</th>
            <td>${machine.engineSerialNumber}</td>

        </tr>
        <tr>
            <th>Владелец</th>
            <td><a href="/customer/${customerId}"> ${machine.customer}</a></td>

        </tr>

        <tr>
            <th>Год производства</th>
            <td>${machine.productionYear}</td>

        </tr>

        <tr>
            <th>Дополнительная информации</th>
            <td>${machine.otherInfo}</td>

        </tr>
    </table>

    <br> <br>
    <a href="/customer/machine/updateMachine/${machine.id}" style="bottom: auto"> Редактировать</a> &nbsp;<b>|</b>
    <a href="/customer/machine/historyRecord/createHistoryRecord/${machine.id}">Создать новую запись истории</a>
    &nbsp;<b>|</b>
    <a href="/customer/machine/historyRecord/recordList/${machine.id}"> Пoказать работы </a>
    <br>
    <br>
    <a href="/customer/machine/deleteMachine/${machine.id}"
       onclick="return confirm('!!!Вы уверены что хатите удалить машину!!!');"
       style="color: crimson;">Удалить машину</a>
</div>
</body>
</html>
