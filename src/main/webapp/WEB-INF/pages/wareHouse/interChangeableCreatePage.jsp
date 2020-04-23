<%--
  Created by IntelliJ IDEA.
  User: Paruyr
  Date: 2/16/2019
  Time: 9:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Создание Заменаемой запчасти</title>
    <style><%@include file="/WEB-INF/pages/CSS/topNavigation.css"%></style>
    <style><%@include file="/WEB-INF/pages/CSS/tables.css"%></style>
</head>
<body>
<div class="topnav">
    <a href="/"> Главное меню</a>
    <a  href="/allCustomers"> Компании </a>
    <a href="/allMachines"> Mашины </a>
    <a href="/analysis/worker/jobs"> Анализ работ</a>
    <a href="/workOrder/home"> Наряды </a>
    <a href="/admin/workers"> Работники </a>
    <a href="/admin/allServiceMachines"> Сервисные Mашины</a>
    <a href="/machineType/allMachineTypes"> Типы Машин</a>
    <a  class="active" href="/wareHouse"> Склад </a>
    <a href="/price/mainPage"> Прайс </a>
</div>
<div class="mainContent">
<h3>Создание Заменаемой запчасти </h3>
<a href="/workOrder/home"> Отмена </a>
<br> <br>
 <form:form action="/wareHouse/interChangeableParts/createdNewInterChangeable" method="post">
     Основной артикул <input type="text" name="basicPartNumber">  &nbsp; | &nbsp;
     Артикул замены <input type="text"   name="partNumber" >
     <input type="submit" value="сохранить">
 </form:form>
    </div>
</body>
</html>
