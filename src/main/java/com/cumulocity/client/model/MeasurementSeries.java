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
public class MeasurementSeries {

	/**
	 * Each property contained here is a date taken from the measurement and it contains an array of objects specifying `min` and `max` pair of values. Each pair corresponds to a single series object in the `series` array. If there is no aggregation used, `min` is equal to `max` in every pair.
	 */
	private Values values;

	/**
	 * An array containing the type of series and units.
	 */
	private MeasurementFragmentSeries[] series;

	/**
	 * If there were more than 5000 values, the final result was truncated.
	 */
	private boolean truncated;

	public Values getValues() {
		return values;
	}
	
	public void setValues(final Values values) {
		this.values = values;
	}

	public MeasurementFragmentSeries[] getSeries() {
		return series;
	}
	
	public void setSeries(final MeasurementFragmentSeries[] series) {
		this.series = series;
	}

	public boolean getTruncated() {
		return truncated;
	}
	
	public void setTruncated(final boolean truncated) {
		this.truncated = truncated;
	}

	/**
	 * Each property contained here is a date taken from the measurement and it contains an array of objects specifying `min` and `max` pair of values. Each pair corresponds to a single series object in the `series` array. If there is no aggregation used, `min` is equal to `max` in every pair.
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class Values {
	
		@Override
		public String toString() {
			try {
				// TODO thats an extensive operation, which only helps debugging
				return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
			} catch (final JsonProcessingException e) {
			}
			return super.toString();
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
		if (r != null && r instanceof MeasurementSeries) {
			MeasurementSeries comparer = (MeasurementSeries) r;
			if (comparer.getValues().equals(this.getValues()) && comparer.getSeries().equals(this.getSeries()) && Boolean.valueOf(comparer.getTruncated()).equals(Boolean.valueOf(this.getTruncated()))) {
				return true;
			}
		}
		return false;
	}
}
