package com.example.springjpajdbc.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService{
	
	//TODO
	//Will write tests for email and password
	
	private final UserRepository userRepository;
	
	

	public UserServiceImp(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails findByEmailAdress(String emailAdress) {
		return userRepository.findByEmailAdress(emailAdress).orElseThrow(() -> new UsernameNotFoundException("The User was not found!"));
	}
	
	public boolean validatePassword(String password) throws Exception{
		
		Pattern passwordPattern = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$");
		
		Matcher mat = passwordPattern.matcher(password);
		
		System.out.println("Password is : " + password);
		
		if(!mat.matches())
		{
			throw new Exception("Password is not valid!");
		}
		
		return true;
	}
	
	@Override
	public void saveUser(User user) throws Exception
	{
		if(user.getFirstName()==null||user.getFirstName().isBlank())
		{
			throw new Exception("First name can not be empty!");
		}
		else if(user.getFirstName().length()>30)
		{
			throw new Exception("First name can not be longer then 30 characters!");
		}
		
		else if( user.getLastName()==null ||user.getLastName().isBlank())
		{
			throw new Exception("Last name can not be empty!");
		}
		
		else if(user.getLastName().length()>30)
		{
			throw new Exception("Last name can not be longer then 30 characters!");
		}
		
		
		/*Regex Explanation:
		 * 
The following restrictions are imposed in the email address’ local part by using this regex:
It allows numeric values from 0 to 9.
Both uppercase and lowercase letters from a to z are allowed.
Allowed are underscore “_”, hyphen “-“, and dot “.”
Dot isn’t allowed at the start and end of the local part.
Consecutive dots aren’t allowed.
For the local part, a maximum of 64 characters are allowed.
Restrictions for the domain part in this regular expression include:
It allows numeric values from 0 to 9.
We allow both uppercase and lowercase letters from a to z.
Hyphen “-” and dot “.” aren’t allowed at the start and end of the domain part.
No consecutive dots.
		 */
		
		String emailAdress = user.getEmailAdress();
		
		Pattern emailPattern = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
		        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
		
		Matcher mat = emailPattern.matcher(emailAdress);
		
		if(!mat.matches())
		{
			throw new Exception("Mail adress is not valid!");
		}
		
		
		/*
		
		String password = user.getPassword();
		
		Pattern passwordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\\\S+$).{5,20}$");
		
		mat = passwordPattern.matcher(password);
		
		System.out.println("Password is : " + password);
		
		if(!mat.matches())
		{
			throw new Exception("Password is not valid!");
		}
		
		*/
		
		userRepository.save(user);
	}
	
	

}
