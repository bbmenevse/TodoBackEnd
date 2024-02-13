package com.example.springjpajdbc.user;

import java.util.Collection;
import java.util.List;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="_user")
//We could also name our class something else like UserAccounts or something random
//And extend UserDetails instead of implementing it.
public class User implements UserDetails{
	
	//TODO
	//CHECK WARNING
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	@Column(unique = true)
	private String emailAdress;
	
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public User(Long id, String firstName, String lastName, String emailAdress, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAdress = emailAdress;
		this.password = password;
		// Don't want to allow any role other than USER to be created.
		// If an admin account needs to be created, It can be done using SQL.
		this.role =Role.USER;
	}
	
	public User() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAdress() {
		return emailAdress;
	}

	public void setEmailAdress(String emailAdress) {
		this.emailAdress = emailAdress;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return emailAdress;
		
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		//Maybe I can send a confirmation mail and
		//Make the account unlocked after confirming ownership.
		//May or may not add this.
		return true;
	}
	
	
	


}
