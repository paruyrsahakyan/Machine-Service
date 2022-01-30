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
    <style><%@include file="/WEB-INF/pages/CSS/topNavigation.css"%></style>
    <style><%@include file="/WEB-INF/pages/CSS/tables.css"%></style>
    <style>
        table, th, td {
            border: 1px solid black;
            align-self: center;
        }

    </style>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Создание нового ТО</title>
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
    <a class="active" href="/machineType/allMachineTypes"> Типы Машин</a>
    <a href="/wareHouse"> Склад </a>
    <a href="/price/mainPage"> Прайс </a>
     <a href="/offer/mainPage">  Заказы </a>

</div>
<div class="mainContent">
    <h2>Создание нового ТО</h2> <br>
    <h3>Тип машины &nbsp; ${machineType.typeDescription}</h3>

    <form:form action="/machineType/${machineType.id}/maintenance/createdNew" method="post"
               onkeypress="return event.keyCode != 13;">
        Периодичность ТO(мч)  <input name ="smr" type="number">
        <input type="submit" value="Сохранить">
         <button> <a href="/machineType/${machineType.id}" style="text-decoration:none">Отменить </a> </button>
                <br> <br>
        <table id = "table" align="center">
            <tr>
                <th>Артикул</th>
                <th>Описание</th>
                <th>Единица</th>
                <th>Количесто</th>
            </tr>
        </table>
    </form:form>
    <button id ="addPart"  onclick="addRow()" >Добавить строку</button>

    <br> <br>
    <script>

        function addRow(){
            var table=document.getElementById("table");
            var row = table.insertRow();
            var partNumberCell =row.insertCell(0);
            var descriptionCell =row.insertCell(1);
            var unitCell =row.insertCell(2);
            var quantityCell =row.insertCell(3);
            partNumberCell.innerHTML ="<input type='text' name= 'partNumber[]' required>";
            descriptionCell.innerHTML ="<input type='text' name= 'description[]' required>";
            unitCell.innerHTML ="<input type='text' name= 'unit[]' required>";
            quantityCell.innerHTML ="<input type='number' name= 'quantity[]' required>";
                    }

    </script>
</div>
</body>
</html>
