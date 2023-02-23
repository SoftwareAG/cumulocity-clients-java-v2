// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import jakarta.ws.rs.core.MediaType;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.ApplicationVersionTag;
import com.cumulocity.client.model.ApplicationVersion;
import com.cumulocity.client.model.ApplicationVersionCollection;

/**
 * API methods to retrieve, create, update and delete application versions. </br>
 * 
 */
public class ApplicationVersionsApi extends AdaptableApi {

	public ApplicationVersionsApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * Retrieve a specific version of an application
	 * Retrieve the selected version of an application in your tenant. To select the version, use only the version or only the tag query parameter.
	 * <section><h5>Required roles</h5> ROLE_APPLICATION_MANAGEMENT_READ </section>
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The request has succeeded and the application version is sent in the response.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - Application not found., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 422 - both parameters (version and tag) are present.</li>
	 * </ul>
	 * @param id Unique identifier of the application.
	 * @param version The version field of the application version.
	 * @param tag The tag of the application version.
	 * @return
	 */
	public CompletionStage<ApplicationVersion> getApplicationVersion(final String id, final String version, final String tag) {
		return adapt().path("application").path("applications").path(valueOf(id)).path("versions?version=1.0")
			.queryParam("version", version)
			.queryParam("tag", tag)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.applicationVersion+json")
			.rx()
			.method("GET", ApplicationVersion.class);
	}
	
	/**
	 * Retrieve all versions of an application
	 * Retrieve all versions of an application in your tenant.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_APPLICATION_MANAGEMENT_READ
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The request has succeeded and the list of application versions is sent in the response.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - Application version not found., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 422 - This application doesn't support versioning.</li>
	 * </ul>
	 * @param id Unique identifier of the application.
	 * @return
	 */
	public CompletionStage<ApplicationVersionCollection> getApplicationVersions(final String id) {
		return adapt().path("application").path("applications").path(valueOf(id)).path("versions")
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.applicationVersionCollection+json")
			.rx()
			.method("GET", ApplicationVersionCollection.class);
	}
	
	/**
	 * Create an application version
	 * Create an application version in your tenant.
	 * 
	 * Uploaded version and tags can only contain upper and lower case letters, integers and `.`,` + `,` -`. Other characters are prohibited.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_APPLICATION_MANAGEMENT_ADMIN
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 201 - An application version was created.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - Application version not found., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 409 - Duplicate version/tag or versions limit exceeded., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 422 - tag or version contains unacceptable characters.</li>
	 * </ul>
	 * @param applicationBinary The ZIP file to be uploaded.
	 * @param applicationVersion The JSON file with version information.
	 * @param id Unique identifier of the application.
	 * @return
	 */
	public CompletionStage<ApplicationVersion> createApplicationVersion(final byte[] applicationBinary, final String applicationVersion, final String id) {
		final FormDataMultiPart multiPartEntity = new FormDataMultiPart();
		multiPartEntity.field("applicationBinary", applicationBinary, MediaType.valueOf("application/zip"));
		multiPartEntity.field("applicationVersion", applicationVersion, MediaType.valueOf("text/plain"));
		return adapt().path("application").path("applications").path(valueOf(id)).path("versions")
			.request()
			.header("Content-Type", "multipart/form-data")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.applicationVersion+json")
			.rx()
			.method("POST", Entity.entity(multiPartEntity, "multipart/form-data"), ApplicationVersion.class);
	}
	
	/**
	 * Delete a specific version of an application
	 * Delete a specific version of an application in your tenant, by a given tag or version.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_APPLICATION_MANAGEMENT_READ
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 204 - A version was removed.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - Application version not found., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 409 - Version with tag latest cannot be removed.</li>
	 *     <li>HTTP 422 - both parameters (version and tag) are present.</li>
	 * </ul>
	 * @param id Unique identifier of the application.
	 * @param version The version field of the application version.
	 * @param tag The tag of the application version.
	 */
	public CompletionStage<Response> deleteApplicationVersion(final String id, final String version, final String tag) {
		return adapt().path("application").path("applications").path(valueOf(id)).path("versions")
			.queryParam("version", version)
			.queryParam("tag", tag)
			.request()
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
	
	/**
	 * Replace an application version's tags
	 * Replaces the tags of a given application version in your tenant.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_APPLICATION_MANAGEMENT_ADMIN
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 201 - An application version was updated.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - Application version not found., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 409 - Duplicate version/tag or versions limit exceeded., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 422 - tag contains unacceptable characters.</li>
	 * </ul>
	 * @param body 
	 * @param id Unique identifier of the application.
	 * @param version Version of the application.
	 * @return
	 */
	public CompletionStage<ApplicationVersion> updateApplicationVersion(final ApplicationVersionTag body, final String id, final String version) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("application").path("applications").path(valueOf(id)).path("versions").path(valueOf(version))
			.request()
			.header("Content-Type", "application/json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.applicationVersion+json")
			.rx()
			.method("PUT", Entity.json(jsonNode), ApplicationVersion.class);
	}
}
