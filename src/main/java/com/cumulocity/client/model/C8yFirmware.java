// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Contains information on a device's firmware. In the inventory, `c8y_Firmware` represents the currently installed firmware on the device. As part of an operation, `c8y_Firmware` requests the device to install the indicated firmware. To enable firmware installation through the user interface, add `c8y_Firmware` to the list of supported operations.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class C8yFirmware {

	/**
	 * Name of the firmware.
	 */
	private String name;

	/**
	 * A version identifier of the firmware.
	 */
	private String version;

	/**
	 * A URI linking to the location to download the firmware from.
	 */
	private String url;

	public String getName() {
		return name;
	}
	
	public void setName(final String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}
	
	public void setVersion(final String version) {
		this.version = version;
	}

	public String getUrl() {
		return url;
	}
	
	public void setUrl(final String url) {
		this.url = url;
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
		if (r != null && r instanceof C8yFirmware) {
			C8yFirmware comparer = (C8yFirmware) r;
			if (String.valueOf(comparer.getName()).equals(String.valueOf(this.getName())) && String.valueOf(comparer.getVersion()).equals(String.valueOf(this.getVersion())) && String.valueOf(comparer.getUrl()).equals(String.valueOf(this.getUrl()))) {
				return true;
			}
		}
		return false;
	}
}
