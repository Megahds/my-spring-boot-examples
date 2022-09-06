package com.megah.springsecurityjwt.models;

import java.io.Serializable;

public class JwtRequestModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2558508678823424002L;

	private String username;
	private String password;
	
	public JwtRequestModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public JwtRequestModel(String username, String password) {
		super();
		this.username = username;
		this.password = password;
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
