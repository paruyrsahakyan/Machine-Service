<%--
  Created by IntelliJ IDEA.
  User: IKO
  Date: 1/5/2020
  Time: 7:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Прайс Лист</title>
          <style><%@include file="/WEB-INF/pages/CSS/topNavigation.css"%></style>
        <style><%@include file="/WEB-INF/pages/CSS/tables.css"%></style>
</head>
   <body>
   <body>
   <div class="topnav">
       <a href="/"> Главное меню</a>
       <a href="/allCustomers"> Компании </a>
       <a  href="/allMachines"> Mашины </a>
       <a href="/analysis/worker/jobs"> Анализ работ</a>
       <a href="/workOrder/home"> Наряды </a>
       <a href="/admin/workers"> Работники </a>
       <a href="/admin/allServiceMachines"> Сервисные Mашины</a>
       <a href="/machineType/allMachineTypes"> Типы Машин</a>
       <a href="/wareHouse"> Склад </a>
   </div>

   <div class="mainContent">
       <form:form action="/priceForCustomer" method="post" accept-charset="UTF-8">
           <br>
           <br>
           <B>Клиент </B>
           <input list="customers" name="customer" id="selectedCustomer" onchange="tableCreate()" >
           <datalist id="customers" >
               <c:forEach items="${customerList}" var="customer">
                   <option value="${customer.name}" hidden> ${customer.name} </option>
               </c:forEach>
           </datalist>
           &nbsp;&nbsp;&nbsp;&nbsp;
           <B>Артикул </B>
           <input list="article" name="article" id="articleSearch" onchange="tableCreate()" >
           </select>
           &nbsp;&nbsp;&nbsp;&nbsp;
           <button type="button" onclick="showHiddenForm()"> Добавить </button>
           <br><br>
           <div id="hiddenForm" style="display: none;">
               <input name="article" type="text" placeholder="Введите  Артикул">
               <input type="text" name="description" placeholder="Введите Название">
               <input type="nuber" name="price" placeholder="Введите Цену">
               <input type="submit" value="сохранить">
               <input id="customerId" name="newCustomerId" type="hidden">
           </div>
           <br>

       </form:form>

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
       var priceListForSelectedCustomer = [];

       //  <c:forEach items="${priceList}" var="priceForCustomer">
       // var article = "${priceForCustomer.article}";
       // var description = "${priceForCustomer.description}";
       // var price = "${priceForCustomer.price}";
       // initialPriceList.push({article: article,
       // description:description,
       //  price:price});
       //  </c:forEach>

       function initPriceListForSelectedCustomer() {
           var selectedCustomer = document.getElementById("selectedCustomer").value;
           priceListOfSelectedCustomer=[];
           for (var i = 0; i < initialPriceList.length; i++) {
               if (initialMachineList[i].customerName === selectedCustomer) {
                   priceListOfSelectedCustomer.push(initialPriceList[i])
               }
           }
       }

       function tableCreate(customerList) {

           document.getElementById("customerId").value=selectedCustomer.id;

           initPriceListForSelectedCustomer
           var table = document.getElementById("dynamicTable");
           table.innerText = "";
           var titleRow = table.insertRow();
           var titleCell1 = titleRow.insertCell(0);
           var titleCell2 = titleRow.insertCell(1);
           var titleCell3 = titleRow.insertCell(2);
           var titleCell3 = titleRow.insertCell(3);
           titleCell1.innerHTML = "N";
           titleCell1.innerHTML = "Артикул";
           titleCell1.style.fontWeight = 'bold';
           titleCell2.innerHTML = "Название";
           titleCell2.style.fontWeight = 'bold';
           titleCell3.innerHTML = "Цена Без НДС";
           titleCell3.style.fontWeight = 'bold';

           for (var i = 0; i < priceListForSelectedCustomer.length; i++) {
               var row   = table.insertRow();
               var cell1 = row.insertCell(0);
               var cell2 = row.insertCell(1);
               var cell3 = row.insertCell(2);
               var cell4 = row.insertCell(3);
               cell1.innerHTML = (i + 1).toString();
               cell2.innerHTML = priceListForSelectedCustomer[i].article;
               cell3.innerHTML = priceListForSelectedCustomer[i].description;
               cell4.innerHTML = priceListForSelectedCustomer[i].price;
               // cell2.addEventListener("click", copyToTextArea);

           }

       }

       function showHiddenForm() {
           document.getElementById("hiddenForm").style.display = "block";
           // body...
       }
   </script>
   </body>

</html>
