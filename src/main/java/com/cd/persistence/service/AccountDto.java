package com.cd.persistence.service;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.cd.validation.PasswordMatches;
import com.cd.validation.ValidEmail;
import com.cd.validation.ValidPassword;

@PasswordMatches
public class AccountDto {
	@NotNull
	@Size(min = 3)
	private String username;
	
	@ValidEmail
	@NotNull
	@Size(min = 3)
	private String email;
	
	@ValidPassword
	private String password;
	
	@NotNull
	@Size(min = 4, max = 16)
	private String confirmPassword;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
