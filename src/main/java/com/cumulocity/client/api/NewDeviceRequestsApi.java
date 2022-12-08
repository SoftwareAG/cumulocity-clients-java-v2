// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api;

import java.util.concurrent.Future;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.NewDeviceRequest;
import com.cumulocity.client.model.NewDeviceRequestCollection;

/**
 * API methods to create, retrieve, update and delete new device requests in Cumulocity IoT.
 * 
 * > **&#9432; Info:** The Accept header should be provided in all POST/PUT requests, otherwise an empty response body will be returned.
 *  </br>
 * 
 */ 
public class NewDeviceRequestsApi extends AdaptableApi {

	public NewDeviceRequestsApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * Retrieve a list of new device requests </br>
	 * Retrieve a list of new device requests.  <section><h5>Required roles</h5> ROLE_DEVICE_CONTROL_READ </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the list of new device requests sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 * <p>
	 * @param currentPage The current page of the paginated results.
	 * @param pageSize Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param withTotalElements When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @param withTotalPages When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @return
	 */
	public Future<NewDeviceRequestCollection> getNewDeviceRequests(final int currentPage, final int pageSize, final boolean withTotalElements, final boolean withTotalPages) {
		return getRootTarget().path("devicecontrol").path("newDeviceRequests")
			.queryParam("currentPage", valueOf(currentPage))
			.queryParam("pageSize", valueOf(pageSize))
			.queryParam("withTotalElements", valueOf(withTotalElements))
			.queryParam("withTotalPages", valueOf(withTotalPages))
				.request()
				.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.newdevicerequestcollection+json")
				.build("GET")
				.submit(NewDeviceRequestCollection.class);
	}
	
	/**
	 * Create a new device request </br>
	 * Create a new device request.  <section><h5>Required roles</h5> ROLE_DEVICE_CONTROL_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 A new device request was created.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>422 Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 * <p>
	 * @param body 
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 * @return
	 */
	public Future<NewDeviceRequest> createNewDeviceRequest(final NewDeviceRequest body, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "status");
		return getRootTarget().path("devicecontrol").path("newDeviceRequests")
				.request()
				.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
				.header("Content-Type", "application/vnd.com.nsn.cumulocity.newdevicerequest+json")
				.header("Accept", "application/vnd.com.nsn.cumulocity.newdevicerequest+json, application/vnd.com.nsn.cumulocity.error+json")
				.build("POST", Entity.json(jsonNode))
				.submit(NewDeviceRequest.class);
	}
	
	/**
	 * Retrieve a specific new device request </br>
	 * Retrieve a specific new device request (by a given ID).  <section><h5>Required roles</h5> ROLE_DEVICE_CONTROL_READ </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the new device request is sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 New device request not found.</li>
	 * </ul>
	 * <p>
	 * @param requestId Unique identifier of the new device request.
	 * @return
	 */
	public Future<NewDeviceRequest> getNewDeviceRequest(final String requestId) {
		return getRootTarget().path("devicecontrol").path("newDeviceRequests").path(valueOf(requestId))
				.request()
				.header("Accept", "application/vnd.com.nsn.cumulocity.newdevicerequest+json, application/vnd.com.nsn.cumulocity.error+json")
				.build("GET")
				.submit(NewDeviceRequest.class);
	}
	
	/**
	 * Update a specific new device request status </br>
	 * Update a specific new device request (by a given ID). You can only update its status.  <section><h5>Required roles</h5> ROLE_DEVICE_CONTROL_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 A new device request was updated.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 New device request not found.</li>
	 * </ul>
	 * <p>
	 * @param body 
	 * @param requestId Unique identifier of the new device request.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 * @return
	 */
	public Future<NewDeviceRequest> updateNewDeviceRequest(final NewDeviceRequest body, final String requestId, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "id");
		return getRootTarget().path("devicecontrol").path("newDeviceRequests").path(valueOf(requestId))
				.request()
				.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
				.header("Content-Type", "application/vnd.com.nsn.cumulocity.newdevicerequest+json")
				.header("Accept", "application/vnd.com.nsn.cumulocity.newdevicerequest+json, application/vnd.com.nsn.cumulocity.error+json")
				.build("PUT", Entity.json(jsonNode))
				.submit(NewDeviceRequest.class);
	}
	
	/**
	 * Delete a specific new device request </br>
	 * Delete a specific new device request (by a given ID).  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>204 A new device request was removed.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not authorized to perform this operation.</li>
	 * <li>404 New device request not found.</li>
	 * </ul>
	 * <p>
	 * @param requestId Unique identifier of the new device request.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	public Future<Response> deleteNewDeviceRequest(final String requestId, final String xCumulocityProcessingMode) {
		return getRootTarget().path("devicecontrol").path("newDeviceRequests").path(valueOf(requestId))
				.request()
				.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
				.header("Accept", "application/json")
				.build("DELETE")
				.submit();
	}
}
