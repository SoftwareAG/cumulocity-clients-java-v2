// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api;

import java.util.concurrent.Future;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.AuditRecord;
import com.cumulocity.client.model.AuditRecordCollection;

/**
 * An audit log stores events that are security-relevant and should be stored for auditing. For example, an audit log should be generated when a user logs into a gateway.
 * 
 * An audit log extends an event through:
 * 
 * * A username of the user that carried out the activity.
 * * An application that was used to carry out the activity.
 * * The actual activity.
 * * A severity.
 * 
 * > **&#9432; Info:** The Accept header should be provided in all POST requests, otherwise an empty response body will be returned.
 *  </br>
 * 
 */ 
public class AuditsApi extends AdaptableApi {

	public AuditsApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * Retrieve all audit records </br>
	 * Retrieve all audit records registered on your tenant, or a specific subset based on queries. 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and all audit records are sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 * <p>
	 * @param application Name of the application from which the audit was carried out.
	 * @param currentPage The current page of the paginated results.
	 * @param dateFrom Start date or date and time of the audit record.
	 * @param dateTo End date or date and time of the audit record.
	 * @param pageSize Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param source The platform component ID to which the audit is associated.
	 * @param type The type of audit record to search for.
	 * @param user The username to search for.
	 * @param withTotalElements When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @param withTotalPages When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @return
	 */
	public Future<AuditRecordCollection> getAuditRecords(final String application, final int currentPage, final String dateFrom, final String dateTo, final int pageSize, final String source, final String type, final String user, final boolean withTotalElements, final boolean withTotalPages) {
		return getRootTarget().path("audit").path("auditRecords")
			.queryParam("application", application)
			.queryParam("currentPage", valueOf(currentPage))
			.queryParam("dateFrom", dateFrom)
			.queryParam("dateTo", dateTo)
			.queryParam("pageSize", valueOf(pageSize))
			.queryParam("source", source)
			.queryParam("type", type)
			.queryParam("user", user)
			.queryParam("withTotalElements", valueOf(withTotalElements))
			.queryParam("withTotalPages", valueOf(withTotalPages))
				.request()
				.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.auditrecordcollection+json")
				.build("GET")
				.submit(AuditRecordCollection.class);
	}
	
	/**
	 * Create an audit record </br>
	 * Create an audit record.  <section><h5>Required roles</h5> ROLE_AUDIT_ADMIN <b>OR</b> ROLE_SYSTEM <b>OR</b> AUDIT_ADMIN permission on the resource </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>201 An audit record was created.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 * <p>
	 * @param body 
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 * @return
	 */
	public Future<AuditRecord> createAuditRecord(final AuditRecord body, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "severity");
		removeFromNode(jsonNode, "application");
		removeFromNode(jsonNode, "creationTime");
		removeFromNode(jsonNode, "c8y_Metadata");
		removeFromNode(jsonNode, "changes");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "id");
		removeFromNode(jsonNode, "source", "self");
		return getRootTarget().path("audit").path("auditRecords")
				.request()
				.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
				.header("Content-Type", "application/vnd.com.nsn.cumulocity.auditrecord+json")
				.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.auditrecord+json")
				.build("POST", Entity.json(jsonNode))
				.submit(AuditRecord.class);
	}
	
	/**
	 * Retrieve a specific audit record </br>
	 * Retrieve a specific audit record by a given ID.  <section><h5>Required roles</h5> ROLE_AUDIT_READ <b>OR</b> AUDIT_READ permission on the source </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the audit record is sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 * <p>
	 * @param id Unique identifier of the audit record.
	 * @return
	 */
	public Future<AuditRecord> getAuditRecord(final String id) {
		return getRootTarget().path("audit").path("auditRecords").path(valueOf(id))
				.request()
				.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.auditrecord+json")
				.build("GET")
				.submit(AuditRecord.class);
	}
}
