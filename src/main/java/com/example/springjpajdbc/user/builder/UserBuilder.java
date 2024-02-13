package com.example.springjpajdbc.user.builder;
import com.example.springjpajdbc.user.Role;
import com.example.springjpajdbc.user.User;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class UserBuilder {
	
	private String firstName;
	
	private String lastName;
	
	private String emailAdress;
	
	private String password;
	
	public UserBuilder firstName(String firstName)
	{
		this.firstName=firstName;
		return this;
	}
	
	public UserBuilder lastName(String lastName)
	{
		this.lastName=lastName;
		return this;
	}
	
	public UserBuilder emailAdress(String emailAdress)
	{
		this.emailAdress=emailAdress;
		return this;
	}
	
	public UserBuilder password(String password)
	{
		this.password=password;
		return this;
	}
	
	public User build()
	{
		return new User(null,firstName,lastName,emailAdress,password);
	}
	
	
	
	

}
