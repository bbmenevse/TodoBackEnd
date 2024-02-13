package com.example.springjpajdbc.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceImpTest {
	
	@Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImp userService;
	

	@Test
	void validateTruePassword() {

		String validPassword1 = "12345678Aa@";
		
		String validPassword2 = "ValidPassword123@";
		
		String validPassword3 = "randomPassword2@";
		
		String validPassword4 = "@@@1Aa1@@@";
		
		try {
			assertTrue(userService.validatePassword(validPassword1));
			assertTrue(userService.validatePassword(validPassword2));
			assertTrue(userService.validatePassword(validPassword3));
			assertTrue(userService.validatePassword(validPassword4));
		}
		catch(Exception e){
			fail("Unexpected exception: "+ e.getMessage());
		}
		
	}
	
	@Test
	void validateFalsePassword() {

		String validPassword1 = "12345678Aa";
		
		String validPassword2 = "ValidPassword123";
		
		String validPassword3 = "@randomPassword@";
		
		String validPassword4 = "@@@1A1@@@";
		
		try {
			assertThrows(Exception.class,() ->  userService.validatePassword(validPassword1));
			assertThrows(Exception.class,() ->  userService.validatePassword(validPassword2));
			assertThrows(Exception.class,() ->  userService.validatePassword(validPassword3));
			assertThrows(Exception.class,() ->  userService.validatePassword(validPassword4));
		}
		catch(Exception e){
			fail("No Exceptions were thrown on invalid passwords: "+e.getMessage());
			
		}
		
	}
	
	@Test
	void saveUser() {
		
		
	}

}
