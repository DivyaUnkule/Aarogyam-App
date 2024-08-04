package com.app.dto;

import java.util.Set;

import com.app.enums.Role;

public class AdminDTO {
	public Long id;
	public String email;
	private Set<Role> roles;
	private String message;
	private String jwt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public AdminDTO(Long id, String email, Set<Role> roles, String message, String jwt) {
		super();
		this.id = id;
		this.email = email;
		this.roles = roles;
		this.message = message;
		this.jwt = jwt;
	}

	public AdminDTO() {
		super();
	}

}
