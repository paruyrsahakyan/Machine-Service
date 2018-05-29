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

<div>

    <h2>информация о машине</h2>

    <a href="updateMachine/${machine.id}"></a>

    <table style="width: auto">
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
            <td>${machine.customer.name}</td>

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
    </div>
<br> <br>
<a href="/updateMachine/${machine.id}" style="bottom: auto"> Редактировать</a>  &nbsp;
<a href="/createHistoryRecord/${machine.id}">Создать новую запись истории</a>  &nbsp;
<a href="/customer/machine/recordList/${machine.id}"> Пoказать работы </a>

</body>
</html>
