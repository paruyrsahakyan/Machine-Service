<%--
  Created by IntelliJ IDEA.
  User: IKO
  Date: 3/10/2020
  Time: 12:29 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Прайс Лист</title>
    <style>
        <%@include file="/WEB-INF/pages/CSS/topNavigation.css" %>
    </style>
    <style>
        <%@include file="/WEB-INF/pages/CSS/tables.css" %>
    </style>
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
    <a href="/wareHouse"> Склад </a>
    <a  class="active" href="/price/mainPage"> Прайс </a>

</div>
<br>
<br>

<div class="mainContent">

    <form:form id="searchByArticle" action="/price/pricesByArticleForAllCustomers" method="get" accept-charset="UTF-8">
         <input list="article" name="article" id="articleToSearch" placeholder="поиск по артикулу">
         <datalist id="article">
            <c:forEach items="${articleList}" var="article">
                <option value="${article}" > ${article} </option>
            </c:forEach>
        </datalist>
     <input type="submit" value="Поиск">
    </form:form>

    <H3> Артикул ${selectedArticle}  </H3>

    <div class="mainContent">
        <table class="mainTables" id="dynamicTable" style="width: auto" align="center">
            <tr>

                <td>Клиент</td>
                <td>Цена без НДС</td>
                <td>МП%</td>
            </tr>
                <c:forEach items="${priceList}" var="priceForCustomer">
                <tr>
                    <td>${priceForCustomer.customerName}</td>
                    <td>${priceForCustomer.price}</td>
                    <td>${priceForCustomer.profit}</td>
                    </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
