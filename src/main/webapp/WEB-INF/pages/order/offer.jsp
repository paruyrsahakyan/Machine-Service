<%--
  Created by IntelliJ IDEA.
  User: Paruyr
  Date: 1/9/2019
  Time: 11:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <style><%@include file="/WEB-INF/pages/CSS/topNavigation.css"%></style>
    <style><%@include file="/WEB-INF/pages/CSS/tables.css"%></style>
    </head>
<body>
<div class="topnav">
    <a href="/"> Главное меню</a>
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
    <h2>Коммерческие Предложения</h2>

  <a href="/machineType/newTypeCreation"> Создать Новое КП</a>
    &nbsp; &nbsp;
 <h2> ${OfferMode} </h2>
 </div>

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
  
          var AllOffers = [];
          var FilteredOffers[];
          var customerList = [];

  <c:forEach items="${currentOffers}" var="offer">
                var id ="${offer.id}"
                var customer = "${offer.customer}";
                var offerDate = "${offer.date}";
                var validationDate = "${offer.validationDate}";
                var sum = "${offer.sum}";
                AllOffers.push({
                id: id,
                customer: customer,
                offerDate: offerDate,
                validationDate: validationDate,
                 sum: sum,
                  });
            </c:forEach>
   <c:forEach items="${allCustomers}" var="customer">
              var id = "${customer.id}"
              var customerName = "${customer.name}";
              customerList.push({
              id: id,
              customerName: customerName
              });
          </c:forEach>

createTable();


         function createTable() {

                var table = document.getElementById("dynamicTable");
                table.innerText = "";
                var titleRow = table.insertRo/*w();
                var titleCell1 = titleRow.insertCell(0);
                var titleCell2 = titleRow.insertCell(1);
                var titleCell3 = titleRow.insertCell(2);
                var titleCell4 = titleRow.insertCell(3);
                var titleCell5 = titleRow.insertCell(4);


                titleCell1.innerHTML = "N";
                titleCell1.style.fontWeight = 'bold';
                titleCell2.innerHTML = "Клиент";
                titleCell2.style.fontWeight = 'bold';
                titleCell3.innerHTML = "Номер заявки";
                titleCell3.style.fontWeight = 'bold';
                titleCell4.innerHTML = "Дата КП";
                titleCell4.style.fontWeight = 'bold';
                titleCell5.innerHTML = "Сумма КП";
                titleCell5.style.fontWeight = 'bold';

                        for (var i = 0; i < filteredOffers.length; i++) {
                        var row = table.insertRow();
                        var cell1 = row.insertCell(0);
                        var cell2 = row.insertCell(1);
                        var cell3 = row.insertCell(2);
                        var cell4 = row.insertCell(3);
                        var cell5 = row.insertCell(4);

var offerSelectionRef='<a href="/offer/'+FilteredOffers[i].id+
                              '">'+(i+1).toString() +  '</a>';
                        cell1.innerHTML = offerSelectionRef;
                       cell2.innerHTML =  FilteredOffers[i].customer;
                        cell3.innerHTML = FilteredOffers[i].offerDate;
                        cell4.innerHTML = FilteredOffers[i].validationDate;
                        cell5.innerHTML = FilteredOffers[i].sum;


                        }
                        }




