package com.app.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrePersist;
import javax.persistence.Table;



import com.app.enums.Gender;
import com.app.enums.Role;
import com.app.enums.Status;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
	@Column(length = 20, unique = true)
	private String email;
	@Column(length = 20, nullable = false)
	private String password;
//	@Enumerated(EnumType.STRING)
//	@Column(name="user_roles",length = 20)
//	private Set<Role> userRoles = new HashSet<Role>();
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column(nullable = false)
	private String profilePicPath;
	@Column(length = 14, unique = true)
	private String phoneNo;
	@Enumerated(EnumType.STRING)
	@Column(length = 20, nullable = false)
	private Status status;
	@Column(length = 100, nullable = false)
	private String address;
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private Gender gender;

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

//	public Set<Role> getUserRoles() {
//		return userRoles;
//	}
//
//	public void setUserRoles(Set<Role> role) {
//		this.userRoles = role;
//	}

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

	public String getProfilePicPath() {
		return profilePicPath;
	}

	public void setProfilePicPath(String profilePicPath) {
		this.profilePicPath = profilePicPath;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public User(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, String email, String password, Set<Role> role,
			String firstName, String lastName, String profilePicPath, String phoneNo, Status status, String address,
			Gender gender) {
		super(id, createdAt, updatedAt);
		this.email = email;
		this.password = password;
//		this.userRoles = role;
		this.firstName = firstName;
		this.lastName = lastName;
		this.profilePicPath = profilePicPath;
		this.phoneNo = phoneNo;
		this.status = status;
		this.address = address;
		this.gender = gender;
	}

	public User(Long id, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super(id, createdAt, updatedAt);
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", profilePicPath=" + profilePicPath + ", phoneNo=" + phoneNo + ", status="
				+ status + ", address=" + address + ", gender=" + gender + "]";
	}

	@PrePersist
	protected void onCreate() {
		if (status == null) {
			status = Status.INACTIVE;
		}
	}

}
