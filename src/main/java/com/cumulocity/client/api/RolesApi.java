// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.SubscribedRole;
import com.cumulocity.client.model.UserRoleCollection;
import com.cumulocity.client.model.Role;
import com.cumulocity.client.model.RoleReferenceCollection;
import com.cumulocity.client.model.RoleReference;

/**
 * API methods to create, retrieve, update and delete user roles.
 * 
 * > **&#9432; Info:** The Accept header should be provided in all POST requests, otherwise an empty response body will be returned.
 *  </br>
 * 
 */
public class RolesApi extends AdaptableApi {

	public RolesApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * Retrieve all user roles
	 * Retrieve all user roles.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_READ <b>OR</b> ROLE_USER_MANAGEMENT_CREATE <b>AND</b> has access to the user role
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The request has succeeded and all user roles are sent in the response.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param currentPage The current page of the paginated results.
	 * @param pageSize Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param withTotalElements When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @param withTotalPages When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @return
	 */
	public CompletionStage<UserRoleCollection> getUserRoles(final int currentPage, final int pageSize, final boolean withTotalElements, final boolean withTotalPages) {
		return adapt().path("user").path("roles")
			.queryParam("currentPage", currentPage)
			.queryParam("pageSize", pageSize)
			.queryParam("withTotalElements", withTotalElements)
			.queryParam("withTotalPages", withTotalPages)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.rolecollection+json")
			.rx()
			.method("GET", UserRoleCollection.class);
	}
	
	/**
	 * Retrieve a user role by name
	 * Retrieve a user role by name.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_READ <b>OR</b> ROLE_USER_MANAGEMENT_CREATE <b>AND</b> current user has access to the role with this name
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The request has succeeded and the user role is sent in the response.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - Role not found., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param name The name of the user role.
	 * @return
	 */
	public CompletionStage<Role> getUserRole(final String name) {
		return adapt().path("user").path("roles").path(valueOf(name))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.role+json")
			.rx()
			.method("GET", Role.class);
	}
	
	/**
	 * Retrieve all roles assigned to a specific user group in a specific tenant
	 * Retrieve all roles assigned to a specific user group (by a given user group ID) in a specific tenant (by a given tenant ID).
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_READ
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The request succeeded and the roles are sent in the response.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 403 - Not enough permissions/roles to perform this operation., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - Group not found., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param groupId Unique identifier of the user group.
	 * @param currentPage The current page of the paginated results.
	 * @param pageSize Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @return
	 */
	public CompletionStage<RoleReferenceCollection> getGroupRoles(final String tenantId, final int groupId, final int currentPage, final int pageSize) {
		return adapt().path("user").path(valueOf(tenantId)).path("groups").path(valueOf(groupId)).path("roles")
			.queryParam("currentPage", currentPage)
			.queryParam("pageSize", pageSize)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.rolereferencecollection+json")
			.rx()
			.method("GET", RoleReferenceCollection.class);
	}
	
	/**
	 * Assign a role to a specific user group in a specific tenant
	 * Assign a role to a specific user group (by a given user group ID) in a specific tenant (by a given tenant ID).
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_ADMIN
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 201 - A user role was assigned to a user group.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 403 - Not authorized to perform this operation.</li>
	 *     <li>HTTP 404 - Group not found., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 409 - Conflict – Role already assigned to the user group.</li>
	 *     <li>HTTP 422 - Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 * @param body 
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param groupId Unique identifier of the user group.
	 * @return
	 */
	public CompletionStage<RoleReference> assignGroupRole(final SubscribedRole body, final String tenantId, final int groupId) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("user").path(valueOf(tenantId)).path("groups").path(valueOf(groupId)).path("roles")
			.request()
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.rolereference+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.rolereference+json")
			.rx()
			.method("POST", Entity.json(jsonNode), RoleReference.class);
	}
	
	/**
	 * Unassign a specific role for a specific user group in a specific tenant
	 * Unassign a specific role (given by a role ID) for a specific user group (by a given user group ID) in a specific tenant (by a given tenant ID).
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_ADMIN
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 204 - A role was unassigned from a user group.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 403 - Not authorized to perform this operation.</li>
	 *     <li>HTTP 404 - Role not found., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param groupId Unique identifier of the user group.
	 * @param roleId Unique identifier of the user role.
	 */
	public CompletionStage<Response> unassignGroupRole(final String tenantId, final int groupId, final String roleId) {
		return adapt().path("user").path(valueOf(tenantId)).path("groups").path(valueOf(groupId)).path("roles").path(valueOf(roleId))
			.request()
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
	
	/**
	 * Assign a role to specific user in a specific tenant
	 * Assign a role to a specific user (by a given user ID) in a specific tenant (by a given tenant ID).
	 * 
	 * When a role is assigned to a user, a corresponding audit record is created with type "User" and activity "User updated".
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_ADMIN to assign any role to root users in a user hierarchy <b>OR</b> users that are not in any hierarchy<br/>
	 * ROLE_USER_MANAGEMENT_ADMIN to assign roles accessible by the parent of assigned user to non-root users in a user hierarchy<br/>
	 * ROLE_USER_MANAGEMENT_CREATE to assign roles accessible by the current user <b>AND</b> accessible by the parent of the assigned user to the descendants of the current user in a user hierarchy
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 201 - A user role was assigned to a user.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 403 - Not enough permissions/roles to perform this operation., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - User not found., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 422 - Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 * @param body 
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param userId Unique identifier of the a user.
	 * @return
	 */
	public CompletionStage<RoleReference> assignUserRole(final SubscribedRole body, final String tenantId, final String userId) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("user").path(valueOf(tenantId)).path("users").path(valueOf(userId)).path("roles")
			.request()
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.rolereference+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.rolereference+json")
			.rx()
			.method("POST", Entity.json(jsonNode), RoleReference.class);
	}
	
	/**
	 * Unassign a specific role from a specific user in a specific tenant
	 * Unassign a specific role (by a given role ID) from a specific user (by a given user ID) in a specific tenant (by a given tenant ID).
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_USER_MANAGEMENT_READ <b>OR</b> ROLE_USER_MANAGEMENT_CREATE <b>AND</b> is parent of the user <b>AND</b> has access to roles
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 204 - A user role was unassigned from a user.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 403 - Not authorized to perform this operation.</li>
	 *     <li>HTTP 404 - User not found., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param userId Unique identifier of the a user.
	 * @param roleId Unique identifier of the user role.
	 */
	public CompletionStage<Response> unassignUserRole(final String tenantId, final String userId, final String roleId) {
		return adapt().path("user").path(valueOf(tenantId)).path("users").path(valueOf(userId)).path("roles").path(valueOf(roleId))
			.request()
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
}
