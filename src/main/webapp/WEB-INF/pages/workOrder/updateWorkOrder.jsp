<%--
  Created by IntelliJ IDEA.
  User: Paruyr
  Date: 2/9/2019
  Time: 12:58 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Редактирование Наряда</title>
</head>
<body>
<div style="text-align: center">
    <h2>Редактирование Наряда</h2>
    <a href="/workOrder/${workOrder.id}"> Отменить </a>">
    <br>
    <br>
    <form:form action="/workOrder/${workOrder.id}/upadated" method="post" accept-charset="UTF-8">
        Заказчик:  <br>
        <input list="customers" value="${workOrder.machine.customer}" id="selectedCustomer" onchange="setMachineList()" >
        <datalist id="customers" >
              <c:forEach items="${customerList}" var="customer">
                <option value="${customer.name}">
            </c:forEach>
        </datalist>
        <br><br>
        Машина:  <br>
        <select name="machineId"  id="machineOptions" oninput="setMaintenanceList()">
            <option selected value="${workOrder.machine.id}">
                    ${workOrder.machine.model} &nbsp; sn ${workOrder.machine.serialNumber}</option>
        </select>
        <br><br>
        Планируемая ТО:  <br>
        <select name="periodicMaintenance" id="periodicMaintenance" >
            <option selected value="${workOrder.periodicMaintenance.id}">
              TO ${workOrder.periodicMaintenance.smr}
            </option>
        </select>
        <br><br>
        Моточасы:<br>
        <input type="number" name="smr"  value="${workOrder.smr}">
        <br><br>
        Дата:<br>
        <input type="date" name="date" value="${workOrder.orderDate}" required >
        <br><br>
        Местoнахождение Машины:<br>
        <input type="text" name="location" value="${workOrder.location}" >
        <br><br>
        Ответсвенный: <br>
        <select name="worker">
            <c:forEach items="${workerList}" var="worker">
                <option value="${worker.name}"> ${worker.name}</option>
            </c:forEach>
        </select>  <br> <br>
        Сервисная Машина: <br>
        <select name="serviceMachine" >
            <option value="${workOrder.machine}"> ${workOrder.machine}</option>
            <c:forEach items="${serviceMachineList}" var="serviceMachine">
                <option value="${serviceMachine.name}"> ${serviceMachine.name}</option>
            </c:forEach>
        </select>
        <br> <br>
        <input type="submit" value="Сохранить">
    </form:form>
</div>
<script>
    var initialMachineList = [];
    var machinesOfSelectedCustomer = [];
    var maintenanceList=[];
    <c:forEach items="${machineList}" var="machine">
    var machineModel = "${machine.model}";
    var machineSerialNumber = "${machine.serialNumber}";
    var customerName = "${machine.customer}";
    var machineId = "${machine.id}";
    var periodicMaintenanceList =[];
    <c:forEach items="${machine.machineType.periodicMaintenanceList}" var="periodicMaintenance">
    var maintenanceSmr ="${periodicMaintenance.smr}";
    var maintenanceId= "${periodicMaintenance.id}";
    periodicMaintenanceList.push({actualSmr: maintenanceSmr,
        maintenanceId: maintenanceId});
    </c:forEach>
    initialMachineList.push({model: machineModel,
        serialNumber: machineSerialNumber,
        customerName: customerName,
        id: machineId,
        selectedMaintenanceList: periodicMaintenanceList});
    </c:forEach>

    function initSelectedCustomersMachines() {
        var selectedCustomer = document.getElementById("selectedCustomer").value;
        machinesOfSelectedCustomer=[];
        for (var i = 0; i < initialMachineList.length; i++) {
            if (initialMachineList[i].customerName === selectedCustomer) {
                machinesOfSelectedCustomer.push(initialMachineList[i])
            }
        }
    }
    function setMachineList(){
        initSelectedCustomersMachines();
        var machineInput = document.getElementById("machineOptions");
        machineInput.innerHTML = "<option> &nbsp; </option> "
        for (var i=0; i<machinesOfSelectedCustomer.length; i++) {
            machineInput.innerHTML += "<option value='"+
                machinesOfSelectedCustomer[i].id + "'>" +
                machinesOfSelectedCustomer[i].model+ "; sn" +
                machinesOfSelectedCustomer[i].serialNumber + "</option>";
        }
    }
    function initMaintenanceList() {
        var selectedMachineId = document.getElementById("machineOptions").value;
        maintenanceList = [];
        for (var i = 0; i < initialMachineList.length; i++) {
            if (initialMachineList[i].id.toString()=== selectedMachineId) {
                maintenanceList = initialMachineList[i].selectedMaintenanceList;
            }
        }
    }
    function setMaintenanceList() {
        initMaintenanceList();
        var maintenanceInput=document.getElementById("periodicMaintenance");
        maintenanceInput.innerHTML=" ";
        for(var i=0; i<maintenanceList.length; i++){
            var a = maintenanceList[i].maintenanceId;
            var b = maintenanceList[i].actualSmr;
            maintenanceInput.innerHTML +="<option value='"+
                a + "'> TO" +
                b + "</option>";
        }
    }
</script>
</body>
</html>
