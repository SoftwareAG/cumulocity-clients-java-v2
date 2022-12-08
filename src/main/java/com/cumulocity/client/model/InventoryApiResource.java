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
public class InventoryApiResource {

	/**
	 * Read-only collection of all managed objects with a particular fragment type or capability (placeholder {fragmentType}).
	 */
	private String managedObjectsForFragmentType;

	/**
	 * Read-only collection of all managed objects of a particular type (placeholder {type}).
	 */
	private String managedObjectsForType;

	/**
	 * Read-only collection of managed objects fetched for a given list of ids, for example, “ids=41,43,68”.
	 */
	private String managedObjectsForListOfIds;

	/**
	 * Collection of all managed objects
	 */
	private ManagedObjects managedObjects;

	/**
	 * A URL linking to this resource.
	 */
	private String self;

	public String getManagedObjectsForFragmentType() {
		return managedObjectsForFragmentType;
	}
	
	public void setManagedObjectsForFragmentType(final String managedObjectsForFragmentType) {
		this.managedObjectsForFragmentType = managedObjectsForFragmentType;
	}

	public String getManagedObjectsForType() {
		return managedObjectsForType;
	}
	
	public void setManagedObjectsForType(final String managedObjectsForType) {
		this.managedObjectsForType = managedObjectsForType;
	}

	public String getManagedObjectsForListOfIds() {
		return managedObjectsForListOfIds;
	}
	
	public void setManagedObjectsForListOfIds(final String managedObjectsForListOfIds) {
		this.managedObjectsForListOfIds = managedObjectsForListOfIds;
	}

	public ManagedObjects getManagedObjects() {
		return managedObjects;
	}
	
	public void setManagedObjects(final ManagedObjects managedObjects) {
		this.managedObjects = managedObjects;
	}

	public String getSelf() {
		return self;
	}
	
	public void setSelf(final String self) {
		this.self = self;
	}

	/**
	 * Collection of all managed objects
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class ManagedObjects {
	
		/**
		 * An array containing the referenced managed objects.
		 */
		private ManagedObject[] references;
	
		/**
		 * A URL linking to this resource.
		 */
		private String self;
	
		public ManagedObject[] getReferences() {
			return references;
		}
		
		public void setReferences(final ManagedObject[] references) {
			this.references = references;
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
			if (r != null && r instanceof ManagedObjects) {
				ManagedObjects comparer = (ManagedObjects) r;
				if (comparer.getReferences().equals(this.getReferences()) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf()))) {
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
		if (r != null && r instanceof InventoryApiResource) {
			InventoryApiResource comparer = (InventoryApiResource) r;
			if (String.valueOf(comparer.getManagedObjectsForFragmentType()).equals(String.valueOf(this.getManagedObjectsForFragmentType())) && String.valueOf(comparer.getManagedObjectsForType()).equals(String.valueOf(this.getManagedObjectsForType())) && String.valueOf(comparer.getManagedObjectsForListOfIds()).equals(String.valueOf(this.getManagedObjectsForListOfIds())) && comparer.getManagedObjects().equals(this.getManagedObjects()) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf()))) {
				return true;
			}
		}
		return false;
	}
}
