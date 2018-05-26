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



    <table style="width: auto">
        <tr>
            <th>N</th>
            <th>Модель</th>
            <th>Серийный номер</th>
            <th>Владелец</th>
        </tr>

            <% int i = 1; %>
            <c:forEach items="${machineList}" var="machine">
        <tr>
                <td><%= i++%> </td>
                <td>${machine.model}</td>
                <td><a href="/machine/${machine.id}"> ${machine.serialNumber} </a></td>
                <td>${machine.customer.name}</td>
        </tr>
            </c:forEach>
        </tr>
    </table>
</div>
</body>
</html>
