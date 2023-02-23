// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import jakarta.ws.rs.core.MediaType;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.ApplicationBinaries;
import com.cumulocity.client.model.Application;

/**
 * An API method to upload an application binary. It is a deployable microservice or web application. </br>
 * 
 */
public class ApplicationBinariesApi extends AdaptableApi {

	public ApplicationBinariesApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * Retrieve all application attachments
	 * Retrieve all application attachments.
	 * This method is not supported by microservice applications.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_APPLICATION_MANAGEMENT_ADMIN
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The request has succeeded and the application attachments are sent in the response.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - Application not found., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param id Unique identifier of the application.
	 * @return
	 */
	public CompletionStage<ApplicationBinaries> getApplicationAttachments(final String id) {
		return adapt().path("application").path("applications").path(valueOf(id)).path("binaries")
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.applicationbinaries+json, application/vnd.com.nsn.cumulocity.error+json")
			.rx()
			.method("GET", ApplicationBinaries.class);
	}
	
	/**
	 * Upload an application attachment
	 * Upload an application attachment (by a given application ID).
	 * 
	 * For the applications of type “microservice” and “web application” to be available for Cumulocity IoT platform users, an attachment ZIP file must be uploaded.
	 * 
	 * For a microservice application, the ZIP file must consist of:
	 * 
	 * * cumulocity.json - file describing the deployment
	 * * image.tar - executable Docker image
	 * 
	 * For a web application, the ZIP file must include an index.html file in the root directory.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_APPLICATION_MANAGEMENT_ADMIN <b>AND</b> tenant is the owner of the application
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 201 - The application attachments have been uploaded.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param file The ZIP file to be uploaded.
	 * @param id Unique identifier of the application.
	 * @return
	 */
	public CompletionStage<Application> uploadApplicationAttachment(final byte[] file, final String id) {
		final FormDataMultiPart multiPartEntity = new FormDataMultiPart();
		multiPartEntity.field("file", file, MediaType.valueOf("application/zip"));
		return adapt().path("application").path("applications").path(valueOf(id)).path("binaries")
			.request()
			.header("Content-Type", "multipart/form-data")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.application+json")
			.rx()
			.method("POST", Entity.entity(multiPartEntity, "multipart/form-data"), Application.class);
	}
	
	/**
	 * Retrieve a specific application attachment
	 * Retrieve a specific application attachment (by a given application ID and a given binary ID).
	 * This method is not supported by microservice applications.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_APPLICATION_MANAGEMENT_ADMIN
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The request has succeeded and the application attachment is sent as a ZIP file in the response.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param id Unique identifier of the application.
	 * @param binaryId Unique identifier of the binary.
	 */
	public CompletionStage<Response> getApplicationAttachment(final String id, final String binaryId) {
		return adapt().path("application").path("applications").path(valueOf(id)).path("binaries").path(valueOf(binaryId))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/zip")
			.rx()
			.method("GET");
	}
	
	/**
	 * Delete a specific application attachment
	 * Delete  a specific application attachment (by a given application ID and a given binary ID).
	 * This method is not supported by microservice applications.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_APPLICATION_MANAGEMENT_ADMIN <b>AND</b> tenant is the owner of the application
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 204 - An application binary was removed.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 403 - Not authorized to perform this operation.</li>
	 * </ul>
	 * @param id Unique identifier of the application.
	 * @param binaryId Unique identifier of the binary.
	 */
	public CompletionStage<Response> deleteApplicationAttachment(final String id, final String binaryId) {
		return adapt().path("application").path("applications").path(valueOf(id)).path("binaries").path(valueOf(binaryId))
			.request()
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
}
