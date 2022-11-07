package com.login.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.login.bean.Format;
import com.login.bean.LoginBean;
import com.login.database.LoginDatabase;
import com.login.database.SendEmail;

@WebServlet("/SendMailForgot")
public class SendMailForgot extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	LoginDatabase loginDatabase = new LoginDatabase();
	SendEmail sm = new SendEmail();
	SendEmail sendemail = new SendEmail();
       
    public SendMailForgot() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String email = request.getParameter("email");
		
		if(email.isEmpty()) {
			request.setAttribute("message", "ERROR Please Enter Password");

			RequestDispatcher rd = request.getRequestDispatcher(Format.FORGOTPASSWORD);
			rd.include(request, response);
			
		}else if(!loginDatabase.checkEmail(email)) {
			request.setAttribute("message", "ERROR Don't have this Email Please Try Again or check your True Email");

			RequestDispatcher rd = request.getRequestDispatcher(Format.FORGOTPASSWORD);
			rd.include(request, response);
			
		}else {
			String code = sm.getRandom();
			LoginBean auth = new LoginBean(code, email);
			System.out.println(code);
//			sendemail.sendEmailForgotPass(auth);
//			sendemail.sendEmail(auth);
			session.setAttribute(Format.STACK, 3);
			session.setAttribute(Format.AUTHEN, auth);
			session.setAttribute(Format.CONFIRMFORGOT, email);
			response.sendRedirect(Format.CHANGEFORGOT);
//			response.sendRedirect(Format.CHANGEFORGOTPASSWORD);			
			
		}

	}

}
