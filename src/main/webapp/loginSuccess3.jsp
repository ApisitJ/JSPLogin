<%@page import="com.login.database.LoginDatabase"%>
<%@page import="com.login.bean.LoginBean" %>
<%@page import="com.login.verify.CheckStatusUser" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Test User</title>
</head>
<body>
	<div align="center">
		<%
		LoginDatabase logindatabase = new LoginDatabase();
		Cookie[]cookie = request.getCookies();
		String status = (String)request.getSession().getAttribute("status_User");
		CheckStatusUser.setLogout(response);
		try{
			if(!logindatabase.validateCookie(cookie) || status == null){
				throw new Exception("Exception message");
			}

			out.print("Success"+"<br>");
			out.print("<h1>Login Success " + status + "</h1>"+"<br>");
			out.print("<h1>Now you Status Readonly please contact for upgrade your more for status</h1>"+"<br>");
			out.print("<a href='changePassword.jsp'>Change Password</a>"+"</br>" );
			out.print("<a href='logout.jsp'>logout</a>"+"</br>" );
					
			if(status.equals("admin")){
				out.print("Success"+"<br>");
				out.print("<h1>You'r admin plaese enter to Admin page</h1>"+"<br>");
				out.print("<a href='loginSuccess.jsp'>to Admin</a>"+"</br>" );
			}			
	
		} catch(Exception e){
			String redirect = "logout.jsp";
			response.sendRedirect(redirect);
		};
					

		%>
	
	
	

	</div>
</body>
</html>