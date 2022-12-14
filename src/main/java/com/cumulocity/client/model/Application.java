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
public class Application {

	/**
	 * Application access level for other tenants.
	 */
	private Availability availability;

	/**
	 * The context path in the URL makes the application accessible. Mandatory when the type of the application is `HOSTED`.
	 */
	private String contextPath;

	/**
	 * Description of the application.
	 */
	private String description;

	/**
	 * Unique identifier of the application.
	 */
	private String id;

	/**
	 * Applications, regardless of their form, are identified by an application key.
	 */
	private String key;

	/**
	 * Name of the application.
	 */
	private String name;

	/**
	 * Reference to the tenant owning this application. The default value is a reference to the current tenant.
	 */
	private ApplicationOwner owner;

	/**
	 * A URL linking to this resource.
	 */
	private String self;

	/**
	 * The type of the application.
	 */
	private Type type;

	private Object manifest;

	/**
	 * Roles provided by the microservice.
	 */
	private String[] roles;

	/**
	 * List of permissions required by a microservice to work.
	 */
	private String[] requiredRoles;

	/**
	 * A flag to indicate if the application has a breadcrumbs navigation on the UI.
	 * > **&#9432; Info:** This property is specific to the web application type.
	 * 
	 */
	private boolean breadcrumbs;

	/**
	 * The content security policy of the application.
	 * > **&#9432; Info:** This property is specific to the web application type.
	 * 
	 */
	private String contentSecurityPolicy;

	/**
	 * A URL to a JSON object with dynamic content options.
	 * > **&#9432; Info:** This property is specific to the web application type.
	 * 
	 */
	private String dynamicOptionsUrl;

	/**
	 * The global title of the application.
	 * > **&#9432; Info:** This property is specific to the web application type.
	 * 
	 */
	private String globalTitle;

	/**
	 * A flag that shows if the application is a legacy application or not.
	 * > **&#9432; Info:** This property is specific to the web application type.
	 * 
	 */
	private boolean legacy;

	/**
	 * A flag to indicate if the application uses the UI context menu on the right side.
	 * > **&#9432; Info:** This property is specific to the web application type.
	 * 
	 */
	private boolean rightDrawer;

	/**
	 * A flag that shows if the application is hybrid and using Angular and AngularJS simultaneously.
	 * > **&#9432; Info:** This property is specific to the web application type.
	 * 
	 */
	private boolean upgrade;

	/**
	 * The active version ID of the application. For microservice applications the active version ID is the microservice manifest version ID.
	 */
	private String activeVersionId;

	/**
	 * URL to the application base directory hosted on an external server. Only present in legacy hosted applications.
	 */
	@Deprecated
	private String resourcesUrl;

	public Availability getAvailability() {
		return availability;
	}
	
	public void setAvailability(final Availability availability) {
		this.availability = availability;
	}

	public String getContextPath() {
		return contextPath;
	}
	
	public void setContextPath(final String contextPath) {
		this.contextPath = contextPath;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(final String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}
	
	public void setId(final String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}
	
	public void setKey(final String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}
	
	public void setName(final String name) {
		this.name = name;
	}

	public ApplicationOwner getOwner() {
		return owner;
	}
	
	public void setOwner(final ApplicationOwner owner) {
		this.owner = owner;
	}

	public String getSelf() {
		return self;
	}
	
	public void setSelf(final String self) {
		this.self = self;
	}

	public Type getType() {
		return type;
	}
	
	public void setType(final Type type) {
		this.type = type;
	}

	public Object getManifest() {
		return manifest;
	}
	
	public void setManifest(final Object manifest) {
		this.manifest = manifest;
	}

	public String[] getRoles() {
		return roles;
	}
	
	public void setRoles(final String[] roles) {
		this.roles = roles;
	}

	public String[] getRequiredRoles() {
		return requiredRoles;
	}
	
	public void setRequiredRoles(final String[] requiredRoles) {
		this.requiredRoles = requiredRoles;
	}

	public boolean getBreadcrumbs() {
		return breadcrumbs;
	}
	
	public void setBreadcrumbs(final boolean breadcrumbs) {
		this.breadcrumbs = breadcrumbs;
	}

	public String getContentSecurityPolicy() {
		return contentSecurityPolicy;
	}
	
	public void setContentSecurityPolicy(final String contentSecurityPolicy) {
		this.contentSecurityPolicy = contentSecurityPolicy;
	}

	public String getDynamicOptionsUrl() {
		return dynamicOptionsUrl;
	}
	
	public void setDynamicOptionsUrl(final String dynamicOptionsUrl) {
		this.dynamicOptionsUrl = dynamicOptionsUrl;
	}

	public String getGlobalTitle() {
		return globalTitle;
	}
	
	public void setGlobalTitle(final String globalTitle) {
		this.globalTitle = globalTitle;
	}

	public boolean getLegacy() {
		return legacy;
	}
	
	public void setLegacy(final boolean legacy) {
		this.legacy = legacy;
	}

	public boolean getRightDrawer() {
		return rightDrawer;
	}
	
	public void setRightDrawer(final boolean rightDrawer) {
		this.rightDrawer = rightDrawer;
	}

	public boolean getUpgrade() {
		return upgrade;
	}
	
	public void setUpgrade(final boolean upgrade) {
		this.upgrade = upgrade;
	}

	public String getActiveVersionId() {
		return activeVersionId;
	}
	
	public void setActiveVersionId(final String activeVersionId) {
		this.activeVersionId = activeVersionId;
	}

	public String getResourcesUrl() {
		return resourcesUrl;
	}
	
	public void setResourcesUrl(final String resourcesUrl) {
		this.resourcesUrl = resourcesUrl;
	}

	
	/**
	 * Application access level for other tenants.
	 * [MARKET, PRIVATE]
	 */
	public enum Availability {
		@JsonProperty("MARKET")
		MARKET("MARKET"),
		@JsonProperty("PRIVATE")
		PRIVATE("PRIVATE");
	
		private String value;
	
		Availability(final String value) {
			this.value = value;
		}
	
		public String getValue() {
			return value;
		}
	}

	
	/**
	 * The type of the application.
	 * [EXTERNAL, HOSTED, MICROSERVICE]
	 */
	public enum Type {
		@JsonProperty("EXTERNAL")
		EXTERNAL("EXTERNAL"),
		@JsonProperty("HOSTED")
		HOSTED("HOSTED"),
		@JsonProperty("MICROSERVICE")
		MICROSERVICE("MICROSERVICE");
	
		private String value;
	
		Type(final String value) {
			this.value = value;
		}
	
		public String getValue() {
			return value;
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
		if (r != null && r instanceof Application) {
			Application comparer = (Application) r;
			if (comparer.getAvailability().equals(this.getAvailability()) && String.valueOf(comparer.getContextPath()).equals(String.valueOf(this.getContextPath())) && String.valueOf(comparer.getDescription()).equals(String.valueOf(this.getDescription())) && String.valueOf(comparer.getId()).equals(String.valueOf(this.getId())) && String.valueOf(comparer.getKey()).equals(String.valueOf(this.getKey())) && String.valueOf(comparer.getName()).equals(String.valueOf(this.getName())) && comparer.getOwner().equals(this.getOwner()) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && comparer.getType().equals(this.getType()) && comparer.getManifest().equals(this.getManifest()) && comparer.getRoles().equals(this.getRoles()) && comparer.getRequiredRoles().equals(this.getRequiredRoles()) && Boolean.valueOf(comparer.getBreadcrumbs()).equals(Boolean.valueOf(this.getBreadcrumbs())) && String.valueOf(comparer.getContentSecurityPolicy()).equals(String.valueOf(this.getContentSecurityPolicy())) && String.valueOf(comparer.getDynamicOptionsUrl()).equals(String.valueOf(this.getDynamicOptionsUrl())) && String.valueOf(comparer.getGlobalTitle()).equals(String.valueOf(this.getGlobalTitle())) && Boolean.valueOf(comparer.getLegacy()).equals(Boolean.valueOf(this.getLegacy())) && Boolean.valueOf(comparer.getRightDrawer()).equals(Boolean.valueOf(this.getRightDrawer())) && Boolean.valueOf(comparer.getUpgrade()).equals(Boolean.valueOf(this.getUpgrade())) && String.valueOf(comparer.getActiveVersionId()).equals(String.valueOf(this.getActiveVersionId())) && String.valueOf(comparer.getResourcesUrl()).equals(String.valueOf(this.getResourcesUrl()))) {
				return true;
			}
		}
		return false;
	}
}
