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
<a href="/"> главное меню</a>
<br>
<br>

<div>

    <h2>Все машины</h2>

    <h3>${machine.model} : ${machine.serialNumber}</h3>
    <form action="/customer/machine/recordList/${machine.id}">
        <input type="date" name="startDate" value="${startDate}">
        <input type="date" name="endDate" value = "${endDate}">
        <input type="submit" value="Фильтр по датам">
    </form>
    <table style="width: auto">
        <tr>
            <th>N</th>
            <th>Дата</th>
            <th>Описание работы</th>
            <th>Моточасы</th>
            <th>Потраченное время</th>
        </tr>

        <% int i = 1; %>
        <c:forEach items="${recordList}" var="record">
            <tr>
                <td><%= i++%> </td>
                <td>${record.recordDate}</td>
                <td><a href="/cusomer/machine/recordList/record/${record.id}"> ${record.title} </a></td>
                <td>${record.SMR}</td>
                <td>${record.laborHour}</td>
            </tr>
        </c:forEach>
        </tr>
    </table>
</div>
</body>
</html>

