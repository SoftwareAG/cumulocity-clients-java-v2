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
public class ExternalId {

	/**
	 * The identifier used in the external system that Cumulocity IoT interfaces with.
	 */
	private String externalId;

	/**
	 * The managed object linked to the external ID.
	 */
	private ManagedObject managedObject;

	/**
	 * A URL linking to this resource.
	 */
	private String self;

	/**
	 * The type of the external identifier.
	 */
	private String type;

	public ExternalId() {
	}

	public ExternalId(final String externalId, final String type) {
		this.externalId = externalId;
		this.type = type;
	}

	public String getExternalId() {
		return externalId;
	}
	
	public void setExternalId(final String externalId) {
		this.externalId = externalId;
	}

	public ManagedObject getManagedObject() {
		return managedObject;
	}
	
	public void setManagedObject(final ManagedObject managedObject) {
		this.managedObject = managedObject;
	}

	public String getSelf() {
		return self;
	}
	
	public void setSelf(final String self) {
		this.self = self;
	}

	public String getType() {
		return type;
	}
	
	public void setType(final String type) {
		this.type = type;
	}

	/**
	 * The managed object linked to the external ID.
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class ManagedObject {
	
		/**
		 * Unique identifier of the object.
		 */
		private String id;
	
		/**
		 * A URL linking to this resource.
		 */
		private String self;
	
		public String getId() {
			return id;
		}
		
		public void setId(final String id) {
			this.id = id;
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
			if (r != null && r instanceof ManagedObject) {
				ManagedObject comparer = (ManagedObject) r;
				if (String.valueOf(comparer.getId()).equals(String.valueOf(this.getId())) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf()))) {
					return true;
				}
			}
			return false;
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
		if (r != null && r instanceof ExternalId) {
			ExternalId comparer = (ExternalId) r;
			if (String.valueOf(comparer.getExternalId()).equals(String.valueOf(this.getExternalId())) && comparer.getManagedObject().equals(this.getManagedObject()) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && String.valueOf(comparer.getType()).equals(String.valueOf(this.getType()))) {
				return true;
			}
		}
		return false;
	}
}
