// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The availability information computed by Cumulocity IoT is stored in fragments `c8y_Availability` and `c8y_Connection` of the device.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class C8yAvailability {

	/**
	 * The current status, one of `AVAILABLE`, `CONNECTED`, `MAINTENANCE`, `DISCONNECTED`.
	 */
	private C8yAvailabilityStatus status;

	/**
	 * The time when the device sent the last message to Cumulocity IoT.
	 */
	private String lastMessage;

	public C8yAvailabilityStatus getStatus() {
		return status;
	}
	
	public void setStatus(final C8yAvailabilityStatus status) {
		this.status = status;
	}

	public String getLastMessage() {
		return lastMessage;
	}
	
	public void setLastMessage(final String lastMessage) {
		this.lastMessage = lastMessage;
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
		if (r != null && r instanceof C8yAvailability) {
			C8yAvailability comparer = (C8yAvailability) r;
			if (comparer.getStatus().equals(this.getStatus()) && String.valueOf(comparer.getLastMessage()).equals(String.valueOf(this.getLastMessage()))) {
				return true;
			}
		}
		return false;
	}
}
