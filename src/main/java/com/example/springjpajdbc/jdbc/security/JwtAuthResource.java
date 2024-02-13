package com.example.springjpajdbc.jdbc.security;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springjpajdbc.jdbc.security.authentication.AuthenticationRequest;
import com.example.springjpajdbc.jdbc.security.authentication.AuthenticationResponse;
import com.example.springjpajdbc.jdbc.security.authentication.RegisterRequest;

@RestController
public class JwtAuthResource {
	
	private final AuthenticationService authenticationService;
	
	
	
	public JwtAuthResource(AuthenticationService authenticationService) {
		super();
		this.authenticationService = authenticationService;
	}

	@PostMapping("/api/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) throws Exception
	{
		return ResponseEntity.ok(authenticationService.register(request));
	}
	
	@PostMapping("/api/login")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request)
	{
		return ResponseEntity.ok(authenticationService.authenticate(request));
	}
	
	@GetMapping("/api/getstring")
	public ResponseEntity<String> randomDemo()
	{
		return ResponseEntity.ok("Did manage to get to getstring");
	}

}
