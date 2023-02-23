// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.Tenant;
import com.cumulocity.client.model.TenantCollection;
import com.cumulocity.client.model.CurrentTenant;
import com.cumulocity.client.model.TenantTfaData;

/**
 * Tenants are physically separated data spaces with a separate URL, with own users, a separate application management and no sharing of data by default. Users in a single tenant by default share the same URL and the same data space.
 * 
 * ### Tenant ID and tenant domain
 * 
 * The **tenant ID** is a unique identifier across all tenants in Cumulocity IoT and it follows the format t&lt;number>, for example, t07007007. It is possible to specify the tenant ID while creating a subtenant, but the ID cannot be changed after creation. If the ID is not specified (recommended), it gets auto-generated for all tenant types.
 * 
 * The location where a tenant can be accessed is called **tenant domain**, for example, _mytenant.cumulocity.com_. It needs to be unique across all tenants and it can be changed after tenant creation.
 * The tenant domain may contain only lowercase letters, digits and hyphens. It must start with a lowercase letter, hyphens are only allowed in the middle, and the minimum length is 2 characters. Note that the usage of underscore characters is deprecated but still possible for backward compatibility reasons.
 * 
 * In general, the tenant domain should be used for communication if it is known.
 * 
 * > **⚠️ Important:** For support user access, the tenant ID must be used and not the tenant domain.
 * 
 * See [Tenant > Current tenant](#operation/getCurrentTenantResource) for information on how to retrieve the tenant ID and domain of the current tenant via the API.
 * 
 * In the UI, the tenant ID is displayed in the user dropdown menu, see [Getting started > User options and settings](https://cumulocity.com/guides/users-guide/getting-started/#user-settings) in the User guide.
 * 
 * ### Access rights and permissions
 * 
 * There are two types of roles in Cumulocity IoT – global and inventory. Global roles are applied at the tenant level. In a Role Based Access Control (RBAC) approach you must use the inventory roles in order to have the correct level of separation. Apart from some global permissions (like "own user management") customer users will not be assigned any roles. Inventory roles must be created, or the default roles used, and then assigned to the user in combination with the assets the roles apply to. This needs to be done at least once for each customer.
 * 
 * In a multi-tenancy approach, as the tenant is completely separated from all other customers you do not necessarily need to be involved in setting up the access rights of the customer. If customers are given administration rights for their tenants, they can set up permissions on their own. It is not possible for customers to have any sight or knowledge of other customers.
 * 
 * In the RBAC approach, managing access is the most complicated part because a misconfiguration can potentially give customers access to data that they must not see, like other customers' data. The inventory roles allow you to granularly define access for only certain parts of data, but they don't protect you from accidental misconfigurations. A limitation here is that customers won't be able to create their own roles.
 * 
 * For more details, see [RBAC versus multi-tenancy approach](https://cumulocity.com/guides/concepts/tenant-hierarchy/#comparison).
 * 
 * > **&#9432; Info:** The Accept header should be provided in all POST/PUT requests, otherwise an empty response body will be returned.
 *  </br>
 * 
 */
public class TenantsApi extends AdaptableApi {

	public TenantsApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * Retrieve all subtenants
	 * Retrieve all subtenants of the current tenant.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_TENANT_MANAGEMENT_READ
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The request has succeeded and the subtenants are sent in the response.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param currentPage The current page of the paginated results.
	 * @param pageSize Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param withTotalElements When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @param withTotalPages When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @return
	 */
	public CompletionStage<TenantCollection> getTenants(final int currentPage, final int pageSize, final boolean withTotalElements, final boolean withTotalPages) {
		return adapt().path("tenant").path("tenants")
			.queryParam("currentPage", currentPage)
			.queryParam("pageSize", pageSize)
			.queryParam("withTotalElements", withTotalElements)
			.queryParam("withTotalPages", withTotalPages)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.tenantcollection+json")
			.rx()
			.method("GET", TenantCollection.class);
	}
	
	/**
	 * Create a tenant
	 * Create a subtenant for the current tenant.
	 * 
	 * <section><h5>Required roles</h5>
	 * (ROLE_TENANT_MANAGEMENT_ADMIN <b>OR</b> ROLE_TENANT_MANAGEMENT_CREATE) <b>AND</b> the current tenant is allowed to create subtenants
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 201 - A tenant was created.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 403 - Not authorized to perform this operation.</li>
	 *     <li>HTTP 409 - Conflict – The tenant domain/ID already exists.</li>
	 *     <li>HTTP 422 - Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 * @param body 
	 * @return
	 */
	public CompletionStage<Tenant> createTenant(final Tenant body) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "allowCreateTenants");
		removeFromNode(jsonNode, "parent");
		removeFromNode(jsonNode, "creationTime");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "id");
		removeFromNode(jsonNode, "ownedApplications");
		removeFromNode(jsonNode, "applications");
		removeFromNode(jsonNode, "status");
		return adapt().path("tenant").path("tenants")
			.request()
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.tenant+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.tenant+json")
			.rx()
			.method("POST", Entity.json(jsonNode), Tenant.class);
	}
	
	/**
	 * Retrieve the current tenant
	 * Retrieve information about the current tenant.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_OWN_READ <b>OR</b> ROLE_SYSTEM
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The request has succeeded and the information is sent in the response.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param withParent When set to `true`, the returned result will contain the parent of the current tenant.
	 * @return
	 */
	public CompletionStage<CurrentTenant> getCurrentTenant(final boolean withParent) {
		return adapt().path("tenant").path("currentTenant")
			.queryParam("withParent", withParent)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.currenttenant+json")
			.rx()
			.method("GET", CurrentTenant.class);
	}
	
	/**
	 * Retrieve a specific tenant
	 * Retrieve a specific tenant by a given ID.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_TENANT_MANAGEMENT_READ <b>AND</b> the current tenant is its parent <b>OR</b> is the management tenant
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The request has succeeded and the tenant is sent in the response.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 403 - Not authorized to perform this operation.</li>
	 *     <li>HTTP 404 - Tenant not found., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @return
	 */
	public CompletionStage<Tenant> getTenant(final String tenantId) {
		return adapt().path("tenant").path("tenants").path(valueOf(tenantId))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.tenant+json")
			.rx()
			.method("GET", Tenant.class);
	}
	
	/**
	 * Update a specific tenant
	 * Update a specific tenant by a given ID.
	 * 
	 * <section><h5>Required roles</h5>
	 * (ROLE_TENANT_MANAGEMENT_ADMIN <b>OR</b> ROLE_TENANT_MANAGEMENT_UPDATE) <b>AND</b> (the current tenant is its parent <b>AND</b> the current tenant is allowed to create subtenants) <b>OR</b> is the management tenant
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - A tenant was updated.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 403 - Not authorized to perform this operation.</li>
	 *     <li>HTTP 404 - Tenant not found., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 422 - Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 * @param body 
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @return
	 */
	public CompletionStage<Tenant> updateTenant(final Tenant body, final String tenantId) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "adminName");
		removeFromNode(jsonNode, "allowCreateTenants");
		removeFromNode(jsonNode, "parent");
		removeFromNode(jsonNode, "creationTime");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "id");
		removeFromNode(jsonNode, "ownedApplications");
		removeFromNode(jsonNode, "applications");
		removeFromNode(jsonNode, "status");
		return adapt().path("tenant").path("tenants").path(valueOf(tenantId))
			.request()
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.tenant+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.tenant+json")
			.rx()
			.method("PUT", Entity.json(jsonNode), Tenant.class);
	}
	
	/**
	 * Remove a specific tenant
	 * Remove a specific tenant by a given ID.
	 * 
	 * > **⚠️ Important:** Deleting a subtenant cannot be reverted. For security reasons, it is therefore only available in the management tenant. You cannot delete tenants from any tenant but the management tenant.
	 * >
	 * > Administrators in Enterprise Tenants are only allowed to suspend active subtenants, but not to delete them.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_TENANT_MANAGEMENT_ADMIN <b>AND</b> is the management tenant
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 204 - A tenant was removed.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 403 - Not authorized to perform this operation.</li>
	 *     <li>HTTP 404 - Tenant not found., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 */
	public CompletionStage<Response> deleteTenant(final String tenantId) {
		return adapt().path("tenant").path("tenants").path(valueOf(tenantId))
			.request()
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
	
	/**
	 * Retrieve TFA settings of a specific tenant
	 * Retrieve the two-factor authentication settings of a specific tenant by a given tenant ID.
	 * 
	 * <section><h5>Required roles</h5>
	 * ((ROLE_TENANT_MANAGEMENT_READ <b>OR</b> ROLE_USER_MANAGEMENT_READ) <b>AND</b> (the current tenant is its parent <b>OR</b> is the management tenant <b>OR</b> the current user belongs to the tenant)) <b>OR</b> (the user belongs to the tenant <b>AND</b> ROLE_USER_MANAGEMENT_OWN_READ)
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The request has succeeded and the TFA settings are sent in the response.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - Tenant not found., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @return
	 */
	public CompletionStage<TenantTfaData> getTenantTfaSettings(final String tenantId) {
		return adapt().path("tenant").path("tenants").path(valueOf(tenantId)).path("tfa")
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("GET", TenantTfaData.class);
	}
}
