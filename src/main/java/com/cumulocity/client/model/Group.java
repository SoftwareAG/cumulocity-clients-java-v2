// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Group {

	/**
	 * A list of applications.
	 */
	private Application[] applications;

	/**
	 * An object with a list of custom properties.
	 */
	private CustomProperties customProperties;

	/**
	 * A description of the group.
	 */
	private String description;

	/**
	 * An object with a list of the user's device permissions.
	 */
	@Deprecated
	private DevicePermissions devicePermissions;

	/**
	 * A unique identifier for this group.
	 */
	private int id;

	/**
	 * The name of the group.
	 */
	private String name;

	/**
	 * An object containing user roles for this group.
	 */
	private Roles roles;

	/**
	 * A URL linking to this resource.
	 */
	private String self;

	/**
	 * The list of users in this group.
	 */
	private Users users;

	public Group() {
	}

	public Group(final String name) {
		this.name = name;
	}

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

	public String getDescription() {
		return description;
	}
	
	public void setDescription(final String description) {
		this.description = description;
	}

	public DevicePermissions getDevicePermissions() {
		return devicePermissions;
	}
	
	public void setDevicePermissions(final DevicePermissions devicePermissions) {
		this.devicePermissions = devicePermissions;
	}

	public int getId() {
		return id;
	}
	
	public void setId(final int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(final String name) {
		this.name = name;
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

	public Users getUsers() {
		return users;
	}
	
	public void setUsers(final Users users) {
		this.users = users;
	}

	/**
	 * An object containing user roles for this group.
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

	/**
	 * The list of users in this group.
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class Users {
	
		/**
		 * A URL linking to this resource.
		 */
		private String self;
	
		/**
		 * The list of users in this group.
		 */
		private User[] references;
	
		public String getSelf() {
			return self;
		}
		
		public void setSelf(final String self) {
			this.self = self;
		}
	
		public User[] getReferences() {
			return references;
		}
		
		public void setReferences(final User[] references) {
			this.references = references;
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
			if (r != null && r instanceof Users) {
				Users comparer = (Users) r;
				if (String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && comparer.getReferences().equals(this.getReferences())) {
					return true;
				}
			}
			return false;
		}
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
		if (r != null && r instanceof Group) {
			Group comparer = (Group) r;
			if (comparer.getApplications().equals(this.getApplications()) && comparer.getCustomProperties().equals(this.getCustomProperties()) && String.valueOf(comparer.getDescription()).equals(String.valueOf(this.getDescription())) && comparer.getDevicePermissions().equals(this.getDevicePermissions()) && Integer.valueOf(comparer.getId()).equals(Integer.valueOf(this.getId())) && String.valueOf(comparer.getName()).equals(String.valueOf(this.getName())) && comparer.getRoles().equals(this.getRoles()) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && comparer.getUsers().equals(this.getUsers())) {
				return true;
			}
		}
		return false;
	}
}
