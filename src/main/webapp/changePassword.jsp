<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.login.database.LoginDatabase"%>	
<%@page import="com.login.verify.CheckStatusUser" %>
<%@page import="com.login.bean.Format" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Change Password</title>
</head>
<body>
		<%
		LoginDatabase logindatabase = new LoginDatabase();
		Cookie[]cookie = request.getCookies();
		String status = (String)request.getSession().getAttribute(Format.STATUSUSER);
		CheckStatusUser.setLogout(response);
		if(CheckStatusUser.checkStatus(status, cookie)){
			String redirect = Format.LOGOUT;
			response.sendRedirect(redirect);
		}


		%>
	<div align="center">
		<h1>Change Password </h1>
		<form action="ChangePassword" method="post">
			<h3 style="color: red">${message}</h3>
			<table>
				<tr>
					<td>Current Password:</td>
					<td><input type="password" name="oldPassword"></td>
				</tr>
				<tr>
				<td>New Password:</td>
					<td><input type="password" name="newPassword"></td>
				</tr>				
				<tr>
				<td>Confirm Password:</td>
					<td><input type="password" name="confirmPassword"></td>
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