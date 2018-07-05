
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 5/25/2018
  Time: 9:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>

<html>
<head>
    <title>Редактирование записи  в истории</title>
</head>
<body>
<h2>Редактирование записи</h2>
<h3> ${historyRecord.machine.model} :   ${historyRecord.machine.serialNumber}</h3>
<form:form action="/customer/machine/historyRecord/updatedHistoryRecord/${historyRecord.id}" method="post" accept-charset="UTF-8">
    <TABLE>
        <TR>
            <TD>Дата:</TD>
            <TD><input type="date" name="date"  value="${recordDate}"></TD>
        </TR>
        <TR>
            <TD>Описание работы:</TD>
            <TD><input type="text" name="title" value="${historyRecord.title}"> </TD>
        </TR>
        <TR>
            <TD>Моточасы:</TD>
            <TD> <input type="number" name="SMR" value="${historyRecord.SMR}">  </TD>
        </TR>
        <TR>
            <TD> Человекочасы:</TD>
            <TD> <input type="number" name="laborHour" value="${historyRecord.laborHour}" > </TD>
        </TR>
        <TR>
        <TD> Детальная информация: </TD>
        <TD> <textarea name="recordInfo" cols="40" rows="5">${historyRecord.recordInformation} </textarea> </TD>
        </TR>
        <TR>
            <TD> Дополнительная информация: </TD>
            <TD> <input type="text" name="otherInfo" value="${historyRecord.otherInfo}" > </textarea> </TD>
        </TR>
    </TABLE>

<h3> Часы по работникам</h3>

    <c:forEach items="${laborHourList}" var="laborHour">
    Имя раборника:  Длительность работы:
        <select name="workerName[]">
        <option value="${laborHour.workerName}" selected>${laborHour.workerName}</option>
       <option value=""></option>
        <c:forEach items="${workerList}" var="worker">
        <option value="${worker.name}"> ${worker.name}</option>
    </c:forEach>
    </select>   &nbsp;
        <input type="number" name="manHour[]"  step="0.25" value="${laborHour.jobDuration}">
    <br>
    </c:forEach>
    Имя раборника:  Длительность работы:
    <select name="workerName[]">
        <c:forEach items="${workerList}" var="worker">
        <option value="" selected></option>
        <option value="${worker.name}"> ${worker.name}</option>
        </c:forEach>
    </select> &nbsp;
    <input type="number" name="manHour[]" step="0.25"  value="0">
    <br>

    Имя раборника:  Длительность работы:
    <select name="workerName[]">
        <c:forEach items="${workerList}" var="worker">
        <option value="" selected></option>
        <option value="${worker.name}"> ${worker.name}</option>
        </c:forEach>
    </select> &nbsp;
    <input type="number" name="manHour[]" step="0.25"  value="0">


<input type="submit" value="сохранить" >
</form:form>
</body>
</html>
