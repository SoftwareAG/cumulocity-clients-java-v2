// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * All available login options of the tenant.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class LoginOptionCollection {

	/**
	 * An array containing the available login options.
	 */
	private LoginOption[] loginOptions;

	/**
	 * A URL linking to this resource.
	 */
	private String self;

	public LoginOption[] getLoginOptions() {
		return loginOptions;
	}
	
	public void setLoginOptions(final LoginOption[] loginOptions) {
		this.loginOptions = loginOptions;
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
		if (r != null && r instanceof LoginOptionCollection) {
			LoginOptionCollection comparer = (LoginOptionCollection) r;
			if (comparer.getLoginOptions().equals(this.getLoginOptions()) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf()))) {
				return true;
			}
		}
		return false;
	}
}
