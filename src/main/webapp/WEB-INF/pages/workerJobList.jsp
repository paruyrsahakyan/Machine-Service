<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
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

<div>

    <h2 style="text-align: center">Детельность Работника В Периоде Времени</h2>

    <a href="/"> главное меню</a>
    <br>
    <br>
<form action="/analysis/worker/jobs">
    Работник:
    <select name="workerName">
        <option value="${selectedWorker}" selected> ${selectedWorker}</option>
<c:forEach items="${allWorkers}" var="worker">
        <option value="${worker.name}"> ${worker.name}</option>
        </c:forEach>
          </select>
    &nbsp; от:
    <input type="date" name="startDate" value="${startDate}" required>
    &nbsp; до:
    <input type="date" name="endDate" value = "${endDate}" required>
    &nbsp;
    <input type="submit" value="Поиск">

</form>
    <br>
    <br>
    <h3>Суммарные часы в периоде: &nbsp; ${totalTime} </h3>

    <table style="width: auto">
        <tr>
            <th>N</th>
            <th>дата</th>
            <th>Описание работы</th>
            <th>Нормачас</th>
            <th>Машина</th>
            <th>Заказчик</th>
        </tr>

        <% int i = 1; %>
        <c:forEach items="${jobList}" var="job">
            <tr>
                <td><%= i++%> </td>
                <td> ${job.date}</td>
                <td> <a href="/customer/machine/historyRecord/${job.historyRecordId}" target="_blank+">${job.historyRecordTitle}</a></td>

                <td> ${job.jobDuration}</td>
                <td> ${job.machine} </td>
                <td>${job.customer}</td>
            </tr>
        </c:forEach>
            </table>
</div>
</body>
</html>
