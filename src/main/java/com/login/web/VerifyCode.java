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
import com.login.database.LoginDatabase;
import com.login.database.SendEmail;

/**
 * Servlet implementation class VerifyCode
 */
@WebServlet("/VerifyCode")
public class VerifyCode extends HttpServlet {
	private static final long serialVersionUID = 1L;

	LoginBean loginBean = new LoginBean();
	LoginDatabase logindatabase = new LoginDatabase();

    public VerifyCode() {
        super();
    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = request.getParameter(Format.USERNAME_LOWER);
		String password = request.getParameter(Format.PASSWORD_LOWER);

		loginBean.setUsername(username);
		loginBean.setPassword(password);
		session.setAttribute(Format.STATUSUSER, "");
		session.setAttribute(Format.USERNAME_LOWER, username);


		if(!username.isEmpty() && !password.isEmpty() && logindatabase.validate(loginBean)) {

			SendEmail sm = new SendEmail();
			String code = sm.getRandom();

			loginBean.setCode(code);
			String checkGroup = loginBean.getGroup();
			LoginBean auth = new LoginBean(username, password, checkGroup, code, loginBean.getEmail());

			boolean sendVerify = false;

			LoginDatabase logindatabase = new LoginDatabase();
			Cookie[]cookie = request.getCookies();

			if(!logindatabase.validateCookie(cookie)){
//				sendVerify = sm.sendEmail(loginBean);
				System.out.println(code);
				sendVerify = true;
			}
			else {
				if(!logindatabase.validateCookieUser(cookie, username)) {
					Cookie clearcookie = new Cookie(Format.TRUEUSERLOGIN,null);
					clearcookie.setMaxAge(0);
					response.addCookie(clearcookie);
					sendVerify = sm.sendEmail(loginBean);
//					System.out.println(code);
					sendVerify = true;
				}
			}

			if(!sendVerify) {
				session.setAttribute(Format.AUTHEN, auth);
				session.setAttribute(Format.STATUSUSER, checkGroup);
				response.sendRedirect(Format.LOGINSUCCESS);

			}else {
				session.setAttribute(Format.AUTHEN, auth);
				session.setAttribute(Format.STATUSUSER, checkGroup);
				session.setAttribute(Format.STACK, 3);
				response.sendRedirect(Format.VERIFYEMAIL);
			}

		}else {
			request.setAttribute("message", "Username or password Incorrect Please try again");
			RequestDispatcher rd = request.getRequestDispatcher(Format.LOGIN);
			rd.include(request, response);
		}
	}

}
