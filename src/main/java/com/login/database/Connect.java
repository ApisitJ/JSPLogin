package com.login.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
	public static Connection ConnectDB() {
		try {
			//Mysql
//			Class.forName("com.mysql.jdbc.Driver");
//			String url = "jdbc:mysql://localhost/world";
//			String name = "root";
//			String pass = "sysadmin";
//			Connection con = DriverManager.getConnection(url,name,pass);
			
			//Oracle
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@172.19.190.168:1555:CVMPRD";
			String name = "ITO_SS";
			String pass = "ito_ss";
			Connection con = DriverManager.getConnection(url,name,pass);
			return con;	
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static Connection ConnectDBSec() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@172.19.190.168:1555:CVMPRD";
			String name = "ITO_SS";
			String pass = "ito_ss";
			Connection con = DriverManager.getConnection(url,name,pass);
			return con;	
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
