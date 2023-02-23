// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The current user.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class CurrentUser {

	/**
	 * A list of user roles.
	 */
	private Role[] effectiveRoles;

	/**
	 * The user's email address.
	 */
	private String email;

	/**
	 * The user's first name.
	 */
	private String firstName;

	/**
	 * A unique identifier for this user.
	 */
	private String id;

	/**
	 * The user's last name.
	 */
	private String lastName;

	/**
	 * The date and time when the user's password was last changed, in [ISO 8601 datetime format](https://www.w3.org/TR/NOTE-datetime).
	 */
	private String lastPasswordChange;

	/**
	 * The user's password. Only Latin1 characters are allowed.
	 */
	private String password;

	/**
	 * The user's phone number.
	 */
	private String phone;

	/**
	 * A URL linking to this resource.
	 */
	private String self;

	/**
	 * Indicates if the user should reset the password on the next login.
	 */
	private boolean shouldResetPassword;

	/**
	 * Indicates if the user has to use two-factor authentication to log in.
	 */
	private boolean twoFactorAuthenticationEnabled;

	/**
	 * The user's username. It can have a maximum of 1000 characters.
	 */
	private String userName;

	/**
	 * An object with a list of the user's device permissions.
	 */
	@Deprecated
	private DevicePermissions devicePermissions;

	public Role[] getEffectiveRoles() {
		return effectiveRoles;
	}
	
	public void setEffectiveRoles(final Role[] effectiveRoles) {
		this.effectiveRoles = effectiveRoles;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(final String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getId() {
		return id;
	}
	
	public void setId(final String id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getLastPasswordChange() {
		return lastPasswordChange;
	}
	
	public void setLastPasswordChange(final String lastPasswordChange) {
		this.lastPasswordChange = lastPasswordChange;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(final String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}
	
	public void setPhone(final String phone) {
		this.phone = phone;
	}

	public String getSelf() {
		return self;
	}
	
	public void setSelf(final String self) {
		this.self = self;
	}

	public boolean getShouldResetPassword() {
		return shouldResetPassword;
	}
	
	public void setShouldResetPassword(final boolean shouldResetPassword) {
		this.shouldResetPassword = shouldResetPassword;
	}

	public boolean getTwoFactorAuthenticationEnabled() {
		return twoFactorAuthenticationEnabled;
	}
	
	public void setTwoFactorAuthenticationEnabled(final boolean twoFactorAuthenticationEnabled) {
		this.twoFactorAuthenticationEnabled = twoFactorAuthenticationEnabled;
	}

	public String getUserName() {
		return userName;
	}
	
	public void setUserName(final String userName) {
		this.userName = userName;
	}

	public DevicePermissions getDevicePermissions() {
		return devicePermissions;
	}
	
	public void setDevicePermissions(final DevicePermissions devicePermissions) {
		this.devicePermissions = devicePermissions;
	}

	@Override
	public String toString() {
		try {
			return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
		} catch (final JsonProcessingException e) {
		}
		return super.toString();
	}

	@Override
	public boolean equals(final Object r) {
		if (r != null && r instanceof CurrentUser) {
			CurrentUser comparer = (CurrentUser) r;
			if (comparer.getEffectiveRoles().equals(this.getEffectiveRoles()) && String.valueOf(comparer.getEmail()).equals(String.valueOf(this.getEmail())) && String.valueOf(comparer.getFirstName()).equals(String.valueOf(this.getFirstName())) && String.valueOf(comparer.getId()).equals(String.valueOf(this.getId())) && String.valueOf(comparer.getLastName()).equals(String.valueOf(this.getLastName())) && String.valueOf(comparer.getLastPasswordChange()).equals(String.valueOf(this.getLastPasswordChange())) && String.valueOf(comparer.getPassword()).equals(String.valueOf(this.getPassword())) && String.valueOf(comparer.getPhone()).equals(String.valueOf(this.getPhone())) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && Boolean.valueOf(comparer.getShouldResetPassword()).equals(Boolean.valueOf(this.getShouldResetPassword())) && Boolean.valueOf(comparer.getTwoFactorAuthenticationEnabled()).equals(Boolean.valueOf(this.getTwoFactorAuthenticationEnabled())) && String.valueOf(comparer.getUserName()).equals(String.valueOf(this.getUserName())) && comparer.getDevicePermissions().equals(this.getDevicePermissions())) {
				return true;
			}
		}
		return false;
	}
}
