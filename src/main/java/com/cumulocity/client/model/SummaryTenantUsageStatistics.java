// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Summary of the usage statistics.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class SummaryTenantUsageStatistics {

	/**
	 * Number of created alarms.
	 */
	private int alarmsCreatedCount;

	/**
	 * Number of updates made to the alarms.
	 */
	private int alarmsUpdatedCount;

	/**
	 * Date of this usage statistics summary.
	 */
	private String day;

	/**
	 * Number of devices in the tenant identified by the fragment `c8y_IsDevice`. Updated only three times a day starting at 8:57, 16:57 and 23:57.
	 */
	private int deviceCount;

	/**
	 * Number of devices which do not have child devices. Updated only three times a day starting at 8:57, 16:57 and 23:57.
	 */
	private int deviceEndpointCount;

	/**
	 * Number of requests that were issued only by devices against the tenant. Updated every 5 minutes. The following requests are not included:
	 * 
	 * * Requests made to <kbd>/user</kbd>, <kbd>/tenant</kbd> and <kbd>/application</kbd> APIs
	 * * Application related requests (with `X-Cumulocity-Application-Key` header)
	 * 
	 */
	private int deviceRequestCount;

	/**
	 * Number of devices with children. Updated only three times a day starting at 8:57, 16:57 and 23:57.
	 */
	private int deviceWithChildrenCount;

	/**
	 * Number of created events.
	 */
	private int eventsCreatedCount;

	/**
	 * Number of updates made to the events.
	 */
	private int eventsUpdatedCount;

	/**
	 * Number of created managed objects.
	 */
	private int inventoriesCreatedCount;

	/**
	 * Number of updates made to the managed objects.
	 */
	private int inventoriesUpdatedCount;

	/**
	 * Number of created measurements.
	 * 
	 * > **&#9432; Info:** Bulk creation of measurements is handled in a way that each measurement is counted individually.
	 * 
	 */
	private int measurementsCreatedCount;

	/**
	 * Number of requests that were made against the tenant. Updated every 5 minutes. The following requests are not included:
	 * 
	 * *  Internal SmartREST requests used to resolve templates
	 * *  Internal SLA monitoring requests
	 * *  Calls to any <kbd>/health</kbd> endpoint
	 * *  Device bootstrap process requests related to configuring and retrieving device credentials
	 * *  Microservice SDK internal calls for applications and subscriptions
	 * 
	 */
	private int requestCount;

	/**
	 * Resources usage for each subscribed microservice application.
	 */
	private UsageStatisticsResources resources;

	/**
	 * Database storage in use, specified in bytes. It is affected by your retention rules and by the regularly running database optimization functions in Cumulocity IoT. If the size decreases, it does not necessarily mean that data was deleted. Updated only three times a day starting at 8:57, 16:57 and 23:57.
	 */
	private int storageSize;

	/**
	 * Names of the tenant subscribed applications. Updated only three times a day starting at 8:57, 16:57 and 23:57.
	 */
	private String[] subscribedApplications;

	/**
	 * Sum of all inbound transfers.
	 */
	private int totalResourceCreateAndUpdateCount;

	public int getAlarmsCreatedCount() {
		return alarmsCreatedCount;
	}
	
	public void setAlarmsCreatedCount(final int alarmsCreatedCount) {
		this.alarmsCreatedCount = alarmsCreatedCount;
	}

	public int getAlarmsUpdatedCount() {
		return alarmsUpdatedCount;
	}
	
	public void setAlarmsUpdatedCount(final int alarmsUpdatedCount) {
		this.alarmsUpdatedCount = alarmsUpdatedCount;
	}

	public String getDay() {
		return day;
	}
	
	public void setDay(final String day) {
		this.day = day;
	}

	public int getDeviceCount() {
		return deviceCount;
	}
	
	public void setDeviceCount(final int deviceCount) {
		this.deviceCount = deviceCount;
	}

	public int getDeviceEndpointCount() {
		return deviceEndpointCount;
	}
	
	public void setDeviceEndpointCount(final int deviceEndpointCount) {
		this.deviceEndpointCount = deviceEndpointCount;
	}

	public int getDeviceRequestCount() {
		return deviceRequestCount;
	}
	
	public void setDeviceRequestCount(final int deviceRequestCount) {
		this.deviceRequestCount = deviceRequestCount;
	}

	public int getDeviceWithChildrenCount() {
		return deviceWithChildrenCount;
	}
	
	public void setDeviceWithChildrenCount(final int deviceWithChildrenCount) {
		this.deviceWithChildrenCount = deviceWithChildrenCount;
	}

	public int getEventsCreatedCount() {
		return eventsCreatedCount;
	}
	
	public void setEventsCreatedCount(final int eventsCreatedCount) {
		this.eventsCreatedCount = eventsCreatedCount;
	}

	public int getEventsUpdatedCount() {
		return eventsUpdatedCount;
	}
	
	public void setEventsUpdatedCount(final int eventsUpdatedCount) {
		this.eventsUpdatedCount = eventsUpdatedCount;
	}

	public int getInventoriesCreatedCount() {
		return inventoriesCreatedCount;
	}
	
	public void setInventoriesCreatedCount(final int inventoriesCreatedCount) {
		this.inventoriesCreatedCount = inventoriesCreatedCount;
	}

	public int getInventoriesUpdatedCount() {
		return inventoriesUpdatedCount;
	}
	
	public void setInventoriesUpdatedCount(final int inventoriesUpdatedCount) {
		this.inventoriesUpdatedCount = inventoriesUpdatedCount;
	}

	public int getMeasurementsCreatedCount() {
		return measurementsCreatedCount;
	}
	
	public void setMeasurementsCreatedCount(final int measurementsCreatedCount) {
		this.measurementsCreatedCount = measurementsCreatedCount;
	}

	public int getRequestCount() {
		return requestCount;
	}
	
	public void setRequestCount(final int requestCount) {
		this.requestCount = requestCount;
	}

	public UsageStatisticsResources getResources() {
		return resources;
	}
	
	public void setResources(final UsageStatisticsResources resources) {
		this.resources = resources;
	}

	public int getStorageSize() {
		return storageSize;
	}
	
	public void setStorageSize(final int storageSize) {
		this.storageSize = storageSize;
	}

	public String[] getSubscribedApplications() {
		return subscribedApplications;
	}
	
	public void setSubscribedApplications(final String[] subscribedApplications) {
		this.subscribedApplications = subscribedApplications;
	}

	public int getTotalResourceCreateAndUpdateCount() {
		return totalResourceCreateAndUpdateCount;
	}
	
	public void setTotalResourceCreateAndUpdateCount(final int totalResourceCreateAndUpdateCount) {
		this.totalResourceCreateAndUpdateCount = totalResourceCreateAndUpdateCount;
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
		if (r != null && r instanceof SummaryTenantUsageStatistics) {
			SummaryTenantUsageStatistics comparer = (SummaryTenantUsageStatistics) r;
			if (Integer.valueOf(comparer.getAlarmsCreatedCount()).equals(Integer.valueOf(this.getAlarmsCreatedCount())) && Integer.valueOf(comparer.getAlarmsUpdatedCount()).equals(Integer.valueOf(this.getAlarmsUpdatedCount())) && String.valueOf(comparer.getDay()).equals(String.valueOf(this.getDay())) && Integer.valueOf(comparer.getDeviceCount()).equals(Integer.valueOf(this.getDeviceCount())) && Integer.valueOf(comparer.getDeviceEndpointCount()).equals(Integer.valueOf(this.getDeviceEndpointCount())) && Integer.valueOf(comparer.getDeviceRequestCount()).equals(Integer.valueOf(this.getDeviceRequestCount())) && Integer.valueOf(comparer.getDeviceWithChildrenCount()).equals(Integer.valueOf(this.getDeviceWithChildrenCount())) && Integer.valueOf(comparer.getEventsCreatedCount()).equals(Integer.valueOf(this.getEventsCreatedCount())) && Integer.valueOf(comparer.getEventsUpdatedCount()).equals(Integer.valueOf(this.getEventsUpdatedCount())) && Integer.valueOf(comparer.getInventoriesCreatedCount()).equals(Integer.valueOf(this.getInventoriesCreatedCount())) && Integer.valueOf(comparer.getInventoriesUpdatedCount()).equals(Integer.valueOf(this.getInventoriesUpdatedCount())) && Integer.valueOf(comparer.getMeasurementsCreatedCount()).equals(Integer.valueOf(this.getMeasurementsCreatedCount())) && Integer.valueOf(comparer.getRequestCount()).equals(Integer.valueOf(this.getRequestCount())) && comparer.getResources().equals(this.getResources()) && Integer.valueOf(comparer.getStorageSize()).equals(Integer.valueOf(this.getStorageSize())) && comparer.getSubscribedApplications().equals(this.getSubscribedApplications()) && Integer.valueOf(comparer.getTotalResourceCreateAndUpdateCount()).equals(Integer.valueOf(this.getTotalResourceCreateAndUpdateCount()))) {
				return true;
			}
		}
		return false;
	}
}
