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
<div style="text-align: center">
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
        var rowId = 1;
        function addRow(){
            var table=document.getElementById("table");
            var row = table.insertRow();
             row.id=rowId.toString();
            var partNumberCell =row.insertCell(0);
            var descriptionCell =row.insertCell(1);
            var unitCell =row.insertCell(2);
            var quantityCell =row.insertCell(3);
            var deleteCell =row.insertCell(4);
            partNumberCell.innerHTML ="<input type='text' name= 'partNumber[]'>";
            descriptionCell.innerHTML ="<input type='text' name= 'description[]'>";
            unitCell.innerHTML ="<input type='text' name= 'unit[]'>";
            quantityCell.innerHTML ="<input type='number' name= 'quantity[]'>";
            deleteCell.innerHTML ="<button onclick='deleteRow()'>";
        }
        function deleteRow() {
            document.getElementById(rowId.toString()).innerHTML="";
            rowId++;
        }
    </script>
</div>
</body>
</html>
