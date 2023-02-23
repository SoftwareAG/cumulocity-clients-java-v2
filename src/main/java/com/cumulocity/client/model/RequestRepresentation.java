// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model;

import java.util.Map;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class RequestRepresentation {

	/**
	 * Body of the request.
	 */
	private String body;

	/**
	 * Headers of the request.
	 */
	private Headers headers;

	/**
	 * HTTP request method.
	 */
	private Method method;

	/**
	 * Requested operation.
	 */
	private Operation operation;

	/**
	 * Parameters of the request.
	 */
	private RequestParams requestParams;

	/**
	 * Target of the request described as a URL.
	 */
	private String url;

	public String getBody() {
		return body;
	}
	
	public void setBody(final String body) {
		this.body = body;
	}

	public Headers getHeaders() {
		return headers;
	}
	
	public void setHeaders(final Headers headers) {
		this.headers = headers;
	}

	public Method getMethod() {
		return method;
	}
	
	public void setMethod(final Method method) {
		this.method = method;
	}

	public Operation getOperation() {
		return operation;
	}
	
	public void setOperation(final Operation operation) {
		this.operation = operation;
	}

	public RequestParams getRequestParams() {
		return requestParams;
	}
	
	public void setRequestParams(final RequestParams requestParams) {
		this.requestParams = requestParams;
	}

	public String getUrl() {
		return url;
	}
	
	public void setUrl(final String url) {
		this.url = url;
	}

	
	/**
	 * HTTP request method.
	 */
	public enum Method {
		@JsonProperty("GET")
		GET("GET"),
		@JsonProperty("POST")
		POST("POST");
	
		private String value;
	
		Method(final String value) {
			this.value = value;
		}
	
		public String getValue() {
			return value;
		}
	}

	
	/**
	 * Requested operation.
	 */
	public enum Operation {
		@JsonProperty("EXECUTE")
		EXECUTE("EXECUTE"),
		@JsonProperty("REDIRECT")
		REDIRECT("REDIRECT");
	
		private String value;
	
		Operation(final String value) {
			this.value = value;
		}
	
		public String getValue() {
			return value;
		}
	}

	/**
	 * Headers of the request.
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class Headers {
	
		/**
		 * It is possible to add an arbitrary number of headers as a list of key-value string pairs, for example, `"header": "value"`.
		 * 
		 */
		private Map<String, String> requestHeaders;
	
		public Map<String, String> getRequestHeaders() {
			return requestHeaders;
		}
		
		public void setRequestHeaders(final Map<String, String> requestHeaders) {
			this.requestHeaders = requestHeaders;
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
			if (r != null && r instanceof Headers) {
				Headers comparer = (Headers) r;
				if (comparer.getRequestHeaders().equals(this.getRequestHeaders())) {
					return true;
				}
			}
			return false;
		}
	}



	/**
	 * Parameters of the request.
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class RequestParams {
	
		/**
		 * It is possible to add an arbitrary number of parameters as a list of key-value string pairs, for example, `"parameter": "value"`.
		 * 
		 */
		private Map<String, String> requestParameters;
	
		public Map<String, String> getRequestParameters() {
			return requestParameters;
		}
		
		public void setRequestParameters(final Map<String, String> requestParameters) {
			this.requestParameters = requestParameters;
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
			if (r != null && r instanceof RequestParams) {
				RequestParams comparer = (RequestParams) r;
				if (comparer.getRequestParameters().equals(this.getRequestParameters())) {
					return true;
				}
			}
			return false;
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
		if (r != null && r instanceof RequestRepresentation) {
			RequestRepresentation comparer = (RequestRepresentation) r;
			if (String.valueOf(comparer.getBody()).equals(String.valueOf(this.getBody())) && comparer.getHeaders().equals(this.getHeaders()) && comparer.getMethod().equals(this.getMethod()) && comparer.getOperation().equals(this.getOperation()) && comparer.getRequestParams().equals(this.getRequestParams()) && String.valueOf(comparer.getUrl()).equals(String.valueOf(this.getUrl()))) {
				return true;
			}
		}
		return false;
	}
}
