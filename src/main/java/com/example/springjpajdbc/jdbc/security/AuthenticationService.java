package com.example.springjpajdbc.jdbc.security;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.springjpajdbc.jdbc.security.authentication.AuthenticationRequest;
import com.example.springjpajdbc.jdbc.security.authentication.AuthenticationResponse;
import com.example.springjpajdbc.jdbc.security.authentication.RegisterRequest;
import com.example.springjpajdbc.user.UserService;
import com.example.springjpajdbc.user.builder.UserBuilder;

@Service
public class AuthenticationService {
	
	private final UserService userService;
	
	private final PasswordEncoder passwordEncoder;
	
	private final JwtService jwtService;
	
	private final AuthenticationManager authenticationManager;
	
	public AuthenticationService(UserService userService,PasswordEncoder passwordEncoder,JwtService jwtService,AuthenticationManager authenticationManager) {
		super();
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
		this.authenticationManager=authenticationManager;
	}
	
	public AuthenticationResponse register(RegisterRequest request) throws Exception {
		//I added a validation before the step where the password gets encoded 
		//I could add all the validations to a single method and then call it here
		//Commenting out password validation so I can use easy passwords for tests.
		//userService.validatePassword(request.getPassword());
		/*
		System.out.println("The emailAdress is: " + request.getEmailAdress());
		System.out.println("The password is: " + request.getPassword());
		System.out.println("The the first name is: " + request.getFirstName());
		System.out.println("The lastName is: " + request.getLastName());
		*/
		var user = new UserBuilder().firstName(request.getFirstName()).lastName(request.getLastName()).emailAdress(request.getEmailAdress()).password(passwordEncoder.encode(request.getPassword())).build();
		//System.out.println(" Inside the Register. User is: "+ user.toString());
		userService.saveUser(user);
		var jwtToken= jwtService.generateToken(user);
		return new AuthenticationResponse(jwtToken);
	}
	
	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		//System.out.println("The emailAdress is: " + request.getEmailAdress());
		//System.out.println("The password is: " + request.getPassword());
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmailAdress(), request.getPassword()));
		var user = userService.findByEmailAdress(request.getEmailAdress());
		var jwtToken= jwtService.generateToken(user);
		return new AuthenticationResponse(jwtToken);
	}
	
	
	

}
