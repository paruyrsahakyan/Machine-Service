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
        }
    </style>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Создание нового ТО</title>
</head>
<body>
<div style="text-align: center">
    <h2>Создание нового ТО</h2> <br>
    <h3>Тип машины &nbsp; ${machineType.machineDescription}</h3>
    <div id = "dynList">
        <form:form action="/machineType/${machineType.id}/maintenance/createdNew" method="post">
            Периодичность То(мч)  <input name ="smr" type="number">
   <button id ="addPart" type="button " onclick="addRow">Добавить строку</button>
        <input type="submit" value="Сохранить">
<br>
        <table id = "table">
        <tr>
            <th>Артикул</th>
            <th>Описание</th>
            <th>Единица</th>
            <th>Количесто</th>
             </tr> <>
        </table>
        </form:form>
    </div>
    <script>
        function addRow(){
                    var table=document.getElementById("table");
                    var row = table.insertRow();
                    var partNumberCell =row.insertCell(0);
                    var descriptionCell =row.insertCell(1);
                    var unitCell =row.insertCell(2);
                    var quantityCell =row.insertCell(3);
                    partNumberCell.innerHTML ="<input type='text' name= 'partNumber[]'>";
                    descriptionCell.innerHTML ="<input type='text' name= 'description[]'>";
                    unitCell.innerHTML ="<input type='text' name= 'unit[]'>";
                    quantityCell.innerHTML ="<input type='number' name= 'quantity[]'>";
        }
    </script>
</div>
</body>
</html>
