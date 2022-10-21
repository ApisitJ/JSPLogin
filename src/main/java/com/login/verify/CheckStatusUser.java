package com.login.verify;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.login.bean.Format;
import com.login.database.LoginDatabase;

public class CheckStatusUser {
	private static final String ADMIN = "admin";
	private static final String SUPERVISOR = "supervisor";
	private static final String USER = "user";
	
	
	public static String IndexStatus(String status) {
		String redirect = "";
		if(ADMIN.equals(status)) {
			redirect = Format.LOGINSUCCESS;
		}else if(SUPERVISOR.equals(status)) {
			redirect = Format.LOGINSUCCESS2;
		}else if(USER.equals(status)) {
			redirect = Format.LOGINSUCCESS3;
		}else {
			redirect = Format.LOGOUT;
		}
		
	return redirect;
	}
	
	public static HttpServletResponse setLogout (HttpServletResponse res) {	
		res.setHeader("Cache-Control", "no-cache");
		res.setHeader("Cache-Control", "no-store");
		res.setHeader("Pragma", "no-cache");
		res.setDateHeader("Expire", 0);	
		return res;
		
	}
	
	public static String checkGroupAdmin (String statusUser,Cookie[] cookie ) throws IOException {	
		LoginDatabase logindatabase = new LoginDatabase();
		String redirect = Format.LOGOUT;
		
		try{
			if(!logindatabase.validateCookie(cookie) || statusUser == null){
				throw new Exception("Exception message");
			}
			if(SUPERVISOR.equals(statusUser)) {
				redirect = Format.LOGINSUCCESS2;
				
			} else if(USER.equals(statusUser)) {
				redirect = Format.LOGINSUCCESS3;
			}
			
			
		} catch(Exception e){
			redirect = Format.LOGOUT;

		}
		return redirect;
		
	}
	
	public static String checkGroupSuperVisor (String statusUser,Cookie[] cookie ) throws IOException {	
		LoginDatabase logindatabase = new LoginDatabase();
		String redirect = Format.LOGOUT;
		
		try{
			if(!logindatabase.validateCookie(cookie) || statusUser == null){
				throw new Exception("Exception message");
			}
			if(USER.equals(statusUser)) {
				redirect = Format.LOGINSUCCESS3;
				
			}else if(ADMIN.equals(statusUser)){
				redirect = Format.LOGINSUCCESS;
				
			}
			
			
		} catch(Exception e){
			redirect = Format.LOGOUT;

		}
		return redirect;
		
	}
	
	public static String checkGroupUser(String statusUser,Cookie[] cookie ) throws IOException {	
		LoginDatabase logindatabase = new LoginDatabase();
		String redirect = Format.LOGOUT;
		
		try{
			if(!logindatabase.validateCookie(cookie) || statusUser == null){
				throw new Exception("Exception message");
			}
			if(SUPERVISOR.equals(statusUser)) {
				redirect = Format.LOGINSUCCESS2;
			}else if(ADMIN.equals(statusUser)){
				redirect = Format.LOGINSUCCESS;
			}
			
			
		} catch(Exception e){
			redirect = Format.LOGOUT;

		}
		return redirect;
		
	}
	
	public static Boolean checkStatus(String statusUser,Cookie[] cookie ) throws IOException {	
		LoginDatabase logindatabase = new LoginDatabase();
		Boolean redirect = false;
		
		try{
			if(!logindatabase.validateCookie(cookie) || statusUser == null){
				redirect = true;
				throw new Exception("Exception message");		
			}		
			
		} catch(Exception e){
			
			redirect = true;

		}
		return redirect;
		
	}
	
}
