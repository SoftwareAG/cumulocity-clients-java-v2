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
public class NotificationTokenClaims {

	/**
	 * <p>The token expiration duration.</p>
	 */
	private int expiresInMinutes;

	/**
	 * <p>The subscriber name which the client wishes to be identified with.</p>
	 */
	private String subscriber;

	/**
	 * <p>The subscription name. This value must match the same that was used when the subscription was created.</p>
	 */
	private String subscription;

	public NotificationTokenClaims() {
	}

	public NotificationTokenClaims(final String subscriber, final String subscription) {
		this.subscriber = subscriber;
		this.subscription = subscription;
	}

	public int getExpiresInMinutes() {
		return expiresInMinutes;
	}
	
	public void setExpiresInMinutes(final int expiresInMinutes) {
		this.expiresInMinutes = expiresInMinutes;
	}

	public String getSubscriber() {
		return subscriber;
	}
	
	public void setSubscriber(final String subscriber) {
		this.subscriber = subscriber;
	}

	public String getSubscription() {
		return subscription;
	}
	
	public void setSubscription(final String subscription) {
		this.subscription = subscription;
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
		if (r != null && r instanceof NotificationTokenClaims) {
			NotificationTokenClaims comparer = (NotificationTokenClaims) r;
			if (Integer.valueOf(comparer.getExpiresInMinutes()).equals(Integer.valueOf(this.getExpiresInMinutes())) && String.valueOf(comparer.getSubscriber()).equals(String.valueOf(this.getSubscriber())) && String.valueOf(comparer.getSubscription()).equals(String.valueOf(this.getSubscription()))) {
				return true;
			}
		}
		return false;
	}
}
