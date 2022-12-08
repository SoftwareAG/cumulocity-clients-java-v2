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
 * Represents a predicate for verification. It acts as a condition which is necessary to assign a user to the given groups and permit access to the specified applications.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class JSONPredicateRepresentation {

	/**
	 * Nested predicates.
	 */
	private JSONPredicateRepresentation[] childPredicates;

	/**
	 * Operator executed on the parameter from the JWT access token claim pointed by `parameterPath` and the provided parameter `value`.
	 */
	private Operator operator;

	/**
	 * Path to the claim from the JWT access token from the external authorization server.
	 */
	private String parameterPath;

	/**
	 * Given value used for parameter verification.
	 */
	private String value;

	public JSONPredicateRepresentation[] getChildPredicates() {
		return childPredicates;
	}
	
	public void setChildPredicates(final JSONPredicateRepresentation[] childPredicates) {
		this.childPredicates = childPredicates;
	}

	public Operator getOperator() {
		return operator;
	}
	
	public void setOperator(final Operator operator) {
		this.operator = operator;
	}

	public String getParameterPath() {
		return parameterPath;
	}
	
	public void setParameterPath(final String parameterPath) {
		this.parameterPath = parameterPath;
	}

	public String getValue() {
		return value;
	}
	
	public void setValue(final String value) {
		this.value = value;
	}

	
	/**
	 * Operator executed on the parameter from the JWT access token claim pointed by `parameterPath` and the provided parameter `value`.
	 * [EQ, NEQ, GT, LT, GTE, LTE, IN, AND, OR]
	 */
	public enum Operator {
		@JsonProperty("EQ")
		EQ("EQ"),
		@JsonProperty("NEQ")
		NEQ("NEQ"),
		@JsonProperty("GT")
		GT("GT"),
		@JsonProperty("LT")
		LT("LT"),
		@JsonProperty("GTE")
		GTE("GTE"),
		@JsonProperty("LTE")
		LTE("LTE"),
		@JsonProperty("IN")
		IN("IN"),
		@JsonProperty("AND")
		AND("AND"),
		@JsonProperty("OR")
		OR("OR");
	
		private String value;
	
		Operator(final String value) {
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
		if (r != null && r instanceof JSONPredicateRepresentation) {
			JSONPredicateRepresentation comparer = (JSONPredicateRepresentation) r;
			if (comparer.getChildPredicates().equals(this.getChildPredicates()) && comparer.getOperator().equals(this.getOperator()) && String.valueOf(comparer.getParameterPath()).equals(String.valueOf(this.getParameterPath())) && String.valueOf(comparer.getValue()).equals(String.valueOf(this.getValue()))) {
				return true;
			}
		}
		return false;
	}
}
