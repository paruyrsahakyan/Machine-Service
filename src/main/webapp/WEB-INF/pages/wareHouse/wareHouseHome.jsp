<%--
  Created by IntelliJ IDEA.
  User: Paruyr
  Date: 2/15/2019
  Time: 12:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title> Склад</title>
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
    <a href="/admin/allServiceMachines"> Сервисные Mашины</a>
    <a href="/machineType/allMachineTypes"> Типы Машин</a>
    <a class="active" href="/wareHouse"> Склад </a>
    <a href="/price/mainPage"> Прайс </a>
    <a href="/offer/mainPage">  Заказы </a>
</div>
<div style="text-align: center">
<h2>Склад</h2>

 <br> <br>
<a href="/wareHouse/interChangeableParts" > Взаимозаменяемые запчасти </a>
<br> <br>
<a href="/wareHouse/interChangeableParts/createNew"> Создать заменяемую зачасть </a>
<br> <br>
<p style="color: #dc161c"> дата обновления склада ${updateDate}
    <form:form action="/wareHouse/updated" method="post"  accept-charset="UTF-8"
               enctype="multipart/form-data">
    Загрузить склад:
    <input type="file" name="wareHouseFile">
    <input  type="submit" value="загрузить">
    </form:form>
    <br><br>

     <form:form action="/wareHouse/KCISpriceList" method="post"  accept-charset="UTF-8"
                   enctype="multipart/form-data">
        Загрузить прайс:
        <input type="file" name="priceList">
        <input  type="submit" value="загрузить">
        </form:form>

        <br><br>
             <form:form action="/wareHouse/analogs" method="post"  accept-charset="UTF-8"
                           enctype="multipart/form-data">
                Загрузить аналоги:
                <input type="file" name="priceList">
                <input  type="submit" value="загрузить">
                </form:form>
</p>
</div>
</body>
</html>
