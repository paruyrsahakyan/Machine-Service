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
                    <label for="currency">Курс</label>
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
        <table class="mainTables" id="dynamicTable" style="width: auto" align="center">
        <tr>
        <td>N</td>
        <td>Артиикул</td>
        <td>Название</td>
        <td>Цена без НДС</td>
        </tr>
        </table>
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
            profit: profit,
            });
        </c:forEach>

        <c:forEach items="${customerList}" var="customer">
            var id = "${customer.id}";
            var customerName = "${customer.name}";
            customerList.push({
            id: id,
            customerName: customerName
            });
       </c:forEach>

                 <c:forEach items="${offer.offerLines}" var="offerLine">
                   var partName = "${offerLine.requestedPartName}";
                    var partNumber = "${offerLine.requestedPartNumber}";
                    var quantity = "${offerLine.quantity}";
                     var price = "${offerLine.price}";
                     var sum ="${offerLine.sum}";

                   offerLines.push({
                    partName: partName,
                   partNumber: partNumber,
                   quantity: quantity,
                   price:price,
                   sum:sum
                                       });
               </c:forEach>


               if (offerLines.length > 0) {

                // setOfferLinesInTable();
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

        var table = document.getElementById("dynamicTable");
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


        titleCell1.innerHTML = "N";
        titleCell2.innerHTML = "Название";
        titleCell3.innerHTML = "Артикул";
        titleCell4.innerHTML =  "Количество";
        titleCell5.innerHTML = "Артикул Предложения";
        titleCell6.innerHTML = "Единица";
        titleCell7.innerHTML = "Цена";
        titleCell8.innerHTML = "Сумма";
        titleCell9.innerHTML = "Срок поставки";
        titleCell10.innerHTML = "Производитель";
        titleCell11.innerHTML = "Наличие Масиса 3";
        titleCell12.innerHTML = "Наличие в других складах";
        titleCell13.innerHTML = "МП от М3";
        titleCell14.innerHTML = "Цена последнего КП";
        titleCell15.innerHTML = "Дата последнего КП";

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

                cell1.id="N"+(i+1);
                cell2.id="partName"+(i+1);
                cell3.id ="partNumber"+(i+1);
                cell4.id ="quantity"+(i+1);
                cell5.id = "offerPartNumber" +(i+1);
                cell6.id = "unit"+(i+1);
                cell7.id = "price"+(i+1);
                cell8.id = "sum"+(i+1);
                cell9.id= "deliveryDate"+(i+1);
                cell10.id = "manufacturer"+(i+1);
                cell11.id = "availability"+(i+1);
                cell12.id = "otherAvailability"+(i+1);
                cell13.id = "profitMasis"+(i+1);
                cell14.id= "lastOfferPrice"+(i+1);
                cell15.id = "lastOfferDate"+(i+1);

            cell1.innerHTML = (i + 1).toString();
                cell2.innerHTML = offerLines[i].partName;
                cell3.innerHTML = offerLines[i].partNumber;
                cell4.innerHTML = offerLines[i].quantity;
            }


}
         </script>


</body>
</html>
