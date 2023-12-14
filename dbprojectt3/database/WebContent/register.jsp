<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head><title>Registration</title>
<style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            background-color: #f4f4f4;
        }

        form {
            max-width: 400px;
            margin: 0 auto;
        }

        table {
            border-collapse: collapse;
            width: 100%;
            margin: 0 auto;
        }

        th, td {
            padding: 25px;
            text-align: left;
        }

        input[type="text"], input[type="password"] {
            width: 100%;
            margin: 5px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        input[type="submit"] {
            width: 100%;
            padding:5px;
            background-color: #007BFF;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        a {
            text-decoration: none;
            color: #007BFF;
        }

        /* Add more CSS styles as needed */
    </style>
    </head>
<body>
	<div align="center">
		<p> ${errorOne } </p>
		<p> ${errorTwo } </p>
		<form action="register">
			<table border="1" cellpadding="5">
				<tr>
					<th>Username: </th>
					<td align="center" colspan="3">
						<input type="text" name="email" size="45"  value="example@gmail.com" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>First Name: </th>
					<td align="center" colspan="3">
						<input type="text" name="firstName" size="45" value="FirstName" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Last Name: </th>
					<td align="center" colspan="3">
						<input type="text" name="lastName" size="45" value="LastName" onfocus="this.value=''">
					</td>
				</tr>

				<tr>
					<th>Password: </th>
					<td align="center" colspan="3"> 
						<input type="password" name="password" size="45" value="password" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Password Confirmation: </th>
					<td align="center" colspan="3">
						<input type="password" name="confirmation" size="45" value="password" onfocus="this.value=''">
					</td>
				
				</tr>
				<tr>
					<th>Phone Num: </th>
					<td align="center" colspan="3">
						<input type="text" name="phnum" size="45" value="phnum" onfocus="this.value=''">
					</td>
				
				</tr>
				<tr>
					<th>Card Num: </th>
					<td align="center" colspan="3">
						<input type="text" name="card_num" size="45" value="card_num" onfocus="this.value=''">
					</td>
				
				</tr>
				<tr>
					<th>Role: </th>
					<td align="center" colspan="3">
						<input type="text" name="role" size="45" value="role" onfocus="this.value=''">
					</td>
				
				</tr>
				<tr>
					<td align="center" colspan="5">
						<input type="submit" value="Register"/>
					</td>
				</tr>
			</table>
			<a href="login.jsp" target="_self">Return to Login Page</a>
		</form>
	</div>
</body>