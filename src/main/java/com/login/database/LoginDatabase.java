package com.login.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.Cookie;

import com.login.bean.CovertData;
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

	private String dbURL = Configure.getConfig(ConfigName.DBURL);
	private String name = Configure.getConfig(ConfigName.DBNAME);
	private String pass = Configure.getConfig(ConfigName.DBPASSWORD);
	private String dbDriver = Configure.getConfig(ConfigName.DBDRIVER);

//	private static final String SELECT_USERNAME_PASSWORD = "SELECT * FROM world.login where user_name = ? and password = ?";
//	private static final String SELECT_USERNAME_EMAIL = "SELECT * FROM world.login where user_name = ? and email = ?";
//	private static final String SELECT_USERNAME_CHECK_REGISTER = "SELECT user_name FROM world.login where user_name = ?";
//	private static final String SELECT_OLDPASSWORD = "SELECT password FROM world.login where user_name = ? AND password = ?";
//	private static final String INSERT_NEW_USERNAME	= "insert into world.login (user_name, password, group_user, email)\r\n"
//			+ "values( ?, ?, ?, ?)";
//	private static final String UPDATE_PASSWORD = "update world.login SET password = ?"
//			+ "WHERE user_name = ?";
//	private static final String UPDATE_EMAIL = "update world.login SET email = ?"
//			+ "WHERE user_name = ?";
//	private static final String SELECT_USERNAME ="select * FROM world.login where user_name = ?";

	private static final String SELECT_USERNAME_PASSWORD = "SELECT * FROM " + Configure.getConfig(ConfigName.DBTABLEUSERID) + " where USER_NAME = ? and USER_PASSWORD = ?";
	private static final String SELECT_USERNAME_EMAIL = "SELECT * FROM " + Configure.getConfig(ConfigName.DBTABLEUSERID) + " where USER_NAME = ? and EMAIL = ?";
	private static final String SELECT_USERNAME_CHECK_REGISTER = "SELECT USER_NAME FROM " + Configure.getConfig(ConfigName.DBTABLEUSERID) + " where USER_NAME = ?";
	private static final String SELECT_EMPID_CHECK_REGISTER = "SELECT EMPLOYEE_ID FROM " + Configure.getConfig(ConfigName.DBTABLEUSERID) + " where EMPLOYEE_ID = ?";
	private static final String SELECT_EMAIL_CHECK_REGISTER = "SELECT EMAIL FROM " + Configure.getConfig(ConfigName.DBTABLEUSERID) + " where EMAIL = ?";
	private static final String SELECT_OLDPASSWORD = "SELECT USER_PASSWORD FROM " + Configure.getConfig(ConfigName.DBTABLEUSERID) + " where USER_NAME = ? AND USER_PASSWORD = ?";
	private static final String INSERT_NEW_USERNAME	= "insert into " + Configure.getConfig(ConfigName.DBTABLEUSERID) + " (EMPLOYEE_ID, USER_NAME, USER_PASSWORD, GROUP_USER, EMAIL)\r\n"
			+ "values( ?, ?, ?, ?)";
	private static final String UPDATE_PASSWORD = "update " + Configure.getConfig(ConfigName.DBTABLEUSERID) + " SET USER_PASSWORD = ?"
			+ "WHERE USER_NAME = ?";
	private static final String UPDATE_PASSWORD_FORGOT = "update " + Configure.getConfig(ConfigName.DBTABLEUSERID) + " SET USER_PASSWORD = ?"
			+ "WHERE EMAIL = ?";
	private static final String UPDATE_EMAIL = "update " + Configure.getConfig(ConfigName.DBTABLEUSERID) + " SET EMAIL = ?"
			+ "WHERE USER_NAME = ?";
	private static final String SELECT_USERNAME ="select * FROM " + Configure.getConfig(ConfigName.DBTABLEUSERID) + " where USER_NAME = ?";

	CovertData convertData = new CovertData();



	public void loadDriver(String dbDriver) {

		try {
			Class.forName(dbDriver);
			System.out.println("Connect Driver Success");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public Connection getConnection() {
		Connection con = null;

		try {
			con =DriverManager.getConnection(dbURL,name,pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public boolean validate(LoginBean loginBean) {
		loadDriver(dbDriver);
		Connection con = getConnection();
		Boolean status = false;

		PreparedStatement ps;
		try {
			ps = con.prepareStatement(SELECT_USERNAME_PASSWORD);
			ps.setString(1, loginBean.getUsername());
			ps.setString(2, convertData.EncodePassword(loginBean.getPassword()));

			ResultSet rs = ps.executeQuery();
			status = rs.next();
			loginBean.setGroup(rs.getString(Format.GROUPUSER_UPPER));
			loginBean.setEmail(rs.getString(Format.EMAIL_UPPER));
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return status;
	}

	public boolean validateCookie(Cookie[] cookie) {

		boolean checkCookie = false;
		if(cookie != null){
			for(Cookie tmp: cookie){
				if(tmp.getName().equals(Format.TRUEUSERLOGIN)){
					checkCookie = true;
				}
			}
		}

		return checkCookie;
	}

	public boolean validateCookieUser(Cookie[] cookie, String user) {

		boolean checkCookie = false;
		if(cookie != null && user != null){
			for(Cookie tmp: cookie){
				if(tmp.getName().equals(Format.TRUEUSERLOGIN)) {
					String cookieName = tmp.getValue();
					String checkName = cookieName.split("@")[1];
					if(checkName.equals(user)){
						checkCookie = true;
					}
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
			e.printStackTrace();
		}


		return checkUser;
	}

	public boolean checkEmpID(String id ) {
		Boolean checkUser = false;
		loadDriver(dbDriver);
		Connection con = getConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(SELECT_EMPID_CHECK_REGISTER);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			checkUser = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return checkUser;
	}

	public boolean checkEmail(String email ) {
		Boolean checkUser = false;
		loadDriver(dbDriver);
		Connection con = getConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(SELECT_EMAIL_CHECK_REGISTER);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			checkUser = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return checkUser;
	}

	public int getRegisterUser(RegisBean data) {
		int rs = 0;
		loadDriver(dbDriver);
		Connection con = getConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(INSERT_NEW_USERNAME);
			ps.setString(1, data.getUsername());
			ps.setString(2,convertData.EncodePassword(data.getPassword()));
			ps.setString(3, data.getGroup());
			ps.setString(4, data.getEmail());

			rs = ps.executeUpdate();


		} catch (SQLException e) {
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
			ps.setString(1, convertData.EncodePassword(password));
			ps.setString(2, username);

			rs = ps.executeUpdate();

		} catch (SQLException e) {
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
			ps.setString(2, convertData.EncodePassword(oldPassword));

			ResultSet rs = ps.executeQuery();
			password = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return password;
	}

	public int updateEmail(String username , String email) {
		int rs = 0;
		loadDriver(dbDriver);
		Connection con = getConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(UPDATE_EMAIL);
			ps.setString(1, email);
			ps.setString(2, username);

			rs = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public boolean checkUser(String username ) {
		Boolean checkUser = false;
		loadDriver(dbDriver);
		Connection con = getConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(SELECT_USERNAME);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			checkUser = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return checkUser;
	}

	public int updatePasswordForgot(String password , String email) {
		int rs = 0;
		loadDriver(dbDriver);
		Connection con = getConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(UPDATE_PASSWORD_FORGOT);
			ps.setString(1, convertData.EncodePassword(password));
			ps.setString(2, email);

			rs = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public boolean test(String password , String email) {
		boolean verifyEmail = false;
		loadDriver(dbDriver);
//		Connection con = Connect.ConnectDBSec();


		Connection con = getConnection();
		System.out.println("Connection Success");
		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM ITO_SS.APP4_USER_LOGIN";
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			verifyEmail = rs.next();
			while(rs.next()) {
				System.out.print(rs.getString("ACCOUNT_ID"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return verifyEmail;
	}

}
