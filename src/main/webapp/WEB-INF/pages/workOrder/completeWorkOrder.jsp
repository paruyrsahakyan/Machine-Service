<%--
  Created by IntelliJ IDEA.
  User: Paruyr
  Date: 2/9/2019
  Time: 9:46 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style><%@include file="/WEB-INF/pages/CSS/topNavigation.css"%></style>
    <style><%@include file="/WEB-INF/pages/CSS/tables.css"%></style>
    <title> Закрытие Нарада</title>
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

<div style="text-align: center">
    <h2>Закрытие наряда</h2>
    <br>
    <h3>${workOrder.machine.model}:   ${workOrder.machine.serialNumber}</h3>
    <form:form action="/workOrder/${workOrder.id}/completedWorkOrder" method="post" accept-charset="UTF-8">
        Описание работы:  <br>
        <input type="text" name="title" value="TO ${workOrder.periodicMaintenance.smr}" required >
        <br><br>
        Моточасы:<br>
        <input type="number" name="smr" value="${workOrder.smr}" >
        <br><br>
        Дата:<br>
        <input type="date" name="date" required  value="${workOrder.orderDate}">
        <br><br>
        Человекочасы: <br>
        <input type="number" name="laborHour"   step="0.25" >
        <br><br>
        Детальная информация о работе: <br>
        <textarea name="recordInformation" cols="50" rows="6"> </textarea>
        <br><br>
        Дополнительная информация: <br>
        <input type="text" name="otherInfo"> </input>
        <br><br>
        <h3> Часы по работникам</h3>
        Имя работника:  Длительность работы:
        <select name="workerName1">
            <option value="${workOrder.worker}" selected> ${workOrder.worker} </option>
            <c:forEach items="${workerList}" var="worker">
                <option value="${worker.name}"> ${worker.name}</option>
            </c:forEach>
        </select>
        &nbsp; <input type="number" name="manHour1" step="0.25">
        <br><br>
        Имя раборника:   Длительность работы:
        <select name="workerName2">
            <option value="" selected></option>
            <c:forEach items="${workerList}" var="worker">
                <option value="${worker.name}"> ${worker.name}</option>
            </c:forEach>
        </select>
        &nbsp;
        <input type="number" name="manHour2" step="0.25" >
        <br><br>
        Имя раборника: Длительность работы:
        <select name="workerName3" >
            <option value="" selected></option>
            <c:forEach items="${workerList}" var="worker">
                <option value="${worker.name}"> ${worker.name}</option>
            </c:forEach>
        </select>
        &nbsp;
        <input type="number" name="manHour3" step="0.25" >
        <br><br>
        Имя раборника: Длительность работы:
        <select name="workerName4">
            <option value="" selected></option>
            <c:forEach items="${workerList}" var="worker">
                <option value="${worker.name}"> ${worker.name}</option>
            </c:forEach>
        </select>
        &nbsp;
        <input type="number" name="manHour4" step="0.25">
        <br><br>

        <input type="submit" value="сохранить" >
    </form:form>
</div>
</body>
</html>

