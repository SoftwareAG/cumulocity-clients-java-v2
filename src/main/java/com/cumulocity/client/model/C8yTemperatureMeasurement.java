// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Measurement of the temperature.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class C8yTemperatureMeasurement {

	/**
	 * A measurement is a value with a unit.
	 */
	@JsonProperty(value = "T")
	private C8yMeasurementValue t;

	public C8yMeasurementValue getT() {
		return t;
	}
	
	public void setT(final C8yMeasurementValue t) {
		this.t = t;
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
		if (r != null && r instanceof C8yTemperatureMeasurement) {
			C8yTemperatureMeasurement comparer = (C8yTemperatureMeasurement) r;
			if (comparer.getT().equals(this.getT())) {
				return true;
			}
		}
		return false;
	}
}
