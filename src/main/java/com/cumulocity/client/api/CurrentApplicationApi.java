// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api;

import java.util.concurrent.Future;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.Application;
import com.cumulocity.client.model.ApplicationSettings;
import com.cumulocity.client.model.ApplicationUserCollection;

/**
 * API methods to retrieve and update the current application and to retrieve its subscribers.
 * It is the authenticated microservice user's application.
 *  </br>
 * 
 */ 
public class CurrentApplicationApi extends AdaptableApi {

	public CurrentApplicationApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * Retrieve the current application </br>
	 * Retrieve the current application. This only works inside an application, for example, a microservice.  <section><h5>Required roles</h5> Microservice bootstrap user required. </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the current application sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not enough permissions/roles to perform this operation.</li>
	 * </ul>
	 * @return
	 */
	public Future<Application> getCurrentApplication() {
		return getRootTarget().path("application").path("currentApplication")
				.request()
				.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.application+json")
				.build("GET")
				.submit(Application.class);
	}
	
	/**
	 * Update the current application </br>
	 * Update the current application. This only works inside an application, for example, a microservice. This method is deprecated as it is only used by legacy microservices that are not running on Kubernetes.  <section><h5>Required roles</h5> Microservice bootstrap user required. </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The current application was updated.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not enough permissions/roles to perform this operation.</li>
	 * </ul>
	 * <p>
	 * @param body 
	 * @return
	 */
	@Deprecated
	public Future<Application> updateCurrentApplication(final Application body) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "owner");
		removeFromNode(jsonNode, "activeVersionId");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "id");
		removeFromNode(jsonNode, "resourcesUrl");
		return getRootTarget().path("application").path("currentApplication")
				.request()
				.header("Content-Type", "application/vnd.com.nsn.cumulocity.application+json")
				.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.application+json")
				.build("PUT", Entity.json(jsonNode))
				.submit(Application.class);
	}
	
	/**
	 * Retrieve the current application settings </br>
	 * Retrieve the current application settings. This only works inside an application, for example, a microservice.  <section><h5>Required roles</h5> Microservice bootstrap user <b>OR</b> microservice service user required. </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the current application settings are sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not enough permissions/roles to perform this operation.</li>
	 * </ul>
	 * @return
	 */
	public Future<ApplicationSettings[]> getCurrentApplicationSettings() {
		return getRootTarget().path("application").path("currentApplication").path("settings")
				.request()
				.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.applicationsettings+json")
				.build("GET")
				.submit(ApplicationSettings[].class);
	}
	
	/**
	 * Retrieve the subscribed users of the current application </br>
	 * Retrieve the subscribed users of the current application.  <section><h5>Required roles</h5> Microservice bootstrap user required. </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the list of subscribed users for the current application is sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 * @return
	 */
	public Future<ApplicationUserCollection> getSubscribedUsers() {
		return getRootTarget().path("application").path("currentApplication").path("subscriptions")
				.request()
				.header("Accept", "application/vnd.com.nsn.cumulocity.applicationusercollection+json, application/vnd.com.nsn.cumulocity.error+json")
				.build("GET")
				.submit(ApplicationUserCollection.class);
	}
}
