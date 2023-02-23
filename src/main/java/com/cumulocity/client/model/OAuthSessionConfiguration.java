// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The session configuration properties are only available for OAuth internal. See [Changing settings > OAuth internal](https://cumulocity.com/guides/users-guide/administration/#oauth-internal) for more details.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class OAuthSessionConfiguration {

	/**
	 * Maximum session duration (in milliseconds) during which a user does not have to login again.
	 */
	private int absoluteTimeoutMillis;

	/**
	 * Maximum number of parallel sessions for one user.
	 */
	private int maximumNumberOfParallelSessions;

	/**
	 * Amount of time before a token expires (in milliseconds) during which the token may be renewed.
	 */
	private int renewalTimeoutMillis;

	/**
	 * Switch to turn additional user agent verification on or off during the session.
	 */
	private boolean userAgentValidationRequired;

	public int getAbsoluteTimeoutMillis() {
		return absoluteTimeoutMillis;
	}
	
	public void setAbsoluteTimeoutMillis(final int absoluteTimeoutMillis) {
		this.absoluteTimeoutMillis = absoluteTimeoutMillis;
	}

	public int getMaximumNumberOfParallelSessions() {
		return maximumNumberOfParallelSessions;
	}
	
	public void setMaximumNumberOfParallelSessions(final int maximumNumberOfParallelSessions) {
		this.maximumNumberOfParallelSessions = maximumNumberOfParallelSessions;
	}

	public int getRenewalTimeoutMillis() {
		return renewalTimeoutMillis;
	}
	
	public void setRenewalTimeoutMillis(final int renewalTimeoutMillis) {
		this.renewalTimeoutMillis = renewalTimeoutMillis;
	}

	public boolean getUserAgentValidationRequired() {
		return userAgentValidationRequired;
	}
	
	public void setUserAgentValidationRequired(final boolean userAgentValidationRequired) {
		this.userAgentValidationRequired = userAgentValidationRequired;
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
		if (r != null && r instanceof OAuthSessionConfiguration) {
			OAuthSessionConfiguration comparer = (OAuthSessionConfiguration) r;
			if (Integer.valueOf(comparer.getAbsoluteTimeoutMillis()).equals(Integer.valueOf(this.getAbsoluteTimeoutMillis())) && Integer.valueOf(comparer.getMaximumNumberOfParallelSessions()).equals(Integer.valueOf(this.getMaximumNumberOfParallelSessions())) && Integer.valueOf(comparer.getRenewalTimeoutMillis()).equals(Integer.valueOf(this.getRenewalTimeoutMillis())) && Boolean.valueOf(comparer.getUserAgentValidationRequired()).equals(Boolean.valueOf(this.getUserAgentValidationRequired()))) {
				return true;
			}
		}
		return false;
	}
}
