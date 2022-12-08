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
public class Tenant {

	/**
	 * Email address of the tenant's administrator.
	 */
	private String adminEmail;

	/**
	 * Username of the tenant's administrator.
	 * > **&#9432; Info:** When it is provided in the request body, also `adminEmail` and `adminPass` must be provided.
	 * 
	 */
	private String adminName;

	/**
	 * Password of the tenant's administrator.
	 */
	private String adminPass;

	/**
	 * Indicates if this tenant can create subtenants.
	 */
	private boolean allowCreateTenants;

	/**
	 * Collection of the subscribed applications.
	 */
	private Applications applications;

	/**
	 * Tenant's company name.
	 */
	private String company;

	/**
	 * Name of the contact person.
	 */
	private String contactName;

	/**
	 * Phone number of the contact person, provided in the international format, for example, +48 123 456 7890.
	 */
	private String contactPhone;

	/**
	 * The date and time when the tenant was created.
	 */
	private String creationTime;

	/**
	 * An object with a list of custom properties.
	 */
	private CustomProperties customProperties;

	/**
	 * URL of the tenant's domain. The domain name permits only the use of alphanumeric characters separated by dots `.`, hyphens `-` and underscores `_`.
	 */
	private String domain;

	/**
	 * Unique identifier of a Cumulocity IoT tenant.
	 */
	private String id;

	/**
	 * Collection of the owned applications.
	 */
	private OwnedApplications ownedApplications;

	/**
	 * ID of the parent tenant.
	 */
	private String parent;

	/**
	 * A URL linking to this resource.
	 */
	private String self;

	/**
	 * Current status of the tenant.
	 */
	private Status status;

	public String getAdminEmail() {
		return adminEmail;
	}
	
	public void setAdminEmail(final String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getAdminName() {
		return adminName;
	}
	
	public void setAdminName(final String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPass() {
		return adminPass;
	}
	
	public void setAdminPass(final String adminPass) {
		this.adminPass = adminPass;
	}

	public boolean getAllowCreateTenants() {
		return allowCreateTenants;
	}
	
	public void setAllowCreateTenants(final boolean allowCreateTenants) {
		this.allowCreateTenants = allowCreateTenants;
	}

	public Applications getApplications() {
		return applications;
	}
	
	public void setApplications(final Applications applications) {
		this.applications = applications;
	}

	public String getCompany() {
		return company;
	}
	
	public void setCompany(final String company) {
		this.company = company;
	}

	public String getContactName() {
		return contactName;
	}
	
	public void setContactName(final String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhone() {
		return contactPhone;
	}
	
	public void setContactPhone(final String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getCreationTime() {
		return creationTime;
	}
	
	public void setCreationTime(final String creationTime) {
		this.creationTime = creationTime;
	}

	public CustomProperties getCustomProperties() {
		return customProperties;
	}
	
	public void setCustomProperties(final CustomProperties customProperties) {
		this.customProperties = customProperties;
	}

	public String getDomain() {
		return domain;
	}
	
	public void setDomain(final String domain) {
		this.domain = domain;
	}

	public String getId() {
		return id;
	}
	
	public void setId(final String id) {
		this.id = id;
	}

	public OwnedApplications getOwnedApplications() {
		return ownedApplications;
	}
	
	public void setOwnedApplications(final OwnedApplications ownedApplications) {
		this.ownedApplications = ownedApplications;
	}

	public String getParent() {
		return parent;
	}
	
	public void setParent(final String parent) {
		this.parent = parent;
	}

	public String getSelf() {
		return self;
	}
	
	public void setSelf(final String self) {
		this.self = self;
	}

	public Status getStatus() {
		return status;
	}
	
	public void setStatus(final Status status) {
		this.status = status;
	}

	
	/**
	 * Current status of the tenant.
	 * [ACTIVE, SUSPENDED]
	 */
	public enum Status {
		@JsonProperty("ACTIVE")
		ACTIVE("ACTIVE"),
		@JsonProperty("SUSPENDED")
		SUSPENDED("SUSPENDED");
	
		private String value;
	
		Status(final String value) {
			this.value = value;
		}
	
		public String getValue() {
			return value;
		}
	}

	/**
	 * Collection of the subscribed applications.
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class Applications {
	
		/**
		 * An array containing all subscribed applications.
		 */
		private Application[] references;
	
		/**
		 * A URL linking to this resource.
		 */
		private String self;
	
		public Application[] getReferences() {
			return references;
		}
		
		public void setReferences(final Application[] references) {
			this.references = references;
		}
	
		public String getSelf() {
			return self;
		}
		
		public void setSelf(final String self) {
			this.self = self;
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
			if (r != null && r instanceof Applications) {
				Applications comparer = (Applications) r;
				if (comparer.getReferences().equals(this.getReferences()) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf()))) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * Collection of the owned applications.
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class OwnedApplications {
	
		/**
		 * An array containing all owned applications (only applications with availability MARKET).
		 */
		private Application[] references;
	
		/**
		 * A URL linking to this resource.
		 */
		private String self;
	
		public Application[] getReferences() {
			return references;
		}
		
		public void setReferences(final Application[] references) {
			this.references = references;
		}
	
		public String getSelf() {
			return self;
		}
		
		public void setSelf(final String self) {
			this.self = self;
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
			if (r != null && r instanceof OwnedApplications) {
				OwnedApplications comparer = (OwnedApplications) r;
				if (comparer.getReferences().equals(this.getReferences()) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf()))) {
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
		if (r != null && r instanceof Tenant) {
			Tenant comparer = (Tenant) r;
			if (String.valueOf(comparer.getAdminEmail()).equals(String.valueOf(this.getAdminEmail())) && String.valueOf(comparer.getAdminName()).equals(String.valueOf(this.getAdminName())) && String.valueOf(comparer.getAdminPass()).equals(String.valueOf(this.getAdminPass())) && Boolean.valueOf(comparer.getAllowCreateTenants()).equals(Boolean.valueOf(this.getAllowCreateTenants())) && comparer.getApplications().equals(this.getApplications()) && String.valueOf(comparer.getCompany()).equals(String.valueOf(this.getCompany())) && String.valueOf(comparer.getContactName()).equals(String.valueOf(this.getContactName())) && String.valueOf(comparer.getContactPhone()).equals(String.valueOf(this.getContactPhone())) && String.valueOf(comparer.getCreationTime()).equals(String.valueOf(this.getCreationTime())) && comparer.getCustomProperties().equals(this.getCustomProperties()) && String.valueOf(comparer.getDomain()).equals(String.valueOf(this.getDomain())) && String.valueOf(comparer.getId()).equals(String.valueOf(this.getId())) && comparer.getOwnedApplications().equals(this.getOwnedApplications()) && String.valueOf(comparer.getParent()).equals(String.valueOf(this.getParent())) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && comparer.getStatus().equals(this.getStatus())) {
				return true;
			}
		}
		return false;
	}
}
