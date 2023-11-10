<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head><title>David Smith login</title>
<style>
body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    text-align: center;
}

h2 {
    color: #007BFF;
}

.container {
    max-width: 400px;
    margin: 0 auto;
    padding: 20px;
    background-color: #fff;
    border-radius: 5px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
}
button {
    padding: 10px 20px;
    background-color: #007BFF;
    color: #fff;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    margin: 5px 0;
}

button:hover {
    background-color: #0056b3;
}

table {
    width: 50%;
    border-collapse: collapse;
}

th, td {
    text-align: left;
}

input[type="text"],
input[type="password"],
input[type="submit"] {
    width: 100%;
    padding: 8px;
    margin: 5px 0;
    border: 1px solid #ccc;
    border-radius: 4px;
}

input[type="submit"] {
    background-color: #007B

</style>
    </head>
<body>
	<h2>Welcome David Smith, Here you can list all the requests from the users and send quote.</h2>
	<center>
		 <a href="login.jsp"target ="_self" > logout</a><br><br> 
		 
		 </center>
	<form action="listReq" method="get">
	<button>Go to list requests</button>
	</form>
	<form action="listOrder" method="get">
	<button>Go to list Orders</button>
	</form>
	
		 
</body>