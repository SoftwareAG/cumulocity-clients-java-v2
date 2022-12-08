// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The current status, one of `AVAILABLE`, `CONNECTED`, `MAINTENANCE`, `DISCONNECTED`.
 * [AVAILABLE, CONNECTED, MAINTENANCE, DISCONNECTED]
 */
public enum C8yAvailabilityStatus {
	@JsonProperty("AVAILABLE")
	AVAILABLE("AVAILABLE"),
	@JsonProperty("CONNECTED")
	CONNECTED("CONNECTED"),
	@JsonProperty("MAINTENANCE")
	MAINTENANCE("MAINTENANCE"),
	@JsonProperty("DISCONNECTED")
	DISCONNECTED("DISCONNECTED");

	private String value;

	C8yAvailabilityStatus(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
