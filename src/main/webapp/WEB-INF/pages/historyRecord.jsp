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
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: auto;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>
<h2>Запись в истории</h2>
<br>
<table>
<tr>
    <th>Дата</th>
    <td> ${recordDate}</td>
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
        <th>Длительност работы (н/ч)</th>
        <td>${historyRecord.laborHour}</td>
    </tr>
</table>
<table>
    <h4>Исполнители </h4>
    <c:forEach items="${laborHourList}" var="laborHour">
        <tr>
            <th>${laborHour.workerName}</th>
            <td>${laborHour.jobDuration}</td>
        </tr>
    </c:forEach>
</table>
<a href="/updateHistoryRecord/${historyRecord.id}">Редактировать</a>
<a href ="/addFiles/${historyRecord.id}"> Добавить файлы</a>

</body>
</html>
