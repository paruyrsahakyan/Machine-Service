<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <style><%@include file="/WEB-INF/pages/CSS/topNavigation.css"%></style>
    <style><%@include file="/WEB-INF/pages/CSS/tables.css"%></style>

</head>
<body>

    <div class="topnav">
        <a href="/"> Главное меню</a>
        <a class="active" href="/allCustomers"> Компании </a>
        <a href="/allMachines"> Mашины </a>
        <a href="/analysis/worker/jobs"> Анализ работ</a>
        <a href="/workOrder/home"> Наряды </a>
        <a href="/admin/workers"> Работники </a>
        <a href="/admin/allServiceMachines"> Сервисные Mашины</a>
        <a href="/machineType/allMachineTypes"> Типы Машин</a>
        <a href="/wareHouse"> Склад </a>
    </div>

    <div class="mainContent">

    <h2> Компании</h2>

<br>
<br>

<a href="/customer/createCustomer" > Создать новую компанию</a>
<br><br>


<form action="/allCustomers/filtered" method="get">
    <B>Фильтр | </B> Название клиента: <input type="text" name="customerName" value="${customerName}"> &nbsp;&nbsp;
        <input type="submit" value="обновить список">

</form>
<br>

<table class="mainTables" >
    <tr>
        <th>N</th>
        <th>Название Компании</th>
        <th>Kонтакты </th>
        <th>Наличие договора </th>
        </tr>
    <% int i = 1;%>
    <c:forEach items="${customerList}" var="customer">
    <tr>
        <td><%=i++%></td>
        <td><a href="/customer/${customer.id}"> ${customer.name} </a></td>
        <td>${customer.contacts} </td>
        <td>${customer.contract} </td>

    </tr>

    </c:forEach>


</table>
        </div>
</body>
</html>
