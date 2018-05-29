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
    <title>Создание записи  в истории</title>
</head>
<h2>Новая запись</h2>
<br>
    <h3>${model}:   ${serialNumber}</h3>
<form action="/newHistoryRecord/${model}/${serialNumber}">
Описание работы:  <br>
    <input type="text" name="title" required>
    <br><br>
    Моточасы:<br>
    <input type="number" name="SMR">
    <br><br>
    Дата:<br>
    <input type="date" name="date" required>
    <br><br>
    Человекочасы: <br>
    <input type="number" name="laborHour">
    <br><br>
<h3> Часы по работникам</h3>
    Имя раборника:  Длительность работы:
    <input type="text" name="workerName1" > &nbsp; <input type="number" name="manHour1">
    <br><br>
    Имя раборника:   Длительность работы:
    <input type="text" name="workerName2" > &nbsp; <input type="number" name="manHour2" >
    <br><br>
    Имя раборника: Длительность работы:
    <input type="text" name="workerName3" > &nbsp; <input type="number" name="manHour3" >
    <br><br>
    Имя раборника: Длительность работы:
    <input type="text" name="workerName4" > &nbsp; <input type="number" name="manHour4">
    <br><br>

<input type="submit" value="сохранить" >
</form>

</body>
</html>
