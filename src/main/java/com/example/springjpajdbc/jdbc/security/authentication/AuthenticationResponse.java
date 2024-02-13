package com.example.springjpajdbc.jdbc.security.authentication;

import java.util.Objects;

public class AuthenticationResponse {
	
	private String token;
	

	public AuthenticationResponse(String token) {
		super();
		this.token = token;
	}
	
	public AuthenticationResponse() {
		super();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public int hashCode() {
		return Objects.hash(token);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthenticationResponse other = (AuthenticationResponse) obj;
		return Objects.equals(token, other.token);
	}

	@Override
	public String toString() {
		return "AuthenticationResponse [token=" + token + "]";
	}
	
	
	

}
