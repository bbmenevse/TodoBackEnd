package com.example.springjpajdbc.jdbc.security.authentication;

public class AuthenticationRequest {
	
	private String emailAdress;
	
	private String password;

	public String getEmailAdress() {
		return emailAdress;
	}

	public void setEmailAdress(String emailAdress) {
		this.emailAdress = emailAdress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AuthenticationRequest(String emailAdress, String password) {
		super();
		this.emailAdress = emailAdress;
		this.password = password;
	}
	
	public AuthenticationRequest() {
		super();
	}
	
	
	
	
	

}
