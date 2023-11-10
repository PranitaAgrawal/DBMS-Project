<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List Requests</title>
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
Hello! this is request
<div align = "center">
	<center>
		 <a href="david.jsp"target ="_self" > Go to David's Home page</a><br><br> 
		 
		 </center>
	


    <div align="center">
    <form action="quoteCreation" method="post" id="updateForm">
        <table border="1" cellpadding="6" id="attable">
            <caption><h1>List of Requests</h1></caption>
            <tr>
                <th>Client_id</th>
                <th>Req_id</th>
                <th>Status</th>
                <th>Price</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Davids's Note</th>
                <th>Client Note</th>
                <th>Size</th>
                <th>Height</th>
                <th>location</th>
                <th>Distance_from_house</th>
                

            </tr>
             <c:forEach items="${listReqs}" var="requs" >
            <tr style="text-align:center">
            
           
                
                    <td><c:out value="${requs.client_id}" /></td>
                    <td><c:out value="${requs.req_id}" /><input type="hidden" name="req_id" value="${requs.req_id}" /></td>
                    <td><c:out value="${requs.status}" /></td>
                    <td><input type="text" name="price" value="${requs.price}" onfocus="this.value=''"></td>
                    <td><input type="text" name="startdate" value="${requs.startdate}" onfocus="this.value=''"></td>
                    <td><input type="text" name="enddate" value="${requs.enddate}" onfocus="this.value=''"></td>
                    <td><input type="text" name="dnote" value="${requs.dnote}" onfocus="this.value=''"></td>
                    <td><c:out value="${requs.note}" /></td>
                    <td><c:out value="${requs.size}" /></td>
                    <td><c:out value="${requs.height}" /></td>
                    <td><c:out value="${requs.location}" /></td>
                    <td><c:out value="${requs.distance}" /></td>
                    
                   
            
            </tr>
            </c:forEach>
        </table>
        
	<button type="submit">Update</button>
	
	</form>
	</div>
	</div>

</body>
</html>