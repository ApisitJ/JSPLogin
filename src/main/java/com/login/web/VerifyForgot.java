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

@WebServlet("/VerifyForgot")
public class VerifyForgot extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	LoginBean loginBean = new LoginBean();
	
	
    public VerifyForgot() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			HttpSession session = request.getSession();
			LoginBean bean = (LoginBean) session.getAttribute(Format.AUTHEN);
			Integer stack = (Integer) session.getAttribute(Format.STACK) ;
			String code = request.getParameter(Format.AUTHENCODE);
			session.setAttribute(Format.STACK, stack-1);
			if(code.equals(bean.getCode())) {
				response.sendRedirect(Format.CHANGEFORGOTPASSWORD);	
			}else {
				if(stack.equals(0)) {
					response.sendRedirect(Format.LOGOUT);

				}else {
					request.setAttribute("message", "Wrong Code please Try Again " + stack);

					RequestDispatcher rd = request.getRequestDispatcher(Format.CHANGEFORGOT);
					rd.include(request, response);
				}

			}

		} catch (Exception e) {
			response.sendRedirect(Format.LOGOUT);
		}

	}

}
