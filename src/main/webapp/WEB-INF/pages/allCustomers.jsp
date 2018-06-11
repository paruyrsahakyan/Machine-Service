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
            border: 1px solid black;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>

<h2>Все Компании</h2>

<a href="/customer/createCustomer" > Создать новую компанию</a>
<br><br>


<form action="/allCustomers/filtered" method="get">
    <B>Фильтр | </B> Название клиента: <input type="text" name="customerName" value="${customerName}"> &nbsp;&nbsp;
        <input type="submit" value="обновить список">

</form>
<br>

<table>
    <tr>
        <th>N</th>
        <th>Название Компании</th>
        <th>контакты </th>
    </tr>
    <% int i = 1;%>
    <c:forEach items="${customerList}" var="customer">
    <tr>
        <td><%=i++%></td>
        <td><a href="/customer/${customer.id}"> ${customer.name} </a></td>
        <td>${customer.contacts} </td>

    </tr>

    </c:forEach>


</table>

</body>`
</html>
