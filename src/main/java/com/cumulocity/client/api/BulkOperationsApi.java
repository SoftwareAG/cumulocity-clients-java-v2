// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api;

import java.util.concurrent.Future;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.BulkOperation;
import com.cumulocity.client.model.BulkOperationCollection;

/**
 * The bulk operations API allows to schedule an operation on a group of devices to be executed at a specified time.
 * It is required to specify the delay between the creation of subsequent operations.
 * When the bulk operation is created, it has the status ACTIVE.
 * When all operations are created, the bulk operation has the status COMPLETED.
 * It is also possible to cancel an already created bulk operation by deleting it.
 * 
 * When you create a bulk operation, you can run it in two modes:
 * 
 * * If `groupId` is passed, it works the standard way, that means, it takes devices from a group and schedules operations on them.
 * * If `failedParentId` is passed, it takes the already processed bulk operation by that ID, and schedules operations on devices for which the previous operations failed.
 * 
 * Note that passing both `groupId` and `failedParentId` will not work, and a bulk operation works with groups of type `static` and `dynamic`.
 * 
 * > **&#9432; Info:** The bulk operations API requires different roles than the rest of the device control API: `BULK_OPERATION_READ` and `BULK_OPERATION_ADMIN`.
 * >
 * > The Accept header should be provided in all POST/PUT requests, otherwise an empty response body will be returned.
 *  </br>
 * 
 */ 
public class BulkOperationsApi extends AdaptableApi {

	public BulkOperationsApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * Retrieve a list of bulk operations </br>
	 * Retrieve a list of bulk operations.  <section><h5>Required roles</h5> ROLE_BULK_OPERATION_READ </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the list of bulk operations sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 * <p>
	 * @param currentPage The current page of the paginated results.
	 * @param pageSize Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param withTotalElements When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @return
	 */
	public Future<BulkOperationCollection> getBulkOperations(final int currentPage, final int pageSize, final boolean withTotalElements) {
		return getRootTarget().path("devicecontrol").path("bulkoperations")
			.queryParam("currentPage", valueOf(currentPage))
			.queryParam("pageSize", valueOf(pageSize))
			.queryParam("withTotalElements", valueOf(withTotalElements))
				.request()
				.header("Accept", "application/vnd.com.nsn.cumulocity.bulkoperationcollection+json, application/vnd.com.nsn.cumulocity.error+json")
				.build("GET")
				.submit(BulkOperationCollection.class);
	}
	
	/**
	 * Create a bulk operation </br>
	 * Create a bulk operation.  <section><h5>Required roles</h5> ROLE_BULK_OPERATION_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 A bulk operation was created.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 * <p>
	 * @param body 
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 * @return
	 */
	public Future<BulkOperation> createBulkOperation(final BulkOperation body, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "generalStatus");
		removeFromNode(jsonNode, "failedParentId");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "progress");
		removeFromNode(jsonNode, "id");
		removeFromNode(jsonNode, "status");
		return getRootTarget().path("devicecontrol").path("bulkoperations")
				.request()
				.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
				.header("Content-Type", "application/vnd.com.nsn.cumulocity.bulkoperation+json")
				.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.bulkoperation+json")
				.build("POST", Entity.json(jsonNode))
				.submit(BulkOperation.class);
	}
	
	/**
	 * Retrieve a specific bulk operation </br>
	 * Retrieve a specific bulk operation (by a given ID).  <section><h5>Required roles</h5> ROLE_BULK_OPERATION_READ </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the bulk operation is sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Bulk operation not found.</li>
	 * </ul>
	 * <p>
	 * @param id Unique identifier of the bulk operation.
	 * @return
	 */
	public Future<BulkOperation> getBulkOperation(final String id) {
		return getRootTarget().path("devicecontrol").path("bulkoperations").path(valueOf(id))
				.request()
				.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.bulkoperation+json")
				.build("GET")
				.submit(BulkOperation.class);
	}
	
	/**
	 * Update a specific bulk operation </br>
	 * Update a specific bulk operation (by a given ID).  <section><h5>Required roles</h5> ROLE_BULK_OPERATION_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 A bulk operation was updated.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Bulk operation not found.</li>
	 * </ul>
	 * <p>
	 * @param body 
	 * @param id Unique identifier of the bulk operation.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 * @return
	 */
	public Future<BulkOperation> updateBulkOperation(final BulkOperation body, final String id, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "generalStatus");
		removeFromNode(jsonNode, "failedParentId");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "progress");
		removeFromNode(jsonNode, "id");
		removeFromNode(jsonNode, "status");
		return getRootTarget().path("devicecontrol").path("bulkoperations").path(valueOf(id))
				.request()
				.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
				.header("Content-Type", "application/vnd.com.nsn.cumulocity.bulkoperation+json")
				.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.bulkoperation+json")
				.build("PUT", Entity.json(jsonNode))
				.submit(BulkOperation.class);
	}
	
	/**
	 * Delete a specific bulk operation </br>
	 * Delete a specific bulk operation (by a given ID).  <section><h5>Required roles</h5> ROLE_BULK_OPERATION_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>204 A bulk operation was removed.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not authorized to perform this operation.</li>
	 * <li>404 Bulk operation not found.</li>
	 * </ul>
	 * <p>
	 * @param id Unique identifier of the bulk operation.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	public Future<Response> deleteBulkOperation(final String id, final String xCumulocityProcessingMode) {
		return getRootTarget().path("devicecontrol").path("bulkoperations").path(valueOf(id))
				.request()
				.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
				.header("Accept", "application/json")
				.build("DELETE")
				.submit();
	}
}
