<%--
  Created by IntelliJ IDEA.
  User: Paruyr
  Date: 1/9/2019
  Time: 11:36 PM
  To change this template use File | Settings | File Templates.
--%>
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <!DOCTYPE html>
<html>
<head>
    <style><%@include file="/WEB-INF/pages/CSS/topNavigation.css"%></style>
     <style><%@include file="/WEB-INF/pages/CSS/offerTable.css"%></style>
     <style>
/* Chrome, Safari, Edge, Opera */
input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}

/* Firefox */
input[type=number] {
  -moz-appearance: textfield;
}
</style>
    </head>
<body>
<div class="topnav">
    <a href="/"> Главное меню </a>
    <a href="/allCustomers"> Компании </a>
    <a href="/allMachines"> Mашины </a>
    <a href="/analysis/worker/jobs"> Анализ работ</a>
    <a href="/workOrder/home"> Наряды </a>
    <a href="/admin/workers"> Работники </a>
    <a href="/admin/allServiceMachines"> Сервисные Mашины</a>
    <a href="/machineType/allMachineTypes"> Типы Машин</a>
    <a href="/wareHouse"> Склад </a>
    <a href="/price/mainPage"> Прайс </a>
    <a class="active"  href="/offer/mainPage">  Заказы </a>
</div>

<div class="mainContent">
    <h2>Создание Нового КП</h2>
 </div>

<div class = "mainContent">
<table class="offerTable">
             <tr>
                <td>  Заказчик  </td>
                <td>  ${offer.customer.name}</td>   <br> <br>
                <td>  Номер Заявки  </td>
                <td>  ${offer.requestNumber}</td> <br> <br>
                <td>  Дата КП  
                <td>  ${offer.offerDate} </td>  <br>  <br>
                <td>  Срок действия КП </td>
                <td>  ${offer.validationDate}  </td>  <br> <br>
                <td>  Валюта   </td> 
                <td>  ${offer.currency} </td> 
            </tr>
            <tr>
                <td> МП % </td>
                <td> ${offer.profitPercentage}</td>
                <td> Перевозка % </td>
                <td> ${offer.transportation}< /td>
                <td> Скидка </td>
                <td> ${offer.discount}</td>
                <td> Курс </td>
                <td> ${offer.exchangeRate}  </td>
                <td> ${offer.VAT} 
            </tr>
      
    </table>
 </div>

<br><br>

 <br> <br>

<div class="mainContent">
       <table class="offerTable" id="offerTable" style="width: auto" align="center">
                    <script>

     
       
        var offerLines=[];

           
            <c:forEach items="${offer.offerLines}" var="offerLine">
                   var partName = "${offerLine.requestedPartName}";
                   var partNumber = "${offerLine.requestedPartNumber}";
                   var offeredPartNumber = "${offerLine.offeredPartNumber}";
                   var quantity = "${offerLine.quantity}";
                   var unit = "${offerLine.unit}";
                   var price = "${offerLine.price}";
                   var sum ="${offerLine.sum}";
                   var supplyTime ="${offerLine.supplyTime}";
                   var producer ="${offerLine.producer}";
                   var confirmationCondition ="${offerLine.confirmationCondition}";
                   var supplierPrice = "${offerLine.supplierPrice}";
                   var lastOfferPrice = "${offerLine.lastOfferedPrice}";
                   var lastOfferDate = "${offerLine.lastOfferDate}";
                   var availability = "${offerLine.availability}";
                   var profitFromAvailable = "${offerLine.profitFromAvailable}";
                   var inStockNetCost = "${offerLine.inStockNetCost}";
                  
  
                   offerLines.push({
                    partName: partName,
                   partNumber: partNumber,
                   offeredPartNumber: offeredPartNumber,
                   quantity: quantity,
                   unit: unit,
                   price: price,
                   sum:sum,
                   supplyTime: supplyTime,
                   producer: producer,
                   confirmationCondition: confirmationCondition,
                   supplierPrice:supplierPrice,
                   lastOfferPrice:lastOfferPrice,
                   lastOfferDate: lastOfferDate,
                   availability:availability,
                   profitFromAvailable:profitFromAvailable;
                   inStockNetCost:inStockNetCost,

                                       });
             

        function setOfferLinesInTable() {

        var table = document.getElementById("offerTable");
        table.innerText = "";
        var titleRow = table.insertRow();
        var titleCell1 = titleRow.insertCell(0);
        var titleCell2 = titleRow.insertCell(1);
        var titleCell3 = titleRow.insertCell(2);
        var titleCell4 = titleRow.insertCell(3);
        var titleCell5 = titleRow.insertCell(4);
        var titleCell6 = titleRow.insertCell(5);
        var titleCell7 = titleRow.insertCell(6);
        var titleCell8 = titleRow.insertCell(7);
        var titleCell9 = titleRow.insertCell(8);
        var titleCell10 = titleRow.insertCell(9);
        var titleCell11 = titleRow.insertCell(10);
        var titleCell12 = titleRow.insertCell(11);
        var titleCell13 = titleRow.insertCell(12);
        var titleCell14 = titleRow.insertCell(13);
        var titleCell15 = titleRow.insertCell(14);
        var titleCell16 = titleRow.insertCell(15);
        var titleCell17 = titleRow.insertCell(16);

        titleCell1.innerHTML = "N";
        titleCell2.innerHTML = "Название";
        titleCell3.innerHTML = "Артикул запроса";
        titleCell4.innerHTML =  "Количество";
        titleCell5.innerHTML = "Артикул КП";
        titleCell6.innerHTML = "Ед.";
        titleCell7.innerHTML = "Цена";
        titleCell8.innerHTML = "Сумма";
        titleCell9.innerHTML = "Цена послед. КП";
        titleCell10.innerHTML = "Дата посл. КП";
        titleCell11.innerHTML = "Налич. Масиса 3";
        titleCell12.innerHTML = "Себест. Масис3";
        titleCell13.innerHTML = "МП от М3";
        titleCell14.innerHTML = "Срок поставки";
        titleCell15.innerHTML = "Цена поставщика";
        titleCell16.innerHTML = "Авиап.";
        titleCell17.innerHTML = "Произв.";

        titleCell1.style.fontWeight = 'bold';
        titleCell2.style.fontWeight = 'bold';
        titleCell3.style.fontWeight = 'bold';
        titleCell4.style.fontWeight = 'bold'
        titleCell5.style.fontWeight = 'bold'
        titleCell6.style.fontWeight = 'bold'
        titleCell7.style.fontWeight = 'bold';
        titleCell8.style.fontWeight = 'bold';
        titleCell9.style.fontWeight = 'bold';
        titleCell10.style.fontWeight = 'bold';
        titleCell11.style.fontWeight = 'bold';
        titleCell12.style.fontWeight = 'bold';
        titleCell13.style.fontWeight = 'bold';
        titleCell14.style.fontWeight = 'bold';
        titleCell15.style.fontWeight = 'bold';
        titleCell16.style.fontWeight = 'bold';
        titleCell17.style.fontWeight = 'bold';


        for (var i = 0; i < offerLines.length; i++) {
                var row = table.insertRow();
                var cell1 = row.insertCell(0);
                var cell2 = row.insertCell(1);
                var cell3 = row.insertCell(2);
                var cell4 = row.insertCell(3);
                var cell5 = row.insertCell(4);
                var cell6= row.insertCell(5);
                var cell7 = row.insertCell(6);
                var cell8 = row.insertCell(7);
                var cell9= row.insertCell(8);
                var cell10 = row.insertCell(9);
                var cell11 = row.insertCell(10);
                var cell12 = row.insertCell(11);
                var cell13 = row.insertCell(12);
                var cell14 = row.insertCell(13);
                var cell15 = row.insertCell(14);
                var cell16 = row.insertCell(15);
                var cell17 = row.insertCell(16);

                cell1.innerHTML = (i + 1);
                cell2.innerHTML = offerLines[i].partName;
                cell3.innerHTML = offerLines[i].partNumber;
                cell4.innerHTML = offerLines[i].quantity;
                cell5.innerHTML = offerLines[i].offeredPartNumber;
                cell6.innerHTML = offerLines[i].unit;
                cell7.innerHTML = offerLines[i].price;
                cell8.innerHTML = offerLines[i].sum;
                cell9.innerHTML = offerLines[i].lastOfferPrice;
                cell10.innerHTML = offerLines[i].lastOfferDate;
                cell11.innerHTML = offerLines[i].availability;
                cell12.innerHTML = offerLines[i].inStockNetCost;
                cell13.innerHTML = offerLines[i].profitFromAvailable;
                cell14.innerHTML = offerLines[i].deliveryTime;
                cell15.innerHTML = offerLines[i].supplierPrice;
                cell16.innerHTML = " ";
                cell17.innerHTML = offerLines[i].producer;

            }

                var totalRow = table.insertRow();
            for (var i = 0; i < 17; i++) {
                    if (i==7) {
                    var totalSumCell = totalRow.insertCell(i);
                    totalSumCell.innerHTML = ${offer.sum};
                } else {
                  totalRow.insertCell(i);
                }

            }
          

          </script>

</body>
</html>
