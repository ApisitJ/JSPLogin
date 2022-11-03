
<%@page import="com.login.database.LoginDatabase"%>
<%@page import="com.login.bean.LoginBean" %>
<%@page import="com.login.verify.CheckStatusUser" %>
<%@page import="com.login.bean.Format" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<%
		
		LoginDatabase logindatabase = new LoginDatabase();
		Cookie[]cookie = request.getCookies();
		String status = (String)request.getSession().getAttribute(Format.STATUSUSER);
		CheckStatusUser.setLogout(response);
			if(!status.equals(Format.ADMIN)){
				String redirect = CheckStatusUser.checkGroupAdmin(status,cookie);
				response.sendRedirect(redirect);
			}
			
			out.print("<h1>Login Success Welcome" + status + "</h1>"+"<br>");
			out.print("<a href='register.jsp'>register</a>"+"</br>" );
			out.print("<a href='changePassword.jsp'>Change Password</a>"+"</br>" );
			out.print("<a href='changeEmail.jsp'>Change Email</a>"+"</br>" );
			out.print("<a href='logout.jsp'>logout</a>"+"</br>" );
		%>
		<%--
		if(status.equals("admin")){
				out.print("Success"+"<br>");
				out.print("<h1>Login Success Admin</h1>"+"<br>");
				out.print("<a href='logout.jsp'>logout</a>"+"</br>" );
				out.print("<a href='changePassword.jsp'>Change Password</a>"+"</br>" );
				out.print("<a href='changeEmail.jsp'>Change Email</a>"+"</br>" );
			}	
		
		 --%>
	
		<h1>Test Admin index Success</h1><br>
		
		<%
		
		out.print("<h1> " + status + "</h1>"+"<br>");
		%>
			
	

	</div>
</body>
</html>