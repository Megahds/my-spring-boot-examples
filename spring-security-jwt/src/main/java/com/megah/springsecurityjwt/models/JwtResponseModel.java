package com.megah.springsecurityjwt.models;

import java.io.Serializable;

public class JwtResponseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8789515298031457202L;

	private final String token;

	public JwtResponseModel(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}
}
