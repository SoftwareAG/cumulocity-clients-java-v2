// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api;

import java.util.concurrent.Future;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.NotificationTokenClaims;
import com.cumulocity.client.model.NotificationToken;
import com.cumulocity.client.model.Response1;

/**
 * In order to receive subscribed notifications, a consumer application or microservice must obtain an authorization token that provides proof that the holder is allowed to receive subscribed notifications. </br>
 * 
 */ 
public class TokensApi extends AdaptableApi {

	public TokensApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * Create a notification token </br>
	 * Create a new JWT (JSON web token) access token which can be used to establish a successful WebSocket connection to read a sequence of notifications.  In general, each request to obtain an access token consists of:  *  The subscriber name which the client wishes to be identified with. *  The subscription name. This value must be associated with a subscription that's already been created and in essence, the obtained token will give the ability to read notifications for the subscription that is specified here. *  The token expiration duration.  <section><h5>Required roles</h5> ROLE_NOTIFICATION_2_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 A notification token was created.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not enough permissions/roles to perform this operation.</li>
	 * <li>422 Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 * <p>
	 * @param body 
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 * @return
	 */
	public Future<NotificationToken> createToken(final NotificationTokenClaims body, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		return getRootTarget().path("notification2").path("token")
				.request()
				.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
				.header("Content-Type", "application/json")
				.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
				.build("POST", Entity.json(jsonNode))
				.submit(NotificationToken.class);
	}
	
	/**
	 * Unsubscribe a subscriber </br>
	 * Unsubscribe a notification subscriber using the notification token.  Once a subscription is made, notifications will be kept until they are consumed by all subscribers who have previously connected to the subscription. For non-volatile subscriptions, this can result in notifications remaining in storage if never consumed by the application. They will be deleted if a tenant is deleted. It can take up considerable space in permanent storage for high-frequency notification sources. Therefore, we recommend you to unsubscribe a subscriber that will never run again. 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The notification subscription was deleted or is scheduled for deletion.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 * <p>
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 * @param token Subscriptions associated with this token will be removed.
	 * @return
	 */
	public Future<Response1> unsubscribeSubscriber(final String xCumulocityProcessingMode, final String token) {
		return getRootTarget().path("notification2").path("unsubscribe")
			.queryParam("token", token)
				.request()
				.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
				.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
				.build("POST")
				.submit(Response1.class);
	}
}
