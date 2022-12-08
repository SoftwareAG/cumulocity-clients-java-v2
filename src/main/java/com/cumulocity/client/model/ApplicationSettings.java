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
public class ApplicationSettings {

	/**
	 * The name of the setting.
	 */
	private String key;

	/**
	 * The value schema determines the values that the microservice can process.
	 */
	private ValueSchema valueSchema;

	/**
	 * The default value.
	 */
	private String defaultValue;

	/**
	 * Indicates if the value is editable.
	 */
	private boolean editable;

	/**
	 * Indicated wether this setting is inherited.
	 */
	private boolean inheritFromOwner;

	public String getKey() {
		return key;
	}
	
	public void setKey(final String key) {
		this.key = key;
	}

	public ValueSchema getValueSchema() {
		return valueSchema;
	}
	
	public void setValueSchema(final ValueSchema valueSchema) {
		this.valueSchema = valueSchema;
	}

	public String getDefaultValue() {
		return defaultValue;
	}
	
	public void setDefaultValue(final String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public boolean getEditable() {
		return editable;
	}
	
	public void setEditable(final boolean editable) {
		this.editable = editable;
	}

	public boolean getInheritFromOwner() {
		return inheritFromOwner;
	}
	
	public void setInheritFromOwner(final boolean inheritFromOwner) {
		this.inheritFromOwner = inheritFromOwner;
	}

	/**
	 * The value schema determines the values that the microservice can process.
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class ValueSchema {
	
		/**
		 * The value schema type.
		 */
		private String type;
	
		public String getType() {
			return type;
		}
		
		public void setType(final String type) {
			this.type = type;
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
			if (r != null && r instanceof ValueSchema) {
				ValueSchema comparer = (ValueSchema) r;
				if (String.valueOf(comparer.getType()).equals(String.valueOf(this.getType()))) {
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
		if (r != null && r instanceof ApplicationSettings) {
			ApplicationSettings comparer = (ApplicationSettings) r;
			if (String.valueOf(comparer.getKey()).equals(String.valueOf(this.getKey())) && comparer.getValueSchema().equals(this.getValueSchema()) && String.valueOf(comparer.getDefaultValue()).equals(String.valueOf(this.getDefaultValue())) && Boolean.valueOf(comparer.getEditable()).equals(Boolean.valueOf(this.getEditable())) && Boolean.valueOf(comparer.getInheritFromOwner()).equals(Boolean.valueOf(this.getInheritFromOwner()))) {
				return true;
			}
		}
		return false;
	}
}
