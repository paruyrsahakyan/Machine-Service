
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Новая Машина</title>
    <style>

        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: auto;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body >
<div style="text-align: center">
    <h2>Замена Владельца Машины</h2> <br>

    <p id="selected"> xxx </p>
    <form:form action="/customer/machine/${machineId}/updatedCustoemr">
        <input type="text" size="20" name="фильтр списка" onchange="refreshList()">
        <input type="button" onclick="testToDisplayInitList()" >
    </form:form>
</div>
<script>
    function refreshList() {

    }
    var initialCustomerList =[];
    var refreshedList=[];
    var iterator =0;

    function showList() {
    }
        function tableCreate(){
            var body = document.body,
                tbl  = document.createElement('table');
            var customers = ${customerList};
            tbl.style.width  = '100px';
            tbl.style.border = '1px solid black';

            for(var i = 0; i < 3; i++){
                var tr = tbl.insertRow();
                for(var j = 0; j < 2; j++){
                    if(i == 2 && j == 1){

                        break;
                    } else {
                        var td = tr.insertCell();
                        td.appendChild(document.createTextNode(customers[i].name));
                        td.style.border = '1px solid black';
                        if(i == 1 && j == 1){
                            td.setAttribute('rowSpan', '2');
                        }
                    }
                }
            }
            body.appendChild(tbl);
         }
       function  testToDisplayInitList(list) {
        var i = 1;
        document.getElementById("selected").innerHTML="${customerList.get(1)};
       }
</script>


</body>
</html>

