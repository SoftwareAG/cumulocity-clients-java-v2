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
import com.cumulocity.client.model.BinaryInfo;
import com.cumulocity.client.model.EventBinary;

/**
 * It is possible to store, retrieve and delete binaries for events. Each event can have one binary attached. </br>
 * 
 */
public class AttachmentsApi extends AdaptableApi {

	public AttachmentsApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * Retrieve the attached file of a specific event
	 * Retrieve the attached file (binary) of a specific event by a given ID.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_EVENT_READ <b>OR</b> EVENT_READ permission on the source
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The request has succeeded and the file is sent in the response.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - Attachment not found., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param id Unique identifier of the event.
	 */
	public CompletionStage<Response> getEventAttachment(final String id) {
		return adapt().path("event").path("events").path(valueOf(id)).path("binaries")
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/octet-stream")
			.rx()
			.method("GET");
	}
	
	/**
	 * Replace the attached file of a specific event
	 * Upload and replace the attached file (binary) of a specific event by a given ID.<br>
	 * The size of the attachment is configurable, and the default size is 50 MiB. The default chunk size is 5MiB.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_EVENT_ADMIN <b>OR</b> owner of the source <b>OR</b> EVENT_ADMIN permission on the source
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 201 - A file was uploaded.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - Event not found., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param body 
	 * @param id Unique identifier of the event.
	 * @return
	 */
	public CompletionStage<EventBinary> replaceEventAttachment(final byte[] body, final String id) {
		return adapt().path("event").path("events").path(valueOf(id)).path("binaries")
			.request()
			.header("Content-Type", "text/plain")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.event+json")
			.rx()
			.method("PUT", Entity.text(body), EventBinary.class);
	}
	
	/**
	 * Attach a file to a specific event
	 * Upload a file (binary) as an attachment of a specific event by a given ID.<br>
	 * The size of the attachment is configurable, and the default size is 50 MiB. The default chunk size is 5MiB.
	 * 
	 * After the file has been uploaded, the corresponding event will contain the fragment `c8y_IsBinary` similar to:
	 * 
	 * ```json
	 * "c8y_IsBinary": {
	 *     "name": "hello.txt",
	 *     "length": 365,
	 *     "type": "text/plain"
	 * }
	 * ```
	 * 
	 * When using `multipart/form-data` each value is sent as a block of data (body part), with a user agent-defined delimiter (`boundary`) separating each part. The keys are given in the `Content-Disposition` header of each part.
	 * 
	 * ```http
	 * POST /event/events/{id}/binaries
	 * Host: https://<TENANT_DOMAIN>
	 * Authorization: <AUTHORIZATION>
	 * Accept: application/json
	 * Content-Type: multipart/form-data;boundary="boundary"
	 * 
	 * --boundary
	 * Content-Disposition: form-data; name="object"
	 * 
	 * { "name": "hello.txt", "type": "text/plain" }
	 * --boundary
	 * Content-Disposition: form-data; name="file"; filename="hello.txt"
	 * Content-Type: text/plain
	 * 
	 * <FILE_CONTENTS>
	 * --boundary--
	 * ```
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_EVENT_ADMIN <b>OR</b> owner of the source <b>OR</b> EVENT_ADMIN permission on the source
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 201 - A file was uploaded.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - Event not found., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 409 - An attachment exists already., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param body 
	 * @param id Unique identifier of the event.
	 * @return
	 */
	public CompletionStage<EventBinary> uploadEventAttachment(final byte[] body, final String id) {
		return adapt().path("event").path("events").path(valueOf(id)).path("binaries")
			.request()
			.header("Content-Type", "text/plain")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.event+json")
			.rx()
			.method("POST", Entity.text(body), EventBinary.class);
	}
	
	/**
	 * Attach a file to a specific event
	 * Upload a file (binary) as an attachment of a specific event by a given ID.<br>
	 * The size of the attachment is configurable, and the default size is 50 MiB. The default chunk size is 5MiB.
	 * 
	 * After the file has been uploaded, the corresponding event will contain the fragment `c8y_IsBinary` similar to:
	 * 
	 * ```json
	 * "c8y_IsBinary": {
	 *     "name": "hello.txt",
	 *     "length": 365,
	 *     "type": "text/plain"
	 * }
	 * ```
	 * 
	 * When using `multipart/form-data` each value is sent as a block of data (body part), with a user agent-defined delimiter (`boundary`) separating each part. The keys are given in the `Content-Disposition` header of each part.
	 * 
	 * ```http
	 * POST /event/events/{id}/binaries
	 * Host: https://<TENANT_DOMAIN>
	 * Authorization: <AUTHORIZATION>
	 * Accept: application/json
	 * Content-Type: multipart/form-data;boundary="boundary"
	 * 
	 * --boundary
	 * Content-Disposition: form-data; name="object"
	 * 
	 * { "name": "hello.txt", "type": "text/plain" }
	 * --boundary
	 * Content-Disposition: form-data; name="file"; filename="hello.txt"
	 * Content-Type: text/plain
	 * 
	 * <FILE_CONTENTS>
	 * --boundary--
	 * ```
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_EVENT_ADMIN <b>OR</b> owner of the source <b>OR</b> EVENT_ADMIN permission on the source
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 201 - A file was uploaded.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - Event not found., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 409 - An attachment exists already., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param pObject 
	 * @param file Path of the file to be uploaded.
	 * @param id Unique identifier of the event.
	 * @return
	 */
	public CompletionStage<EventBinary> uploadEventAttachment(final BinaryInfo pObject, final byte[] file, final String id) {
		final FormDataMultiPart multiPartEntity = new FormDataMultiPart();
		multiPartEntity.field("object", pObject, MediaType.valueOf("application/json"));
		multiPartEntity.field("file", file, MediaType.valueOf("text/plain"));
		return adapt().path("event").path("events").path(valueOf(id)).path("binaries")
			.request()
			.header("Content-Type", "multipart/form-data")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.event+json")
			.rx()
			.method("POST", Entity.entity(multiPartEntity, "multipart/form-data"), EventBinary.class);
	}
	
	/**
	 * Remove the attached file from a specific event
	 * Remove the attached file (binary) from a specific event by a given ID.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_EVENT_ADMIN <b>OR</b> owner of the source <b>OR</b> EVENT_ADMIN permission on the source
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 204 - A file was removed.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - Event not found., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param id Unique identifier of the event.
	 */
	public CompletionStage<Response> deleteEventAttachment(final String id) {
		return adapt().path("event").path("events").path(valueOf(id)).path("binaries")
			.request()
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
}
