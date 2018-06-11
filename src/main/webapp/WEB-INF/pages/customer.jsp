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

<h2>Информация о заказчике</h2>


    <a href="/customer/updateCustomer/${customer.id}" style="bottom: auto"> Редактировать</a>
    &nbsp; &nbsp;
    <a href="/customer/machine/createMachine/${customer.id}" style="bottom: auto"> Добавить машину</a>
    &nbsp; &nbsp;
    <a href="/customer/deleteCustomer/${customer.id}"
       onclick="return confirm('!!!Вы уверены что хатите удалить компанию!!!');"
       style="color: crimson;">Удалить компанию</a>
    <br> <br>

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
    <h3>Парк машин</h3>
    <table style="width: auto">
        <tr>
            <th>N</th>
            <th>Модель</th>
            <th>Серийный номер</th>
              </tr>

        <% int i = 1; %>
        <c:forEach items="${customer.machineList}" var="machine">
            <tr>
                <td><%= i++%> </td>
                <td>${machine.model}</td>
                <td><a href="/customer/machine/${machine.id}"> ${machine.serialNumber} </a></td>
                            </tr>
        </c:forEach>
        </tr>
    </table>
</div>

</body>
</html>
