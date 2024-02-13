package com.example.springjpajdbc.jdbc.security.authentication;

public class RegisterRequest {
	
	private String firstName;
	private String lastName;
	private String emailAdress;
	private String password;

	public RegisterRequest(String firstName, String lastName, String emailAdress, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAdress = emailAdress;
		this.password = password;
	}
	
	public RegisterRequest() {
		super();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

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
	
	
	

}


