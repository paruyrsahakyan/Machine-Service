<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 7/1/2018
  Time: 11:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Создание Сервисной Машины</title>
    <style><%@include file="/WEB-INF/pages/CSS/topNavigation.css"%></style>
    <style><%@include file="/WEB-INF/pages/CSS/tables.css"%></style>
</head>
<body>
<div class="topnav">
    <a href="/"> Главное меню</a>
    <a href="/allCustomers"> Компании </a>
    <a href="/allMachines"> Mашины </a>
    <a href="/analysis/worker/jobs"> Анализ работ</a>
    <a href="/workOrder/home"> Наряды </a>
    <a href="/admin/workers"> Работники </a>
    <a class="active" href="/admin/allServiceMachines"> Сервисные Mашины</a>
    <a href="/machineType/allMachineTypes"> Типы Машин</a>
    <a href="/wareHouse"> Склад </a>
</div>
<div class="mainContent">
    <H2 style="text-align: center"> Создание Сервисной Машины </H2>
    <a href="/admin/serviceMachines"> Отменить создание новой машины </a>
    <br>
    <br>
    <form:form action="/admin/serviceMachine/serviceMachineAdded" method="post" >
        Машина: <br>
        <input type="text" name="name" required="required" placeholder="Марка Гос.Ном.">
        <br><br>
        <input type="submit" value="Сохранить">

    </form:form>
</div>
</body>
</html>
