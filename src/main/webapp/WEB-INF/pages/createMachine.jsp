<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 5/19/2018
  Time: 9:18 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Новая Машина</title>
</head>
<body>
<h2>Регистрация новой машины</h2> <br>
<h3>Влоделец: &nbsp; ${customerName}</h3>
<form action="/customer/newMachine/${customerId}" method="post" accept-charset="UTF-8">

    Модель Машины:<br>
    <input type="text" name="model" style="height: auto"><br><br>
    Серийный номер:<br>
    <input type="text" name="serialNumber" style="height: auto"><br><br>
    Модель ДВС: <br>
    <input type="text" name="engineModel" style="height: auto"><br><br>
    Сер. номер ДВС: <br>
    <input type="text" name="engineSerialNumber" style="height: auto"><br><br>
    Год производства: <br>
    <input type="number" name="productionYear" style="height: auto"><br><br>
    Другая информация: <br>
    <textarea name="otherInfo" cols="40" rows="5"></textarea> <br><br>


    <input type="submit" value="Сохранить">
</form>

</body>
</html>
