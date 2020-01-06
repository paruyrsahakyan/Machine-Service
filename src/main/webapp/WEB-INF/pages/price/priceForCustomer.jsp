<%--
  Created by IntelliJ IDEA.
  User: IKO
  Date: 1/5/2020
  Time: 7:14 PM
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
</div>
<br>
<br>

<div class="mainContent">

<form:form action="/price/createdNewPrice" method="post" accept-charset="UTF-8">
    <B>Клиент </B>
    <input list="customers" name="customerName" id="selectedCustomerName" onchange="tableCreate()">
    <datalist id="customers">
        <c:forEach items="${customerList}" var="customer">
            <option value="${customer.name}" hidden> ${customer.name} </option>
        </c:forEach>
    </datalist>
    &nbsp;&nbsp;&nbsp;&nbsp;
    <B>Артикул </B>
    <input type="text" name="searchedArticle" id="articleSearch" onchange="tableCreate()">
    &nbsp;&nbsp;&nbsp;&nbsp;
    <button type="button" onclick="showHiddenForm()"> Добавить</button>
    <br><br>
    <%--<form:form action="/price/createdNewPrice" method="post" accept-charset="UTF-8">--%>
        <div id="hiddenForm" style="display: none;">
            <input type="text" name="article" placeholder="Введите  Артикул">
            <input type="text" name="description" placeholder="Введите Название">
            <input type="nuber" name="price" placeholder="Введите Цену">
            <%--<input type="hidden" name="customerId" id="customerId" >--%>
            <input type="submit" value="сохранить">
        </div>
        <br>

    </form:form>

    <table class="mainTables" id="dynamicTable" style="width: auto" align="center">
        <tr>
            <td>N</td>
            <td>Артиикул</td>
            <td>Название</td>
            <td>Цена без НДС</td>
        </tr>
    </table>
</div>
<script>
    var initialPriceList = [];
    var priceListForSelectedCustomer = [];
    var customerList = [];
    var selectedCustomerId;
    var selectedCustomerName;

    <c:forEach items="${priceList}" var="priceForCustomer">
    var article = "${priceForCustomer.article}";
    var description = "${priceForCustomer.description}";
    var price = "${priceForCustomer.price}";
    var customerId = "${priceForCustomer.customerId}";
    var customerName= "${priceForCustomer.customerName}";
    initialPriceList.push({
        article: article,
        description: description,
        price: price,
        customerId: customerId,
        customerName: customerName
    });
    </c:forEach>

    <c:forEach items="${customerList}" var="customer">
    var id = "${customer.id}"
    var customerName = "${customer.name}";
    customerList.push({
        id: id,
        customerName: customerName
    });
    </c:forEach>



    function initPriceListForSelectedCustomer() {
        var selectedCustomerName = document.getElementById("selectedCustomerName").value;

           for (var i = 0; i < initialPriceList.length; i++) {
            if (initialPriceList[i].customerName === selectedCustomerName) {
                priceListForSelectedCustomer.push(initialPriceList[i]);
            }
            alert(priceListForSelectedCustomer.length.toString())
                       }
    }

    function tableCreate() {
                initPriceListForSelectedCustomer();
        var table = document.getElementById("dynamicTable");
        table.innerText = "";
        var titleRow = table.insertRow();
        var titleCell1 = titleRow.insertCell(0);
        var titleCell2 = titleRow.insertCell(1);
        var titleCell3 = titleRow.insertCell(2);
        var titleCell4 = titleRow.insertCell(3);
        titleCell1.innerHTML = "N";
        titleCell1.style.fontWeight = 'bold';
        titleCell2.innerHTML = "Артикул";
        titleCell2.style.fontWeight = 'bold';
        titleCell3.innerHTML = "Название";
        titleCell3.style.fontWeight = 'bold';
        titleCell4.innerHTML = "Цена Без НДС";
        titleCell4.style.fontWeight = 'bold';

        for (var i = 0; i < priceListForSelectedCustomer.length; i++) {
            var row = table.insertRow();
            var cell1 = row.insertCell(0);
            var cell2 = row.insertCell(1);
            var cell3 = row.insertCell(2);
            var cell4 = row.insertCell(3);
            cell1.innerHTML = (i + 1).toString();
            cell2.innerHTML = priceListForSelectedCustomer[i].article;
            cell3.innerHTML = priceListForSelectedCustomer[i].description;
            cell4.innerHTML = priceListForSelectedCustomer[i].price;
        }
    }
    function showHiddenForm() {
        document.getElementById("hiddenForm").style.display = "block";
        // body...
    }
</script>
</body>

</html>
