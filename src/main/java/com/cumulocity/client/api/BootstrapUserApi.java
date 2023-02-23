// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import jakarta.ws.rs.client.WebTarget;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.BootstrapUser;

/**
 * API methods to retrieve the bootstrap user of an application. </br>
 * 
 */
public class BootstrapUserApi extends AdaptableApi {

	public BootstrapUserApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * Retrieve the bootstrap user for a specific application
	 * Retrieve the bootstrap user for a specific application (by a given ID).
	 * 
	 * This only works for microservice applications.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_APPLICATION_MANAGEMENT_ADMIN
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The request has succeeded and the bootstrap user of the application is sent in the response.</li>
	 *     <li>HTTP 400 - Bad request., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param id Unique identifier of the application.
	 * @return
	 */
	public CompletionStage<BootstrapUser> getBootstrapUser(final String id) {
		return adapt().path("application").path("applications").path(valueOf(id)).path("bootstrapUser")
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.user+json")
			.rx()
			.method("GET", BootstrapUser.class);
	}
}
