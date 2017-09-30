package br.com.talles.ecommercebooks.domain.customer;

import br.com.talles.ecommercebooks.domain.Entity;

public class User extends Entity {

	private String email;
	private String password;
	private String passwordVerify;

	public User() {
		super(true);
	}

	public User(String email, String password, String passwordVerify) {
		super(true);
		this.email = email;
		this.password = password;
		this.passwordVerify = passwordVerify;
	}
	
	public User(String email, String password, String passwordVerify, long id) {
		super(id, true);
		this.email = email;
		this.password = password;
		this.passwordVerify = passwordVerify;
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

	public String getPasswordVerify() {
		return passwordVerify;
	}

	public void setPasswordVerify(String passwordVerify) {
		this.passwordVerify = passwordVerify;
	}
	
}
