// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api;

import java.util.concurrent.Future;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.CurrentUser;
import com.cumulocity.client.model.PasswordChange;
import com.cumulocity.client.model.CurrentUserTotpSecretActivity;
import com.cumulocity.client.model.CurrentUserTotpCode;
import com.cumulocity.client.model.CurrentUserTotpSecret;

/**
 * The current user is the user that is currently authenticated with Cumulocity IoT for the API calls.
 * 
 * > **&#9432; Info:** The Accept header should be provided in all PUT requests, otherwise an empty response body will be returned.
 *  </br>
 * 
 */ 
public class CurrentUserApi extends AdaptableApi {

	public CurrentUserApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * Retrieve the current user </br>
	 * Retrieve the user reference of the current user.  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_OWN_READ <b>OR</b> ROLE_SYSTEM </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the current user is sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 * @return
	 */
	public Future<CurrentUser> getCurrentUser() {
		return adapt().path("user").path("currentUser")
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.currentuser+json")
			.build("GET")
			.submit(CurrentUser.class);
	}
	
	/**
	 * Update the current user </br>
	 * Update the current user.  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_OWN_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The current user was updated.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>422 Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 * <p>
	 * @param body 
	 * @return
	 */
	public Future<CurrentUser> updateCurrentUser(final CurrentUser body) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "effectiveRoles");
		removeFromNode(jsonNode, "shouldResetPassword");
		removeFromNode(jsonNode, "id");
		removeFromNode(jsonNode, "lastPasswordChange");
		removeFromNode(jsonNode, "twoFactorAuthenticationEnabled");
		removeFromNode(jsonNode, "devicePermissions");
		return adapt().path("user").path("currentUser")
			.request()
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.currentuser+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.currentuser+json")
			.build("PUT", Entity.json(jsonNode))
			.submit(CurrentUser.class);
	}
	
	/**
	 * Update the current user's password </br>
	 * Update the current user's  password.  > **⚠️ Important:** If the tenant uses OAI-Secure authentication, the current user will not be logged out. Instead, a new cookie will be set with a new token, and the previous token will expire within a minute.  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_OWN_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The current user password was updated.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>422 Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 * <p>
	 * @param body 
	 */
	public Future<Response> updateCurrentUserPassword(final PasswordChange body) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("user").path("currentUser").path("password")
			.request()
			.header("Content-Type", "application/json")
			.header("Accept", "application/json")
			.build("PUT", Entity.json(jsonNode))
			.submit();
	}
	
	/**
	 * Generate secret to set up TFA </br>
	 * Generate a secret code to create a QR code to set up the two-factor authentication functionality using a TFA app/service.  For more information about the feature, see [User Guide > Administration > Two-factor authentication](https://cumulocity.com/guides/users-guide/administration/#tfa) in the *Cumulocity IoT documentation*.  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_OWN_READ <b>OR</b> ROLE_SYSTEM </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the secret is sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 * @return
	 */
	public Future<CurrentUserTotpSecret> generateTfaSecret() {
		return adapt().path("user").path("currentUser").path("totpSecret")
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.build("POST")
			.submit(CurrentUserTotpSecret.class);
	}
	
	/**
	 * Returns the activation state of the two-factor authentication feature. </br>
	 * Returns the activation state of the two-factor authentication feature for the current user.  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_OWN_READ <b>OR</b> ROLE_SYSTEM </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 Returns the activation state.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 User not found.</li>
	 * </ul>
	 * @return
	 */
	public Future<CurrentUserTotpSecretActivity> getTfaState() {
		return adapt().path("user").path("currentUser").path("totpSecret").path("activity")
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.build("GET")
			.submit(CurrentUserTotpSecretActivity.class);
	}
	
	/**
	 * Activates or deactivates the two-factor authentication feature </br>
	 * Activates or deactivates the two-factor authentication feature for the current user.  For more information about the feature, see [User Guide > Administration > Two-factor authentication](https://cumulocity.com/guides/users-guide/administration/#tfa) in the *Cumulocity IoT documentation*.  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_OWN_READ <b>OR</b> ROLE_SYSTEM </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>204 The two-factor authentication was activated or deactivated.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Cannot deactivate TOTP setup.</li>
	 * <li>404 User not found.</li>
	 * </ul>
	 * <p>
	 * @param body 
	 */
	public Future<Response> setTfaState(final CurrentUserTotpSecretActivity body) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("user").path("currentUser").path("totpSecret").path("activity")
			.request()
			.header("Content-Type", "application/json")
			.header("Accept", "application/json")
			.build("POST", Entity.json(jsonNode))
			.submit();
	}
	
	/**
	 * Verify TFA code </br>
	 * Verifies the authentication code that the current user received from a TFA app/service and uploaded to the platform to gain access or enable the two-factor authentication feature.  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_OWN_READ <b>OR</b> ROLE_SYSTEM </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>204 The sent code was correct and the access can be granted.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Invalid verification code.</li>
	 * <li>404 Cannot validate TFA TOTP code - user's TFA TOTP secret does not exist.</li>
	 * <li>422 Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 * <p>
	 * @param body 
	 */
	public Future<Response> verifyTfaCode(final CurrentUserTotpCode body) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("user").path("currentUser").path("totpSecret").path("verify")
			.request()
			.header("Content-Type", "application/json")
			.header("Accept", "application/json")
			.build("POST", Entity.json(jsonNode))
			.submit();
	}
}
