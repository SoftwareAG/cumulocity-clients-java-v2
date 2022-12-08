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
 * A permission object of an inventory role.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class InventoryRolePermission {

	/**
	 * A unique identifier for this permission.
	 */
	private int id;

	/**
	 * The permission level.
	 */
	private Permission permission;

	/**
	 * The scope of this permission.
	 */
	private Scope scope;

	/**
	 * The type of this permission. It can be the name of a fragment, for example, `c8y_Restart`.
	 */
	private String type;

	public int getId() {
		return id;
	}
	
	public void setId(final int id) {
		this.id = id;
	}

	public Permission getPermission() {
		return permission;
	}
	
	public void setPermission(final Permission permission) {
		this.permission = permission;
	}

	public Scope getScope() {
		return scope;
	}
	
	public void setScope(final Scope scope) {
		this.scope = scope;
	}

	public String getType() {
		return type;
	}
	
	public void setType(final String type) {
		this.type = type;
	}

	
	/**
	 * The permission level.
	 * [ADMIN, READ, *]
	 */
	public enum Permission {
		@JsonProperty("ADMIN")
		ADMIN("ADMIN"),
		@JsonProperty("READ")
		READ("READ"),
		@JsonProperty("*")
		ALL("*");
	
		private String value;
	
		Permission(final String value) {
			this.value = value;
		}
	
		public String getValue() {
			return value;
		}
	}

	
	/**
	 * The scope of this permission.
	 * [ALARM, AUDIT, EVENT, MANAGED_OBJECT, MEASUREMENT, OPERATION, *]
	 */
	public enum Scope {
		@JsonProperty("ALARM")
		ALARM("ALARM"),
		@JsonProperty("AUDIT")
		AUDIT("AUDIT"),
		@JsonProperty("EVENT")
		EVENT("EVENT"),
		@JsonProperty("MANAGED_OBJECT")
		MANAGEDOBJECT("MANAGED_OBJECT"),
		@JsonProperty("MEASUREMENT")
		MEASUREMENT("MEASUREMENT"),
		@JsonProperty("OPERATION")
		OPERATION("OPERATION"),
		@JsonProperty("*")
		ALL("*");
	
		private String value;
	
		Scope(final String value) {
			this.value = value;
		}
	
		public String getValue() {
			return value;
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
		if (r != null && r instanceof InventoryRolePermission) {
			InventoryRolePermission comparer = (InventoryRolePermission) r;
			if (Integer.valueOf(comparer.getId()).equals(Integer.valueOf(this.getId())) && comparer.getPermission().equals(this.getPermission()) && comparer.getScope().equals(this.getScope()) && String.valueOf(comparer.getType()).equals(String.valueOf(this.getType()))) {
				return true;
			}
		}
		return false;
	}
}
