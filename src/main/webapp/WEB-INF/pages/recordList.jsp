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
<h2 style="text-align: center">История Машины</h2>

<a href="/"> главное меню</a>
<br>
<br>

    <a href="/customer/machine/${machine.id}" >
        <H3> ${machine.model} : sn${machine.serialNumber} </H3>
    </a>

    <form action="/customer/machine/recordList/${machine.id}">
        <input type="date" name="startDate" value="${startDate}">
        <input type="date" name="endDate" value = "${endDate}">
        <input type="submit" value="Фильтр по датам">
        <br>
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
                <td><a href="/customer/machine/historyRecord/${record.id}"> ${record.title} </a></td>
                <td>${record.SMR}</td>
                <td>${record.laborHour}</td>
            </tr>
        </c:forEach>
        </tr>
    </table>

</body>
</html>

