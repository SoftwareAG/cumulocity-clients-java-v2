// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * A tuple storing tenant configuration.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Option {

	/**
	 * Name of the option category.
	 */
	private String category;

	/**
	 * A unique identifier for this option.
	 */
	private String key;

	/**
	 * Value of this option.
	 */
	private String value;

	/**
	 * A URL linking to this resource.
	 */
	private String self;

	public String getCategory() {
		return category;
	}
	
	public void setCategory(final String category) {
		this.category = category;
	}

	public String getKey() {
		return key;
	}
	
	public void setKey(final String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}
	
	public void setValue(final String value) {
		this.value = value;
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
		if (r != null && r instanceof Option) {
			Option comparer = (Option) r;
			if (String.valueOf(comparer.getCategory()).equals(String.valueOf(this.getCategory())) && String.valueOf(comparer.getKey()).equals(String.valueOf(this.getKey())) && String.valueOf(comparer.getValue()).equals(String.valueOf(this.getValue())) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf()))) {
				return true;
			}
		}
		return false;
	}
}
