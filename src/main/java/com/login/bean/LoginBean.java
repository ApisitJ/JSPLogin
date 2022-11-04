package com.login.bean;

public class LoginBean {

	private String username;
	private String password;
	private String group;
	private String code;
	private String email;

	public LoginBean() {

	}

	public LoginBean(String username, String password , String group, String code , String email) {
		this.username = username;
		this.password = password;
		this.group = group;
		this.code = code;
		this.email = email;
	}
	
	public LoginBean(String code, String email) {
		this.code = code;
		this.email = email;
	}


	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


}
