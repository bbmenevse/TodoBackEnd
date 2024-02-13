package com.example.springjpajdbc.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

public interface UserService {

	public UserDetails findByEmailAdress(String username);
	
	public void saveUser(User user) throws Exception;
	
	public boolean validatePassword(String password) throws Exception;

}
