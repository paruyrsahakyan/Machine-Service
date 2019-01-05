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

    <h2>Информация о машине</h2>

    <a href="/"> Главное меню</a>
    <br> <br>
    <table style="width: auto" align="center">
        <tr>
            <th>Модель:</th>
            <td>${machine.model}</td>
        </tr>
        <tr>
            <th>Серийный номер:</th>
            <td>${machine.serialNumber}</td>
        </tr>
        <tr>
            <th>Модель ДВС</th>
            <td>${machine.engineModel}</td>

        </tr>
        <tr>
            <th>Серийный номер ДВС</th>
            <td>${machine.engineSerialNumber}</td>

        </tr>
        <tr>
            <th>Владелец</th>
            <td><a href="/customer/${customerId}"> ${machine.customer}</a></td>

        </tr>

        <tr>
            <th>Год производства</th>
            <td>${machine.productionYear}</td>

        </tr>

        <tr>
            <th>Дополнительная информации</th>
            <td>${machine.otherInfo}</td>

        </tr>
    </table>

    <br> <br>
    <a href="/customer/machine/updateMachine/${machine.id}" style="bottom: auto"> Редактировать</a> &nbsp;<b>|</b>
    <a href="/customer/machine/historyRecord/createHistoryRecord/${machine.id}">Создать новую запись истории</a>
    &nbsp;<b>|</b>
    <a href="/customer/machine/historyRecord/recordList/${machine.id}"> Пoказать работы </a>
    <br>
    <br>
    <a href="/customer/machine/deleteMachine/${machine.id}"
       onclick="return confirm('!!!Вы уверены что хатите удалить машину!!!');"
       style="color: crimson;">Удалить машину</a>
</div>
</body>
</html>
