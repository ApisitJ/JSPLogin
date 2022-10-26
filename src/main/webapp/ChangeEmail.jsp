<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.login.database.LoginDatabase"%>	
<%@page import="com.login.verify.CheckStatusUser" %>
<%@page import="com.login.bean.Format" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Change Email</title>
</head>
<body>
		<%
		LoginDatabase logindatabase = new LoginDatabase();
		Cookie[]cookie = request.getCookies();
		String status = (String)request.getSession().getAttribute("status_User");
		CheckStatusUser.setLogout(response);
			if(!status.equals(Format.ADMIN)){
				String redirect = CheckStatusUser.checkGroupAdmin(status,cookie);
				response.sendRedirect(redirect);
			}

		%>
	<div align="center">
		<h1>Change Email </h1>
		<form action="ChangeEmail" method="post">
			<h3 style="color: red">${message}</h3>
			<table>
				<tr>
					<td>User Name:</td>
					<td><input type="text" name="username"></td>
				</tr>
				<tr>
					<td>Current Email:</td>
					<td><input type="text" name="currentEmail"></td>
				</tr>
				<tr>
				<td>New Email:</td>
					<td><input type="text" name="newEmail"></td>
				</tr>				
				<tr>
				<td>Confirm Email:</td>
					<td><input type="text" name="confirmEmail"></td>
				</tr>					
				<tr>
					<td></td>
					<td><input type="submit" value="Submit"></td>
					<td><input type="button" value="Back" onclick="history.back()"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>