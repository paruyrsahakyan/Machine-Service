<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 5/25/2018
  Time: 8:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>historyRecord</title>
</head>
<body>
<h2>Запись в испории</h2>
<br>
<br>
<table>
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
        <th>Потраченное время</th>
        <td>${historyRecord.laborHour}</td>
    </tr>
    <c:forEach items="${laborHourList}" var="laborHour">
        <tr>
            <th>${laborHour.workerName}</th>
            <td>${laborHour.jobDuration}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
