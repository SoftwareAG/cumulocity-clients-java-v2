// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.Group;
import com.cumulocity.client.model.UserGroupCollection;
import com.cumulocity.client.model.GroupReferenceCollection;

/**
 * API methods to create, retrieve, update and delete user groups.
 * 
 * > **⚠️ Important:** In the Cumulocity IoT user interface, user groups are referred to as "global roles". Global roles are not to be confused with user roles.
 * 
 * > **&#9432; Info:** The Accept header should be provided in all POST/PUT requests, otherwise an empty response body will be returned.
 *  </br>
 * 
 */
public class GroupsApi extends AdaptableApi {

	public GroupsApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * Retrieve all user groups of a specific tenant
	 * Retrieve all user groups of a specific tenant (by a given tenant ID).
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_READ <b>OR</b> ROLE_USER_MANAGEMENT_CREATE
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The request has succeeded and all user groups are sent in the response.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 403 - Not enough permissions/roles to perform this operation., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param currentPage The current page of the paginated results.
	 * @param pageSize Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param withTotalElements When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @param withTotalPages When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @return
	 */
	public CompletionStage<UserGroupCollection> getTenantUserGroups(final String tenantId, final int currentPage, final int pageSize, final boolean withTotalElements, final boolean withTotalPages) {
		return adapt().path("user").path(valueOf(tenantId)).path("groups")
			.queryParam("currentPage", currentPage)
			.queryParam("pageSize", pageSize)
			.queryParam("withTotalElements", withTotalElements)
			.queryParam("withTotalPages", withTotalPages)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.groupcollection+json")
			.rx()
			.method("GET", UserGroupCollection.class);
	}
	
	/**
	 * Create a user group for a specific tenant
	 * Create a user group for a specific tenant (by a given tenant ID).
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_ADMIN
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 201 - A user group was created.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 403 - Not enough permissions/roles to perform this operation., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 409 - Duplicate – Group name already exists.</li>
	 *     <li>HTTP 422 - Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 * @param body 
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @return
	 */
	public CompletionStage<Group> createUserGroup(final Group body, final String tenantId) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "roles");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "id");
		removeFromNode(jsonNode, "devicePermissions");
		removeFromNode(jsonNode, "users");
		removeFromNode(jsonNode, "applications");
		return adapt().path("user").path(valueOf(tenantId)).path("groups")
			.request()
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.group+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.group+json")
			.rx()
			.method("POST", Entity.json(jsonNode), Group.class);
	}
	
	/**
	 * Retrieve a specific user group for a specific tenant
	 * Retrieve a specific user group (by a given user group ID) for a specific tenant (by a given tenant ID).
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_ADMIN <b>OR</b> ROLE_USER_MANAGEMENT_CREATE <b>AND</b> is parent of the user <b>AND</b> is not the current user
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The request succeeded and the user group is sent in the response.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 403 - Not enough permissions/roles to perform this operation., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - Group not found., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param groupId Unique identifier of the user group.
	 * @return
	 */
	public CompletionStage<Group> getUserGroup(final String tenantId, final int groupId) {
		return adapt().path("user").path(valueOf(tenantId)).path("groups").path(valueOf(groupId))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.group+json")
			.rx()
			.method("GET", Group.class);
	}
	
	/**
	 * Update a specific user group for a specific tenant
	 * Update a specific user group (by a given user group ID) for a specific tenant (by a given tenant ID).
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_ADMIN
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - A user group was updated.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 403 - Not enough permissions/roles to perform this operation., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - Group not found., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 422 - Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 * @param body 
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param groupId Unique identifier of the user group.
	 * @return
	 */
	public CompletionStage<Group> updateUserGroup(final Group body, final String tenantId, final int groupId) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "roles");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "id");
		removeFromNode(jsonNode, "devicePermissions");
		removeFromNode(jsonNode, "users");
		removeFromNode(jsonNode, "applications");
		return adapt().path("user").path(valueOf(tenantId)).path("groups").path(valueOf(groupId))
			.request()
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.group+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.group+json")
			.rx()
			.method("PUT", Entity.json(jsonNode), Group.class);
	}
	
	/**
	 * Delete a specific user group for a specific tenant
	 * Delete a specific user group (by a given user group ID) for a specific tenant (by a given tenant ID).
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_ADMIN
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 204 - A user group was removed.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 403 - Not authorized to perform this operation.</li>
	 *     <li>HTTP 404 - Group not found., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param groupId Unique identifier of the user group.
	 */
	public CompletionStage<Response> deleteUserGroup(final String tenantId, final int groupId) {
		return adapt().path("user").path(valueOf(tenantId)).path("groups").path(valueOf(groupId))
			.request()
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
	
	/**
	 * Retrieve a user group by group name for a specific tenant
	 * Retrieve a user group by group name for a specific tenant (by a given tenant ID).
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_READ <b>OR</b> ROLE_USER_MANAGEMENT_CREATE <b>AND</b> has access to groups
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The request succeeded and the user group is sent in the response.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 403 - Not enough permissions/roles to perform this operation., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - Group not found., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param groupName The name of the user group.
	 * @return
	 */
	public CompletionStage<Group> getUserGroupByName(final String tenantId, final String groupName) {
		return adapt().path("user").path(valueOf(tenantId)).path("groupByName").path(valueOf(groupName))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.group+json")
			.rx()
			.method("GET", Group.class);
	}
	
	/**
	 * Get all user groups for specific user in a specific tenant
	 * Get all user groups for a specific user (by a given user ID) in a specific tenant (by a given tenant ID).
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_READ <b>OR</b> ROLE_USER_MANAGEMENT_CREATE <b>AND</b> is parent of the user
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The request succeeded and all groups for the user are sent in the response.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 403 - Not enough permissions/roles to perform this operation., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - User not found., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param userId Unique identifier of the a user.
	 * @param currentPage The current page of the paginated results.
	 * @param pageSize Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param withTotalElements When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @param withTotalPages When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @return
	 */
	public CompletionStage<GroupReferenceCollection> getUserGroups(final String tenantId, final String userId, final int currentPage, final int pageSize, final boolean withTotalElements, final boolean withTotalPages) {
		return adapt().path("user").path(valueOf(tenantId)).path("users").path(valueOf(userId)).path("groups")
			.queryParam("currentPage", currentPage)
			.queryParam("pageSize", pageSize)
			.queryParam("withTotalElements", withTotalElements)
			.queryParam("withTotalPages", withTotalPages)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.groupreferencecollection+json")
			.rx()
			.method("GET", GroupReferenceCollection.class);
	}
}
