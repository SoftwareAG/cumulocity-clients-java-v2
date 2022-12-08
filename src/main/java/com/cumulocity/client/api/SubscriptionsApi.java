// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api;

import java.util.concurrent.Future;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.NotificationSubscription;
import com.cumulocity.client.model.NotificationSubscriptionCollection;

/**
 * Methods to create, retrieve and delete notification subscriptions. </br>
 * 
 */ 
public class SubscriptionsApi extends AdaptableApi {

	public SubscriptionsApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * Retrieve all subscriptions </br>
	 * Retrieve all subscriptions on your tenant, or a specific subset based on queries.  <section><h5>Required roles</h5> ROLE_NOTIFICATION_2_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and all subscriptions are sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not enough permissions/roles to perform this operation.</li>
	 * </ul>
	 * <p>
	 * @param context The context to which the subscription is associated.
	 * @param currentPage The current page of the paginated results.
	 * @param pageSize Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param source The managed object ID to which the subscription is associated.
	 * @param withTotalPages When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @return
	 */
	public Future<NotificationSubscriptionCollection> getSubscriptions(final String context, final int currentPage, final int pageSize, final String source, final boolean withTotalPages) {
		return getRootTarget().path("notification2").path("subscriptions")
			.queryParam("context", context)
			.queryParam("currentPage", valueOf(currentPage))
			.queryParam("pageSize", valueOf(pageSize))
			.queryParam("source", source)
			.queryParam("withTotalPages", valueOf(withTotalPages))
				.request()
				.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.subscriptioncollection+json")
				.build("GET")
				.submit(NotificationSubscriptionCollection.class);
	}
	
	/**
	 * Create a subscription </br>
	 * Create a new subscription, for example, a subscription that forwards measurements and events of a specific type for a given device.  In general, each subscription may consist of:  *  The managed object to which the subscription is associated. *  The context under which the subscription is to be processed. *  The name of the subscription. *  The applicable filter criteria. *  The option to only include specific custom fragments in the forwarded data.  <section><h5>Required roles</h5> ROLE_NOTIFICATION_2_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 A notification subscription was created.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not enough permissions/roles to perform this operation.</li>
	 * <li>404 Managed object not found.</li>
	 * <li>409 Duplicated subscription.</li>
	 * <li>422 Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 * <p>
	 * @param body 
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 * @return
	 */
	public Future<NotificationSubscription> createSubscription(final NotificationSubscription body, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "id");
		removeFromNode(jsonNode, "source", "self");
		return getRootTarget().path("notification2").path("subscriptions")
				.request()
				.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
				.header("Content-Type", "application/vnd.com.nsn.cumulocity.subscription+json")
				.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.subscription+json")
				.build("POST", Entity.json(jsonNode))
				.submit(NotificationSubscription.class);
	}
	
	/**
	 * Remove subscriptions by source </br>
	 * Remove subscriptions by source and context.  >**&#9432; Info:** The request will result in an error if there are no query parameters. The `source` parameter is optional only if the `context` parameter equals `tenant`.  <section><h5>Required roles</h5> ROLE_NOTIFICATION_2_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>204 A collection of subscriptions was removed.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not enough permissions/roles to perform this operation.</li>
	 * <li>422 Unprocessable Entity – error in query parameters</li>
	 * </ul>
	 * <p>
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 * @param context The context to which the subscription is associated. > **&#9432; Info:** If the value is `mo`, then `source` must also be provided in the query. 
	 * @param source The managed object ID to which the subscription is associated.
	 */
	public Future<Response> deleteSubscriptions(final String xCumulocityProcessingMode, final String context, final String source) {
		return getRootTarget().path("notification2").path("subscriptions")
			.queryParam("context", context)
			.queryParam("source", source)
				.request()
				.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
				.header("Accept", "application/json")
				.build("DELETE")
				.submit();
	}
	
	/**
	 * Retrieve a specific subscription </br>
	 * Retrieve a specific subscription by a given ID.  <section><h5>Required roles</h5> ROLE_NOTIFICATION_2_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the subscription is sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not enough permissions/roles to perform this operation.</li>
	 * <li>404 Subscription not found.</li>
	 * </ul>
	 * <p>
	 * @param id Unique identifier of the notification subscription.
	 * @return
	 */
	public Future<NotificationSubscription> getSubscription(final String id) {
		return getRootTarget().path("notification2").path("subscriptions").path(valueOf(id))
				.request()
				.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.subscription+json")
				.build("GET")
				.submit(NotificationSubscription.class);
	}
	
	/**
	 * Remove a specific subscription </br>
	 * Remove a specific subscription by a given ID.  <section><h5>Required roles</h5> ROLE_NOTIFICATION_2_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>204 A subscription was removed.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not enough permissions/roles to perform this operation.</li>
	 * <li>404 Subscription not found.</li>
	 * </ul>
	 * <p>
	 * @param id Unique identifier of the notification subscription.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	public Future<Response> deleteSubscription(final String id, final String xCumulocityProcessingMode) {
		return getRootTarget().path("notification2").path("subscriptions").path(valueOf(id))
				.request()
				.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
				.header("Accept", "application/json")
				.build("DELETE")
				.submit();
	}
}
