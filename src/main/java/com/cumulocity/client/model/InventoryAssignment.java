// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * An inventory assignment.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class InventoryAssignment {

	/**
	 * A unique identifier for this inventory assignment.
	 */
	private int id;

	/**
	 * A unique identifier for the managed object for which the roles are assigned.
	 */
	private String managedObject;

	/**
	 * An array of roles that are assigned to the managed object for the user.
	 */
	private InventoryRole[] roles;

	/**
	 * A URL linking to this resource.
	 */
	private String self;

	public int getId() {
		return id;
	}
	
	public void setId(final int id) {
		this.id = id;
	}

	public String getManagedObject() {
		return managedObject;
	}
	
	public void setManagedObject(final String managedObject) {
		this.managedObject = managedObject;
	}

	public InventoryRole[] getRoles() {
		return roles;
	}
	
	public void setRoles(final InventoryRole[] roles) {
		this.roles = roles;
	}

	public String getSelf() {
		return self;
	}
	
	public void setSelf(final String self) {
		this.self = self;
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
		if (r != null && r instanceof InventoryAssignment) {
			InventoryAssignment comparer = (InventoryAssignment) r;
			if (Integer.valueOf(comparer.getId()).equals(Integer.valueOf(this.getId())) && String.valueOf(comparer.getManagedObject()).equals(String.valueOf(this.getManagedObject())) && comparer.getRoles().equals(this.getRoles()) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf()))) {
				return true;
			}
		}
		return false;
	}
}
