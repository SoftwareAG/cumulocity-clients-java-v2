// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
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
public class NotificationSubscription {

	/**
	 * <p>The context within which the subscription is to be processed.</p>
	 * <blockquote>
	 * <p><strong>ⓘ Info:</strong> If the value is <code>mo</code>, then <code>source</code> must also be provided in the request body.</p>
	 * </blockquote>
	 */
	private Context context;

	/**
	 * <p>Transforms the data to <em>only</em> include specified custom fragments. Each custom fragment is identified by a unique name. If nothing is specified here, the data is forwarded as-is.</p>
	 */
	private String[] fragmentsToCopy;

	/**
	 * <p>Unique identifier of the subscription.</p>
	 */
	private String id;

	/**
	 * <p>A URL linking to this resource.</p>
	 */
	private String self;

	/**
	 * <p>The managed object to which the subscription is associated.</p>
	 */
	private Source source;

	/**
	 * <p>The subscription name. Each subscription is identified by a unique name within a specific context.</p>
	 */
	private String subscription;

	/**
	 * <p>Applicable filters to the subscription.</p>
	 */
	private SubscriptionFilter subscriptionFilter;

	public NotificationSubscription() {
	}

	public NotificationSubscription(final Context context, final String subscription) {
		this.context = context;
		this.subscription = subscription;
	}

	public Context getContext() {
		return context;
	}
	
	public void setContext(final Context context) {
		this.context = context;
	}

	public String[] getFragmentsToCopy() {
		return fragmentsToCopy;
	}
	
	public void setFragmentsToCopy(final String[] fragmentsToCopy) {
		this.fragmentsToCopy = fragmentsToCopy;
	}

	public String getId() {
		return id;
	}
	
	public void setId(final String id) {
		this.id = id;
	}

	public String getSelf() {
		return self;
	}
	
	public void setSelf(final String self) {
		this.self = self;
	}

	public Source getSource() {
		return source;
	}
	
	public void setSource(final Source source) {
		this.source = source;
	}

	public String getSubscription() {
		return subscription;
	}
	
	public void setSubscription(final String subscription) {
		this.subscription = subscription;
	}

	public SubscriptionFilter getSubscriptionFilter() {
		return subscriptionFilter;
	}
	
	public void setSubscriptionFilter(final SubscriptionFilter subscriptionFilter) {
		this.subscriptionFilter = subscriptionFilter;
	}

	
	/**
	 * <p>The context within which the subscription is to be processed.</p>
	 * <blockquote>
	 * <p><strong>ⓘ Info:</strong> If the value is <code>mo</code>, then <code>source</code> must also be provided in the request body.</p>
	 * </blockquote>
	 */
	public enum Context {
		@JsonProperty("mo")
		MO("mo"),
		@JsonProperty("tenant")
		TENANT("tenant");
	
		private String value;
	
		Context(final String value) {
			this.value = value;
		}
	
		public String getValue() {
			return value;
		}
	}


	/**
	 * <p>The managed object to which the subscription is associated.</p>
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class Source {
	
		/**
		 * <p>Unique identifier of the object.</p>
		 */
		private String id;
	
		/**
		 * <p>Human-readable name that is used for representing the object in user interfaces.</p>
		 */
		private String name;
	
		/**
		 * <p>A URL linking to this resource.</p>
		 */
		private String self;
	
		public String getId() {
			return id;
		}
		
		public void setId(final String id) {
			this.id = id;
		}
	
		public String getName() {
			return name;
		}
		
		public void setName(final String name) {
			this.name = name;
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
			if (r != null && r instanceof Source) {
				Source comparer = (Source) r;
				if (String.valueOf(comparer.getId()).equals(String.valueOf(this.getId())) && String.valueOf(comparer.getName()).equals(String.valueOf(this.getName())) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf()))) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * <p>Applicable filters to the subscription.</p>
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class SubscriptionFilter {
	
		/**
		 * <p>The Notifications are available for Alarms, Alarms with children, Device control, Events, Events with children, Inventory and Measurements for the <code>mo</code> context and for Alarms and Inventory for the <code>tenant</code> context. Alternatively, the wildcard <code>*</code> can be used to match all the permissible APIs within the bound context.</p>
		 * <blockquote>
		 * <p><strong>ⓘ Info:</strong> the wildcard <code>*</code> cannot be used in conjunction with other values.</p>
		 * </blockquote>
		 */
		private String[] apis;
	
		/**
		 * <p>The data needs to have the specified value in its <code>type</code> property to meet the filter criteria.</p>
		 */
		private String typeFilter;
	
		public String[] getApis() {
			return apis;
		}
		
		public void setApis(final String[] apis) {
			this.apis = apis;
		}
	
		public String getTypeFilter() {
			return typeFilter;
		}
		
		public void setTypeFilter(final String typeFilter) {
			this.typeFilter = typeFilter;
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
			if (r != null && r instanceof SubscriptionFilter) {
				SubscriptionFilter comparer = (SubscriptionFilter) r;
				if (comparer.getApis().equals(this.getApis()) && String.valueOf(comparer.getTypeFilter()).equals(String.valueOf(this.getTypeFilter()))) {
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
		if (r != null && r instanceof NotificationSubscription) {
			NotificationSubscription comparer = (NotificationSubscription) r;
			if (comparer.getContext().equals(this.getContext()) && comparer.getFragmentsToCopy().equals(this.getFragmentsToCopy()) && String.valueOf(comparer.getId()).equals(String.valueOf(this.getId())) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && comparer.getSource().equals(this.getSource()) && String.valueOf(comparer.getSubscription()).equals(String.valueOf(this.getSubscription())) && comparer.getSubscriptionFilter().equals(this.getSubscriptionFilter())) {
				return true;
			}
		}
		return false;
	}
}
