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


<div style="text-align: center">

    <h2>Наряд</h2>

    <a href="/"> Главное меню</a>
    <br> <br>
    <table style="width: auto" align="center">
        <tr>
            <th>Заказчик:</th>
            <td>${workOrder.machine.customer}</td>
        </tr>
        <tr>
        <th>Машина:</th>
        <td>${workOrder.machine.model} &nbsp; sn ${workOrder.machine.serialNumber}</td>
    </tr>
         <tr>
            <th>Работа:</th>
            <td>ТО ${workOrder.periodicMaintenance.smr}</td>
        </tr>
        <tr>

            <th>Наработка:</th>
            <td>${workOrder.smr}</td>
        </tr>
        <tr>
            <th>Дата:</th>
            <td>${workOrder.orderDate}</td>
        </tr>
        <tr>
            <th>Ответственный:</th>
            <td>${workOrder.worker}</td>
        </tr>
        <tr>
            <th>Сервисная Машина:</th>
            <td> ${workOrder.serviceMachine}</td>
        </tr>

    </table>

    <br> <br>
    <a href="/workOrder/${workOrder.id}/update" style="bottom: auto"> Редактировать</a> &nbsp;<b>|</b>
    <a href="/workOrder/${workOrder.id}/completeWorkOrder">Закрыть Наряд</a>
    &nbsp;<b>|</b>
       <a href="/workOrder/${workOrder.id}/deleted"
       onclick="return confirm('!!!Вы уверены что хатите удалить машину!!!');"
       style="color: crimson;">Удалить Наряд</a>
    <br><br>
    <a href="/workOrder/${workOrder.id}/serviceReport" style="color: darkgreen"> сервисный отчет</a> &nbsp;<b>|</b>
</div>
</body>
</html>
