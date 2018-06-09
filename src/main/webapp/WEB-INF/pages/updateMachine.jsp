<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 5/24/2018
  Time: 11:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>updateMachine</title>
</head>
<body>
<h2>Редактирование машины</h2>
<br> <br>
<h4>Владелец: ${machine.customer}</h4>
<form action="/customer/machine/updatedMachine/${machine.id}" method="post" accept-charset="UTF-8">

    Модель  Машины:<br>
    <input type="text" name="model" style="height: auto" value="${machine.model}"><br><br>
    Серийный номер:<br>
    <input type="text" name="serialNumber" style="height: auto" value="${machine.serialNumber}"><br><br>
    Модель ДВС: <br>
    <input type="text" name="engineModel" style="height: auto" value="${machine.engineModel}"><br><br>
    Сер. номер ДВС: <br>
    <input type="text" name="engineSerialNumber" style="height: auto" value="${machine.engineSerialNumber}"><br><br>
     Год производства: <br>
    <input type="number" name="productionYear" style="height: auto" value="${machine.productionYear}"><br><br>
    Другая информация: <br>
    <textarea name="otherInfo" cols="40" rows="5" >${machine.otherInfo}</textarea> <br><br>
           <input type="submit" value="Сохранить">

</form>

</body>
</html>
