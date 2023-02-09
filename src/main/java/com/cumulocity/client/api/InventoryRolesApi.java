// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api;

import java.util.concurrent.Future;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.InventoryRole;
import com.cumulocity.client.model.InventoryAssignment;
import com.cumulocity.client.model.InventoryAssignmentReference;
import com.cumulocity.client.model.InventoryRoleCollection;
import com.cumulocity.client.model.InventoryAssignmentCollection;

/**
 * API methods to create, retrieve, update and delete inventory roles.
 * 
 * > **&#9432; Info:** The Accept header should be provided in all POST/PUT requests, otherwise an empty response body will be returned.
 *  </br>
 * 
 */ 
public class InventoryRolesApi extends AdaptableApi {

	public InventoryRolesApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * Retrieve all inventory roles </br>
	 * Retrieve all inventory roles.  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_READ <b>OR</b> ROLE_USER_MANAGEMENT_CREATE </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request succeeded and all inventory roles are sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 * <p>
	 * @param currentPage The current page of the paginated results.
	 * @param pageSize Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param withTotalElements When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @return
	 */
	public Future<InventoryRoleCollection> getInventoryRoles(final int currentPage, final int pageSize, final boolean withTotalElements) {
		return adapt().path("user").path("inventoryroles")
			.queryParam("currentPage", currentPage)
			.queryParam("pageSize", pageSize)
			.queryParam("withTotalElements", withTotalElements)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.inventoryrolecollection+json")
			.build("GET")
			.submit(InventoryRoleCollection.class);
	}
	
	/**
	 * Create an inventory role </br>
	 * Create an inventory role.  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>201 An inventory role was created.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>409 Duplicate – The inventory role already exists.</li>
	 * <li>422 Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 * <p>
	 * @param body 
	 * @return
	 */
	public Future<InventoryRole> createInventoryRole(final InventoryRole body) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "id");
		return adapt().path("user").path("inventoryroles")
			.request()
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.inventoryrole+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.inventoryrole+json, application/vnd.com.nsn.cumulocity.error+json")
			.build("POST", Entity.json(jsonNode))
			.submit(InventoryRole.class);
	}
	
	/**
	 * Retrieve a specific inventory role </br>
	 * Retrieve a specific inventory role (by a given ID).  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_READ <b>OR</b> ROLE_USER_MANAGEMENT_CREATE <b>AND</b> has access to the inventory role </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request succeeded and the inventory role is sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Role not found.</li>
	 * </ul>
	 * <p>
	 * @param id Unique identifier of the inventory role.
	 * @return
	 */
	public Future<InventoryRole> getInventoryRole(final int id) {
		return adapt().path("user").path("inventoryroles").path(valueOf(id))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.inventoryrole+json, application/vnd.com.nsn.cumulocity.error+json")
			.build("GET")
			.submit(InventoryRole.class);
	}
	
	/**
	 * Update a specific inventory role </br>
	 * Update a specific inventory role (by a given ID).  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 An inventory role was updated.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Role not found.</li>
	 * <li>422 Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 * <p>
	 * @param body 
	 * @param id Unique identifier of the inventory role.
	 * @return
	 */
	public Future<InventoryRole> updateInventoryRole(final InventoryRole body, final int id) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "id");
		return adapt().path("user").path("inventoryroles").path(valueOf(id))
			.request()
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.inventoryrole+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.inventoryrole+json, application/vnd.com.nsn.cumulocity.error+json")
			.build("PUT", Entity.json(jsonNode))
			.submit(InventoryRole.class);
	}
	
	/**
	 * Remove a specific inventory role </br>
	 * Remove a specific inventory role (by a given ID).  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>204 An inventory role was removed.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not authorized to perform this operation.</li>
	 * <li>404 Role not found.</li>
	 * </ul>
	 * <p>
	 * @param id Unique identifier of the inventory role.
	 */
	public Future<Response> deleteInventoryRole(final int id) {
		return adapt().path("user").path("inventoryroles").path(valueOf(id))
			.request()
			.header("Accept", "application/json")
			.build("DELETE")
			.submit();
	}
	
	/**
	 * Retrieve all inventory roles assigned to a user </br>
	 * Retrieve all inventory roles assigned to a specific user (by a given user ID) in a specific tenant (by a given tenant ID).  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_READ <b>OR</b> ROLE_USER_MANAGEMENT_CREATE <b>AND</b> is the parent of the user </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the inventory roles are sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not enough permissions/roles to perform this operation.</li>
	 * <li>404 User not found.</li>
	 * </ul>
	 * <p>
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param userId Unique identifier of the a user.
	 * @return
	 */
	public Future<InventoryAssignmentCollection> getUserInventoryRoles(final String tenantId, final String userId) {
		return adapt().path("user").path(valueOf(tenantId)).path("users").path(valueOf(userId)).path("roles").path("inventory")
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.inventoryassignmentcollection+json")
			.build("GET")
			.submit(InventoryAssignmentCollection.class);
	}
	
	/**
	 * Assign an inventory role to a user </br>
	 * Assign an existing inventory role to a specific user (by a given user ID) in a specific tenant (by a given tenant ID).  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_ADMIN to assign any inventory role to root users in a user hierarchy <b>OR</b> users that are not in any hierarchy<br/> ROLE_USER_MANAGEMENT_ADMIN to assign inventory roles accessible by the parent of the assigned user to non-root users in a user hierarchy<br/> ROLE_USER_MANAGEMENT_CREATE to assign inventory roles accessible by the current user <b>AND</b> accessible by the parent of the assigned user to the descendants of the current user in a user hierarchy </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 An inventory role was assigned to a user.</li>
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
	public Future<InventoryAssignment> assignUserInventoryRole(final InventoryAssignment body, final String tenantId, final String userId) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "id");
		return adapt().path("user").path(valueOf(tenantId)).path("users").path(valueOf(userId)).path("roles").path("inventory")
			.request()
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.inventoryassignment+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.inventoryassignment+json")
			.build("POST", Entity.json(jsonNode))
			.submit(InventoryAssignment.class);
	}
	
	/**
	 * Retrieve a specific inventory role assigned to a user </br>
	 * Retrieve a specific inventory role (by a given ID) assigned to a specific user (by a given user ID) in a specific tenant (by a given tenant ID).  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_READ <b>OR</b> ROLE_USER_MANAGEMENT_CREATE <b>AND</b> is the parent of the user </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the inventory role is sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not enough permissions/roles to perform this operation.</li>
	 * <li>404 Role not found.</li>
	 * </ul>
	 * <p>
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param userId Unique identifier of the a user.
	 * @param id Unique identifier of the inventory assignment.
	 * @return
	 */
	public Future<InventoryAssignment> getUserInventoryRole(final String tenantId, final String userId, final int id) {
		return adapt().path("user").path(valueOf(tenantId)).path("users").path(valueOf(userId)).path("roles").path("inventory").path(valueOf(id))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.inventoryassignment+json")
			.build("GET")
			.submit(InventoryAssignment.class);
	}
	
	/**
	 * Update a specific inventory role assigned to a user </br>
	 * Update a specific inventory role (by a given ID) assigned to a specific user (by a given user ID) in a specific tenant (by a given tenant ID).  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_ADMIN to update the assignment of any inventory roles to root users in a user hierarchy <b>OR</b> users that are not in any hierarchy<br/> ROLE_USER_MANAGEMENT_ADMIN to update the assignment of inventory roles accessible by the assigned user parent, to non-root users in a user hierarchy<br/> ROLE_USER_MANAGEMENT_CREATE to update the assignment of inventory roles accessible by the current user <b>AND</b> the parent of the assigned user to the descendants of the current user in the user hierarchy </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 An inventory assignment was updated.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not enough permissions/roles to perform this operation.</li>
	 * <li>404 Role not found.</li>
	 * <li>422 Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 * <p>
	 * @param body 
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param userId Unique identifier of the a user.
	 * @param id Unique identifier of the inventory assignment.
	 * @return
	 */
	public Future<InventoryAssignment> updateUserInventoryRole(final InventoryAssignmentReference body, final String tenantId, final String userId, final int id) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("user").path(valueOf(tenantId)).path("users").path(valueOf(userId)).path("roles").path("inventory").path(valueOf(id))
			.request()
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.inventoryassignment+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.inventoryassignment+json")
			.build("PUT", Entity.json(jsonNode))
			.submit(InventoryAssignment.class);
	}
	
	/**
	 * Remove a specific inventory role assigned to a user </br>
	 * Remove a specific inventory role (by a given ID) assigned to a specific user (by a given user ID) in a specific tenant (by a given tenant ID).  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_ADMIN <b>AND</b> (is not in user hierarchy <b>OR</b> is root in the user hierarchy) <b>OR</b> ROLE_USER_MANAGEMENT_ADMIN <b>AND</b> is in user hiararchy <b>AND</b> has parent access to inventory assignments <b>OR</b> ROLE_USER_MANAGEMENT_CREATE <b>AND</b> is parent of the user <b>AND</b> is not the current user <b>AND</b> has current user access to inventory assignments <b>AND</b> has parent access to inventory assignments </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>204 An inventory assignment was removed.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not authorized to perform this operation.</li>
	 * <li>404 Role not found.</li>
	 * </ul>
	 * <p>
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param userId Unique identifier of the a user.
	 * @param id Unique identifier of the inventory assignment.
	 */
	public Future<Response> unassignUserInventoryRole(final String tenantId, final String userId, final int id) {
		return adapt().path("user").path(valueOf(tenantId)).path("users").path(valueOf(userId)).path("roles").path("inventory").path(valueOf(id))
			.request()
			.header("Accept", "application/json")
			.build("DELETE")
			.submit();
	}
}
