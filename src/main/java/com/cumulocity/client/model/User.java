// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class User {

	/**
	 * A list of applications for this user.
	 */
	private Application[] applications;

	/**
	 * An object with a list of custom properties.
	 */
	private CustomProperties customProperties;

	/**
	 * The user's display name in Cumulocity IoT.
	 */
	private String displayName;

	/**
	 * The user's email address.
	 */
	private String email;

	/**
	 * Indicates whether the user is enabled or not. Disabled users cannot log in or perform API requests.
	 */
	private boolean enabled;

	/**
	 * The user's first name.
	 */
	private String firstName;

	/**
	 * An object with a list of user groups.
	 */
	private Groups groups;

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
	 * Indicates whether the user is subscribed to the newsletter or not.
	 */
	private boolean newsletter;

	/**
	 * Identifier of the parent user. If present, indicates that a user belongs to a user hierarchy by pointing to its direct ancestor. Can only be set by users with role USER_MANAGEMENT_ADMIN during user creation. Otherwise it's assigned automatically.
	 */
	private String owner;

	/**
	 * The user's password. Only Latin1 characters are allowed.
	 * 
	 * If you do not specify a password when creating a new user with a POST request, it must contain the property `sendPasswordResetEmail` with a value of `true`.
	 * 
	 */
	private String password;

	/**
	 * Indicates the password strength. The value can be GREEN, YELLOW or RED for decreasing password strengths.
	 */
	private PasswordStrength passwordStrength;

	/**
	 * The user's phone number.
	 */
	private String phone;

	/**
	 * An object with a list of user roles.
	 */
	private Roles roles;

	/**
	 * A URL linking to this resource.
	 */
	private String self;

	/**
	 * When set to `true`, this field will cause Cumulocity IoT to send a password reset email to the email address specified.
	 * 
	 * If there is no password specified when creating a new user with a POST request, this must be specified and it must be set to `true`.
	 * 
	 */
	private boolean sendPasswordResetEmail;

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

	public Application[] getApplications() {
		return applications;
	}
	
	public void setApplications(final Application[] applications) {
		this.applications = applications;
	}

	public CustomProperties getCustomProperties() {
		return customProperties;
	}
	
	public void setCustomProperties(final CustomProperties customProperties) {
		this.customProperties = customProperties;
	}

	public String getDisplayName() {
		return displayName;
	}
	
	public void setDisplayName(final String displayName) {
		this.displayName = displayName;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(final String email) {
		this.email = email;
	}

	public boolean getEnabled() {
		return enabled;
	}
	
	public void setEnabled(final boolean enabled) {
		this.enabled = enabled;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public Groups getGroups() {
		return groups;
	}
	
	public void setGroups(final Groups groups) {
		this.groups = groups;
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

	public boolean getNewsletter() {
		return newsletter;
	}
	
	public void setNewsletter(final boolean newsletter) {
		this.newsletter = newsletter;
	}

	public String getOwner() {
		return owner;
	}
	
	public void setOwner(final String owner) {
		this.owner = owner;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(final String password) {
		this.password = password;
	}

	public PasswordStrength getPasswordStrength() {
		return passwordStrength;
	}
	
	public void setPasswordStrength(final PasswordStrength passwordStrength) {
		this.passwordStrength = passwordStrength;
	}

	public String getPhone() {
		return phone;
	}
	
	public void setPhone(final String phone) {
		this.phone = phone;
	}

	public Roles getRoles() {
		return roles;
	}
	
	public void setRoles(final Roles roles) {
		this.roles = roles;
	}

	public String getSelf() {
		return self;
	}
	
	public void setSelf(final String self) {
		this.self = self;
	}

	public boolean getSendPasswordResetEmail() {
		return sendPasswordResetEmail;
	}
	
	public void setSendPasswordResetEmail(final boolean sendPasswordResetEmail) {
		this.sendPasswordResetEmail = sendPasswordResetEmail;
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

	
	/**
	 * Indicates the password strength. The value can be GREEN, YELLOW or RED for decreasing password strengths.
	 * [GREEN, YELLOW, RED]
	 */
	public enum PasswordStrength {
		@JsonProperty("GREEN")
		GREEN("GREEN"),
		@JsonProperty("YELLOW")
		YELLOW("YELLOW"),
		@JsonProperty("RED")
		RED("RED");
	
		private String value;
	
		PasswordStrength(final String value) {
			this.value = value;
		}
	
		public String getValue() {
			return value;
		}
	}

	/**
	 * An object with a list of user groups.
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class Groups {
	
		/**
		 * A URL linking to this resource.
		 */
		private String self;
	
		/**
		 * A list of user group references.
		 */
		private GroupReference[] references;
	
		/**
		 * Information about paging statistics.
		 */
		private PageStatistics statistics;
	
		public String getSelf() {
			return self;
		}
		
		public void setSelf(final String self) {
			this.self = self;
		}
	
		public GroupReference[] getReferences() {
			return references;
		}
		
		public void setReferences(final GroupReference[] references) {
			this.references = references;
		}
	
		public PageStatistics getStatistics() {
			return statistics;
		}
		
		public void setStatistics(final PageStatistics statistics) {
			this.statistics = statistics;
		}
	
		@Override
		public String toString() {
			try {
				// TODO thats an extensive operation, which only helps debugging
				return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
			} catch (final JsonProcessingException e) {
			}
			return super.toString();
		}
	
		@Override
		public boolean equals(final Object r) {
			if (r != null && r instanceof Groups) {
				Groups comparer = (Groups) r;
				if (String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && comparer.getReferences().equals(this.getReferences()) && comparer.getStatistics().equals(this.getStatistics())) {
					return true;
				}
			}
			return false;
		}
	}


	/**
	 * An object with a list of user roles.
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class Roles {
	
		/**
		 * A URL linking to this resource.
		 */
		private String self;
	
		/**
		 * A list of user role references.
		 */
		private RoleReference[] references;
	
		/**
		 * Information about paging statistics.
		 */
		private PageStatistics statistics;
	
		public String getSelf() {
			return self;
		}
		
		public void setSelf(final String self) {
			this.self = self;
		}
	
		public RoleReference[] getReferences() {
			return references;
		}
		
		public void setReferences(final RoleReference[] references) {
			this.references = references;
		}
	
		public PageStatistics getStatistics() {
			return statistics;
		}
		
		public void setStatistics(final PageStatistics statistics) {
			this.statistics = statistics;
		}
	
		@Override
		public String toString() {
			try {
				// TODO thats an extensive operation, which only helps debugging
				return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
			} catch (final JsonProcessingException e) {
			}
			return super.toString();
		}
	
		@Override
		public boolean equals(final Object r) {
			if (r != null && r instanceof Roles) {
				Roles comparer = (Roles) r;
				if (String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && comparer.getReferences().equals(this.getReferences()) && comparer.getStatistics().equals(this.getStatistics())) {
					return true;
				}
			}
			return false;
		}
	}

	@Override
	public String toString() {
		try {
			// TODO thats an extensive operation, which only helps debugging
			return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
		} catch (final JsonProcessingException e) {
		}
		return super.toString();
	}

	@Override
	public boolean equals(final Object r) {
		if (r != null && r instanceof User) {
			User comparer = (User) r;
			if (comparer.getApplications().equals(this.getApplications()) && comparer.getCustomProperties().equals(this.getCustomProperties()) && String.valueOf(comparer.getDisplayName()).equals(String.valueOf(this.getDisplayName())) && String.valueOf(comparer.getEmail()).equals(String.valueOf(this.getEmail())) && Boolean.valueOf(comparer.getEnabled()).equals(Boolean.valueOf(this.getEnabled())) && String.valueOf(comparer.getFirstName()).equals(String.valueOf(this.getFirstName())) && comparer.getGroups().equals(this.getGroups()) && String.valueOf(comparer.getId()).equals(String.valueOf(this.getId())) && String.valueOf(comparer.getLastName()).equals(String.valueOf(this.getLastName())) && String.valueOf(comparer.getLastPasswordChange()).equals(String.valueOf(this.getLastPasswordChange())) && Boolean.valueOf(comparer.getNewsletter()).equals(Boolean.valueOf(this.getNewsletter())) && String.valueOf(comparer.getOwner()).equals(String.valueOf(this.getOwner())) && String.valueOf(comparer.getPassword()).equals(String.valueOf(this.getPassword())) && comparer.getPasswordStrength().equals(this.getPasswordStrength()) && String.valueOf(comparer.getPhone()).equals(String.valueOf(this.getPhone())) && comparer.getRoles().equals(this.getRoles()) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && Boolean.valueOf(comparer.getSendPasswordResetEmail()).equals(Boolean.valueOf(this.getSendPasswordResetEmail())) && Boolean.valueOf(comparer.getShouldResetPassword()).equals(Boolean.valueOf(this.getShouldResetPassword())) && Boolean.valueOf(comparer.getTwoFactorAuthenticationEnabled()).equals(Boolean.valueOf(this.getTwoFactorAuthenticationEnabled())) && String.valueOf(comparer.getUserName()).equals(String.valueOf(this.getUserName())) && comparer.getDevicePermissions().equals(this.getDevicePermissions())) {
				return true;
			}
		}
		return false;
	}
}
