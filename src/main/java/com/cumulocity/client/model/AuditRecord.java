// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model;

import java.util.Map;
import java.util.HashMap;
import java.io.IOException;
import java.lang.reflect.Field;

import com.fasterxml.jackson.annotation.JsonAnyGetter;			
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@JsonDeserialize(using = AuditRecord.Deserializer.class)
public class AuditRecord {

	/**
	 * Summary of the action that was carried out.
	 */
	private String activity;

	/**
	 * Name of the application that performed the action.
	 */
	private String application;

	/**
	 * Metadata of the audit record.
	 */
	@JsonProperty(value = "c8y_Metadata")
	private C8yMetadata c8yMetadata;

	/**
	 * Collection of objects describing the changes that were carried out.
	 */
	private Changes[] changes;

	/**
	 * The date and time when the audit record was created.
	 */
	private String creationTime;

	/**
	 * Unique identifier of the audit record.
	 */
	private String id;

	/**
	 * A URL linking to this resource.
	 */
	private String self;

	/**
	 * The severity of the audit action.
	 */
	private Severity severity;

	/**
	 * The managed object to which the audit is associated.
	 */
	private Source source;

	/**
	 * Details of the action that was carried out.
	 */
	private String text;

	/**
	 * The date and time when the audit is updated.
	 */
	private String time;

	/**
	 * Identifies the platform component of the audit.
	 */
	private Type type;

	/**
	 * The user who carried out the activity.
	 */
	private String user;

	/**
	 * It is possible to add an arbitrary number of additional properties as a list of key-value pairs, for example, `"property1": {}`, `"property2": "value"`. These properties can be of any type, for example, object or string.
	 * 
	 */
	private Map<String, Object> customProperties;

	public AuditRecord() {
	}

	public AuditRecord(final String activity, final Source source, final String text, final String time, final Type type) {
		this.activity = activity;
		this.source = source;
		this.text = text;
		this.time = time;
		this.type = type;
	}

	public String getActivity() {
		return activity;
	}
	
	public void setActivity(final String activity) {
		this.activity = activity;
	}

	public String getApplication() {
		return application;
	}
	
	public void setApplication(final String application) {
		this.application = application;
	}

	public C8yMetadata getC8yMetadata() {
		return c8yMetadata;
	}
	
	public void setC8yMetadata(final C8yMetadata c8yMetadata) {
		this.c8yMetadata = c8yMetadata;
	}

	public Changes[] getChanges() {
		return changes;
	}
	
	public void setChanges(final Changes[] changes) {
		this.changes = changes;
	}

	public String getCreationTime() {
		return creationTime;
	}
	
	public void setCreationTime(final String creationTime) {
		this.creationTime = creationTime;
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

	public Severity getSeverity() {
		return severity;
	}
	
	public void setSeverity(final Severity severity) {
		this.severity = severity;
	}

	public Source getSource() {
		return source;
	}
	
	public void setSource(final Source source) {
		this.source = source;
	}

	public String getText() {
		return text;
	}
	
	public void setText(final String text) {
		this.text = text;
	}

	public String getTime() {
		return time;
	}
	
	public void setTime(final String time) {
		this.time = time;
	}

	public Type getType() {
		return type;
	}
	
	public void setType(final Type type) {
		this.type = type;
	}

	public String getUser() {
		return user;
	}
	
	public void setUser(final String user) {
		this.user = user;
	}

	@JsonAnyGetter
	public Map<String, Object> getCustomProperties() {
		return customProperties;
	}
	
	public void setCustomProperties(final Map<String, Object> customProperties) {
		this.customProperties = customProperties;
	}

	
	/**
	 * The severity of the audit action.
	 * [CRITICAL, MAJOR, MINOR, WARNING, INFORMATION]
	 */
	public enum Severity {
		@JsonProperty("CRITICAL")
		CRITICAL("CRITICAL"),
		@JsonProperty("MAJOR")
		MAJOR("MAJOR"),
		@JsonProperty("MINOR")
		MINOR("MINOR"),
		@JsonProperty("WARNING")
		WARNING("WARNING"),
		@JsonProperty("INFORMATION")
		INFORMATION("INFORMATION");
	
		private String value;
	
		Severity(final String value) {
			this.value = value;
		}
	
		public String getValue() {
			return value;
		}
	}

	
	/**
	 * Identifies the platform component of the audit.
	 * [Alarm, Application, BulkOperation, CepModule, Connector, Event, Group, Inventory, InventoryRole, Operation, Option, Report, SingleSignOn, SmartRule, SYSTEM, Tenant, TenantAuthConfig, TrustedCertificates, User, UserAuthentication]
	 */
	public enum Type {
		@JsonProperty("Alarm")
		ALARM("Alarm"),
		@JsonProperty("Application")
		APPLICATION("Application"),
		@JsonProperty("BulkOperation")
		BULKOPERATION("BulkOperation"),
		@JsonProperty("CepModule")
		CEPMODULE("CepModule"),
		@JsonProperty("Connector")
		CONNECTOR("Connector"),
		@JsonProperty("Event")
		EVENT("Event"),
		@JsonProperty("Group")
		GROUP("Group"),
		@JsonProperty("Inventory")
		INVENTORY("Inventory"),
		@JsonProperty("InventoryRole")
		INVENTORYROLE("InventoryRole"),
		@JsonProperty("Operation")
		OPERATION("Operation"),
		@JsonProperty("Option")
		OPTION("Option"),
		@JsonProperty("Report")
		REPORT("Report"),
		@JsonProperty("SingleSignOn")
		SINGLESIGNON("SingleSignOn"),
		@JsonProperty("SmartRule")
		SMARTRULE("SmartRule"),
		@JsonProperty("SYSTEM")
		SYSTEM("SYSTEM"),
		@JsonProperty("Tenant")
		TENANT("Tenant"),
		@JsonProperty("TenantAuthConfig")
		TENANTAUTHCONFIG("TenantAuthConfig"),
		@JsonProperty("TrustedCertificates")
		TRUSTEDCERTIFICATES("TrustedCertificates"),
		@JsonProperty("User")
		USER("User"),
		@JsonProperty("UserAuthentication")
		USERAUTHENTICATION("UserAuthentication");
	
		private String value;
	
		Type(final String value) {
			this.value = value;
		}
	
		public String getValue() {
			return value;
		}
	}

	/**
	 * Metadata of the audit record.
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class C8yMetadata {
	
		/**
		 * The action that was carried out.
		 */
		private Action action;
	
		public Action getAction() {
			return action;
		}
		
		public void setAction(final Action action) {
			this.action = action;
		}
	
		
		/**
		 * The action that was carried out.
		 * [SUBSCRIBE, DEPLOY, SCALE, DELETE]
		 */
		public enum Action {
			@JsonProperty("SUBSCRIBE")
			SUBSCRIBE("SUBSCRIBE"),
			@JsonProperty("DEPLOY")
			DEPLOY("DEPLOY"),
			@JsonProperty("SCALE")
			SCALE("SCALE"),
			@JsonProperty("DELETE")
			DELETE("DELETE");
		
			private String value;
		
			Action(final String value) {
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
			if (r != null && r instanceof C8yMetadata) {
				C8yMetadata comparer = (C8yMetadata) r;
				if (comparer.getAction().equals(this.getAction())) {
					return true;
				}
			}
			return false;
		}
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class Changes {
	
		/**
		 * The attribute that was changed.
		 */
		private String attribute;
	
		/**
		 * The type of change that was carried out.
		 */
		private ChangeType changeType;
	
		/**
		 * The new value of the object.
		 */
		private Object newValue;
	
		/**
		 * The previous value of the object.
		 */
		private Object previousValue;
	
		/**
		 * The type of the object.
		 */
		private String type;
	
		public String getAttribute() {
			return attribute;
		}
		
		public void setAttribute(final String attribute) {
			this.attribute = attribute;
		}
	
		public ChangeType getChangeType() {
			return changeType;
		}
		
		public void setChangeType(final ChangeType changeType) {
			this.changeType = changeType;
		}
	
		public Object getNewValue() {
			return newValue;
		}
		
		public void setNewValue(final Object newValue) {
			this.newValue = newValue;
		}
	
		public Object getPreviousValue() {
			return previousValue;
		}
		
		public void setPreviousValue(final Object previousValue) {
			this.previousValue = previousValue;
		}
	
		public String getType() {
			return type;
		}
		
		public void setType(final String type) {
			this.type = type;
		}
	
		
		/**
		 * The type of change that was carried out.
		 * [ADDED, REPLACED]
		 */
		public enum ChangeType {
			@JsonProperty("ADDED")
			ADDED("ADDED"),
			@JsonProperty("REPLACED")
			REPLACED("REPLACED");
		
			private String value;
		
			ChangeType(final String value) {
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
			if (r != null && r instanceof Changes) {
				Changes comparer = (Changes) r;
				if (String.valueOf(comparer.getAttribute()).equals(String.valueOf(this.getAttribute())) && comparer.getChangeType().equals(this.getChangeType()) && comparer.getNewValue().equals(this.getNewValue()) && comparer.getPreviousValue().equals(this.getPreviousValue()) && String.valueOf(comparer.getType()).equals(String.valueOf(this.getType()))) {
					return true;
				}
			}
			return false;
		}
	}


	/**
	 * The managed object to which the audit is associated.
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class Source {
	
		/**
		 * Unique identifier of the object.
		 */
		private String id;
	
		/**
		 * A URL linking to this resource.
		 */
		private String self;
	
		public Source() {
		}
	
		public Source(final String id) {
			this.id = id;
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
			if (r != null && r instanceof Source) {
				Source comparer = (Source) r;
				if (String.valueOf(comparer.getId()).equals(String.valueOf(this.getId())) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf()))) {
					return true;
				}
			}
			return false;
		}
	}


	public static class Deserializer extends JsonDeserializer<AuditRecord> {

		@Override
		public AuditRecord deserialize(final JsonParser p, final DeserializationContext ctxt)
				throws IOException, JsonProcessingException {
			final AuditRecord auditRecord = new AuditRecord();
			final Map<String, Object> additionalObjects = new HashMap<>();
			auditRecord.setCustomProperties(additionalObjects);
			final JsonNode jsonNode = p.readValueAsTree();
			jsonNode.fields().forEachRemaining(entry -> {
				try {
					final Field field = auditRecord.getClass().getDeclaredField(entry.getKey());
					field.setAccessible(true);
					final ObjectMapper objectMapper = new ObjectMapper();
					final Object value = objectMapper.readValue(entry.getValue().traverse(), field.getType());
					field.set(auditRecord, value);
				} catch (final NoSuchFieldException nse) {
					if (Serialization.additionalPropertyClasses.containsKey(entry.getKey())) {
						final ObjectMapper objectMapper = new ObjectMapper();
						try {
							final Object value = objectMapper.readValue(entry.getValue().traverse(), Serialization.additionalPropertyClasses.get(entry.getKey()));
							additionalObjects.put(entry.getKey(), value);
						} catch (final Exception e) {
							e.printStackTrace();
						}
					}
				} catch (final IOException | IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			});
			return auditRecord;
		}
	}

	public static class Serialization {

		private static Map<String, Class<?>> additionalPropertyClasses = new HashMap<String, Class<?>>();

		public static void registerAdditionalProperty(final String typeName, final Class<?> type) {
			additionalPropertyClasses.put(typeName, type);
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
		if (r != null && r instanceof AuditRecord) {
			AuditRecord comparer = (AuditRecord) r;
			if (String.valueOf(comparer.getActivity()).equals(String.valueOf(this.getActivity())) && String.valueOf(comparer.getApplication()).equals(String.valueOf(this.getApplication())) && comparer.getC8yMetadata().equals(this.getC8yMetadata()) && comparer.getChanges().equals(this.getChanges()) && String.valueOf(comparer.getCreationTime()).equals(String.valueOf(this.getCreationTime())) && String.valueOf(comparer.getId()).equals(String.valueOf(this.getId())) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && comparer.getSeverity().equals(this.getSeverity()) && comparer.getSource().equals(this.getSource()) && String.valueOf(comparer.getText()).equals(String.valueOf(this.getText())) && String.valueOf(comparer.getTime()).equals(String.valueOf(this.getTime())) && comparer.getType().equals(this.getType()) && String.valueOf(comparer.getUser()).equals(String.valueOf(this.getUser())) && comparer.getCustomProperties().equals(this.getCustomProperties())) {
				return true;
			}
		}
		return false;
	}
}
