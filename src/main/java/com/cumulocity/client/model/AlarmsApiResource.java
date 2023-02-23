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
public class AlarmsApiResource {

	/**
	 * Collection of all alarms
	 */
	private Alarms alarms;

	/**
	 * Read-only collection of all alarms for a specific source object. The placeholder {source} must be a unique ID of an object in the inventory.
	 */
	private String alarmsForSource;

	/**
	 * Read-only collection of all alarms in a particular status. The placeholder {status} can be one of the following values: ACTIVE, ACKNOWLEDGED or CLEARED
	 */
	private String alarmsForStatus;

	/**
	 * Read-only collection of all alarms for a specific source, status and time range.
	 */
	private String alarmsForSourceAndStatusAndTime;

	/**
	 * Read-only collection of all alarms for a particular status and time range.
	 */
	private String alarmsForStatusAndTime;

	/**
	 * Read-only collection of all alarms for a specific source and time range.
	 */
	private String alarmsForSourceAndTime;

	/**
	 * Read-only collection of all alarms for a particular time range.
	 */
	private String alarmsForTime;

	/**
	 * Read-only collection of all alarms for a specific source object in a particular status.
	 */
	private String alarmsForSourceAndStatus;

	/**
	 * A URL linking to this resource.
	 */
	private String self;

	public Alarms getAlarms() {
		return alarms;
	}
	
	public void setAlarms(final Alarms alarms) {
		this.alarms = alarms;
	}

	public String getAlarmsForSource() {
		return alarmsForSource;
	}
	
	public void setAlarmsForSource(final String alarmsForSource) {
		this.alarmsForSource = alarmsForSource;
	}

	public String getAlarmsForStatus() {
		return alarmsForStatus;
	}
	
	public void setAlarmsForStatus(final String alarmsForStatus) {
		this.alarmsForStatus = alarmsForStatus;
	}

	public String getAlarmsForSourceAndStatusAndTime() {
		return alarmsForSourceAndStatusAndTime;
	}
	
	public void setAlarmsForSourceAndStatusAndTime(final String alarmsForSourceAndStatusAndTime) {
		this.alarmsForSourceAndStatusAndTime = alarmsForSourceAndStatusAndTime;
	}

	public String getAlarmsForStatusAndTime() {
		return alarmsForStatusAndTime;
	}
	
	public void setAlarmsForStatusAndTime(final String alarmsForStatusAndTime) {
		this.alarmsForStatusAndTime = alarmsForStatusAndTime;
	}

	public String getAlarmsForSourceAndTime() {
		return alarmsForSourceAndTime;
	}
	
	public void setAlarmsForSourceAndTime(final String alarmsForSourceAndTime) {
		this.alarmsForSourceAndTime = alarmsForSourceAndTime;
	}

	public String getAlarmsForTime() {
		return alarmsForTime;
	}
	
	public void setAlarmsForTime(final String alarmsForTime) {
		this.alarmsForTime = alarmsForTime;
	}

	public String getAlarmsForSourceAndStatus() {
		return alarmsForSourceAndStatus;
	}
	
	public void setAlarmsForSourceAndStatus(final String alarmsForSourceAndStatus) {
		this.alarmsForSourceAndStatus = alarmsForSourceAndStatus;
	}

	public String getSelf() {
		return self;
	}
	
	public void setSelf(final String self) {
		this.self = self;
	}

	/**
	 * Collection of all alarms
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class Alarms {
	
		/**
		 * A URL linking to this resource.
		 */
		private String self;
	
		private Alarm[] alarms;
	
		public String getSelf() {
			return self;
		}
		
		public void setSelf(final String self) {
			this.self = self;
		}
	
		public Alarm[] getAlarms() {
			return alarms;
		}
		
		public void setAlarms(final Alarm[] alarms) {
			this.alarms = alarms;
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
			if (r != null && r instanceof Alarms) {
				Alarms comparer = (Alarms) r;
				if (String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && comparer.getAlarms().equals(this.getAlarms())) {
					return true;
				}
			}
			return false;
		}
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
		if (r != null && r instanceof AlarmsApiResource) {
			AlarmsApiResource comparer = (AlarmsApiResource) r;
			if (comparer.getAlarms().equals(this.getAlarms()) && String.valueOf(comparer.getAlarmsForSource()).equals(String.valueOf(this.getAlarmsForSource())) && String.valueOf(comparer.getAlarmsForStatus()).equals(String.valueOf(this.getAlarmsForStatus())) && String.valueOf(comparer.getAlarmsForSourceAndStatusAndTime()).equals(String.valueOf(this.getAlarmsForSourceAndStatusAndTime())) && String.valueOf(comparer.getAlarmsForStatusAndTime()).equals(String.valueOf(this.getAlarmsForStatusAndTime())) && String.valueOf(comparer.getAlarmsForSourceAndTime()).equals(String.valueOf(this.getAlarmsForSourceAndTime())) && String.valueOf(comparer.getAlarmsForTime()).equals(String.valueOf(this.getAlarmsForTime())) && String.valueOf(comparer.getAlarmsForSourceAndStatus()).equals(String.valueOf(this.getAlarmsForSourceAndStatus())) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf()))) {
				return true;
			}
		}
		return false;
	}
}
