<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 5/25/2018
  Time: 9:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Создание записи  в истории</title>
</head>
<body>
<div style="text-align: center">
<h2>Новая запись</h2>
<br>
    <h3>${model}:   ${serialNumber}</h3>
<form:form action="/customer/machine/historyRecord/newHistoryRecord/${machineId}" method="post" accept-charset="UTF-8">
Описание работы:  <br>
    <input type="text" name="title" required>
    <br><br>
    Моточасы:<br>
    <input type="number" name="SMR" >
    <br><br>
    Дата:<br>
    <input type="date" name="date" required>
    <br><br>
    Человекочасы: <br>
    <input type="number" name="laborHour" step="0.25">
    <br><br>
    Детальная информация о работе: <br>
    <textarea name="recordInformation" cols="40" rows="5"> </textarea>
    <br><br>
    Дополнительная информация: <br>
    <input type="text" name="otherInfo"> </input>
    <br><br>
<h3> Часы по работникам</h3>
    Имя работника:  Длительность работы:
    <select name="workerName1">
        <option value="" selected></option>
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
    <select name="workerName3">
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
