package com.example.springjpajdbc.oldsecurity;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//@Configuration
//@EnableWebSecurity
//OriginalCourse
public class SpringSecurityConfiguration {
	
	@Bean
	public InMemoryUserDetailsManager createUserDetailsManager() {
		
		UserDetails userDetails1 = createNewUser("example", "example");
		UserDetails userDetails2 = createNewUser("123", "123");
		System.out.print(userDetails1);
		System.out.print(userDetails2);
		
		return new InMemoryUserDetailsManager(userDetails1, userDetails2);
	}
	
	private UserDetails createNewUser(String username, String password) {
		Function<String, String> passwordEncoder
		= input -> passwordEncoder().encode(input);

		UserDetails userDetails = User.builder()
									.passwordEncoder(passwordEncoder)
									.username(username)
									.password(password)
									.roles("USER","ADMIN")
									.build();
		return userDetails;
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		
		http.authorizeHttpRequests(
				auth -> auth
				//.requestMatchers(AntPathRequestMatcher.antMatcher("/api/**")).permitAll() //Allow all calls coming from /api/
				//.requestMatchers(AntPathRequestMatcher.antMatcher("/api/login/**")).permitAll() //Allow all to login page
				.anyRequest().authenticated());
		
		http.httpBasic(Customizer.withDefaults());
		//http.formLogin(withDefaults());
		
		http.sessionManagement(
				session -> session.sessionCreationPolicy
				(SessionCreationPolicy.STATELESS));
		
		
		
		http.csrf(csrf -> csrf.disable());
		// OR
		// http.csrf(AbstractHttpConfigurer::disable);

		http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)); // Starting from SB 3.1.x
		
		//added later
		http.cors(withDefaults());
		
		return http.build();
	}

}
