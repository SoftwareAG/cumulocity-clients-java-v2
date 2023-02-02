// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api;

import java.util.concurrent.Future;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.ExternalId;
import com.cumulocity.client.model.ExternalIds;

/**
 * The external ID resource represents an individual external ID that can be queried and deleted.
 * 
 * > **&#9432; Info:** The Accept header should be provided in all POST requests, otherwise an empty response body will be returned.
 *  </br>
 * 
 */ 
public class ExternalIDsApi extends AdaptableApi {

	public ExternalIDsApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * Retrieve all external IDs of a specific managed object </br>
	 * Retrieve all external IDs of a existing managed object (identified by ID).  <section><h5>Required roles</h5> ROLE_IDENTITY_READ <b>OR</b> owner of the resource <b>OR</b> MANAGED_OBJECT_READ permission on the resource </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and all the external IDs are sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 * <p>
	 * @param id Unique identifier of the managed object.
	 * @return
	 */
	public Future<ExternalIds> getExternalIds(final String id) {
		return getRootTarget().path("identity").path("globalIds").path(valueOf(id)).path("externalIds")
				.request()
				.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.externalidcollection+json")
				.build("GET")
				.submit(ExternalIds.class);
	}
	
	/**
	 * Create an external ID </br>
	 * Create an external ID for an existing managed object (identified by ID).  <section><h5>Required roles</h5> ROLE_IDENTITY_ADMIN <b>OR</b> owner of the resource <b>OR</b> MANAGED_OBJECT_ADMIN permission on the resource </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>201 An external ID was created.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>409 Duplicate – Identity already bound to a different Global ID.</li>
	 * </ul>
	 * <p>
	 * @param body 
	 * @param id Unique identifier of the managed object.
	 * @return
	 */
	public Future<ExternalId> createExternalId(final ExternalId body, final String id) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "managedObject");
		removeFromNode(jsonNode, "self");
		return getRootTarget().path("identity").path("globalIds").path(valueOf(id)).path("externalIds")
				.request()
				.header("Content-Type", "application/vnd.com.nsn.cumulocity.externalid+json")
				.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.externalid+json")
				.build("POST", Entity.json(jsonNode))
				.submit(ExternalId.class);
	}
	
	/**
	 * Retrieve a specific external ID </br>
	 * Retrieve a specific external ID of a particular type.  <section><h5>Required roles</h5> ROLE_IDENTITY_READ <b>OR</b> owner of the resource <b>OR</b> MANAGED_OBJECT_READ permission on the resource </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the external ID is sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 External ID not found.</li>
	 * </ul>
	 * <p>
	 * @param type The identifier used in the external system that Cumulocity IoT interfaces with.
	 * @param externalId The type of the external identifier.
	 * @return
	 */
	public Future<ExternalId> getExternalId(final String type, final String externalId) {
		return getRootTarget().path("identity").path("externalIds").path(valueOf(type)).path(valueOf(externalId))
				.request()
				.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.externalid+json")
				.build("GET")
				.submit(ExternalId.class);
	}
	
	/**
	 * Remove a specific external ID </br>
	 * Remove a specific external ID of a particular type.  <section><h5>Required roles</h5> ROLE_IDENTITY_ADMIN <b>OR</b> owner of the resource <b>OR</b> MANAGED_OBJECT_ADMIN permission on the resource </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>204 An external ID was deleted.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 External ID not found.</li>
	 * </ul>
	 * <p>
	 * @param type The identifier used in the external system that Cumulocity IoT interfaces with.
	 * @param externalId The type of the external identifier.
	 */
	public Future<Response> deleteExternalId(final String type, final String externalId) {
		return getRootTarget().path("identity").path("externalIds").path(valueOf(type)).path(valueOf(externalId))
				.request()
				.header("Accept", "application/json")
				.build("DELETE")
				.submit();
	}
}
