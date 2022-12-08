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
public class ApplicationManifestProbe {

	/**
	 * The probe failure threshold.
	 */
	private int failureThreshold;

	/**
	 * The probe period in seconds.
	 */
	private int periodSeconds;

	/**
	 * The probe timeout in seconds.
	 */
	private int timeoutSeconds;

	/**
	 * The probe success threshold.
	 */
	private int successThreshold;

	/**
	 * The probe's initial delay in seconds.
	 */
	private int initialDelaySeconds;

	/**
	 * The probe's HTTP GET method information.
	 */
	private HttpGet httpGet;

	public int getFailureThreshold() {
		return failureThreshold;
	}
	
	public void setFailureThreshold(final int failureThreshold) {
		this.failureThreshold = failureThreshold;
	}

	public int getPeriodSeconds() {
		return periodSeconds;
	}
	
	public void setPeriodSeconds(final int periodSeconds) {
		this.periodSeconds = periodSeconds;
	}

	public int getTimeoutSeconds() {
		return timeoutSeconds;
	}
	
	public void setTimeoutSeconds(final int timeoutSeconds) {
		this.timeoutSeconds = timeoutSeconds;
	}

	public int getSuccessThreshold() {
		return successThreshold;
	}
	
	public void setSuccessThreshold(final int successThreshold) {
		this.successThreshold = successThreshold;
	}

	public int getInitialDelaySeconds() {
		return initialDelaySeconds;
	}
	
	public void setInitialDelaySeconds(final int initialDelaySeconds) {
		this.initialDelaySeconds = initialDelaySeconds;
	}

	public HttpGet getHttpGet() {
		return httpGet;
	}
	
	public void setHttpGet(final HttpGet httpGet) {
		this.httpGet = httpGet;
	}

	/**
	 * The probe's HTTP GET method information.
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class HttpGet {
	
		/**
		 * The HTTP path.
		 */
		private String path;
	
		/**
		 * The HTTP port.
		 */
		private int port;
	
		public String getPath() {
			return path;
		}
		
		public void setPath(final String path) {
			this.path = path;
		}
	
		public int getPort() {
			return port;
		}
		
		public void setPort(final int port) {
			this.port = port;
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
			if (r != null && r instanceof HttpGet) {
				HttpGet comparer = (HttpGet) r;
				if (String.valueOf(comparer.getPath()).equals(String.valueOf(this.getPath())) && Integer.valueOf(comparer.getPort()).equals(Integer.valueOf(this.getPort()))) {
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
		if (r != null && r instanceof ApplicationManifestProbe) {
			ApplicationManifestProbe comparer = (ApplicationManifestProbe) r;
			if (Integer.valueOf(comparer.getFailureThreshold()).equals(Integer.valueOf(this.getFailureThreshold())) && Integer.valueOf(comparer.getPeriodSeconds()).equals(Integer.valueOf(this.getPeriodSeconds())) && Integer.valueOf(comparer.getTimeoutSeconds()).equals(Integer.valueOf(this.getTimeoutSeconds())) && Integer.valueOf(comparer.getSuccessThreshold()).equals(Integer.valueOf(this.getSuccessThreshold())) && Integer.valueOf(comparer.getInitialDelaySeconds()).equals(Integer.valueOf(this.getInitialDelaySeconds())) && comparer.getHttpGet().equals(this.getHttpGet())) {
				return true;
			}
		}
		return false;
	}
}
