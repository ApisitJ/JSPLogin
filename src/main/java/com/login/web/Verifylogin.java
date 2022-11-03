package com.login.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.login.bean.Format;
import com.login.bean.LoginBean;
//import com.login.configuration.ConfigName;
//import com.login.configuration.Configure;
import com.login.database.LoginDatabase;


@WebServlet("/Verifylogin")
public class Verifylogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	LoginBean loginBean = new LoginBean();
	LoginDatabase logindatabase = new LoginDatabase();

    public Verifylogin() {
        super();

    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		try {
			HttpSession session = request.getSession();
			LoginBean bean = (LoginBean) session.getAttribute(Format.AUTHEN);
			Integer stack = (Integer) session.getAttribute(Format.STACK) ;
			String code = request.getParameter(Format.AUTHENCODE);
			session.setAttribute(Format.STACK, stack-1);
			if(code.equals(bean.getCode())) {
				Cookie cookie = new Cookie(Format.TRUEUSERLOGIN,bean.getGroup() + "@" + bean.getUsername());
//				Integer setTime = 60*60*20;
				cookie.setMaxAge(60*60*20);
				response.addCookie(cookie);



				if(Format.ADMIN.equals(bean.getGroup().toLowerCase())) {
					response.sendRedirect(Format.LOGINSUCCESS);
				} else if(Format.SUPER_VISOR.equals(bean.getGroup().toLowerCase())) {
					response.sendRedirect(Format.LOGINSUCCESS2);
				} else if(Format.USER.equals(bean.getGroup().toLowerCase())) {
					response.sendRedirect(Format.LOGINSUCCESS3);
				}else {
					response.sendRedirect(Format.LOGOUT);
				}
			}else {
				if(stack.equals(0)) {
					response.sendRedirect(Format.LOGOUT);

				}else {
					request.setAttribute("message", "Wrong Code please Try Again " + stack);

					RequestDispatcher rd = request.getRequestDispatcher(Format.VERIFYEMAIL);
					rd.include(request, response);
				}

			}

		} catch (Exception e) {
			response.sendRedirect(Format.LOGOUT);
		}

	}

}
