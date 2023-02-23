// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class TrustedCertificate {

	/**
	 * Algorithm used to decode/encode the certificate.
	 */
	private String algorithmName;

	/**
	 * Indicates whether the automatic device registration is enabled or not.
	 */
	private boolean autoRegistrationEnabled;

	/**
	 * Trusted certificate in PEM format.
	 */
	private String certInPemFormat;

	/**
	 * Unique identifier of the trusted certificate.
	 */
	private String fingerprint;

	/**
	 * The name of the organization which signed the certificate.
	 */
	private String issuer;

	/**
	 * Name of the certificate.
	 */
	private String name;

	/**
	 * The end date and time of the certificate's validity.
	 */
	private String notAfter;

	/**
	 * The start date and time of the certificate's validity.
	 */
	private String notBefore;

	/**
	 * A URL linking to this resource.
	 */
	private String self;

	/**
	 * The certificate's serial number.
	 */
	private String serialNumber;

	/**
	 * Indicates if the certificate is active and can be used by the device to establish a connection to the Cumulocity IoT platform.
	 */
	private Status status;

	/**
	 * Name of the organization to which the certificate belongs.
	 */
	private String subject;

	/**
	 * Version of the X.509 certificate standard.
	 */
	private int version;

	public String getAlgorithmName() {
		return algorithmName;
	}
	
	public void setAlgorithmName(final String algorithmName) {
		this.algorithmName = algorithmName;
	}

	public boolean getAutoRegistrationEnabled() {
		return autoRegistrationEnabled;
	}
	
	public void setAutoRegistrationEnabled(final boolean autoRegistrationEnabled) {
		this.autoRegistrationEnabled = autoRegistrationEnabled;
	}

	public String getCertInPemFormat() {
		return certInPemFormat;
	}
	
	public void setCertInPemFormat(final String certInPemFormat) {
		this.certInPemFormat = certInPemFormat;
	}

	public String getFingerprint() {
		return fingerprint;
	}
	
	public void setFingerprint(final String fingerprint) {
		this.fingerprint = fingerprint;
	}

	public String getIssuer() {
		return issuer;
	}
	
	public void setIssuer(final String issuer) {
		this.issuer = issuer;
	}

	public String getName() {
		return name;
	}
	
	public void setName(final String name) {
		this.name = name;
	}

	public String getNotAfter() {
		return notAfter;
	}
	
	public void setNotAfter(final String notAfter) {
		this.notAfter = notAfter;
	}

	public String getNotBefore() {
		return notBefore;
	}
	
	public void setNotBefore(final String notBefore) {
		this.notBefore = notBefore;
	}

	public String getSelf() {
		return self;
	}
	
	public void setSelf(final String self) {
		this.self = self;
	}

	public String getSerialNumber() {
		return serialNumber;
	}
	
	public void setSerialNumber(final String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Status getStatus() {
		return status;
	}
	
	public void setStatus(final Status status) {
		this.status = status;
	}

	public String getSubject() {
		return subject;
	}
	
	public void setSubject(final String subject) {
		this.subject = subject;
	}

	public int getVersion() {
		return version;
	}
	
	public void setVersion(final int version) {
		this.version = version;
	}

	
	/**
	 * Indicates if the certificate is active and can be used by the device to establish a connection to the Cumulocity IoT platform.
	 */
	public enum Status {
		@JsonProperty("ENABLED")
		ENABLED("ENABLED"),
		@JsonProperty("DISABLED")
		DISABLED("DISABLED");
	
		private String value;
	
		Status(final String value) {
			this.value = value;
		}
	
		public String getValue() {
			return value;
		}
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
		if (r != null && r instanceof TrustedCertificate) {
			TrustedCertificate comparer = (TrustedCertificate) r;
			if (String.valueOf(comparer.getAlgorithmName()).equals(String.valueOf(this.getAlgorithmName())) && Boolean.valueOf(comparer.getAutoRegistrationEnabled()).equals(Boolean.valueOf(this.getAutoRegistrationEnabled())) && String.valueOf(comparer.getCertInPemFormat()).equals(String.valueOf(this.getCertInPemFormat())) && String.valueOf(comparer.getFingerprint()).equals(String.valueOf(this.getFingerprint())) && String.valueOf(comparer.getIssuer()).equals(String.valueOf(this.getIssuer())) && String.valueOf(comparer.getName()).equals(String.valueOf(this.getName())) && String.valueOf(comparer.getNotAfter()).equals(String.valueOf(this.getNotAfter())) && String.valueOf(comparer.getNotBefore()).equals(String.valueOf(this.getNotBefore())) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && String.valueOf(comparer.getSerialNumber()).equals(String.valueOf(this.getSerialNumber())) && comparer.getStatus().equals(this.getStatus()) && String.valueOf(comparer.getSubject()).equals(String.valueOf(this.getSubject())) && Integer.valueOf(comparer.getVersion()).equals(Integer.valueOf(this.getVersion()))) {
				return true;
			}
		}
		return false;
	}
}
