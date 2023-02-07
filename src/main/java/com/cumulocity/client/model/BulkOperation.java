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
public class BulkOperation {

	/**
	 * A URL linking to this resource.
	 */
	private String self;

	/**
	 * Unique identifier of this bulk operation.
	 */
	private String id;

	/**
	 * Identifies the target group on which this operation should be performed.
	 * >**&#9432; Info:** `groupId` and `failedParentId` are mutually exclusive. Use only one of them in your request.
	 * 
	 */
	private String groupId;

	/**
	 * Identifies the failed bulk operation from which the failed operations should be rescheduled.
	 * >**&#9432; Info:** `groupId` and `failedParentId` are mutually exclusive. Use only one of them in your request. 
	 * 
	 */
	private String failedParentId;

	/**
	 * Date and time when the operations of this bulk operation should be created.
	 */
	private String startDate;

	/**
	 * Delay between every operation creation in seconds.
	 */
	private float creationRamp;

	/**
	 * Operation to be executed for every device in a group.
	 */
	private OperationPrototype operationPrototype;

	/**
	 * The status of this bulk operation, in context of the execution of all its single operations.
	 */
	private Status status;

	/**
	 * The general status of this bulk operation. The general status is visible for end users and they can filter and evaluate bulk operations by this status.
	 */
	private GeneralStatus generalStatus;

	/**
	 * Contains information about the number of processed operations.
	 */
	private Progress progress;

	public String getSelf() {
		return self;
	}
	
	public void setSelf(final String self) {
		this.self = self;
	}

	public String getId() {
		return id;
	}
	
	public void setId(final String id) {
		this.id = id;
	}

	public String getGroupId() {
		return groupId;
	}
	
	public void setGroupId(final String groupId) {
		this.groupId = groupId;
	}

	public String getFailedParentId() {
		return failedParentId;
	}
	
	public void setFailedParentId(final String failedParentId) {
		this.failedParentId = failedParentId;
	}

	public String getStartDate() {
		return startDate;
	}
	
	public void setStartDate(final String startDate) {
		this.startDate = startDate;
	}

	public float getCreationRamp() {
		return creationRamp;
	}
	
	public void setCreationRamp(final float creationRamp) {
		this.creationRamp = creationRamp;
	}

	public OperationPrototype getOperationPrototype() {
		return operationPrototype;
	}
	
	public void setOperationPrototype(final OperationPrototype operationPrototype) {
		this.operationPrototype = operationPrototype;
	}

	public Status getStatus() {
		return status;
	}
	
	public void setStatus(final Status status) {
		this.status = status;
	}

	public GeneralStatus getGeneralStatus() {
		return generalStatus;
	}
	
	public void setGeneralStatus(final GeneralStatus generalStatus) {
		this.generalStatus = generalStatus;
	}

	public Progress getProgress() {
		return progress;
	}
	
	public void setProgress(final Progress progress) {
		this.progress = progress;
	}

	
	/**
	 * The status of this bulk operation, in context of the execution of all its single operations.
	 * [ACTIVE, IN_PROGRESS, COMPLETED, DELETED]
	 */
	public enum Status {
		@JsonProperty("ACTIVE")
		ACTIVE("ACTIVE"),
		@JsonProperty("IN_PROGRESS")
		INPROGRESS("IN_PROGRESS"),
		@JsonProperty("COMPLETED")
		COMPLETED("COMPLETED"),
		@JsonProperty("DELETED")
		DELETED("DELETED");
	
		private String value;
	
		Status(final String value) {
			this.value = value;
		}
	
		public String getValue() {
			return value;
		}
	}

	
	/**
	 * The general status of this bulk operation. The general status is visible for end users and they can filter and evaluate bulk operations by this status.
	 * [SCHEDULED, EXECUTING, EXECUTING_WITH_ERRORS, SUCCESSFUL, FAILED, CANCELED]
	 */
	public enum GeneralStatus {
		@JsonProperty("SCHEDULED")
		SCHEDULED("SCHEDULED"),
		@JsonProperty("EXECUTING")
		EXECUTING("EXECUTING"),
		@JsonProperty("EXECUTING_WITH_ERRORS")
		EXECUTINGWITHERRORS("EXECUTING_WITH_ERRORS"),
		@JsonProperty("SUCCESSFUL")
		SUCCESSFUL("SUCCESSFUL"),
		@JsonProperty("FAILED")
		FAILED("FAILED"),
		@JsonProperty("CANCELED")
		CANCELED("CANCELED");
	
		private String value;
	
		GeneralStatus(final String value) {
			this.value = value;
		}
	
		public String getValue() {
			return value;
		}
	}

	/**
	 * Operation to be executed for every device in a group.
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class OperationPrototype {
	
		@Override
		public String toString() {
			try {
				// TODO thats an extensive operation, which only helps debugging
				return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
			} catch (final JsonProcessingException e) {
			}
			return super.toString();
		}
	
	}



	/**
	 * Contains information about the number of processed operations.
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class Progress {
	
		/**
		 * Number of pending operations.
		 */
		private int pending;
	
		/**
		 * Number of failed operations.
		 */
		private int failed;
	
		/**
		 * Number of operations being executed.
		 */
		private int executing;
	
		/**
		 * Number of operations successfully processed.
		 */
		private int successful;
	
		/**
		 * Total number of processed operations.
		 */
		private int all;
	
		public int getPending() {
			return pending;
		}
		
		public void setPending(final int pending) {
			this.pending = pending;
		}
	
		public int getFailed() {
			return failed;
		}
		
		public void setFailed(final int failed) {
			this.failed = failed;
		}
	
		public int getExecuting() {
			return executing;
		}
		
		public void setExecuting(final int executing) {
			this.executing = executing;
		}
	
		public int getSuccessful() {
			return successful;
		}
		
		public void setSuccessful(final int successful) {
			this.successful = successful;
		}
	
		public int getAll() {
			return all;
		}
		
		public void setAll(final int all) {
			this.all = all;
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
			if (r != null && r instanceof Progress) {
				Progress comparer = (Progress) r;
				if (Integer.valueOf(comparer.getPending()).equals(Integer.valueOf(this.getPending())) && Integer.valueOf(comparer.getFailed()).equals(Integer.valueOf(this.getFailed())) && Integer.valueOf(comparer.getExecuting()).equals(Integer.valueOf(this.getExecuting())) && Integer.valueOf(comparer.getSuccessful()).equals(Integer.valueOf(this.getSuccessful())) && Integer.valueOf(comparer.getAll()).equals(Integer.valueOf(this.getAll()))) {
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
		if (r != null && r instanceof BulkOperation) {
			BulkOperation comparer = (BulkOperation) r;
			if (String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && String.valueOf(comparer.getId()).equals(String.valueOf(this.getId())) && String.valueOf(comparer.getGroupId()).equals(String.valueOf(this.getGroupId())) && String.valueOf(comparer.getFailedParentId()).equals(String.valueOf(this.getFailedParentId())) && String.valueOf(comparer.getStartDate()).equals(String.valueOf(this.getStartDate())) && Float.valueOf(comparer.getCreationRamp()).equals(Float.valueOf(this.getCreationRamp())) && comparer.getOperationPrototype().equals(this.getOperationPrototype()) && comparer.getStatus().equals(this.getStatus()) && comparer.getGeneralStatus().equals(this.getGeneralStatus()) && comparer.getProgress().equals(this.getProgress())) {
				return true;
			}
		}
		return false;
	}
}
