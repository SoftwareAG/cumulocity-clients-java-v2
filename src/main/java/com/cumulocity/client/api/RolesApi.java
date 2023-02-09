// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api;

import java.util.concurrent.Future;
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
	 * Retrieve all user roles </br>
	 * Retrieve all user roles.  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_READ <b>OR</b> ROLE_USER_MANAGEMENT_CREATE <b>AND</b> has access to the user role </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and all user roles are sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 * <p>
	 * @param currentPage The current page of the paginated results.
	 * @param pageSize Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param withTotalElements When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @param withTotalPages When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @return
	 */
	public Future<UserRoleCollection> getUserRoles(final int currentPage, final int pageSize, final boolean withTotalElements, final boolean withTotalPages) {
		return adapt().path("user").path("roles")
			.queryParam("currentPage", currentPage)
			.queryParam("pageSize", pageSize)
			.queryParam("withTotalElements", withTotalElements)
			.queryParam("withTotalPages", withTotalPages)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.rolecollection+json")
			.build("GET")
			.submit(UserRoleCollection.class);
	}
	
	/**
	 * Retrieve a user role by name </br>
	 * Retrieve a user role by name.  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_READ <b>OR</b> ROLE_USER_MANAGEMENT_CREATE <b>AND</b> current user has access to the role with this name </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the user role is sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Role not found.</li>
	 * </ul>
	 * <p>
	 * @param name The name of the user role.
	 * @return
	 */
	public Future<Role> getUserRole(final String name) {
		return adapt().path("user").path("roles").path(valueOf(name))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.role+json")
			.build("GET")
			.submit(Role.class);
	}
	
	/**
	 * Retrieve all roles assigned to a specific user group in a specific tenant </br>
	 * Retrieve all roles assigned to a specific user group (by a given user group ID) in a specific tenant (by a given tenant ID).  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_READ </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request succeeded and the roles are sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not enough permissions/roles to perform this operation.</li>
	 * <li>404 Group not found.</li>
	 * </ul>
	 * <p>
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param groupId Unique identifier of the user group.
	 * @param currentPage The current page of the paginated results.
	 * @param pageSize Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @return
	 */
	public Future<RoleReferenceCollection> getGroupRoles(final String tenantId, final int groupId, final int currentPage, final int pageSize) {
		return adapt().path("user").path(valueOf(tenantId)).path("groups").path(valueOf(groupId)).path("roles")
			.queryParam("currentPage", currentPage)
			.queryParam("pageSize", pageSize)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.rolereferencecollection+json")
			.build("GET")
			.submit(RoleReferenceCollection.class);
	}
	
	/**
	 * Assign a role to a specific user group in a specific tenant </br>
	 * Assign a role to a specific user group (by a given user group ID) in a specific tenant (by a given tenant ID).  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>201 A user role was assigned to a user group.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not authorized to perform this operation.</li>
	 * <li>404 Group not found.</li>
	 * <li>409 Conflict – Role already assigned to the user group.</li>
	 * <li>422 Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 * <p>
	 * @param body 
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param groupId Unique identifier of the user group.
	 * @return
	 */
	public Future<RoleReference> assignGroupRole(final SubscribedRole body, final String tenantId, final int groupId) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("user").path(valueOf(tenantId)).path("groups").path(valueOf(groupId)).path("roles")
			.request()
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.rolereference+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.rolereference+json")
			.build("POST", Entity.json(jsonNode))
			.submit(RoleReference.class);
	}
	
	/**
	 * Unassign a specific role for a specific user group in a specific tenant </br>
	 * Unassign a specific role (given by a role ID) for a specific user group (by a given user group ID) in a specific tenant (by a given tenant ID).  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>204 A role was unassigned from a user group.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not authorized to perform this operation.</li>
	 * <li>404 Role not found.</li>
	 * </ul>
	 * <p>
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param groupId Unique identifier of the user group.
	 * @param roleId Unique identifier of the user role.
	 */
	public Future<Response> unassignGroupRole(final String tenantId, final int groupId, final String roleId) {
		return adapt().path("user").path(valueOf(tenantId)).path("groups").path(valueOf(groupId)).path("roles").path(valueOf(roleId))
			.request()
			.header("Accept", "application/json")
			.build("DELETE")
			.submit();
	}
	
	/**
	 * Assign a role to specific user in a specific tenant </br>
	 * Assign a role to a specific user (by a given user ID) in a specific tenant (by a given tenant ID).  When a role is assigned to a user, a corresponding audit record is created with type "User" and activity "User updated".  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_ADMIN to assign any role to root users in a user hierarchy <b>OR</b> users that are not in any hierarchy<br/> ROLE_USER_MANAGEMENT_ADMIN to assign roles accessible by the parent of assigned user to non-root users in a user hierarchy<br/> ROLE_USER_MANAGEMENT_CREATE to assign roles accessible by the current user <b>AND</b> accessible by the parent of the assigned user to the descendants of the current user in a user hierarchy </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>201 A user role was assigned to a user.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not enough permissions/roles to perform this operation.</li>
	 * <li>404 User not found.</li>
	 * <li>422 Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 * <p>
	 * @param body 
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param userId Unique identifier of the a user.
	 * @return
	 */
	public Future<RoleReference> assignUserRole(final SubscribedRole body, final String tenantId, final String userId) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("user").path(valueOf(tenantId)).path("users").path(valueOf(userId)).path("roles")
			.request()
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.rolereference+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.rolereference+json")
			.build("POST", Entity.json(jsonNode))
			.submit(RoleReference.class);
	}
	
	/**
	 * Unassign a specific role from a specific user in a specific tenant </br>
	 * Unassign a specific role (by a given role ID) from a specific user (by a given user ID) in a specific tenant (by a given tenant ID).  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_READ <b>OR</b> ROLE_USER_MANAGEMENT_CREATE <b>AND</b> is parent of the user <b>AND</b> has access to roles </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>204 A user role was unassigned from a user.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not authorized to perform this operation.</li>
	 * <li>404 User not found.</li>
	 * </ul>
	 * <p>
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param userId Unique identifier of the a user.
	 * @param roleId Unique identifier of the user role.
	 */
	public Future<Response> unassignUserRole(final String tenantId, final String userId, final String roleId) {
		return adapt().path("user").path(valueOf(tenantId)).path("users").path(valueOf(userId)).path("roles").path(valueOf(roleId))
			.request()
			.header("Accept", "application/json")
			.build("DELETE")
			.submit();
	}
}
