// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api;

import java.util.concurrent.Future;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.Application;
import com.cumulocity.client.model.ApplicationCollection;

/**
 * API methods to retrieve, create, update and delete applications.
 * 
 * ### Application names
 * 
 * For each tenant, Cumulocity IoT manages the subscribed applications and provides a number of applications of various types.
 * In case you want to subscribe a tenant to an application using an API, you must use the application name in the argument (as name).
 * 
 * Refer to the tables in [Administration > Managing applications](https://cumulocity.com/guides/10.7.0/users-guide/administration#managing-applications) in the User guide for the respective application name to be used.
 * 
 * > **&#9432; Info:** The Accept header should be provided in all POST/PUT requests, otherwise an empty response body will be returned.
 *  </br>
 * 
 */ 
public class ApplicationsApi extends AdaptableApi {

	public ApplicationsApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * Retrieve all applications </br>
	 * Retrieve all applications on your tenant.  <section><h5>Required roles</h5> ROLE_APPLICATION_MANAGEMENT_READ </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the list of applications is sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 * <p>
	 * @param currentPage The current page of the paginated results.
	 * @param name The name of the application.
	 * @param owner The ID of the tenant that owns the applications.
	 * @param pageSize Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param providedFor The ID of a tenant that is subscribed to the applications but doesn't own them.
	 * @param subscriber The ID of a tenant that is subscribed to the applications.
	 * @param tenant The ID of a tenant that either owns the application or is subscribed to the applications.
	 * @param type The type of the application. It is possible to use multiple values separated by a comma. For example, `EXTERNAL,HOSTED` will return only applications with type `EXTERNAL` or `HOSTED`.
	 * @param user The ID of a user that has access to the applications.
	 * @param withTotalElements When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @param withTotalPages When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @param hasVersions When set to `true`, the returned result contains applications with an `applicationVersions` field that is not empty. When set to `false`, the result will contain applications with an empty `applicationVersions` field.
	 * @return
	 */
	public Future<ApplicationCollection> getApplications(final int currentPage, final String name, final String owner, final int pageSize, final String providedFor, final String subscriber, final String tenant, final String type, final String user, final boolean withTotalElements, final boolean withTotalPages, final boolean hasVersions) {
		return getRootTarget().path("application").path("applications")
			.queryParam("currentPage", valueOf(currentPage))
			.queryParam("name", name)
			.queryParam("owner", owner)
			.queryParam("pageSize", valueOf(pageSize))
			.queryParam("providedFor", providedFor)
			.queryParam("subscriber", subscriber)
			.queryParam("tenant", tenant)
			.queryParam("type", type)
			.queryParam("user", user)
			.queryParam("withTotalElements", valueOf(withTotalElements))
			.queryParam("withTotalPages", valueOf(withTotalPages))
			.queryParam("hasVersions", valueOf(hasVersions))
				.request()
				.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.applicationcollection+json")
				.build("GET")
				.submit(ApplicationCollection.class);
	}
	
	/**
	 * Create an application </br>
	 * Create an application on your tenant.  <section><h5>Required roles</h5> ROLE_APPLICATION_MANAGEMENT_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>201 An application was created.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>409 Duplicate key/name.</li>
	 * <li>422 Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 * <p>
	 * @param body 
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 * @return
	 */
	public Future<Application> createApplication(final Application body, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "owner");
		removeFromNode(jsonNode, "activeVersionId");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "id");
		removeFromNode(jsonNode, "resourcesUrl");
		return getRootTarget().path("application").path("applications")
				.request()
				.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
				.header("Content-Type", "application/vnd.com.nsn.cumulocity.application+json")
				.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.application+json")
				.build("POST", Entity.json(jsonNode))
				.submit(Application.class);
	}
	
	/**
	 * Retrieve a specific application </br>
	 * Retrieve a specific application (by a given ID).  <section><h5>Required roles</h5> ROLE_APPLICATION_MANAGEMENT_READ <b>OR</b> current user has explicit access to the application </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the application is sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Application not found.</li>
	 * </ul>
	 * <p>
	 * @param id Unique identifier of the application.
	 * @return
	 */
	public Future<Application> getApplication(final String id) {
		return getRootTarget().path("application").path("applications").path(valueOf(id))
				.request()
				.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.application+json")
				.build("GET")
				.submit(Application.class);
	}
	
	/**
	 * Update a specific application </br>
	 * Update a specific application (by a given ID).  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 An application was updated.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Application not found.</li>
	 * </ul>
	 * <p>
	 * @param body 
	 * @param id Unique identifier of the application.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 * @return
	 */
	public Future<Application> updateApplication(final Application body, final String id, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "owner");
		removeFromNode(jsonNode, "activeVersionId");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "id");
		removeFromNode(jsonNode, "type");
		removeFromNode(jsonNode, "resourcesUrl");
		return getRootTarget().path("application").path("applications").path(valueOf(id))
				.request()
				.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
				.header("Content-Type", "application/vnd.com.nsn.cumulocity.application+json")
				.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.application+json")
				.build("PUT", Entity.json(jsonNode))
				.submit(Application.class);
	}
	
	/**
	 * Delete an application </br>
	 * Delete an application (by a given ID). This method is not supported by microservice applications.  > **&#9432; Info:** With regards to a hosted application, there is a caching mechanism in place that keeps the information about the placement of application files (html, javascript, css, fonts, etc.). Removing a hosted application, in normal circumstances, will cause the subsequent requests for application files to fail with an HTTP 404 error because the application is removed synchronously, its files are immediately removed on the node serving the request and at the same time the information is propagated to other nodes – but in rare cases there might be a delay with this propagation. In such situations, the files of the removed application can be served from those nodes up until the aforementioned cache expires. For the same reason, the cache can also cause HTTP 404 errors when the application is updated as it will keep the path to the files of the old version of the application. The cache is filled on demand, so there should not be issues if application files were not accessed prior to the delete request. The expiration delay of the cache can differ, but should not take more than one minute.  <section><h5>Required roles</h5> ROLE_APPLICATION_MANAGEMENT_ADMIN <b>AND</b> tenant is the owner of the application </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>204 An application was removed.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not authorized to perform this operation.</li>
	 * <li>404 Application not found.</li>
	 * </ul>
	 * <p>
	 * @param id Unique identifier of the application.
	 * @param force Force deletion by unsubscribing all tenants from the application first and then deleting the application itself.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	public Future<Response> deleteApplication(final String id, final boolean force, final String xCumulocityProcessingMode) {
		return getRootTarget().path("application").path("applications").path(valueOf(id))
			.queryParam("force", valueOf(force))
				.request()
				.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
				.header("Accept", "application/json")
				.build("DELETE")
				.submit();
	}
	
	/**
	 * Copy an application </br>
	 * Copy an application (by a given ID).  This method is not supported by microservice applications.  A request to the "clone" resource creates a new application based on an already existing one.  The properties are copied to the newly created application and the prefix "clone" is added to the properties `name`, `key` and `contextPath` in order to be unique.  If the target application is hosted and has an active version, the new application will have the active version with the same content. <section><h5>Required roles</h5> ROLE_APPLICATION_MANAGEMENT_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>201 An application was copied.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>422 Unprocessable Entity – method not supported</li>
	 * </ul>
	 * <p>
	 * @param id Unique identifier of the application.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 * @return
	 */
	public Future<Application> copyApplication(final String id, final String xCumulocityProcessingMode) {
		return getRootTarget().path("application").path("applications").path(valueOf(id)).path("clone")
				.request()
				.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
				.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.application+json")
				.build("POST")
				.submit(Application.class);
	}
	
	/**
	 * Retrieve applications by name </br>
	 * Retrieve applications by name.  <section><h5>Required roles</h5> ROLE_APPLICATION_MANAGEMENT_READ </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the applications are sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 * <p>
	 * @param name The name of the application.
	 * @return
	 */
	public Future<ApplicationCollection> getApplicationsByName(final String name) {
		return getRootTarget().path("application").path("applicationsByName").path(valueOf(name))
				.request()
				.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.applicationcollection+json")
				.build("GET")
				.submit(ApplicationCollection.class);
	}
	
	/**
	 * Retrieve applications by tenant </br>
	 * Retrieve applications subscribed or owned by a particular tenant (by a given tenant ID).  <section><h5>Required roles</h5> ROLE_APPLICATION_MANAGEMENT_READ </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the applications are sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 * <p>
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @return
	 */
	public Future<ApplicationCollection> getApplicationsByTenant(final String tenantId) {
		return getRootTarget().path("application").path("applicationsByTenant").path(valueOf(tenantId))
				.request()
				.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.applicationcollection+json")
				.build("GET")
				.submit(ApplicationCollection.class);
	}
	
	/**
	 * Retrieve applications by owner </br>
	 * Retrieve all applications owned by a particular tenant (by a given tenant ID).  <section><h5>Required roles</h5> ROLE_APPLICATION_MANAGEMENT_READ </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the applications are sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 * <p>
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param currentPage The current page of the paginated results.
	 * @param pageSize Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param withTotalElements When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @param withTotalPages When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @return
	 */
	public Future<ApplicationCollection> getApplicationsByOwner(final String tenantId, final int currentPage, final int pageSize, final boolean withTotalElements, final boolean withTotalPages) {
		return getRootTarget().path("application").path("applicationsByOwner").path(valueOf(tenantId))
			.queryParam("currentPage", valueOf(currentPage))
			.queryParam("pageSize", valueOf(pageSize))
			.queryParam("withTotalElements", valueOf(withTotalElements))
			.queryParam("withTotalPages", valueOf(withTotalPages))
				.request()
				.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.applicationcollection+json")
				.build("GET")
				.submit(ApplicationCollection.class);
	}
	
	/**
	 * Retrieve applications by user </br>
	 * Retrieve all applications for a particular user (by a given username).  <section><h5>Required roles</h5> (ROLE_USER_MANAGEMENT_OWN_READ <b>AND</b> is the current user) <b>OR</b> (ROLE_USER_MANAGEMENT_READ <b>AND</b> ROLE_APPLICATION_MANAGEMENT_READ) </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the applications are sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 * <p>
	 * @param username The username of the a user.
	 * @param currentPage The current page of the paginated results.
	 * @param pageSize Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param withTotalElements When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @param withTotalPages When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @return
	 */
	public Future<ApplicationCollection> getApplicationsByUser(final String username, final int currentPage, final int pageSize, final boolean withTotalElements, final boolean withTotalPages) {
		return getRootTarget().path("application").path("applicationsByUser").path(valueOf(username))
			.queryParam("currentPage", valueOf(currentPage))
			.queryParam("pageSize", valueOf(pageSize))
			.queryParam("withTotalElements", valueOf(withTotalElements))
			.queryParam("withTotalPages", valueOf(withTotalPages))
				.request()
				.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.applicationcollection+json")
				.build("GET")
				.submit(ApplicationCollection.class);
	}
}
