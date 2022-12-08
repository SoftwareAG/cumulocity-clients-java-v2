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
public class SummaryAllTenantsUsageStatistics {

	/**
	 * Number of created alarms.
	 */
	private int alarmsCreatedCount;

	/**
	 * Number of updates made to the alarms.
	 */
	private int alarmsUpdatedCount;

	/**
	 * Date and time of the tenant's creation.
	 */
	private String creationTime;

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
	 * Tenant external reference.
	 */
	private String externalReference;

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
	 * ID of the parent tenant.
	 */
	private String parentTenantId;

	/**
	 * Peak value of `deviceCount` calculated for the requested time period of the summary.
	 */
	private int peakDeviceCount;

	/**
	 * Peak value of `deviceWithChildrenCount` calculated for the requested time period of the summary.
	 */
	private int peakDeviceWithChildrenCount;

	/**
	 * Peak value of used storage size in bytes, calculated for the requested time period of the summary.
	 */
	private int peakStorageSize;

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
	 * The tenant's company name.
	 */
	private String tenantCompany;

	/**
	 * An object with a list of custom properties.
	 */
	private CustomProperties tenantCustomProperties;

	/**
	 * URL of the tenant's domain. The domain name permits only the use of alphanumeric characters separated by dots `.`, hyphens `-` and underscores `_`.
	 */
	private String tenantDomain;

	/**
	 * Unique identifier of a Cumulocity IoT tenant.
	 */
	private String tenantId;

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

	public String getCreationTime() {
		return creationTime;
	}
	
	public void setCreationTime(final String creationTime) {
		this.creationTime = creationTime;
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

	public String getExternalReference() {
		return externalReference;
	}
	
	public void setExternalReference(final String externalReference) {
		this.externalReference = externalReference;
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

	public String getParentTenantId() {
		return parentTenantId;
	}
	
	public void setParentTenantId(final String parentTenantId) {
		this.parentTenantId = parentTenantId;
	}

	public int getPeakDeviceCount() {
		return peakDeviceCount;
	}
	
	public void setPeakDeviceCount(final int peakDeviceCount) {
		this.peakDeviceCount = peakDeviceCount;
	}

	public int getPeakDeviceWithChildrenCount() {
		return peakDeviceWithChildrenCount;
	}
	
	public void setPeakDeviceWithChildrenCount(final int peakDeviceWithChildrenCount) {
		this.peakDeviceWithChildrenCount = peakDeviceWithChildrenCount;
	}

	public int getPeakStorageSize() {
		return peakStorageSize;
	}
	
	public void setPeakStorageSize(final int peakStorageSize) {
		this.peakStorageSize = peakStorageSize;
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

	public String getTenantCompany() {
		return tenantCompany;
	}
	
	public void setTenantCompany(final String tenantCompany) {
		this.tenantCompany = tenantCompany;
	}

	public CustomProperties getTenantCustomProperties() {
		return tenantCustomProperties;
	}
	
	public void setTenantCustomProperties(final CustomProperties tenantCustomProperties) {
		this.tenantCustomProperties = tenantCustomProperties;
	}

	public String getTenantDomain() {
		return tenantDomain;
	}
	
	public void setTenantDomain(final String tenantDomain) {
		this.tenantDomain = tenantDomain;
	}

	public String getTenantId() {
		return tenantId;
	}
	
	public void setTenantId(final String tenantId) {
		this.tenantId = tenantId;
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
			// TODO thats an extensive operation, which only helps debugging
			return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
		} catch (final JsonProcessingException e) {
		}
		return super.toString();
	}

	@Override
	public boolean equals(final Object r) {
		if (r != null && r instanceof SummaryAllTenantsUsageStatistics) {
			SummaryAllTenantsUsageStatistics comparer = (SummaryAllTenantsUsageStatistics) r;
			if (Integer.valueOf(comparer.getAlarmsCreatedCount()).equals(Integer.valueOf(this.getAlarmsCreatedCount())) && Integer.valueOf(comparer.getAlarmsUpdatedCount()).equals(Integer.valueOf(this.getAlarmsUpdatedCount())) && String.valueOf(comparer.getCreationTime()).equals(String.valueOf(this.getCreationTime())) && Integer.valueOf(comparer.getDeviceCount()).equals(Integer.valueOf(this.getDeviceCount())) && Integer.valueOf(comparer.getDeviceEndpointCount()).equals(Integer.valueOf(this.getDeviceEndpointCount())) && Integer.valueOf(comparer.getDeviceRequestCount()).equals(Integer.valueOf(this.getDeviceRequestCount())) && Integer.valueOf(comparer.getDeviceWithChildrenCount()).equals(Integer.valueOf(this.getDeviceWithChildrenCount())) && String.valueOf(comparer.getExternalReference()).equals(String.valueOf(this.getExternalReference())) && Integer.valueOf(comparer.getEventsCreatedCount()).equals(Integer.valueOf(this.getEventsCreatedCount())) && Integer.valueOf(comparer.getEventsUpdatedCount()).equals(Integer.valueOf(this.getEventsUpdatedCount())) && Integer.valueOf(comparer.getInventoriesCreatedCount()).equals(Integer.valueOf(this.getInventoriesCreatedCount())) && Integer.valueOf(comparer.getInventoriesUpdatedCount()).equals(Integer.valueOf(this.getInventoriesUpdatedCount())) && Integer.valueOf(comparer.getMeasurementsCreatedCount()).equals(Integer.valueOf(this.getMeasurementsCreatedCount())) && String.valueOf(comparer.getParentTenantId()).equals(String.valueOf(this.getParentTenantId())) && Integer.valueOf(comparer.getPeakDeviceCount()).equals(Integer.valueOf(this.getPeakDeviceCount())) && Integer.valueOf(comparer.getPeakDeviceWithChildrenCount()).equals(Integer.valueOf(this.getPeakDeviceWithChildrenCount())) && Integer.valueOf(comparer.getPeakStorageSize()).equals(Integer.valueOf(this.getPeakStorageSize())) && Integer.valueOf(comparer.getRequestCount()).equals(Integer.valueOf(this.getRequestCount())) && comparer.getResources().equals(this.getResources()) && Integer.valueOf(comparer.getStorageSize()).equals(Integer.valueOf(this.getStorageSize())) && comparer.getSubscribedApplications().equals(this.getSubscribedApplications()) && String.valueOf(comparer.getTenantCompany()).equals(String.valueOf(this.getTenantCompany())) && comparer.getTenantCustomProperties().equals(this.getTenantCustomProperties()) && String.valueOf(comparer.getTenantDomain()).equals(String.valueOf(this.getTenantDomain())) && String.valueOf(comparer.getTenantId()).equals(String.valueOf(this.getTenantId())) && Integer.valueOf(comparer.getTotalResourceCreateAndUpdateCount()).equals(Integer.valueOf(this.getTotalResourceCreateAndUpdateCount()))) {
				return true;
			}
		}
		return false;
	}
}
