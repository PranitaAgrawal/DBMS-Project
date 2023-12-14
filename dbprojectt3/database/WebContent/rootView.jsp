<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Root page</title>
<style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
        }

        h1 {
            color: #007BFF;
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 5px;
            text-align: center;
        }

        th {
            background-color: #007BFF;
            color: #fff;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        a {
            text-decoration: none;
            color: #007BFF;
        }
    </style>
</head>
<body>

<div align = "center">
	
	<form action = "initialize">
		<input type = "submit" value = "Initialize the Database"/>
	</form>
	<a href="login.jsp"target ="_self" > logout</a><br><br> 


    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h1>List of Users</h1></caption>
            <tr>
                <th>Email</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Adress</th>
                <th>Password</th>
                <th>Phnum</th>

            </tr>
            <c:forEach var="users" items="${listUser}">
                <tr style="text-align:center">
                    <td><c:out value="${users.email}" /></td>
                    <td><c:out value="${users.firstName}" /></td>
                    <td><c:out value="${users.lastName}" /></td>
                    <td><c:out value= "${users.adress_street_num} ${users.adress_street} ${users.adress_city} ${users.adress_state} ${users.adress_zip_code}" /></td>
                    <td><c:out value="${users.password}" /></td>
                    <td><c:out value="${users.phnum}" /></td>
                    
            </c:forEach>
        </table>
	</div>
	</div>
	<table border="1" cellpadding="6">
            <caption><h1>Big Clients</h1></caption>
            <tr>
                <th>Big Clients</th>
                

            </tr>
            
                    <td>${query1}</td>
                    
                    
            
        </table>
        <table border="1" cellpadding="6">
        <caption><h1>Easy Clients</h1></caption>
        
            <tr>
                <th>Names</th>
                
                

            </tr>
            <c:forEach var="query2" items="${query2}">
                <tr style="text-align:center">
                    
                    <td><c:out value="${query2}" /></td>
             </tr>       
            </c:forEach>
            
        </table>
        <table border="1" cellpadding="6" id="attable">
            <caption><h1>One Tree Agreed Quotes</h1></caption>
            <tr>
                <th>Client_id</th>
                <th>Req_id</th>
                <th>Client Note</th>
                <th>Size</th>
                <th>Height</th>
                <th>location</th>
                <th>Distance_from_house</th>
	</tr>
             <c:forEach items="${query3}" var="requs" >
            <tr style="text-align:center">
            
           
                
                    <td><c:out value="${requs.client_id}" /></td>
                    <td><c:out value="${requs.req_id}" /></td>
                    <td><c:out value="${requs.note}" /></td>
                    <td><c:out value="${requs.size}" /></td>
                    <td><c:out value="${requs.height}" /></td>
                    <td><c:out value="${requs.location}" /></td>
                    <td><c:out value="${requs.distance}" /></td>
                    
                   
            
            </tr>
            </c:forEach>
        </table>
        <table border="1" cellpadding="6">
        <caption><h1>Prospective Clients</h1></caption>
        
            <tr>
                <th>Names</th>
                
                

            </tr>
            <c:forEach var="query4" items="${query4}">
                <tr style="text-align:center">
                    
                    <td><c:out value="${query4}" /></td>
             </tr>       
            </c:forEach>
            
        </table>
        
        <table border="1" cellpadding="6" id="attable">
            <caption><h1>Highest Tree</h1></caption>
            <tr>
                <th>Client_id</th>
                <th>Req_id</th>
                <th>Client Note</th>
                <th>Size</th>
                <th>Height</th>
                <th>location</th>
                <th>Distance_from_house</th>
	</tr>
             <c:forEach items="${query5}" var="requs" >
            <tr style="text-align:center">
            
           
                
                    <td><c:out value="${requs.client_id}" /></td>
                    <td><c:out value="${requs.req_id}" /></td>
                    <td><c:out value="${requs.note}" /></td>
                    <td><c:out value="${requs.size}" /></td>
                    <td><c:out value="${requs.height}" /></td>
                    <td><c:out value="${requs.location}" /></td>
                    <td><c:out value="${requs.distance}" /></td>
                    
                   
            
            </tr>
            </c:forEach>
        </table>
        <caption><h1>Overdue Bills</h1></caption>
        <table border="1" cellpadding="6" id="attable">
            <tr>
            	<th>Bill_id</th>
                <th>Order_id</th>
                <th>Client_id</th>
                <th>Status</th>
                
                <th>Amount</th>
                <th>Note</th>
                <th>Duedate</th>
                <th>generateddate</th>

            </tr>
            
             <c:forEach items="${query6}" var="bills" >
             
            <tr style="text-align:center">
           
           
                
                    <td><c:out value="${bills.bill_id}" /></td>
                    <td><c:out value="${bills.order_id}" /></td>
                    <td><c:out value="${bills.client_id}" /></td>
                    <td><c:out value="${bills.status}" /></td>
                    <td><c:out value="${bills.amount}" /></td>
                    <td><c:out value="${bills.note}" /></td>
                    <td><c:out value="${bills.duedate}" /></td>
                    <td><c:out value="${bills.generateddate}" /></td>
            </tr>
            </c:forEach>
        </table>
        <table border="1" cellpadding="6">
        <caption><h1>Bad Clients</h1></caption>
        
            <tr>
                <th>Names</th>
                
                

            </tr>
            <c:forEach var="query7" items="${query7}">
                <tr style="text-align:center">
                    
                    <td><c:out value="${query7}" /></td>
             </tr>       
            </c:forEach>
            
        </table>
        <table border="1" cellpadding="6">
        <caption><h1>Good Clients</h1></caption>
        
            <tr>
                <th>Names</th>
                
                

            </tr>
            <c:forEach var="query8" items="${query8}">
                <tr style="text-align:center">
                    
                    <td><c:out value="${query8}" /></td>
             </tr>       
            </c:forEach>
            
        </table>
        <caption><h1>Statistics</h1></caption>
        <table border="1" cellpadding="6" id="attable">
            <tr>
            	<th>Client_id</th>
                <th>Amount</th>
                <th>Dueamount</th>
                <th>TreeCount</th>
                
                

            </tr>
            
             <c:forEach items="${query9}" var="bills" >
             
            <tr style="text-align:center">
           
           
                
                    
                    <td><c:out value="${bills.client_id}" /></td>
                    <td><c:out value="${bills.amount}" /></td>
                    <td><c:out value="${bills.dueamount}" /></td>
                    <td><c:out value="${bills.tree}" /></td>
                    
            </tr>
            </c:forEach>
        </table>
	

</body>
</html>