<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Please Login</title>
</head>
<body>
	<div align="center">
		<h1>User Login </h1>
			<form action=tester method="post">
						<h3 style="color: red">${message}
						</h3>
			<table>
				<tr>
					<td>UserName:</td>
					<td><input type="text" name="username"></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password"></td>
				</tr>

				<tr>
					<td></td>
					<td><input type="submit" value="Login"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>