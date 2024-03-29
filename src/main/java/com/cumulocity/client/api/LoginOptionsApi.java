// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
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
 * <p>API methods to retrieve the login options configured in the tenant.</p>
 * <p>More detailed information about the parameters and their meaning can be found in <a href="https://cumulocity.com/guides/users-guide/administration/#changing-settings">Administration > Changing settings</a> in the <em>Users guide</em>.</p>
 * <blockquote>
 * <p><strong>ⓘ Info:</strong> If OAuth external is the only login option shown in the response, the user will be automatically redirected to the SSO login screen.</p>
 * </blockquote>
 */
public class LoginOptionsApi extends AdaptableApi {

	public LoginOptionsApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * <p>Retrieve the login options</p>
	 * <p>Retrieve the login options available in the tenant.</p>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the login options are sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 400 <p>Bad request – invalid parameters.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param management
	 * <p>If this is set to <code>true</code>, the management tenant login options will be returned.</p>
	 * <p><strong>ⓘ Info:</strong> The <code>tenantId</code> parameter must not be present in the request when using the <code>management</code> parameter, otherwise it will cause an error.</p>
	 * @param tenantId
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
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
	 * <p>Create a login option</p>
	 * <p>Create an authentication configuration on your tenant.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_TENANT_ADMIN <b>OR</b> ROLE_TENANT_MANAGEMENT_ADMIN
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>A login option was created.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 400 <p>Duplicated – The login option already exists.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Unprocessable Entity – invalid payload.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
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
	 * <p>Update a tenant's access to the login option</p>
	 * <p>Update the tenant's access to the authentication configuration.</p>
	 * <section><h5>Required roles</h5>
	 * ROLE_TENANT_MANAGEMENT_ADMIN <b>AND</b> is the management tenant
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The login option was updated.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not authorized to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Tenant not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param typeOrId
	 * <p>The type or ID of the login option. The type's value is case insensitive and can be <code>OAUTH2</code>, <code>OAUTH2_INTERNAL</code> or <code>BASIC</code>.</p>
	 * @param targetTenant
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
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
