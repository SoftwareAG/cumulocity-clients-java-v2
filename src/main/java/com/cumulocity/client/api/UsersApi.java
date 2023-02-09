// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api;

import java.util.concurrent.Future;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.User;
import com.cumulocity.client.model.PasswordChange;
import com.cumulocity.client.model.SubscribedUser;
import com.cumulocity.client.model.UserCollection;
import com.cumulocity.client.model.UserTfaData;
import com.cumulocity.client.model.UserReferenceCollection;
import com.cumulocity.client.model.UserReference;

/**
 * API methods to create, retrieve, update and delete users in Cumulocity IoT.
 * 
 * > **&#9432; Info:** The Accept header should be provided in all POST/PUT requests, otherwise an empty response body will be returned.
 *  </br>
 * 
 */ 
public class UsersApi extends AdaptableApi {

	public UsersApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * Retrieve all users for a specific tenant </br>
	 * Retrieve all users for a specific tenant (by a given tenant ID).  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_READ <b>OR</b> ROLE_USER_MANAGEMENT_CREATE </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and all users are sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not enough permissions/roles to perform this operation.</li>
	 * </ul>
	 * <p>
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param currentPage The current page of the paginated results.
	 * @param groups Numeric group identifiers separated by commas. The response will contain only users which belong to at least one of the specified groups.
	 * @param onlyDevices If set to `true`, the response will only contain users created during bootstrap process (starting with “device_”). If the flag is absent or `false` the result will not contain “device_” users. 
	 * @param owner Exact username of the owner of the user
	 * @param pageSize Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param username Prefix or full username
	 * @param withSubusersCount If set to `true`, then each of returned user will contain an additional field “subusersCount”. It is the number of direct subusers (users with corresponding “owner”). 
	 * @param withTotalElements When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @param withTotalPages When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @return
	 */
	public Future<UserCollection> getUsers(final String tenantId, final int currentPage, final String[] groups, final boolean onlyDevices, final String owner, final int pageSize, final String username, final boolean withSubusersCount, final boolean withTotalElements, final boolean withTotalPages) {
		return adapt().path("user").path(valueOf(tenantId)).path("users")
			.queryParam("currentPage", currentPage)
			.queryParam("groups", groups, false)
			.queryParam("onlyDevices", onlyDevices)
			.queryParam("owner", owner)
			.queryParam("pageSize", pageSize)
			.queryParam("username", username)
			.queryParam("withSubusersCount", withSubusersCount)
			.queryParam("withTotalElements", withTotalElements)
			.queryParam("withTotalPages", withTotalPages)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.usercollection+json")
			.build("GET")
			.submit(UserCollection.class);
	}
	
	/**
	 * Create a user for a specific tenant </br>
	 * Create a user for a specific tenant (by a given tenant ID).  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_ADMIN <b>OR</b> ROLE_USER_MANAGEMENT_CREATE <b>AND</b> has access to roles, groups, device permissions and applications </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>201 A user was created.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not enough permissions/roles to perform this operation.</li>
	 * <li>409 Duplicate – The userName or alias already exists.</li>
	 * <li>422 Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 * <p>
	 * @param body 
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @return
	 */
	public Future<User> createUser(final User body, final String tenantId) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "owner");
		removeFromNode(jsonNode, "passwordStrength");
		removeFromNode(jsonNode, "roles");
		removeFromNode(jsonNode, "groups");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "shouldResetPassword");
		removeFromNode(jsonNode, "id");
		removeFromNode(jsonNode, "lastPasswordChange");
		removeFromNode(jsonNode, "twoFactorAuthenticationEnabled");
		removeFromNode(jsonNode, "devicePermissions");
		removeFromNode(jsonNode, "applications");
		return adapt().path("user").path(valueOf(tenantId)).path("users")
			.request()
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.user+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.user+json")
			.build("POST", Entity.json(jsonNode))
			.submit(User.class);
	}
	
	/**
	 * Retrieve a specific user for a specific tenant </br>
	 * Retrieve a specific user (by a given user ID) for a specific tenant (by a given tenant ID).  Users in the response are sorted by username in ascending order. Only objects which the user is allowed to see are returned to the user. The user password is never returned in a GET response. Authentication mechanism is provided by another interface.  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_READ <b>OR</b> ROLE_USER_MANAGEMENT_CREATE <b>AND</b> is parent of the user </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the user is sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not enough permissions/roles to perform this operation.</li>
	 * <li>404 User not found.</li>
	 * </ul>
	 * <p>
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param userId Unique identifier of the a user.
	 * @return
	 */
	public Future<User> getUser(final String tenantId, final String userId) {
		return adapt().path("user").path(valueOf(tenantId)).path("users").path(valueOf(userId))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.user+json")
			.build("GET")
			.submit(User.class);
	}
	
	/**
	 * Update a specific user for a specific tenant </br>
	 * Update a specific user (by a given user ID) for a specific tenant (by a given tenant ID).  Any change in user's roles, device permissions and groups creates corresponding audit records with type "User" and activity "User updated" with information which properties have been changed.  When the user is updated with changed permissions or groups, a corresponding audit record is created with type "User" and activity "User updated".  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_ADMIN to update root users in a user hierarchy <b>OR</b> users that are not in any hierarchy<br/> ROLE_USER_MANAGEMENT_ADMIN to update non-root users in a user hierarchy <b>AND</b> whose parents have access to roles, groups, device permissions and applications being assigned<br/> ROLE_USER_MANAGEMENT_CREATE to update descendants of the current user in a user hierarchy <b>AND</b> whose parents have access to roles, groups, device permissions and applications being assigned </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 A user was updated.</li>
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
	public Future<User> updateUser(final User body, final String tenantId, final String userId) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "owner");
		removeFromNode(jsonNode, "passwordStrength");
		removeFromNode(jsonNode, "roles");
		removeFromNode(jsonNode, "groups");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "shouldResetPassword");
		removeFromNode(jsonNode, "id");
		removeFromNode(jsonNode, "lastPasswordChange");
		removeFromNode(jsonNode, "userName");
		removeFromNode(jsonNode, "twoFactorAuthenticationEnabled");
		removeFromNode(jsonNode, "devicePermissions");
		removeFromNode(jsonNode, "applications");
		return adapt().path("user").path(valueOf(tenantId)).path("users").path(valueOf(userId))
			.request()
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.user+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.user+json")
			.build("PUT", Entity.json(jsonNode))
			.submit(User.class);
	}
	
	/**
	 * Delete a specific user for a specific tenant </br>
	 * Delete a specific user (by a given user ID) for a specific tenant (by a given tenant ID).  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_ADMIN <b>OR</b> ROLE_USER_MANAGEMENT_CREATE <b>AND</b> is parent of the user <b>AND</b> not the current user </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>204 A user was removed.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not authorized to perform this operation.</li>
	 * <li>404 User not found.</li>
	 * </ul>
	 * <p>
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param userId Unique identifier of the a user.
	 */
	public Future<Response> deleteUser(final String tenantId, final String userId) {
		return adapt().path("user").path(valueOf(tenantId)).path("users").path(valueOf(userId))
			.request()
			.header("Accept", "application/json")
			.build("DELETE")
			.submit();
	}
	
	/**
	 * Update a specific user's password of a specific tenant </br>
	 * Update a specific user's password (by a given user ID) of a specific tenant (by a given tenant ID).  Changing the user's password creates a corresponding audit record of type "User" and activity "User updated", and specifying that the password has been changed.  > **⚠️ Important:** If the tenant uses OAI-Secure authentication, the target user will be logged out.  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_ADMIN to update root users in a user hierarchy <b>OR</b> users that are not in any hierarchy<br/> ROLE_USER_MANAGEMENT_ADMIN to update non-root users in a user hierarchy <b>AND</b> whose parents have access to assigned roles, groups, device permissions and applications<br/> ROLE_USER_MANAGEMENT_CREATE to update descendants of the current user in a user hierarchy <b>AND</b> whose parents have access to assigned roles, groups, device permissions and applications </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 A user was updated.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not enough permissions/roles to perform this operation.</li>
	 * <li>422 Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 * <p>
	 * @param body 
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param userId Unique identifier of the a user.
	 */
	public Future<Response> updateUserPassword(final PasswordChange body, final String tenantId, final String userId) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("user").path(valueOf(tenantId)).path("users").path(valueOf(userId)).path("password")
			.request()
			.header("Content-Type", "application/json")
			.header("Accept", "application/json")
			.build("PUT", Entity.json(jsonNode))
			.submit();
	}
	
	/**
	 * Retrieve the TFA settings of a specific user </br>
	 * Retrieve the two-factor authentication settings for the specified user.  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_READ <b>OR</b> (ROLE_USER_MANAGEMENT_CREATE <b>AND</b> is parent of the user) <b>OR</b> is the current user </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the TFA settings are sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not enough permissions/roles to perform this operation.</li>
	 * <li>404 User not found.</li>
	 * </ul>
	 * <p>
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param userId Unique identifier of the a user.
	 * @return
	 */
	public Future<UserTfaData> getUserTfaSettings(final String tenantId, final String userId) {
		return adapt().path("user").path(valueOf(tenantId)).path("users").path(valueOf(userId)).path("tfa")
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.build("GET")
			.submit(UserTfaData.class);
	}
	
	/**
	 * Retrieve a user by username in a specific tenant </br>
	 * Retrieve a user by username in a specific tenant (by a given tenant ID).  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_ADMIN <b>OR</b> ROLE_USER_MANAGEMENT_CREATE <b>AND</b> is parent of the user </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the user is sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not enough permissions/roles to perform this operation.</li>
	 * <li>404 User not found.</li>
	 * </ul>
	 * <p>
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param username The username of the a user.
	 * @return
	 */
	public Future<User> getUserByUsername(final String tenantId, final String username) {
		return adapt().path("user").path(valueOf(tenantId)).path("userByName").path(valueOf(username))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.user+json")
			.build("GET")
			.submit(User.class);
	}
	
	/**
	 * Retrieve the users of a specific user group of a specific tenant </br>
	 * Retrieve the users of a specific user group (by a given user group ID) of a specific tenant (by a given tenant ID).  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_READ <b>OR</b> (ROLE_USER_MANAGEMENT_CREATE <b>AND</b> has access to the user group) </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the users are sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not enough permissions/roles to perform this operation.</li>
	 * <li>404 Group not found.</li>
	 * </ul>
	 * <p>
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param groupId Unique identifier of the user group.
	 * @param currentPage The current page of the paginated results.
	 * @param pageSize Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param withTotalElements When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @return
	 */
	public Future<UserReferenceCollection> getUsersFromUserGroup(final String tenantId, final int groupId, final int currentPage, final int pageSize, final boolean withTotalElements) {
		return adapt().path("user").path(valueOf(tenantId)).path("groups").path(valueOf(groupId)).path("users")
			.queryParam("currentPage", currentPage)
			.queryParam("pageSize", pageSize)
			.queryParam("withTotalElements", withTotalElements)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.userreferencecollection+json")
			.build("GET")
			.submit(UserReferenceCollection.class);
	}
	
	/**
	 * Add a user to a specific user group of a specific tenant </br>
	 * Add a user to a specific user group (by a given user group ID) of a specific tenant (by a given tenant ID).  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_ADMIN to assign root users in a user hierarchy <b>OR</b> users that are not in any hierarchy to any group<br/> ROLE_USER_MANAGEMENT_ADMIN to assign non-root users in a user hierarchy to groups accessible by the parent of assigned user<br/> ROLE_USER_MANAGEMENT_CREATE to assign descendants of the current user in a user hierarchy to groups accessible by current user <b>AND</b> accessible by the parent of assigned user </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>201 The user was added to the group.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not enough permissions/roles to perform this operation.</li>
	 * <li>404 Group not found.</li>
	 * <li>422 Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 * <p>
	 * @param body 
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param groupId Unique identifier of the user group.
	 * @return
	 */
	public Future<UserReference> assignUserToUserGroup(final SubscribedUser body, final String tenantId, final int groupId) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("user").path(valueOf(tenantId)).path("groups").path(valueOf(groupId)).path("users")
			.request()
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.userreference+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.userreference+json")
			.build("POST", Entity.json(jsonNode))
			.submit(UserReference.class);
	}
	
	/**
	 * Remove a specific user from a specific user group of a specific tenant </br>
	 * Remove a specific user (by a given user ID) from a specific user group (by a given user group ID) of a specific tenant (by a given tenant ID).  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_ADMIN <b>OR</b> ROLE_USER_MANAGEMENT_CREATE <b>AND</b> is parent of the user <b>AND</b> is not the current user </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>204 A user was removed from a group.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not authorized to perform this operation.</li>
	 * <li>404 User not found.</li>
	 * </ul>
	 * <p>
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param groupId Unique identifier of the user group.
	 * @param userId Unique identifier of the a user.
	 */
	public Future<Response> removeUserFromUserGroup(final String tenantId, final int groupId, final String userId) {
		return adapt().path("user").path(valueOf(tenantId)).path("groups").path(valueOf(groupId)).path("users").path(valueOf(userId))
			.request()
			.header("Accept", "application/json")
			.build("DELETE")
			.submit();
	}
	
	/**
	 * Terminate a user's session </br>
	 * After logging out, a user has to enter valid credentials again to get access to the platform.  The request is responsible for removing cookies from the browser and invalidating internal platform access tokens. 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the user is logged out.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 * <p>
	 * @param cookie The authorization cookie storing the access token of the user. This parameter is specific to OAI-Secure authentication.
	 * @param xXSRFTOKEN Prevents XRSF attack of the authenticated user. This parameter is specific to OAI-Secure authentication.
	 */
	public Future<Response> logout(final String cookie, final String xXSRFTOKEN) {
		return adapt().path("user").path("logout")
			.request()
			.header("Cookie", cookie)
			.header("X-XSRF-TOKEN", xXSRFTOKEN)
			.header("Accept", "application/json")
			.build("POST")
			.submit();
	}
}
