package com.app.dto;

import java.util.Arrays;
import java.util.Set;

import com.app.enums.Role;

public class WeightLossUserDTO {
	private Long loginId;

	private Set<Role> roles;

	private String email;

	private String firstName;

	private String lastName;

	private String phoneNo;

	private String address;

	private byte[] profilePicture;

	private String gender;

	private String message;

	private String jwt;

	public Long getLoginId() {
		return loginId;
	}

	public void setLoginId(Long loginId) {
		this.loginId = loginId;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public byte[] getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(byte[] profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public WeightLossUserDTO(Long loginId, Set<Role> roles, String email, String firstName, String lastName,
			String phoneNo, String address, byte[] profilePicture, String gender, String message, String jwt) {
		super();
		this.loginId = loginId;
		this.roles = roles;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNo = phoneNo;
		this.address = address;
		this.profilePicture = profilePicture;
		this.gender = gender;
		this.message = message;
		this.jwt = jwt;
	}

	public WeightLossUserDTO() {
		super();
	}

	@Override
	public String toString() {
		return "WeightLossUserDTO [loginId=" + loginId + ", roles=" + roles + ", email=" + email + ", firstName="
				+ firstName + ", lastName=" + lastName + ", phoneNo=" + phoneNo + ", address=" + address
				+ ", profilePicture=" + Arrays.toString(profilePicture) + ", gender=" + gender + ", message=" + message
				+ ", jwt=" + jwt + "]";
	}

	// age === age field have to add after health table created

}
