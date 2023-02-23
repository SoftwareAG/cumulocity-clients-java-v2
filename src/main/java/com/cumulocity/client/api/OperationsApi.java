// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.Operation;
import com.cumulocity.client.model.OperationCollection;

/**
 * API methods to create, retrieve, update and delete operations in Cumulocity IoT.
 * 
 * > **&#9432; Info:** The Accept header should be provided in all POST/PUT requests, otherwise an empty response body will be returned.
 *  </br>
 * 
 */
public class OperationsApi extends AdaptableApi {

	public OperationsApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * Retrieve a list of operations
	 * Retrieve a list of operations.
	 * 
	 * Notes about operation collections:
	 * 
	 * * The embedded operation object contains `deviceExternalIDs` only when queried with an `agentId` parameter.
	 * * The embedded operation object is filled with `deviceName`, but only when requesting resource: Get a collection of operations.
	 * * Operations are returned in the order of their ascending IDs.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_DEVICE_CONTROL_READ
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The request has succeeded and the list of operations is sent in the response.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param agentId An agent ID that may be part of the operation. If this parameter is set, the operation response objects contain the `deviceExternalIDs` object.
	 * @param bulkOperationId The bulk operation ID that this operation belongs to.
	 * @param currentPage The current page of the paginated results.
	 * @param dateFrom Start date or date and time of the operation.
	 * @param dateTo End date or date and time of the operation.
	 * @param deviceId The ID of the device the operation is performed for.
	 * @param fragmentType The type of fragment that must be part of the operation.
	 * @param pageSize Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param revert If you are using a range query (that is, at least one of the `dateFrom` or `dateTo` parameters is included in the request), then setting `revert=true` will sort the results by the newest operations first. By default, the results are sorted by the oldest operations first. 
	 * @param status Status of the operation.
	 * @param withTotalElements When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @param withTotalPages When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @return
	 */
	public CompletionStage<OperationCollection> getOperations(final String agentId, final String bulkOperationId, final int currentPage, final String dateFrom, final String dateTo, final String deviceId, final String fragmentType, final int pageSize, final boolean revert, final String status, final boolean withTotalElements, final boolean withTotalPages) {
		return adapt().path("devicecontrol").path("operations")
			.queryParam("agentId", agentId)
			.queryParam("bulkOperationId", bulkOperationId)
			.queryParam("currentPage", currentPage)
			.queryParam("dateFrom", dateFrom)
			.queryParam("dateTo", dateTo)
			.queryParam("deviceId", deviceId)
			.queryParam("fragmentType", fragmentType)
			.queryParam("pageSize", pageSize)
			.queryParam("revert", revert)
			.queryParam("status", status)
			.queryParam("withTotalElements", withTotalElements)
			.queryParam("withTotalPages", withTotalPages)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.operationcollection+json")
			.rx()
			.method("GET", OperationCollection.class);
	}
	
	/**
	 * Create an operation
	 * Create an operation.
	 * 
	 * It is possible to add custom fragments to operations, for example `com_cumulocity_model_WebCamDevice` is a custom object of the webcam operation.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_DEVICE_CONTROL_ADMIN <b>OR</b> owner of the device <b>OR</b> ADMIN permissions on the device
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 201 - An operation was created.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 422 - Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 * @param body 
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 * @return
	 */
	public CompletionStage<Operation> createOperation(final Operation body, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "creationTime");
		removeFromNode(jsonNode, "deviceExternalIDs", "self");
		removeFromNode(jsonNode, "bulkOperationId");
		removeFromNode(jsonNode, "failureReason");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "id");
		removeFromNode(jsonNode, "status");
		return adapt().path("devicecontrol").path("operations")
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.operation+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.operation+json")
			.rx()
			.method("POST", Entity.json(jsonNode), Operation.class);
	}
	
	/**
	 * Delete a list of operations
	 * Delete a list of operations.
	 * 
	 * The DELETE method allows for deletion of operation collections.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_DEVICE_CONTROL_ADMIN
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 204 - A list of operations was removed.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 403 - Not authorized to perform this operation.</li>
	 * </ul>
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 * @param agentId An agent ID that may be part of the operation.
	 * @param dateFrom Start date or date and time of the operation.
	 * @param dateTo End date or date and time of the operation.
	 * @param deviceId The ID of the device the operation is performed for.
	 * @param status Status of the operation.
	 */
	public CompletionStage<Response> deleteOperations(final String xCumulocityProcessingMode, final String agentId, final String dateFrom, final String dateTo, final String deviceId, final String status) {
		return adapt().path("devicecontrol").path("operations")
			.queryParam("agentId", agentId)
			.queryParam("dateFrom", dateFrom)
			.queryParam("dateTo", dateTo)
			.queryParam("deviceId", deviceId)
			.queryParam("status", status)
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
	
	/**
	 * Retrieve a specific operation
	 * Retrieve a specific operation (by a given ID).
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_DEVICE_CONTROL_READ <b>OR</b> owner of the resource <b>OR</b> ADMIN permission on the device
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The request has succeeded and the operation is sent in the response.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - Operation not found., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param id Unique identifier of the operation.
	 * @return
	 */
	public CompletionStage<Operation> getOperation(final String id) {
		return adapt().path("devicecontrol").path("operations").path(valueOf(id))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.operation+json")
			.rx()
			.method("GET", Operation.class);
	}
	
	/**
	 * Update a specific operation status
	 * Update a specific operation (by a given ID).
	 * You can only update its status.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_DEVICE_CONTROL_ADMIN <b>OR</b> owner of the resource <b>OR</b> ADMIN permission on the device
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - An operation was updated.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - Operation not found., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 422 - Validation error., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param body 
	 * @param id Unique identifier of the operation.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 * @return
	 */
	public CompletionStage<Operation> updateOperation(final Operation body, final String id, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "creationTime");
		removeFromNode(jsonNode, "deviceExternalIDs", "self");
		removeFromNode(jsonNode, "bulkOperationId");
		removeFromNode(jsonNode, "failureReason");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "id");
		removeFromNode(jsonNode, "deviceId");
		return adapt().path("devicecontrol").path("operations").path(valueOf(id))
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.operation+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.operation+json")
			.rx()
			.method("PUT", Entity.json(jsonNode), Operation.class);
	}
}
