// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.AuthConfig;
import com.cumulocity.client.model.AuthConfigAccess;
import com.cumulocity.client.model.LoginOptionCollection;

/**
 * API methods to retrieve the login options configured in the tenant.
 * 
 * More detailed information about the parameters and their meaning can be found in [Administration > Changing settings](https://cumulocity.com/guides/users-guide/administration/#changing-settings) in the *Users guide*.
 * > **&#9432; Info:** If OAuth external is the only login option shown in the response, the user will be automatically redirected to the SSO login screen.
 *  </br>
 * 
 */
public class LoginOptionsApi extends AdaptableApi {

	public LoginOptionsApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * Retrieve the login options
	 * Retrieve the login options available in the tenant.
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The request has succeeded and the login options are sent in the response.</li>
	 *     <li>HTTP 400 - Bad request – invalid parameters., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param management If this is set to `true`, the management tenant login options will be returned.  > **&#9432; Info:** The `tenantId` parameter must not be present in the request when using the `management` parameter, otherwise it will cause an error. 
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @return
	 */
	public CompletionStage<LoginOptionCollection> getLoginOptions(final boolean management, final String tenantId) {
		return adapt().path("tenant").path("loginOptions")
			.queryParam("management", management)
			.queryParam("tenantId", tenantId)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.loginoptioncollection+json")
			.rx()
			.method("GET", LoginOptionCollection.class);
	}
	
	/**
	 * Create a login option
	 * Create an authentication configuration on your tenant.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_TENANT_ADMIN <b>OR</b> ROLE_TENANT_MANAGEMENT_ADMIN
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - A login option was created.</li>
	 *     <li>HTTP 400 - Duplicated – The login option already exists.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 422 - Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 * @param body 
	 * @return
	 */
	public CompletionStage<AuthConfig> createLoginOption(final AuthConfig body) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "self");
		return adapt().path("tenant").path("loginOptions")
			.request()
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.authconfig+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.authconfig+json")
			.rx()
			.method("POST", Entity.json(jsonNode), AuthConfig.class);
	}
	
	/**
	 * Update a tenant's access to the login option
	 * Update the tenant's access to the authentication configuration.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_TENANT_MANAGEMENT_ADMIN <b>AND</b> is the management tenant
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The login option was updated.</li>
	 *     <li>HTTP 403 - Not authorized to perform this operation.</li>
	 *     <li>HTTP 404 - Tenant not found., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param body 
	 * @param typeOrId The type or ID of the login option. The type's value is case insensitive and can be `OAUTH2`, `OAUTH2_INTERNAL` or `BASIC`.
	 * @param targetTenant Unique identifier of a Cumulocity IoT tenant.
	 * @return
	 */
	public CompletionStage<AuthConfig> updateLoginOption(final AuthConfigAccess body, final String typeOrId, final String targetTenant) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("tenant").path("loginOptions").path(valueOf(typeOrId)).path("restrict")
			.queryParam("targetTenant", targetTenant)
			.request()
			.header("Content-Type", "application/json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.authconfig+json")
			.rx()
			.method("PUT", Entity.json(jsonNode), AuthConfig.class);
	}
}
