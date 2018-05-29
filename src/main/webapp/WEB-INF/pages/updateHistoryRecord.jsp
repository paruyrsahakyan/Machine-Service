<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 5/25/2018
  Time: 9:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактирование записи  в истории</title>
</head>
<h2>Редактирование записи</h2>
<h3> ${historyRecord.machine.model} :   ${historyRecord.machine.serialNumber}</h3>
<form action="/updatedHistoryRecord/${historyRecord.id}">
    Описание работы:
    <input type="text" name="title" value="${historyRecord.title}">
    <br>
    Моточасы:
    <input type="number" name="SMR" value="${historyRecord.SMR}">
    <br>
    Дата:
    <input type="date" name="date"  value="${recordDate}">
    <br>
    Человекочасы:
    <input type="number" name="laborHour" value="${historyRecord.laborHour}" >
    <br>
<h3> Часы по работникам</h3>

    <c:forEach items="${laborHourList}" var="laborHour">
    Имя раборника:  Длительность работы:

    <input type="text" name="workerName[]"  value="${laborHour.workerName}"> &nbsp;
        <input type="number" name="manHour[]" value="${laborHour.jobDuration}">
    <br>
    </c:forEach>
    Имя раборника:  Длительность работы:
    <input type="text" name="workerName[]" > &nbsp;
    <input type="number" name="manHour[]"  >
    <br>
    Имя раборника:  Длительность работы:
    <input type="text" name="workerName[]"  > &nbsp;
    <input type="number" name="manHour[]" >
    <br>
    <input type="submit" name="Сохранить">
</form>
</body>
</html>
