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
        <form:form action="/workOrder/createdWorkOrder" method="post" accept-charset="UTF-8">
                 Заказчик:  <br>
        <input list="customers" name="customer" id="selectedCustomer" oninput="setMachineList()" >
        <datalist id="customers" >
            <c:forEach items="${customerList}" var="customer">
                <option value="${customer.name}" hidden> ${customer.name} </option>
            </c:forEach>
        </datalist>
        <br><br>
        Машина:  <br>
            <select name="machineId" id="machineOptions" oninput="setMaintenanceList()">

            </select>
            <br><br>
        Планируемая ТО:  <br>
            <select name="periodicMaintenance" id="periodicMaintenance" >
                  </select>
            <br><br>
        Моточасы:<br>
        <input type="number" name="smr" >
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
        maintenanceList: periodicMaintenanceList});
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
                        for(var j=0; j<initialMachineList[i].maintenanceList.length; j++){
                            maintenanceList.push({maintenanceId:initialMachineList[i].maintenanceList[j].maintenanceId,
                                actualSmr:initialMachineList[i].maintenanceList[j].actualSmr});
                        }
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
