<%--
  Created by IntelliJ IDEA.
  User: Paruyr
  Date: 1/10/2019
  Time: 12:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Создание нового ТО</title>
</head>
<body>
<div style="text-align: center">
    <h2>Создание нового ТО</h2> <br>
    <h3>Тип машины &nbsp; ${machineType.machineDescription}</h3>
    < action="/customer/machine/newMachine/${customerId}" method="Post" >
       <form:form action="/machineType/${machineType.id}/periodicMaintenance/periodicMaintenanceCreated"
       method="post">
        <input type="number" name="smr" > &nbsp; периодичность
        <br><br>
        <input type="text" name="partNumber"> &nbsp;  артикул
        <br><br>
        <input type="text" name="partDescription"> &nbsp; описание запчасти
        <br><br>
        <input type="text" name="unit"> &nbsp; единица
        <br><br>
        <input type="number" name="quantity"> &nbsp; количество
        <br><br>
         <input type="submit" value="Сохранить">
    </form:form>
</div>
</body>
</html>
