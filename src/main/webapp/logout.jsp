<%

	try{
		Cookie clearcookie = new Cookie("trueuserlogin",null);
		clearcookie.setMaxAge(0);
		response.addCookie(clearcookie);
		session.removeAttribute("stack");
		session.removeAttribute("authen");
		session.invalidate();
		
		//session.removeAttribute("username");
		//session.removeAttribute("status_User");
		//session.setAttribute("msg", "Log out Success");
		response.sendRedirect("login.jsp"); 
	}catch(Exception e){
		
	}




%>>