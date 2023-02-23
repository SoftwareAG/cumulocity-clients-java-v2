// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
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
	 * Retrieve all subscriptions
	 * Retrieve all subscriptions on your tenant, or a specific subset based on queries.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_NOTIFICATION_2_ADMIN
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The request has succeeded and all subscriptions are sent in the response.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 403 - Not enough permissions/roles to perform this operation., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param context The context to which the subscription is associated.
	 * @param currentPage The current page of the paginated results.
	 * @param pageSize Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param source The managed object ID to which the subscription is associated.
	 * @param withTotalPages When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @return
	 */
	public CompletionStage<NotificationSubscriptionCollection> getSubscriptions(final String context, final int currentPage, final int pageSize, final String source, final boolean withTotalPages) {
		return adapt().path("notification2").path("subscriptions")
			.queryParam("context", context)
			.queryParam("currentPage", currentPage)
			.queryParam("pageSize", pageSize)
			.queryParam("source", source)
			.queryParam("withTotalPages", withTotalPages)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.subscriptioncollection+json")
			.rx()
			.method("GET", NotificationSubscriptionCollection.class);
	}
	
	/**
	 * Create a subscription
	 * Create a new subscription, for example, a subscription that forwards measurements and events of a specific type for a given device.
	 * 
	 * In general, each subscription may consist of:
	 * 
	 * *  The managed object to which the subscription is associated.
	 * *  The context under which the subscription is to be processed.
	 * *  The name of the subscription.
	 * *  The applicable filter criteria.
	 * *  The option to only include specific custom fragments in the forwarded data.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_NOTIFICATION_2_ADMIN
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 201 - A notification subscription was created.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 403 - Not enough permissions/roles to perform this operation., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - Managed object not found., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 409 - Duplicated subscription., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 422 - Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 * @param body 
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 * @return
	 */
	public CompletionStage<NotificationSubscription> createSubscription(final NotificationSubscription body, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "id");
		removeFromNode(jsonNode, "source", "self");
		return adapt().path("notification2").path("subscriptions")
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.subscription+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.subscription+json")
			.rx()
			.method("POST", Entity.json(jsonNode), NotificationSubscription.class);
	}
	
	/**
	 * Remove subscriptions by source
	 * Remove subscriptions by source and context.
	 * 
	 * >**&#9432; Info:** The request will result in an error if there are no query parameters. The `source` parameter is optional only if the `context` parameter equals `tenant`.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_NOTIFICATION_2_ADMIN
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 204 - A collection of subscriptions was removed.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 403 - Not enough permissions/roles to perform this operation., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 422 - Unprocessable Entity – error in query parameters</li>
	 * </ul>
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 * @param context The context to which the subscription is associated. > **&#9432; Info:** If the value is `mo`, then `source` must also be provided in the query. 
	 * @param source The managed object ID to which the subscription is associated.
	 */
	public CompletionStage<Response> deleteSubscriptions(final String xCumulocityProcessingMode, final String context, final String source) {
		return adapt().path("notification2").path("subscriptions")
			.queryParam("context", context)
			.queryParam("source", source)
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
	
	/**
	 * Retrieve a specific subscription
	 * Retrieve a specific subscription by a given ID.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_NOTIFICATION_2_ADMIN
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The request has succeeded and the subscription is sent in the response.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 403 - Not enough permissions/roles to perform this operation., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - Subscription not found., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param id Unique identifier of the notification subscription.
	 * @return
	 */
	public CompletionStage<NotificationSubscription> getSubscription(final String id) {
		return adapt().path("notification2").path("subscriptions").path(valueOf(id))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.subscription+json")
			.rx()
			.method("GET", NotificationSubscription.class);
	}
	
	/**
	 * Remove a specific subscription
	 * Remove a specific subscription by a given ID.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_NOTIFICATION_2_ADMIN
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 204 - A subscription was removed.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 403 - Not enough permissions/roles to perform this operation., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - Subscription not found., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param id Unique identifier of the notification subscription.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	public CompletionStage<Response> deleteSubscription(final String id, final String xCumulocityProcessingMode) {
		return adapt().path("notification2").path("subscriptions").path(valueOf(id))
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
}
