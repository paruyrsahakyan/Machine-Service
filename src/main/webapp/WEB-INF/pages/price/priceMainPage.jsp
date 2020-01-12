
        <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <html>
        <head>
        <title>Прайс Лист</title>
        <style>
        <%@include file="/WEB-INF/pages/CSS/topNavigation.css" %>
        </style>
        <style>
        <%@include file="/WEB-INF/pages/CSS/tables.css" %>
        </style>
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
        </div>
        <br>
        <br>

        <div class="mainContent">

        <form:form id="toCreateNewItem" action="/price/createdNewPrice" method="post" accept-charset="UTF-8">
            <B>Клиент </B>
            <input list="customers" name="customerName" id="selectedCustomerName"  value="${selectedCustomer.name}"
                   onchange="createTableOnCustomerSelection()">

            <datalist id="customers">
                <c:forEach items="${customerList}" var="customer">
                    <option value="${customer.name}" hidden> ${customer.name} </option>
                </c:forEach>
            </datalist>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <B>Артикул </B>
            <input type="text" id="articleSearch" onkeyup="updateTableByArticle()">
            &nbsp;&nbsp;&nbsp;&nbsp;
            <button type="button" onclick="showHiddenForm()"> Добавить позицию</button>
            <br><br>
            <div id="hiddenForm" style="display: none;">
                <input id="newArticle" type="text" name="article" placeholder="Вводите  Артикул" onkeyup="checkTheArticle()"
                       required>
                <input type="text" name="description" placeholder="Вводите Название" required>
                <input type="nuber" name="price" placeholder="Вводите Цену" required>
                <input type="submit" value="сохранить">
            </div>
        </form:form>
        </div>


        <div  class="mainContent" id="hiddenFormForItemEdit" style="display: none;">
        <form:form action="/price/ItemEdited" method="post" accept-charset="UTF-8">
            <input id="articleToEdit" type="text" name="articleToEdit" required>
            <input id="descriptionToEdit" type="text" name="description" required>
            <input id="priceToEdit" type="nuber" name="price" required>
            <input type="submit" value="Записать Изменение">
        </form:form>
        </div>
        <br>
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
        var priceListForTableCreation = [];
        var customerNameAfterDeleteOrEdit="${selectedCustomer.name}";
        var customerNameForTableCreation;

         <c:forEach items="${priceList}" var="priceForCustomer">
            var id ="${priceForCustomer.id}"
            var article = "${priceForCustomer.article}";
            var description = "${priceForCustomer.description}";
            var price = "${priceForCustomer.price}";
            var customerId = "${priceForCustomer.customerId}";
            var customerName = "${priceForCustomer.customerName}";
            initialPriceList.push({
            id: id,
            article: article,
            description: description,
            price: price,
            customerId: customerId,
            customerName: customerName
            });
        </c:forEach>

        <c:forEach items="${customerList}" var="customer">
            var id = "${customer.id}"
            var customerName = "${customer.name}";
            customerList.push({
            id: id,
            customerName: customerName
            });
        </c:forEach>

        if(customerNameAfterDeleteOrEdit!==""){
            createTableAfterDeletingOrEditing();
        }

        function initPriceListForCustomer() {
            priceListForSelectedCustomer=[];
            var customerName = customerNameForTableCreation;
            for (var i = 0; i < initialPriceList.length; i++) {
        if (initialPriceList[i].customerName === customerName) {
            priceListForSelectedCustomer.push(initialPriceList[i]);
            priceListForTableCreation=priceListForSelectedCustomer;
        }
        }
                }

        function createTable() {

        var table = document.getElementById("dynamicTable");
        table.innerText = "";
        var titleRow = table.insertRow();
        var titleCell1 = titleRow.insertCell(0);
        var titleCell2 = titleRow.insertCell(1);
        var titleCell3 = titleRow.insertCell(2);
        var titleCell4 = titleRow.insertCell(3);
        var titleCell5 = titleRow.insertCell(4);
        var titleCell6 = titleRow.insertCell(5);
        titleCell1.innerHTML = "N";
        titleCell1.style.fontWeight = 'bold';
        titleCell2.innerHTML = "Артикул";
        titleCell2.style.fontWeight = 'bold';
        titleCell3.innerHTML = "Название";
        titleCell3.style.fontWeight = 'bold';
        titleCell4.innerHTML = "Цена Без НДС";
        titleCell4.style.fontWeight = 'bold';
        titleCell5.innerHTML = "Удаление";
        titleCell5.style.fontWeight = 'bold';
        titleCell6.innerHTML = "Редактирование";
        titleCell6.style.fontWeight = 'bold';

                for (var i = 0; i < priceListForTableCreation.length; i++) {
                var row = table.insertRow();
                var cell1 = row.insertCell(0);
                var cell2 = row.insertCell(1);
                var cell3 = row.insertCell(2);
                var cell4 = row.insertCell(3);
                var cell5 = row.insertCell(4);
                var cell6 = row.insertCell(5);

                cell1.innerHTML = (i + 1).toString();
                cell2.innerHTML = priceListForTableCreation[i].article;
                cell3.innerHTML = priceListForTableCreation[i].description;
                cell4.innerHTML = priceListForTableCreation[i].price;
                var buttonDeleteInnerHTML= '<form action="/price/itemDeleted" method="get">'+
                    '<input type="hidden" name="id" value="'+priceListForTableCreation[i].id+'">'+
                    '<input type="submit" value="Удалить"'+
                    'onclick="return confirm('+"'"+'!!!Вы уверены что хатите удалить Позицию!!!'+
                    "'"+
                    ')">'+
                    '</form>';
                cell5.innerHTML =  buttonDeleteInnerHTML;
                var buttonEdit= '<input type="button" value="редактировать" onclick="showHiddenFormForItemEdit('+i+')">';
                cell6.innerHTML = buttonEdit;
                }
                }
                function showHiddenForm() {
                document.getElementById("hiddenForm").style.display = "block";
                }

                function showHiddenFormForItemEdit(i) {
            
                document.getElementById("hiddenFormForItemEdit").style.display = "block";
                document.getElementById("articleToEdit").value=priceListForTableCreation[i].article;
                document.getElementById("descriptionToEdit").value=priceListForTableCreation[i].description;
                document.getElementById("priceToEdit").value=priceListForTableCreation[i].price;
                // body...
                }

                function updateTableByArticle() {
                var searchInput = document.getElementById("articleSearch").value;
                if (searchInput ==="") {
                createTableOnCustomerSelection();
                }
                else {
                priceListFilteredByArticle = [];
                for (var i = 0; i <priceListForSelectedCustomer.length; i++) {
                      if (priceListForSelectedCustomer[i].article.toLowerCase().indexOf(searchInput) >= 0) {
                priceListFilteredByArticle.push(priceListForSelectedCustomer[i])
                }
                }
                priceListForTableCreation=priceListFilteredByArticle;
                }
                createTable();
                }

                function createTableOnCustomerSelection() {
                customerNameForTableCreation=document.getElementById("selectedCustomerName").value;
                initPriceListForCustomer();
                priceListForSelectedCustomer=priceListForTableCreation;
                createTable();
                }

                function checkTheArticle() {
                var newArticle = document.getElementById("newArticle").value;
                for (var i = 0; i < priceListForSelectedCustomer.length; i++) {
                if (newArticle==priceListForSelectedCustomer[i].article) {
                alert("Aртикул уже внесен");
                }
                }
                }
                function createTableAfterDeletingOrEditing(){
               customerNameForTableCreation=customerNameAfterDeleteOrEdit;
               initPriceListForCustomer();
               createTable();
                   }

                </script>
                </body>

                </html>
