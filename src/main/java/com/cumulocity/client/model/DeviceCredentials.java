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
public class DeviceCredentials {

	/**
	 * The external ID of the device.
	 */
	private String id;

	/**
	 * Password of these device credentials.
	 */
	private String password;

	/**
	 * A URL linking to this resource.
	 */
	private String self;

	/**
	 * Tenant ID for these device credentials.
	 */
	private String tenantId;

	/**
	 * Username of these device credentials.
	 */
	private String username;

	public String getId() {
		return id;
	}
	
	public void setId(final String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(final String password) {
		this.password = password;
	}

	public String getSelf() {
		return self;
	}
	
	public void setSelf(final String self) {
		this.self = self;
	}

	public String getTenantId() {
		return tenantId;
	}
	
	public void setTenantId(final String tenantId) {
		this.tenantId = tenantId;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(final String username) {
		this.username = username;
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
		if (r != null && r instanceof DeviceCredentials) {
			DeviceCredentials comparer = (DeviceCredentials) r;
			if (String.valueOf(comparer.getId()).equals(String.valueOf(this.getId())) && String.valueOf(comparer.getPassword()).equals(String.valueOf(this.getPassword())) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && String.valueOf(comparer.getTenantId()).equals(String.valueOf(this.getTenantId())) && String.valueOf(comparer.getUsername()).equals(String.valueOf(this.getUsername()))) {
				return true;
			}
		}
		return false;
	}
}
