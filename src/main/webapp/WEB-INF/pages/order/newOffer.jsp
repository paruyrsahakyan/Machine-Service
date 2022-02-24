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
    <style><%@include file="/WEB-INF/pages/CSS/tables.css"%></style>
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
<table style="alignment:center">
        <form>
            <tr>
                <td>
                    <label for="selectedCustomerName">
                         Заказчик
                    </label>
                </td>
                <td>  <input list="customers" name="customerName" id="selectedCustomerName"  value="${selectedCustomer.name}"
                          onchange="setCustomer()">
                   <datalist id="customers">
                       <c:forEach items="${customerList}" var="customer">
                           <option value="${customer.name}" hidden> ${customer.name} </option>
                       </c:forEach>
                   </datalist>
                </td>     <br> <br>
                <td>
                    <label for = "requestNumber">
                        Номер Заявки
                        </label>
                </td>
                 <td>
                    <input type="text" id="requestNumber" name="requestNumber">
                 </td> <br> <br>
                 <td>
                    <label for = "offerDate">
                        Дата КП
                        </label>
                </td>
                <td>
                     <input type="Date" name="offerDate"  id="offerDate">
                 </td>
                  <br> <br>
                  <td>
                    <label for = "offerValidationDate">
                        Срок действия КП
                        </label>
                </td>
                <td>
                     <input type="Date" name="offerValidationDate" id="offerValidationDate" >
                 </td>     <br> <br>
                  <td>
                    <label for = "currency">
                        Валюта
                        </label>
            
                     <select name="currency" required  id="currency">
            <option  value="AMD"> AMD  </option> 
            <option  value="USD"> USD </option>
            <option  value="RUB"> RUB </option>
            <option  value="EURO"> EURO</option>
            
        </select> 
                 </td>
            </tr>
            <tr>
                <td>
                    <label for="profitPercentage">МП %</label>
                </td>
                <td>
                    <input type="number" id = "profitPercentage" name = "profitPercentage">

                </td>
                <td>
                    <label for="transportation">Перевозка %</label>
                </td>
                <td>
                <input type="number" name="transportation" id="transportation">
                </td>
                <td>
                    <label for="discuntPercentage">Скидка</label>
                </td>

                <td>
                <input type="number" name="discuntPercentage" id="discuntPercentage">
                </td>
                <td>
                    <label for="exchangeRate">Курс</label>
                </td>
                <td>
                      <input type="number" name="exchangeRate" step="0.01" id="exchangeRate">
                </td>
                <td>
                    <input type="radio" name="VAT" value="withoutVAT" id="VAT">
                    <label for="VAT"> Без НДС </label>
                     <br>
                    <input type="radio" name="VAT" value="withVAT" id="VAT">
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
                    </form:form>
         </div>

<br> <br>

<div class="mainContent">
     <form:form action="/offer/newOffer/saveOffer" method="post"  accept-charset="UTF-8"   enctype="multipart/form-data">

        <table class="mainTables" id="offerTable" style="width: auto" align="center">
        <tr>
        </tr>
        </table>
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
              initialPriceList.push({
             id: id,
             article: article,
             description: description,
             price: price,
             customerName: customerName,
             quantityInStock: quantityInStock,
             profit: profit
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

               if (offerLines.length > 0) {
             setOfferLinesInTable();
                setCustomer();
               }

              function setCustomer() {
         selectedCustomerName=document.getElementById("selectedCustomerName").value;
                document.getElementById("customerNameInUploadForm").value=selectedCustomerName;
    }

          function initPriceListForSelectedCustomer() {
              var customerName = customerNameForTableCreation;
            for (var i = 0; i < initialPriceList.length; i++) {
        if (initialPriceList[i].customerName === customerName) {
            priceListForSelectedCustomer.push(initialPriceList[i]);
                  }
        }
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
        var titleCell17 = titleRow.insertCell(15);

        titleCell1.innerHTML = "N";
        titleCell2.innerHTML = "Название";
        titleCell3.innerHTML = "Артикул";
        titleCell4.innerHTML =  "Количество";
        titleCell5.innerHTML = "Артикул КП";
        titleCell6.innerHTML = "Единица";
        titleCell7.innerHTML = "Цена";
        titleCell8.innerHTML = "Сумма";
        titleCell9.innerHTML = "Цена послед. КП";
        titleCell10.innerHTML = "Дата посл. КП";
        titleCell11.innerHTML = "Налич. Масиса 3";
        titleCell12.innerHTML = "Себест. Масис3";
        titleCell13.innerHTML = "МП от М3";
        titleCell14.innerHTML = "Срок поставки";
        titleCell15.innerHTML = "Цена поставщика";
        titleCell16.innerHTML = "Авиапоставка";
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
        titleCell16.style.fontWeight = 'bold';

  
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

        
        var producerCell = parameterRow.insertCell(15);
        producerCell.innerHTML =  "<input id='ProducerForAllLines' onkeyup = 'setProducer()'  value='KOMATSU' >"
     
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

                cell1.id="position"+(i+1);
                cell2.id="partName"+(i+1);
                cell3.id ="partNumber"+(i+1);
                cell4.id ="quantity"+(i+1);
                cell5.id = "offeredPartNumber" +(i+1);
                cell6.id = "unit"+(i+1);
                cell7.id = "price"+(i+1);
                cell8.id = "sum"+(i+1);
                cell9.id= "lastOfferPrice"+(i+1);
                cell10.id = "lastOfferDate"+(i+1);
                cell11.id = "availability"+(i+1);
                cell12.id = "InStockNetCost"
                cell13.id = "profitFromAvailable"+(i+1);
                cell14.id = "deliveryDate"+(i+1);
                cell15.id = "supplierPrice"+(i+1);
                cell16.id = "avia"+(i+1);
                cell17.id = "producer"+(i+1);

            cell1.innerHTML = (i + 1).toString();
                cell2.innerHTML = offerLines[i].partName;
                cell3.innerHTML = offerLines[i].partNumber;
                cell4.innerHTML = offerLines[i].quantity;
                cell5.innerHTML = offerLines[i].offeredPartNumber;
                cell9.innerHTML = offerLines[i].lastOfferPrice;
                cell10.innerHTML= offerLines[i].lastOfferDate;
                cell11.innerHTML= offerLines[i].availability;
                cell12.innerHTML= offerLines[i].inStockNetCost;
            }
}

function setUnits()  {

 var units = document.getElementById("unitForAllLines").value;
for (var i = 1; i <= offerLines.length; i++) {
    document.getElementById("unit"+i).innerHTML = units;

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
    var discuntPercentage = document.getElementById("discuntPercentage").value;
    var exchangeRate = document.getElementById("exchangeRate").value;

    for (var i = 1; i <= offerLines.length; i++) {

     var  supplierPrice = document.getElementById("supplierPrice"+i).innerHTML;
     var quantity = document.getElementById("quantity"+i).innerHTML;
     var inStockNetCost = document.getElementById("inStockNetCost"+i).innerHTML;
     var price = document.getElementById("price"+i).innerHTML;

   price = supplierPrice/1.2*(100-discuntPercentage)/100*transportation*exchangeRate*profitPercentage;
   document.getElementById("sum"+i).innerHTML = price*quantity

   var profitFromAvailable = (price-inStockNetCost)/price*100 +"%";


    }

}
         </script>

</body>
</html>
