<%--
  Created by IntelliJ IDEA.
  User: Paruyr
  Date: 2/3/2019
  Time: 6:53 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Создание записи  в истории</title>
</head>
<body>
<div style="text-align: center">
    <h2>Новый Наряд</h2>
    <br>
    <div id = "test"> </div>
       <form:form action="/workOrder/new" method="post" accept-charset="UTF-8">
        Заказчик:  <br>
        <input list="customers" name="customer" onchange="getMachineList()" >
        <datalist id="customers" >
            <c:forEach items="${customerList}" var="customer">
            <option value="${customer.id}"> ${customer.name}</option>
            </c:forEach>
        </datalist>
        <br><br>
        Машина:  <br>
        <select name="machineId">
              <div id="machineOptions"> &nbsp; </div>
        </select>
        <br><br>
        Моточасы:<br>
        <input type="number" name="SMR" >
        <br><br>
        Дата:<br>
        <input type="date" name="date" required >
        <br><br>
        Местанахождение Машины:<br>
        <input type="text" name="location" >
        <br><br>
         Ответсвенный: <br>
        <select name="worker">
            <c:forEach items="${workerList}" var="worker">
                <option value="${worker.name}"> ${worker.name}</option>
            </c:forEach>
        </select>  <br> <br>
        Сервисная Машина: <br>
        <select name="serviceMachine">
            <c:forEach items="${serviceMachineList}" var="serviceMachine">
                <option value="${serviceMachine.id}"> ${serviceMachine.name}</option>
            </c:forEach>
        </select>
    </form:form>
</div>
<script>
    var initialMachineList = [];
    var selectedCustomersMachines = [];

    <c:forEach items="${machineList}" var="machine">
    var machineModel = "${machine.model}";
    var machineSerialNumber = "${machine.serialNumber}";
    var customerName = "${machine.customer}";
    var machineId = "${machine.id}";

    initialMachineList.push({model: machineModel,
        serialNumber: machineSerialNumber,
        customerName: customerName,
        id: machineId});
    </c:forEach>

    function initSelectedCustomersMachines() {
        var selectedCustomer = document.getElementById("customers").value;
             for (var i = 0; i < initialMachineList.length; i++) {
                if (initialMachineList[i].customerName.e === selectedCustomer) {
                    selectedCustomersMachines.push(initialMachineList[i])
                           }
            }
    }

        function getMachineList(){
        alert("selcted");
          initSelectedCustomersMachines();
                    var machineInput = document.getElementById("machineOptions");
            document.getElementById("test").innerHTML="<b>" + machineInput + "</b>";
            for (var i=0; i<selectedCustomersMachines.length; i++) {
            machineInput.innerHTML += "<option value='"+
                selectedCustomersMachines[i].id + "'>" +
                selectedCustomersMachines[i].model+ "; sn" +
                selectedCustomersMachines[i].serialNumber + "</option>";

        }
                }

</script>
</body>
</html>
