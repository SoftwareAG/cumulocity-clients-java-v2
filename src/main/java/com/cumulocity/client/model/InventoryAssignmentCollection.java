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
public class InventoryAssignmentCollection {

	/**
	 * A URL linking to this resource.
	 */
	private String self;

	/**
	 * An array of inventory assignments.
	 */
	private InventoryAssignment[] inventoryAssignments;

	public String getSelf() {
		return self;
	}
	
	public void setSelf(final String self) {
		this.self = self;
	}

	public InventoryAssignment[] getInventoryAssignments() {
		return inventoryAssignments;
	}
	
	public void setInventoryAssignments(final InventoryAssignment[] inventoryAssignments) {
		this.inventoryAssignments = inventoryAssignments;
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
		if (r != null && r instanceof InventoryAssignmentCollection) {
			InventoryAssignmentCollection comparer = (InventoryAssignmentCollection) r;
			if (String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && comparer.getInventoryAssignments().equals(this.getInventoryAssignments())) {
				return true;
			}
		}
		return false;
	}
}
