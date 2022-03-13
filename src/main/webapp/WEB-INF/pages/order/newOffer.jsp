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
        <form>
            <tr>
                <td>  <label for="selectedCustomerName">  Заказчик   </label>  </td>
                <td>  <input list="customers" name="customerName" id="selectedCustomerName"  value="${selectedCustomer.name}"
                          onchange="setCustomer()">
                   <datalist id="customers">
                       <c:forEach items="${customerList}" var="customer">
                           <option value="${customer.name}" hidden> ${customer.name} </option>
                       </c:forEach>
                   </datalist>
                </td>   <br> <br>
                <td>  <label for = "requestNumber">  Номер Заявки   </label> </td> 
                <td>   <input type="text" id="requestNumber" name="requestNumber" onchange="setHiddenRequestNumber()">  </td> <br> <br>
                <td>  <label for = "offerDate"> Дата КП  </label>   </td>
                <td>  <input type="Date" name="offerDate"  id="offerDate" onchange="setHiddenOfferDate()"> </td>
                <br>  <br>
                <td>  <label for = "offerValidationDate"> Срок действия КП </label> </td>
                <td>  <input type="Date" name="offerValidationDate" id="offerValidationDate"  onchange="setHiddenValidationDate()"> </td>  <br> <br>
                <td>  <label for = "currency">  Валюта </label>
            
                     <select name="currency" required  id="currency" onchange="setHiddenCurrency()">
            <option  value="AMD"> AMD  </option> 
            <option  value="USD"> USD </option>
            <option  value="RUB"> RUB </option>
            <option  value="EURO"> EURO</option>
            
        </select> 
                 </td>
            </tr>
            <tr>
                <td> <label for="profitPercentage">МП %</label>  </td>
                <td> <input type="number" id = "profitPercentage" name = "profitPercentage" onchange="setHiddenProfitPercentage()">  </td>
                <td> <label for="transportation">Перевозка %</label> </td>
                <td> <input type="number" name="transportation" id="transportation" onchange="setHiddenTransportation()">  </td>
                <td> <label for="discount">Скидка</label>  </td>
                <td> <input type="number" name="discount" id="discount" onchange="setHiddenDiscount()">  </td>
                <td> <label for="exchangeRate">Курс</label> </td>
                <td> <input type="number" name="exchangeRate" step="0.01" id="exchangeRate" onchange="setHiddenExchangeRate()">   </td>
                <td> <input type="radio" name="VAT" value="withoutVAT" id="VAT" onchange="setHiddenVAT()">  <label for="VAT"> Без НДС </label>
                     <br>  <input type="radio" name="VAT" value="withVAT" id="VAT"> 
                       <label for="VAT"> Вкл 20% НДС</label>
                 </td>
            </tr>
        </form>
    </table>
 </div>

<br><br>

 <div  class="mainContent" id="fileUploadForm" >
                    <form:form action="/offer/newOffer/setRequestFromFile" method="post"  accept-charset="UTF-8"   enctype="multipart/form-data">
                     Загрузить запрос:
                     <input type="file" name="requestFile">
                     <input id="customerNameInUploadForm"  type="hidden" name="customerName" value="${selectedCustomer.name}">
                     <input  type="submit" value="загрузить">
                     <button type="button"  onclick="setPrice()"> Пересчитать </button>
                    </form:form>
         </div>

<br> <br>

<div class="mainContent">
     <form:form action="/offer/newOffer/saveOffer" method="post"  accept-charset="UTF-8" >
                   <table class="offerTable" id="offerTable" style="width: auto" align="center">
        <tr>
        </tr>
        </table>
        <input id="hiddenCustomerName"  type="hidden" name="customerName" value="${selectedCustomer.name}">
        <input id="hiddenRequestNumber"  type="hidden" name="requestNumber" value="${offer.requestNumber}">
        <input id="hiddenOfferDate"  type="hidden" name="offerDate" value="${offer.offerDate}">
        <input id="hiddenOfferValidationDate"  type="hidden" name="offerValidationDate" value="${offer.validationDate.name}">
        <input id="hiddenCurrency"  type="hidden" name="currency" value="${offer.currency}">
        <input id="hiddenProfitPercentage"  type="hidden" name="profit" value="${offer.profitPercentage}">
        <input id="hiddenTransportation"  type="hidden" name="transportation" value="${offer.transportation}">
        <input id="hiddenDiscount"  type="hidden" name="discount" value="${offer.discount}">
        <input id="hiddenExchangeRate"  type="hidden" name="exchangeRate" value="${offer.exchangeRate}" >
        <input id="hiddenVAT"  type="hidden" name="VAT" value="${offer.VAT}" >
        <bvr>
            <br>
      <input type="submit" value="Сохранить КП">
        </form:form>
        

 </div>
             <script>

        var initialPriceList = [];
        var customerList = [];
        var priceListForSelectedCustomer = [];
        var priceListFilteredByArticle = [];
        var priceListForSelectedCustomer = [];
        var selectedCustomerName = "${selectedCustomer.name}";
        var offerLines=[];

         <c:forEach items="${priceList}" var="priceForCustomer">
            var id ="${priceForCustomer.id}";
            var article = "${priceForCustomer.article}";
             var description = "${priceForCustomer.description}";
             var price = "${priceForCustomer.price}";
             var customerName = "${priceForCustomer.customerName}";
             var quantityInStock = "${priceForCustomer.quantityInStock}";
             var profit = "${priceForCustomer.profit}";
             var lastOfferDate = "1111-11-11"; 
              initialPriceList.push({
             id: id,
             article: article,
             description: description,
             price: price,
             customerName: customerName,
             quantityInStock: quantityInStock,
             profit: profit,
             lastOfferDate : lastOfferDate
             });
           </c:forEach>

            <c:forEach items="${offer.offerLines}" var="offerLine">
                   var partName = "${offerLine.requestedPartName}";
                   var partNumber = "${offerLine.requestedPartNumber}";
                   var quantity = "${offerLine.quantity}";
                   var offeredPartNumber = "${offerLine.offeredPartNumber}";
                   var supplierPrice = "${offerLine.supplierPrice}";
                   var sum ="${offerLine.sum}";
                   var inStockNetCost = "${offerLine.inStockNetCost}";
                   var lastOfferPrice = "${offerLine.lastOfferedPrice}";
                   var availability = "${offerLine.availability}";

                   offerLines.push({
                    partName: partName,
                   partNumber: partNumber,
                   quantity: quantity,
                   offeredPartNumber:offeredPartNumber,
                   supplierPrice:supplierPrice,
                   sum:sum,
                   inStockNetCost:inStockNetCost,
                   lastOfferPrice:lastOfferPrice,
                   availability:availability
                                       });
               </c:forEach>

                
                 setDefaultOfferDates();

               if (offerLines.length > 0) {
             setOfferLinesInTable();
                setCustomer();
               }

              function setCustomer() {
         selectedCustomerName=document.getElementById("selectedCustomerName").value;
                document.getElementById("customerNameInUploadForm").value=selectedCustomerName;
                document.getElementById("hiddenCustomerName").value = selectedCustomerName;
    }

          function initPriceListForSelectedCustomer() {
              var customerName = customerNameForTableCreation;
            for (var i = 0; i < initialPriceList.length; i++) {
        if (initialPriceList[i].customerName === customerName) {
            priceListForSelectedCustomer.push(initialPriceList[i]);
                  }
        }
                }

        var setLastOfferPrice = function () {
            alert("Hello! I am an alert box!!");
       }


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

  
     var parameterRow = table.insertRow();

       parameterRow.insertCell();
       parameterRow.insertCell();
       parameterRow.insertCell();
       parameterRow.insertCell();
       parameterRow.insertCell();
       var cellUnits = parameterRow.insertCell(5);
         cellUnits.innerHTML =  "<select id='unitForAllLines' onChange = 'setUnits()'>"+
        "<option  value='шт'> шт </option> "+
        "<option  value='л'> л </option> "+
        "</select>" ;
        parameterRow.insertCell();
        parameterRow.insertCell();
        parameterRow.insertCell();
        parameterRow.insertCell();
        parameterRow.insertCell();
        parameterRow.insertCell();
        parameterRow.insertCell();
        parameterRow.insertCell();
        parameterRow.insertCell();
        parameterRow.insertCell();

        
        var producerCell = parameterRow.insertCell(16);
        producerCell.innerHTML =  "<input id='ProducerForAllLines'  size='5' onkeyup = 'setProducer()'  value='KOMATSU' >"
     
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

                cell1.innerHTML = "<input type='number'  style ='width:30px' name= 'position[]'  value='" +(i + 1) + "' >";
                cell2.innerHTML = "<input type='text'   name= 'partName[]'  value='" +offerLines[i].partName+"'"+"> ";
                cell3.innerHTML = "<input type='text' size='11'name= 'partNumber[]'  value='" +offerLines[i].partNumber+"'"+"> ";
                cell4.innerHTML = "<input type='number' style ='width:30px' name= 'quantity[]'  value='" +offerLines[i].quantity+"'"+"> ";
                cell5.innerHTML = "<input type='text' size='11' name= 'offeredPartNumber[]'  value='" +offerLines[i].offeredPartNumber+"'"+"> ";
                cell6.innerHTML = "<input type='text' size='2' id='unit"+(i+1)+"' name= 'unit[]'  value='шт'>";
                cell7.innerHTML = "<input type='number' style ='width:70px' id='price"+(i+1)+"' name= 'price[]' onChange ='setSum("+i+")' >";
                cell8 .innerHTML = "<input type='number' style ='width:70px' id='sum"+(i+1)+"' name= 'sum[]' >"
                cell9 .innerHTML = "<input type='number' style ='width:100px' name= 'lastOfferPrice[]' value='" +offerLines[i].lastOfferPrice+"' >";
                cell10.innerHTML = "<input type='date' style ='width:130px' name= 'lastOfferDate[]' value='" + offerLines[i].lastOfferDate + "' >";
                cell11.innerHTML = "<input type='number' style ='width:100px' name= 'availability[]' value='" + offerLines[i].availability + "' >";
                cell12.innerHTML = "<input type='number' style ='width:100px' name= 'inStockNetCost[]' value='" + offerLines[i].inStockNetCost + "' >";
                cell13.innerHTML = "<input type='number' style ='width:60px' id='profitFromAvailable"+(i+1)+"' name= 'profitFromAvailable[]' >";
                cell14.innerHTML = "<input type='number' style ='width:130px' name= 'deliveryTime[]' >";
                cell15.innerHTML = "<input type='number' step = 'any' style ='width:110px' id='supplierPrice"+(i+1)+"'name= 'supplierPrice[]' value='" + offerLines[i].supplierPrice + "' >";
                cell16.innerHTML = "<input type='number' style ='width:50px' id='avia"+(i+1)+"'name= 'avia[]' >";
                cell17.innerHTML = "<input type='text'  size='6'   id='producer"+(i+1)+"' name= 'producer[]' >";
                
             
            }
}

 function setSum(rowNumber) {
    var price = document.getElementById("price"+(rowNumber+1)).value;
    var quantity  = document.getElementById("unit" +(rowNumber+1)).value;
    document.getElementById("sum"+(rowNumber+1)).value = price*quantity;

 }

function setUnits()  {

 var units = document.getElementById("unitForAllLines").value;
for (var i = 1; i <= offerLines.length; i++) {
    document.getElementById("unit"+i).value = units;

    }
}

function setProducer() {

 var producer = document.getElementById("ProducerForAllLines").value;
for (var i = 1; i <= offerLines.length; i++) {
     document.getElementById("producer"+i).innerHTML = producer;
    }
}


function setPrice() {
    var profitPercentage  = document.getElementById("profitPercentage").value;
    var transportation = document.getElementById("transportation").value;
    var discount = document.getElementById("discount").value;
    var exchangeRate = document.getElementById("exchangeRate").value;

    for (var i = 1; i <= offerLines.length; i++) {

     var  supplierPrice = offerLines[i-1].supplierPrice;
     var quantity = offerLines[i-1].quantity;
     var inStockNetCost = offerLines[i-1].inStockNetCost;
     var price = supplierPrice/1.2*((100-discount)/100)*exchangeRate/(100-profitPercentage)*100;
     price = Math.round(price+price*transportation/100);
     var profitFromAvailable = Math.round((price-inStockNetCost)/price*100);
     var sum = price*quantity;
      
     var priceCell = document.getElementById("price"+i);
     var sumCell = document.getElementById("sum"+i);
     var profitFromAvailableCell = document.getElementById("profitFromAvailable"+i);

     priceCell.value = price;
     sumCell.value = sum;
     profitFromAvailableCell.value = profitFromAvailable;
  
    }

}

function setHiddenRequestNumber(){
    var requestNumber = document.getElementById("requestNumber").value;
    document.getElementById("hiddenRequestNumber").value = requestNumber;
}

function setHiddenCustomerName(){
    var customerName = document.getElementById("customerName").value;
    document.getElementById("setHiddenCustomerName").value = customerName;
}

function setHiddenOfferDate(){
    var offerDate = document.getElementById("offerDate").value;
    document.getElementById("hiddenOfferDate").value = offerDate;
}

function setHiddenValidationDate(){
    var offerValidationDate = document.getElementById("offerValidationDate").value;
    document.getElementById("hiddenOfferValidationDate").value = offerValidationDate;
}

function setHiddenCurrency(){
    var currency = document.getElementById("currency").value;
    document.getElementById("hiddenCurrency").value = currency;
}

function setHiddenProfitPercentage(){
    var profitPercentage = document.getElementById("profitPercentage").value;
    document.getElementById("hiddenProfitPercentage").value = profitPercentage;
}

function setHiddenTransportation(){
    var transportation = document.getElementById("transportation").value;
    document.getElementById("hiddenTransportation").value = transportation;
}

function setHiddenDiscount(){
    var discount = document.getElementById("discount").value;
    document.getElementById("hiddenDiscount").value = discount;
}

function setHiddenExchangeRate(){
    var exchangeRate = document.getElementById("exchangeRate").value;
    document.getElementById("hiddenExchangeRate").value = exchangeRate;
}

function setHiddenVAT(){
    var VAT = document.getElementById("VAT").value;
    document.getElementById("hiddenVAT").value = VAT;
}

function setDefaultOfferDates(){

   var today =   new Date();
   document.getElementById("offerDate").valueAsDate = today;
   document.getElementById("hiddenOfferDate").value =  document.getElementById("offerDate").value;

   var lastDayOfMonth = new Date(today.getFullYear(), today.getMonth()+1, 0);
      document.getElementById("offerValidationDate").valueAsDate = lastDayOfMonth;
 document.getElementById("hiddenOfferValidationDate").value = document.getElementById("offerValidationDate").value;

}

         

         </script>

</body>
</html>
