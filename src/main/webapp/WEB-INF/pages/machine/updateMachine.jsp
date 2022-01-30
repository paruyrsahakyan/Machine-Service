<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 5/24/2018
  Time: 11:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>updateMachine</title>
    <style><%@include file="/WEB-INF/pages/CSS/topNavigation.css"%></style>
    <style><%@include file="/WEB-INF/pages/CSS/tables.css"%></style>
</head>
<body>

<div class="topnav">
    <a href="/"> Главное меню</a>
    <a class="active" href="/allCustomers"> Компании </a>
    <a href="/allMachines"> Mашины </a>
    <a href="/analysis/worker/jobs"> Анализ работ</a>
    <a href="/workOrder/home"> Наряды </a>
    <a href="/admin/workers"> Работники </a>
    <a href="/admin/allServiceMachines"> Сервисные Mашины</a>
    <a href="/machineType/allMachineTypes"> Типы Машин</a>
    <a href="/wareHouse"> Склад </a>
    <a href="/price/mainPage"> Прайс </a>
    <a href="/offer/mainPage">  Заказы </a>

    </div>
<div class="mainContent">
    <h2>Редактирование машины</h2>
    <a href="/customer/machine/${machine.id}">Отменить редактирование</a> &nbsp;<b>|</b>
    <a href="/customer/machine/${machine.id}/changeCustomer" style="bottom: auto"> Менять Влодельца</a>
    <br>
    <h4>Владелец: ${machine.customer}</h4>
      <form:form action="/customer/machine/updatedMachine/${machine.id}" method="post" accept-charset="UTF-8">
          Тип Машины<br>
          <select name="machineType" required >
              <option value="${machine.machineType.id}" selected> ${machine.machineType.typeDescription}</option>
              <c:forEach items="${machineTypeList}" var="machineType">
                  <option value=${machineType.id}> ${machineType.typeDescription}</option>
              </c:forEach>
          </select>
          <br><br>
            Обслуживает ДБ? &nbsp;
              <input type="radio"  id="radioYes" name="maintainedByIko" value="Да" > Да
              &nbsp; &nbsp;
              <input type="radio" id="radioNo"name="maintainedByIko" value="Нет" > Нет
          <br><br>
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

    </form:form>
    <script>
        var radio= "${machine.maintainedByIko}";
        if (radio==="Да"){
            document.getElementById("radioYes").checked=true;
        }
        else document.getElementById("radioNo").checked=true;

    </script>

</div>
</body>
</html>
