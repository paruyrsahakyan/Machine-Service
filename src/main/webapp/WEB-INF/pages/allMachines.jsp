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

    <h2 style="text-align: center">Все машины</h2>

    <a href="/"> главное меню</a>
    <br>
    <br>


    <form action="/allMachines/filtered" method="get">
        <B>Фильтры | </B> модел: <input type="text" name="model" value="${model}"> &nbsp;&nbsp;
        серийный номер։ <input type="text" name="serialNumber" value="${serialNumber}">
        <input type="submit" value="обновить список">

    </form>
    <br>

    <table style="width: auto">
        <tr>
            <th>N</th>
            <th>Модель</th>
            <th>Серийный номер</th>
            <th>Владелец</th>
            <th>Год производства</th>
            <th>Последняя инфо</th>
            <th>Дата информации</th>
            <th>Наработка</th>

        </tr>

        <% int i = 1; %>
        <c:forEach items="${machineList}" var="machine">
            <tr>
                <td><%= i++%>
                </td>
                <td>${machine.model}</td>
                <td><a href="/customer/machine/${machine.id}"> ${machine.serialNumber} </a></td>
                <td>${machine.customer}</td>
                <td>${machine.productionYear}</td>
                <td>${machine.lastInfo}</td>
                <td>${machine.lastInfoDate}</td>
                <td>${machine.lastSMR}</td>
            </tr>
        </c:forEach>
        </tr>
    </table>
</div>
</body>
</html>
