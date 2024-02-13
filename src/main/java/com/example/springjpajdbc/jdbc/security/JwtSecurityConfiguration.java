package com.example.springjpajdbc.jdbc.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class JwtSecurityConfiguration {
	
	private final JwtAuthenticationFilter jwtAuthFilter;
	private final AuthenticationProvider authenticationProvider;
	
	public JwtSecurityConfiguration(JwtAuthenticationFilter jwtAuthFilter,AuthenticationProvider authenticationProvider) {
		super();
		this.jwtAuthFilter = jwtAuthFilter;
		this.authenticationProvider = authenticationProvider;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		http.csrf(csrf -> csrf.disable());
		//Login or 
		http.authorizeHttpRequests(
				auth -> auth
				//.requestMatchers(AntPathRequestMatcher.antMatcher("/api/**")).permitAll() //Allow all calls coming from /api/
				.requestMatchers(AntPathRequestMatcher.antMatcher("/api/register")).permitAll()
				.requestMatchers(AntPathRequestMatcher.antMatcher("/api/login")).permitAll()
				.anyRequest().authenticated());
		http.sessionManagement(
				session -> session.sessionCreationPolicy
				(SessionCreationPolicy.STATELESS));
		
		http.authenticationProvider(authenticationProvider).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		
		http.cors(withDefaults());
		
		
		return http.build();
	}

}
