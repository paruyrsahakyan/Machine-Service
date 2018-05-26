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

<h2>информация о заказчике</h2>


    <a href="/updateCustomer/${customer.id}" style="bottom: auto"> Редактировать</a>
    <a href="/createMachine/${customer.id}" style="bottom: auto"> Добавить машину</a>

<table style="width: auto">
    <tr>
        <th>Название Компании: </th>
        <td>${customer.name}</td>
    </tr>
    <tr>
        <th>Kонтакты:</th>
        <td>${customer.contacts}</td>
    </tr>
    <tr>
        <th>Другие информации</th>
        <td>${customer.otherInfo}</td>

    </tr>

</table>
</div>

</body>
</html>
