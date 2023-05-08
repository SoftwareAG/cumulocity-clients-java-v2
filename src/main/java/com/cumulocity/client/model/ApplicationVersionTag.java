// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ApplicationVersionTag {

	/**
	 * <p>Tag assigned to the version. Version tags must be unique across all versions and version fields of application versions.</p>
	 */
	private String[] tag;

	public ApplicationVersionTag() {
	}

	public ApplicationVersionTag(final String[] tag) {
		this.tag = tag;
	}

	public String[] getTag() {
		return tag;
	}
	
	public void setTag(final String[] tag) {
		this.tag = tag;
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
		if (r != null && r instanceof ApplicationVersionTag) {
			ApplicationVersionTag comparer = (ApplicationVersionTag) r;
			if (comparer.getTag().equals(this.getTag())) {
				return true;
			}
		}
		return false;
	}
}
