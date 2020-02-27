<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Новая Машина</title>
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
    <a class="active" href="/allMachines"> Mашины </a>
    <a href="/analysis/worker/jobs"> Анализ работ</a>
    <a href="/workOrder/home"> Наряды </a>
    <a href="/admin/workers"> Работники </a>
    <a href="/admin/allServiceMachines"> Сервисные Mашины</a>
    <a href="/machineType/allMachineTypes"> Типы Машин</a>
    <a href="/wareHouse"> Склад </a>
</div>

<div class="mainContent">
    <h2>Замена Влaдельца Машины</h2> <br>
    <a href="/customer/machine/${machineId}">Отменить Процедуру</a>
    <br> <br>
    Поиск по названию: <input id="search" type="text" onkeyup="refreshTheTable()">
    <br><br>
    <form:form action="/customer/machine/${machineId}/changedMachineCustomer" method="post">
        <input id="selectedCustomerId" name="newCustomerId" type="hidden">
        Выбран заказчик: <input id="selectedCustomerName" type="text" readonly style="font-weight: bold">
        &nbsp <b>|</b> &nbsp Дата: <input type="date" name="date" required>
        &nbsp <input type="submit" value="Сохранить">
    </form:form>
    <br>
    <table class="mainTables" id="dynamicTable" style="width: auto" align="center">
        <tr>
            <td> N</td>
            <td>Название</td>
        </tr>
    </table>
    <br>
</div>

<script>
    var initialCustomerList = [];
    var filteredList = [];

    <c:forEach items="${customerList}" var="customer">
    var customerName = "${customer.name}";
    var customerId = "${customer.id}";
    initialCustomerList.push({name: customerName, id: customerId});
    </c:forEach>

    tableCreate(initialCustomerList);

    function refreshTheTable() {
        filterTheList();
        tableCreate(filteredList);
    }

    var copyToTextArea = function () {
        var selectedCustomerName = event.target.innerHTML;
        document.getElementById("selectedCustomerName").value = selectedCustomerName;
        var selectedCustomer = filteredList.find(customer => {return customer.name === selectedCustomerName}
    )
        ;
        document.getElementById("selectedCustomerId").value = selectedCustomer.id;

    }

    function filterTheList() {
        var searchInput = document.getElementById("search").value;
        if (searchInput === "") {
            filteredList = initialCustomerList;
        }
        else {
            filteredList = [];
            for (var i = 0; i < initialCustomerList.length; i++) {
                if (initialCustomerList[i].name.toLowerCase().indexOf(searchInput) >= 0) {
                    filteredList.push(initialCustomerList[i])
                }
            }
        }
    }

    function tableCreate(customerList) {

        var table = document.getElementById("dynamicTable");
        table.innerText = "";
        var titleRow = table.insertRow();
        var titleCell1 = titleRow.insertCell(0);
        var titleCell2 = titleRow.insertCell(1);
        //  var titleCell3 = titleRow.insertCell(2);
        titleCell1.innerHTML = "N";
        titleCell1.style.fontWeight = 'bold';
        titleCell2.innerHTML = "Название";
        titleCell2.style.fontWeight = 'bold';
        //   titleCell3.innerHTML = "ИН";
        //  titleCell3.style.fontWeight = 'bold';

        for (var i = 0; i < customerList.length; i++) {
            var row = table.insertRow();
            var cell1 = row.insertCell(0);
            var cell2 = row.insertCell(1);
            // var cell3 = row.insertCell(2);
            cell1.innerHTML = (i + 1).toString();
            cell2.innerHTML = customerList[i].name;
            //  cell3.innerHTML = customerList[i].id;
            cell2.addEventListener("click", copyToTextArea);

        }

    }
</script>

</body>
</html>

