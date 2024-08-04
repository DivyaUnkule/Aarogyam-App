
package com.app.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "login")
public class Login extends BaseEntity {

	@Column(length = 35, unique = true)
	private String email;
	@Column(length = 350)
	private String password;

    @OneToOne
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<RoleEntity> userRoles = new HashSet<>();

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

	public Set<RoleEntity> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<RoleEntity> userRoles) {
		this.userRoles = userRoles;
	}

	@Override
	public String toString() {
		return "Login [email=" + email + ", password=" + password + "]";
	}
    
    
    

}
