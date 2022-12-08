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
public class IdentityApiResource {

	/**
	 * Single external ID, represented by the type and the value of the external ID.
	 */
	private String externalId;

	/**
	 * Represents a collection of external IDs for a specified global ID.
	 */
	private String externalIdsOfGlobalId;

	/**
	 * A URL linking to this resource.
	 */
	private String self;

	public String getExternalId() {
		return externalId;
	}
	
	public void setExternalId(final String externalId) {
		this.externalId = externalId;
	}

	public String getExternalIdsOfGlobalId() {
		return externalIdsOfGlobalId;
	}
	
	public void setExternalIdsOfGlobalId(final String externalIdsOfGlobalId) {
		this.externalIdsOfGlobalId = externalIdsOfGlobalId;
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
		if (r != null && r instanceof IdentityApiResource) {
			IdentityApiResource comparer = (IdentityApiResource) r;
			if (String.valueOf(comparer.getExternalId()).equals(String.valueOf(this.getExternalId())) && String.valueOf(comparer.getExternalIdsOfGlobalId()).equals(String.valueOf(this.getExternalIdsOfGlobalId())) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf()))) {
				return true;
			}
		}
		return false;
	}
}
