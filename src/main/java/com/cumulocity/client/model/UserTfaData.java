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
public class UserTfaData {

	/**
	 * Latest date and time when the user has used two-factor authentication to log in.
	 */
	private String lastTfaRequestTime;

	/**
	 * Two-factor authentication strategy.
	 */
	private Strategy strategy;

	/**
	 * Indicates whether the user has enabled two-factor authentication or not.
	 */
	private boolean tfaEnabled;

	/**
	 * Indicates whether two-factor authentication is enforced by the tenant admin or not.
	 */
	private boolean tfaEnforced;

	public String getLastTfaRequestTime() {
		return lastTfaRequestTime;
	}
	
	public void setLastTfaRequestTime(final String lastTfaRequestTime) {
		this.lastTfaRequestTime = lastTfaRequestTime;
	}

	public Strategy getStrategy() {
		return strategy;
	}
	
	public void setStrategy(final Strategy strategy) {
		this.strategy = strategy;
	}

	public boolean getTfaEnabled() {
		return tfaEnabled;
	}
	
	public void setTfaEnabled(final boolean tfaEnabled) {
		this.tfaEnabled = tfaEnabled;
	}

	public boolean getTfaEnforced() {
		return tfaEnforced;
	}
	
	public void setTfaEnforced(final boolean tfaEnforced) {
		this.tfaEnforced = tfaEnforced;
	}

	
	/**
	 * Two-factor authentication strategy.
	 * [SMS, TOTP]
	 */
	public enum Strategy {
		@JsonProperty("SMS")
		SMS("SMS"),
		@JsonProperty("TOTP")
		TOTP("TOTP");
	
		private String value;
	
		Strategy(final String value) {
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
		if (r != null && r instanceof UserTfaData) {
			UserTfaData comparer = (UserTfaData) r;
			if (String.valueOf(comparer.getLastTfaRequestTime()).equals(String.valueOf(this.getLastTfaRequestTime())) && comparer.getStrategy().equals(this.getStrategy()) && Boolean.valueOf(comparer.getTfaEnabled()).equals(Boolean.valueOf(this.getTfaEnabled())) && Boolean.valueOf(comparer.getTfaEnforced()).equals(Boolean.valueOf(this.getTfaEnforced()))) {
				return true;
			}
		}
		return false;
	}
}
