
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Новая Машина</title>
</head>
<body>
<div style="text-align: center">
    <h2>Замена Владельца Машины</h2> <br>

<table align="center">

<% int i = 1;%>
<c:forEach items="${customerList}" var="customer">

    <td><a href="/customer/machine/${machineId}/updatedCustomer"> ${customer.name} </a></td>
    </c:forEach>
</table>

</div>
</body>
</html>

