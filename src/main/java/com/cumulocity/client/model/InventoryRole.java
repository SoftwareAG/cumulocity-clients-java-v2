// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * An inventory role.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class InventoryRole {

	/**
	 * A description for this inventory role.
	 */
	private String description;

	/**
	 * A unique identifier for this inventory role.
	 */
	private int id;

	/**
	 * The name of this inventory role.
	 */
	private String name;

	/**
	 * A set of permissions for this inventory role.
	 */
	private InventoryRolePermission[] permissions;

	/**
	 * A URL linking to this resource.
	 */
	private String self;

	public String getDescription() {
		return description;
	}
	
	public void setDescription(final String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}
	
	public void setId(final int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(final String name) {
		this.name = name;
	}

	public InventoryRolePermission[] getPermissions() {
		return permissions;
	}
	
	public void setPermissions(final InventoryRolePermission[] permissions) {
		this.permissions = permissions;
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
			// TODO thats an extensive operation, which only helps debugging
			return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
		} catch (final JsonProcessingException e) {
		}
		return super.toString();
	}

	@Override
	public boolean equals(final Object r) {
		if (r != null && r instanceof InventoryRole) {
			InventoryRole comparer = (InventoryRole) r;
			if (String.valueOf(comparer.getDescription()).equals(String.valueOf(this.getDescription())) && Integer.valueOf(comparer.getId()).equals(Integer.valueOf(this.getId())) && String.valueOf(comparer.getName()).equals(String.valueOf(this.getName())) && comparer.getPermissions().equals(this.getPermissions()) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf()))) {
				return true;
			}
		}
		return false;
	}
}
