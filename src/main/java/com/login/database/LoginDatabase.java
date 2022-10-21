package com.login.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.Cookie;

import com.login.bean.Format;
import com.login.bean.LoginBean;
import com.login.bean.RegisBean;
import com.login.configuration.ConfigName;
import com.login.configuration.Configure;

public class LoginDatabase {
	
	//Mysql
//	Class.forName("com.mysql.jdbc.Driver");
//	String url = "jdbc:mysql://localhost/world";
//	String name = "root";
//	String pass = "sysadmin";
//	Connection con = DriverManager.getConnection(url,name,pass);

//	private String dbURL = "jdbc:mysql://localhost/world";
//	private String name = "root";
//	private String pass = "sysadmin";
//	private String dbDriver = "com.mysql.jdbc.Driver";
	private String dbURL = Configure.getConfig(ConfigName.DBURL);
	private String name = Configure.getConfig(ConfigName.DBNAME);
	private String pass = Configure.getConfig(ConfigName.DBPASSWORD);
	private String dbDriver = Configure.getConfig(ConfigName.DBDRIVER);
	
	private static final String SELECT_USERNAME_PASSWORD = "SELECT * FROM world.login where user_name = ? and password = ?";
	private static final String SELECT_USERNAME_EMAIL = "SELECT * FROM world.login where user_name = ? and email = ?";
	private static final String SELECT_USERNAME_CHECK_REGISTER = "SELECT user_name FROM world.login where user_name = ?";
	private static final String SELECT_OLDPASSWORD = "SELECT password FROM world.login where user_name = ? AND password = ?";
	private static final String INSERT_NEW_USERNAME	= "insert into world.login (user_name, password, group_user, email)\r\n"
			+ "values( ?, ?, ?, ?)";
	private static final String UPDATE_PASSWORD = "update world.login SET password = ?"
			+ "WHERE user_name = ?";
	private static final String UPDATE_EMAIL = "update world.login SET email = ?"
			+ "WHERE user_name = ?";
	
	
	public void loadDriver(String dbDriver) {
		
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Connection getConnection() {
		Connection con = null;
		
		try {
			con =DriverManager.getConnection(dbURL,name,pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public boolean validate(LoginBean loginBean) {
		loadDriver(dbDriver);
//		loadDriver(Configure.getConfig(ConfigName.DBDRIVER));
		Connection con = getConnection();
		Boolean status = false;
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(SELECT_USERNAME_PASSWORD);
			ps.setString(1, loginBean.getUsername());
			ps.setString(2, loginBean.getPassword());
			
			ResultSet rs = ps.executeQuery();			
			status = rs.next();
			loginBean.setGroup(rs.getString(Format.GROUPUSER));
			loginBean.setEmail(rs.getString(Format.EMAIL));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return status;
	}
	
	public boolean validateCookie(Cookie[] cookie) {

		Boolean checkCookie = false;
		if(cookie != null){
			for(Cookie tmp: cookie){
				if(tmp.getName().equals(Format.TRUEUSERLOGIN)){
//					System.out.print(tmp.getName());
					checkCookie = true;
				}	
			}
		}
		
		return checkCookie;
	}
	
	public boolean verifyUser(String username , String email) {
		Boolean verifyEmail = false;
		loadDriver(dbDriver);
		Connection con = getConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(SELECT_USERNAME_EMAIL);
			ps.setString(1, username);
			ps.setString(2, email);
			
			ResultSet rs = ps.executeQuery();			
			verifyEmail = rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return verifyEmail;
	}
	
	
	public boolean checkUserName(String username ) {
		Boolean checkUser = false;
		loadDriver(dbDriver);
		Connection con = getConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(SELECT_USERNAME_CHECK_REGISTER);
			ps.setString(1, username);			
			ResultSet rs = ps.executeQuery();			
			checkUser = rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return checkUser;
	}
	
	public int getregisterUser(RegisBean data) {
		int rs = 0;
		loadDriver(dbDriver);
		Connection con = getConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(INSERT_NEW_USERNAME);
			ps.setString(1, data.getUsername());
			ps.setString(2, data.getPassword());
			ps.setString(3, data.getGroup());
			ps.setString(4, data.getEmail());
			
			rs = ps.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public int updatePassword(String username , String password) {
		int rs = 0;
		loadDriver(dbDriver);
		Connection con = getConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(UPDATE_PASSWORD);
			ps.setString(1, password);
			ps.setString(2, username);
			
			rs = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public boolean checkOLDPassword(String username , String oldPassword) {
		Boolean password = false;
		loadDriver(dbDriver);
		Connection con = getConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(SELECT_OLDPASSWORD);
			ps.setString(1, username);
			ps.setString(2, oldPassword);
			
			ResultSet rs = ps.executeQuery();			
			password = rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return password;
	}
	
	public int updateEmail(String username , String password) {
		int rs = 0;
		loadDriver(dbDriver);
		Connection con = getConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(UPDATE_EMAIL);
			ps.setString(1, password);
			ps.setString(2, username);
			
			rs = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
}
