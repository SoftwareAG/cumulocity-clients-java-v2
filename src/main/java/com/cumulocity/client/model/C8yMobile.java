// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model;

import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Holds basic connectivity-related information, such as the equipment identifier of the modem (IMEI) in the device. This identifier is globally unique and often used to identify a mobile device.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class C8yMobile {

	/**
	 * The equipment identifier (IMEI) of the modem in the device.
	 */
	private String imei;

	/**
	 * The identifier of the cell in the mobile network that the device is currently connected with.
	 */
	private String cellId;

	/**
	 * The identifier of the SIM card that is currently in the device (often printed on the card).
	 */
	private String iccid;

	/**
	 * Other possible values are: `c8y_Mobile.imsi`, `c8y_Mobile.currentOperator`, `c8y_Mobile.currentBand`, `c8y_Mobile.connType`, `c8y_Mobile.rssi`, `c8y_Mobile.ecn0`, `c8y_Mobile.rcsp`, `c8y_Mobile.mnc`, `c8y_Mobile.lac` and `c8y_Mobile.msisdn`.
	 * 
	 */
	private Map<String, String> customFragments;

	public C8yMobile() {
	}

	public C8yMobile(final String imei, final String cellId, final String iccid) {
		this.imei = imei;
		this.cellId = cellId;
		this.iccid = iccid;
	}

	public String getImei() {
		return imei;
	}
	
	public void setImei(final String imei) {
		this.imei = imei;
	}

	public String getCellId() {
		return cellId;
	}
	
	public void setCellId(final String cellId) {
		this.cellId = cellId;
	}

	public String getIccid() {
		return iccid;
	}
	
	public void setIccid(final String iccid) {
		this.iccid = iccid;
	}

	public Map<String, String> getCustomFragments() {
		return customFragments;
	}
	
	public void setCustomFragments(final Map<String, String> customFragments) {
		this.customFragments = customFragments;
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
		if (r != null && r instanceof C8yMobile) {
			C8yMobile comparer = (C8yMobile) r;
			if (String.valueOf(comparer.getImei()).equals(String.valueOf(this.getImei())) && String.valueOf(comparer.getCellId()).equals(String.valueOf(this.getCellId())) && String.valueOf(comparer.getIccid()).equals(String.valueOf(this.getIccid())) && comparer.getCustomFragments().equals(this.getCustomFragments())) {
				return true;
			}
		}
		return false;
	}
}
