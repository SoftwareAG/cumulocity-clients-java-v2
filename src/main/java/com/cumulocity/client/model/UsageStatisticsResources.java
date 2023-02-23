// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Resources usage for each subscribed microservice application.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class UsageStatisticsResources {

	/**
	 * Total number of CPU usage for tenant microservices, specified in CPU milliseconds (1000m = 1 CPU).
	 */
	private int cpu;

	/**
	 * Total number of memory usage for tenant microservices, specified in MB.
	 */
	private int memory;

	/**
	 * Collection of resources usage for each microservice.
	 */
	private UsageStatisticsResourcesUsedBy[] usedBy;

	public int getCpu() {
		return cpu;
	}
	
	public void setCpu(final int cpu) {
		this.cpu = cpu;
	}

	public int getMemory() {
		return memory;
	}
	
	public void setMemory(final int memory) {
		this.memory = memory;
	}

	public UsageStatisticsResourcesUsedBy[] getUsedBy() {
		return usedBy;
	}
	
	public void setUsedBy(final UsageStatisticsResourcesUsedBy[] usedBy) {
		this.usedBy = usedBy;
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
		if (r != null && r instanceof UsageStatisticsResources) {
			UsageStatisticsResources comparer = (UsageStatisticsResources) r;
			if (Integer.valueOf(comparer.getCpu()).equals(Integer.valueOf(this.getCpu())) && Integer.valueOf(comparer.getMemory()).equals(Integer.valueOf(this.getMemory())) && comparer.getUsedBy().equals(this.getUsedBy())) {
				return true;
			}
		}
		return false;
	}
}
