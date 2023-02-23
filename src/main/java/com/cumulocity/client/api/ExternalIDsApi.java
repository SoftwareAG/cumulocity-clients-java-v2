// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
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
	 * Retrieve all external IDs of a specific managed object
	 * Retrieve all external IDs of a existing managed object (identified by ID).
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_IDENTITY_READ <b>OR</b> owner of the resource <b>OR</b> MANAGED_OBJECT_READ permission on the resource
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The request has succeeded and all the external IDs are sent in the response.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param id Unique identifier of the managed object.
	 * @return
	 */
	public CompletionStage<ExternalIds> getExternalIds(final String id) {
		return adapt().path("identity").path("globalIds").path(valueOf(id)).path("externalIds")
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.externalidcollection+json")
			.rx()
			.method("GET", ExternalIds.class);
	}
	
	/**
	 * Create an external ID
	 * Create an external ID for an existing managed object (identified by ID).
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_IDENTITY_ADMIN <b>OR</b> owner of the resource <b>OR</b> MANAGED_OBJECT_ADMIN permission on the resource
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 201 - An external ID was created.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 409 - Duplicate – Identity already bound to a different Global ID.</li>
	 * </ul>
	 * @param body 
	 * @param id Unique identifier of the managed object.
	 * @return
	 */
	public CompletionStage<ExternalId> createExternalId(final ExternalId body, final String id) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "managedObject");
		removeFromNode(jsonNode, "self");
		return adapt().path("identity").path("globalIds").path(valueOf(id)).path("externalIds")
			.request()
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.externalid+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.externalid+json")
			.rx()
			.method("POST", Entity.json(jsonNode), ExternalId.class);
	}
	
	/**
	 * Retrieve a specific external ID
	 * Retrieve a specific external ID of a particular type.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_IDENTITY_READ <b>OR</b> owner of the resource <b>OR</b> MANAGED_OBJECT_READ permission on the resource
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The request has succeeded and the external ID is sent in the response.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - External ID not found., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param type The identifier used in the external system that Cumulocity IoT interfaces with.
	 * @param externalId The type of the external identifier.
	 * @return
	 */
	public CompletionStage<ExternalId> getExternalId(final String type, final String externalId) {
		return adapt().path("identity").path("externalIds").path(valueOf(type)).path(valueOf(externalId))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.externalid+json")
			.rx()
			.method("GET", ExternalId.class);
	}
	
	/**
	 * Remove a specific external ID
	 * Remove a specific external ID of a particular type.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_IDENTITY_ADMIN <b>OR</b> owner of the resource <b>OR</b> MANAGED_OBJECT_ADMIN permission on the resource
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 204 - An external ID was deleted.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - External ID not found., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param type The identifier used in the external system that Cumulocity IoT interfaces with.
	 * @param externalId The type of the external identifier.
	 */
	public CompletionStage<Response> deleteExternalId(final String type, final String externalId) {
		return adapt().path("identity").path("externalIds").path(valueOf(type)).path(valueOf(externalId))
			.request()
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
}
