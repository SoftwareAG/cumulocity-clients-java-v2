// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api;

import java.util.concurrent.Future;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.RetentionRule;
import com.cumulocity.client.model.RetentionRuleCollection;

/**
 * It is possible to define rules that make the platform remove certain data. A retention rule shows which data will be deleted. For example, a retention rule with `dataType=EVENT` and `maximumAge=30` removes from the system all events older than 30 days.
 * 
 * > **&#9432; Info:** The Accept header should be provided in all POST/PUT requests, otherwise an empty response body will be returned.
 *  </br>
 * 
 */ 
public class RetentionRulesApi extends AdaptableApi {

	public RetentionRulesApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * Retrieve all retention rules </br>
	 * Retrieve all retention rules on your tenant.  <section><h5>Required roles</h5> ROLE_RETENTION_RULE_READ </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and all retention rules are sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not authorized to perform this operation.</li>
	 * </ul>
	 * <p>
	 * @param currentPage The current page of the paginated results.
	 * @param pageSize Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param withTotalElements When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @param withTotalPages When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @return
	 */
	public Future<RetentionRuleCollection> getRetentionRules(final int currentPage, final int pageSize, final boolean withTotalElements, final boolean withTotalPages) {
		return adapt().path("retention").path("retentions")
			.queryParam("currentPage", currentPage)
			.queryParam("pageSize", pageSize)
			.queryParam("withTotalElements", withTotalElements)
			.queryParam("withTotalPages", withTotalPages)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.retentionrulecollection+json")
			.build("GET")
			.submit(RetentionRuleCollection.class);
	}
	
	/**
	 * Create a retention rule </br>
	 * Create a retention rule on your tenant.  <section><h5>Required roles</h5> ROLE_RETENTION_RULE_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>201 A retention rule was created.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not authorized to perform this operation.</li>
	 * <li>422 Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 * <p>
	 * @param body 
	 * @return
	 */
	public Future<RetentionRule> createRetentionRule(final RetentionRule body) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "id");
		return adapt().path("retention").path("retentions")
			.request()
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.retentionrule+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.retentionrule+json")
			.build("POST", Entity.json(jsonNode))
			.submit(RetentionRule.class);
	}
	
	/**
	 * Retrieve a retention rule </br>
	 * Retrieve a specific retention rule by a given ID.  <section><h5>Required roles</h5> ROLE_RETENTION_RULE_READ </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the retention rule is sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not authorized to perform this operation.</li>
	 * <li>404 Retention rule not found.</li>
	 * </ul>
	 * <p>
	 * @param id Unique identifier of the retention rule.
	 * @return
	 */
	public Future<RetentionRule> getRetentionRule(final String id) {
		return adapt().path("retention").path("retentions").path(valueOf(id))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.retentionrule+json")
			.build("GET")
			.submit(RetentionRule.class);
	}
	
	/**
	 * Update a retention rule </br>
	 * Update a specific retention rule by a given ID.  <section><h5>Required roles</h5> ROLE_RETENTION_RULE_ADMIN <b>AND</b> (the rule is editable <b>OR</b> it's the tenant management) </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 A retention rule was updated.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not authorized to perform this operation.</li>
	 * <li>404 Retention rule not found.</li>
	 * <li>422 Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 * <p>
	 * @param body 
	 * @param id Unique identifier of the retention rule.
	 * @return
	 */
	public Future<RetentionRule> updateRetentionRule(final RetentionRule body, final String id) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "id");
		return adapt().path("retention").path("retentions").path(valueOf(id))
			.request()
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.retentionrule+json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.retentionrule+json")
			.build("PUT", Entity.json(jsonNode))
			.submit(RetentionRule.class);
	}
	
	/**
	 * Remove a retention rule </br>
	 * Remove a specific retention rule by a given ID.  <section><h5>Required roles</h5> ROLE_RETENTION_RULE_ADMIN <b>AND</b> the rule is editable </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>204 A retention rule was removed.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not authorized to perform this operation.</li>
	 * <li>404 Retention rule not found.</li>
	 * </ul>
	 * <p>
	 * @param id Unique identifier of the retention rule.
	 */
	public Future<Response> deleteRetentionRule(final String id) {
		return adapt().path("retention").path("retentions").path(valueOf(id))
			.request()
			.header("Accept", "application/json")
			.build("DELETE")
			.submit();
	}
}
