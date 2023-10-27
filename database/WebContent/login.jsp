<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login to Database</title>
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
            max-width: 400px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
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
           	padding:-5px;
            margin: 5px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        input[type="submit"] {
            background-color: #007BFF;
            color: #fff;
            border: none;
            cursor: pointer;
            padding:5px;
        }

        a {
            text-decoration: bold;
            font-size:20	px;
            color: #007BFF;
        }
    </style>
</head>
<body>
 <center>	<h1> Welcome to Login page </h1> </center>
	<div align="center">
		<p> ${loginFailedStr} </p>
		<form action="login" method="post">
			<table border="1" cellpadding="5">
				<tr>
					<th>Username: </th>
					<td>
						<input type="text" name="email" size="45" autofocus>
					</td>
				</tr>
				<tr>
					<th>Password: </th>
					<td> 
						<input type="password" name="password" size="45">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="Login"/>
					</td>
				</tr>
			</table>
			<a href="register.jsp" target="_self">Register Here</a>
		</form>
	</div>
</body>
</html>