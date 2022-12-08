// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Devices can be monitored for availability by adding a `c8y_RequiredAvailability` fragment to the device.
 * 
 * Devices that have not sent any message in the response interval are considered disconnected. The response interval can have a value between `-32768` and `32767` and any values out of range will be shrunk to the range borders. Such devices are marked as unavailable and an unavailability alarm is raised.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class C8yRequiredAvailability {

	private int responseInterval;

	public int getResponseInterval() {
		return responseInterval;
	}
	
	public void setResponseInterval(final int responseInterval) {
		this.responseInterval = responseInterval;
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
		if (r != null && r instanceof C8yRequiredAvailability) {
			C8yRequiredAvailability comparer = (C8yRequiredAvailability) r;
			if (Integer.valueOf(comparer.getResponseInterval()).equals(Integer.valueOf(this.getResponseInterval()))) {
				return true;
			}
		}
		return false;
	}
}
