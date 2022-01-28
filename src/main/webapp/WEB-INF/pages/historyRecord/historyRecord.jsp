<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 5/25/2018
  Time: 8:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Создание новой компании</title>
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
<h2 >Запись истории</h2>
<br>
<a href="/customer/machine/${historyRecord.machine.id}" >
<H3> ${historyRecord.machine.model} : sn${historyRecord.machine.serialNumber} </H3>
</a>
   <table class="mainTables">
<tr>
    <th>Дата</th>
    <td> ${historyRecord.recordDate}</td>
</tr>
    <tr>
        <th>Описание Работы </th>
        <td>${historyRecord.title} </td>
    </tr>
    <tr>
        <th>Наработка Машины        </th>
        <td>${historyRecord.SMR}</td>
    </tr>
      <tr>
        <th>Длительность работы (н/ч)</th>
        <td>${historyRecord.laborHour}</td>
    </tr>
    <tr>
        <th>Детальная информация о работе</th>
        <td>${historyRecord.recordInformation}</td>
    </tr>
    <tr>
        <th>Дополнительная информация</th>
        <td>${historyRecord.otherInfo}</td>
    </tr>
</table>
<h3>Исполнители: </h3>
<table class="mainTables">
       <c:forEach items="${historyRecord.detailedLaborHours}" var="detailedLaborHour">
        <tr>
            <th>${detailedLaborHour.workerName}</th>
            <td>${detailedLaborHour.jobDuration}</td>
        </tr>
    </c:forEach>
</table>
    <h3>Прикрепленные файлы: </h3>

<c:forEach items="${fileList}" var="file">
    &nbsp;
    <a href="/customer/machine/historyRecord/downloadFile/${file.id}" target="_blank" >
            ${file.fileDescription}  </a>
    &nbsp;|
</c:forEach>
<br>
<br>
<a href="/customer/machine/historyRecord/updateHistoryRecord/${historyRecord.id}" >Редактировать</a>
&nbsp;
<a href ="/customer/machine/historyRecord/addFiles/${historyRecord.id}"> Добавить/удалить файлы</a>
<br>
<br>
<a href="/customer/machine/historyRecord/deleteHistoryRecord/${historyRecord.id}"
   onclick="return confirm('!!!Вы уверены что хатите удалить запись!!!');"
style="color: crimson">Удалить запись</a>
</div>
</body>
</html>
