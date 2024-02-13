package com.example.springjpajdbc.jdbc.security.authentication.builder;

import com.example.springjpajdbc.jdbc.security.authentication.RegisterRequest;


// Not sure if it's prefered to create builder inside or outside of the original class
public class RegisterRequestBuilder {
	
	private String firstName;
	private String lastName;
	private String emailAdress;
	private String password;
	
	public RegisterRequestBuilder firstname(String firstName)
	{
		this.firstName=firstName;
		return this;
	}
	
	public RegisterRequestBuilder lastname(String lastName)
	{
		this.lastName=lastName;
		return this;
	}
	
	public RegisterRequestBuilder emailAdress(String emailAdress)
	{
		this.emailAdress=emailAdress;
		return this;
	}
	
	public RegisterRequestBuilder password(String password)
	{
		this.password=password;
		return this;
	}
	
	public RegisterRequest build()
	{
		return new RegisterRequest(firstName,lastName,emailAdress,password);
	}
	
	

}
