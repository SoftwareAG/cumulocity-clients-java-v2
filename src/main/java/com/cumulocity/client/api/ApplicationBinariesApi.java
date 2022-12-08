// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api;

import java.util.concurrent.Future;
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
	 * Retrieve all application attachments </br>
	 * Retrieve all application attachments. This method is not supported by microservice applications.  <section><h5>Required roles</h5> ROLE_APPLICATION_MANAGEMENT_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the application attachments are sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Application not found.</li>
	 * </ul>
	 * <p>
	 * @param id Unique identifier of the application.
	 * @return
	 */
	public Future<ApplicationBinaries> getApplicationAttachments(final String id) {
		return getRootTarget().path("application").path("applications").path(valueOf(id)).path("binaries")
				.request()
				.header("Accept", "application/vnd.com.nsn.cumulocity.applicationbinaries+json, application/vnd.com.nsn.cumulocity.error+json")
				.build("GET")
				.submit(ApplicationBinaries.class);
	}
	
	/**
	 * Upload an application attachment </br>
	 * Upload an application attachment (by a given application ID).  For the applications of type “microservice” and “web application” to be available for Cumulocity IoT platform users, an attachment ZIP file must be uploaded.  For a microservice application, the ZIP file must consist of:  * cumulocity.json - file describing the deployment * image.tar - executable Docker image  For a web application, the ZIP file must include an index.html file in the root directory.  <section><h5>Required roles</h5> ROLE_APPLICATION_MANAGEMENT_ADMIN <b>AND</b> tenant is the owner of the application </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>201 The application attachments have been uploaded.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 * <p>
	 * @param file The ZIP file to be uploaded.
	 * @param id Unique identifier of the application.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 * @return
	 */
	public Future<Application> uploadApplicationAttachment(final byte[] file, final String id, final String xCumulocityProcessingMode) {
		final FormDataMultiPart multiPartEntity = new FormDataMultiPart();
		multiPartEntity.field("file", file, MediaType.valueOf("application/zip"));
		return getRootTarget().path("application").path("applications").path(valueOf(id)).path("binaries")
				.request()
				.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
				.header("Content-Type", "multipart/form-data")
				.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.application+json")
				.build("POST", Entity.entity(multiPartEntity, "multipart/form-data"))
				.submit(Application.class);
	}
	
	/**
	 * Retrieve a specific application attachment </br>
	 * Retrieve a specific application attachment (by a given application ID and a given binary ID). This method is not supported by microservice applications.  <section><h5>Required roles</h5> ROLE_APPLICATION_MANAGEMENT_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the application attachment is sent as a ZIP file in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 * <p>
	 * @param id Unique identifier of the application.
	 * @param binaryId Unique identifier of the binary.
	 */
	public Future<Response> getApplicationAttachment(final String id, final String binaryId) {
		return getRootTarget().path("application").path("applications").path(valueOf(id)).path("binaries").path(valueOf(binaryId))
				.request()
				.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/zip")
				.build("GET")
				.submit();
	}
	
	/**
	 * Delete a specific application attachment </br>
	 * Delete  a specific application attachment (by a given application ID and a given binary ID). This method is not supported by microservice applications.  <section><h5>Required roles</h5> ROLE_APPLICATION_MANAGEMENT_ADMIN <b>AND</b> tenant is the owner of the application </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>204 An application binary was removed.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not authorized to perform this operation.</li>
	 * </ul>
	 * <p>
	 * @param id Unique identifier of the application.
	 * @param binaryId Unique identifier of the binary.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	public Future<Response> deleteApplicationAttachment(final String id, final String binaryId, final String xCumulocityProcessingMode) {
		return getRootTarget().path("application").path("applications").path(valueOf(id)).path("binaries").path(valueOf(binaryId))
				.request()
				.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
				.header("Accept", "application/json")
				.build("DELETE")
				.submit();
	}
}
