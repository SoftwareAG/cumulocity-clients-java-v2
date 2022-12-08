// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api;

import java.util.concurrent.Future;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.Alarm;
import com.cumulocity.client.model.AlarmCollection;

/**
 * An alarm represents an event that requires manual action, for example, when the temperature of a fridge increases above a particular threshold.
 * 
 * > **&#9432; Info:** The Accept header should be provided in all POST/PUT requests, otherwise an empty response body will be returned.
 *  </br>
 * 
 */ 
public class AlarmsApi extends AdaptableApi {

	public AlarmsApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * Retrieve all alarms </br>
	 * Retrieve all alarms on your tenant, or a specific subset based on queries. The results are sorted by the newest alarms first.  #### Query parameters  The query parameter `withTotalPages` only works when the user has the ROLE_ALARM_READ role, otherwise it is ignored.  <section><h5>Required roles</h5> The role ROLE_ALARM_READ is not required, but if a user has this role, all the alarms on the tenant are returned. If a user has access to alarms through inventory roles, only those alarms are returned. </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and all alarms are sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 * <p>
	 * @param createdFrom Start date or date and time of the alarm creation.
	 * @param createdTo End date or date and time of the alarm creation.
	 * @param currentPage The current page of the paginated results.
	 * @param dateFrom Start date or date and time of the alarm occurrence.
	 * @param dateTo End date or date and time of the alarm occurrence.
	 * @param lastUpdatedFrom Start date or date and time of the last update made.
	 * @param lastUpdatedTo End date or date and time of the last update made.
	 * @param pageSize Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param resolved When set to `true` only alarms with status CLEARED will be fetched, whereas `false` will fetch all alarms with status ACTIVE or ACKNOWLEDGED.
	 * @param severity The severity of the alarm to search for.
	 * @param source The managed object ID to which the alarm is associated.
	 * @param status The status of the alarm to search for.
	 * @param type The types of alarm to search for (comma separated).
	 * @param withSourceAssets When set to `true` also alarms for related source assets will be included in the request. When this parameter is provided a `source` must be specified.
	 * @param withSourceDevices When set to `true` also alarms for related source devices will be included in the request. When this parameter is provided a `source` must be specified.
	 * @param withTotalElements When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @param withTotalPages When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @return
	 */
	public Future<AlarmCollection> getAlarms(final String createdFrom, final String createdTo, final int currentPage, final String dateFrom, final String dateTo, final String lastUpdatedFrom, final String lastUpdatedTo, final int pageSize, final boolean resolved, final String severity, final String source, final String status, final String[] type, final boolean withSourceAssets, final boolean withSourceDevices, final boolean withTotalElements, final boolean withTotalPages) {
		return getRootTarget().path("alarm").path("alarms")
			.queryParam("createdFrom", createdFrom)
			.queryParam("createdTo", createdTo)
			.queryParam("currentPage", valueOf(currentPage))
			.queryParam("dateFrom", dateFrom)
			.queryParam("dateTo", dateTo)
			.queryParam("lastUpdatedFrom", lastUpdatedFrom)
			.queryParam("lastUpdatedTo", lastUpdatedTo)
			.queryParam("pageSize", valueOf(pageSize))
			.queryParam("resolved", valueOf(resolved))
			.queryParam("severity", severity)
			.queryParam("source", source)
			.queryParam("status", status)
			.queryParam("type", valueOf(type))
			.queryParam("withSourceAssets", valueOf(withSourceAssets))
			.queryParam("withSourceDevices", valueOf(withSourceDevices))
			.queryParam("withTotalElements", valueOf(withTotalElements))
			.queryParam("withTotalPages", valueOf(withTotalPages))
				.request()
				.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.alarmcollection+json")
				.build("GET")
				.submit(AlarmCollection.class);
	}
	
	/**
	 * Update alarm collections </br>
	 * Update alarm collections specified by query parameters. At least one query parameter is required to avoid accidentally updating all existing alarms.<br> Currently, only the status of alarms can be modified.  > **&#9432; Info:** Since this operation can take considerable time, the request returns after maximum 0.5 seconds of processing, and the update operation continues as a background process in the platform.  <section><h5>Required roles</h5> ROLE_ALARM_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 An alarm collection was updated.</li>
	 * <li>202 An alarm collection is being updated in background.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not authorized to perform this operation.</li>
	 * <li>422 Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 * <p>
	 * @param body 
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 * @param createdFrom Start date or date and time of the alarm creation.
	 * @param createdTo End date or date and time of the alarm creation.
	 * @param dateFrom Start date or date and time of the alarm occurrence.
	 * @param dateTo End date or date and time of the alarm occurrence.
	 * @param resolved When set to `true` only alarms with status CLEARED will be fetched, whereas `false` will fetch all alarms with status ACTIVE or ACKNOWLEDGED.
	 * @param severity The severity of the alarm to search for.
	 * @param source The managed object ID to which the alarm is associated.
	 * @param status The status of the alarm to search for.
	 * @param withSourceAssets When set to `true` also alarms for related source assets will be included in the request. When this parameter is provided a `source` must be specified.
	 * @param withSourceDevices When set to `true` also alarms for related source devices will be included in the request. When this parameter is provided a `source` must be specified.
	 */
	public Future<Response> updateAlarms(final Alarm body, final String xCumulocityProcessingMode, final String createdFrom, final String createdTo, final String dateFrom, final String dateTo, final boolean resolved, final String severity, final String source, final String status, final boolean withSourceAssets, final boolean withSourceDevices) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "firstOccurrenceTime");
		removeFromNode(jsonNode, "severity");
		removeFromNode(jsonNode, "lastUpdated");
		removeFromNode(jsonNode, "creationTime");
		removeFromNode(jsonNode, "count");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "id");
		removeFromNode(jsonNode, "source");
		removeFromNode(jsonNode, "text");
		removeFromNode(jsonNode, "time");
		removeFromNode(jsonNode, "type");
		return getRootTarget().path("alarm").path("alarms")
			.queryParam("createdFrom", createdFrom)
			.queryParam("createdTo", createdTo)
			.queryParam("dateFrom", dateFrom)
			.queryParam("dateTo", dateTo)
			.queryParam("resolved", valueOf(resolved))
			.queryParam("severity", severity)
			.queryParam("source", source)
			.queryParam("status", status)
			.queryParam("withSourceAssets", valueOf(withSourceAssets))
			.queryParam("withSourceDevices", valueOf(withSourceDevices))
				.request()
				.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
				.header("Content-Type", "application/vnd.com.nsn.cumulocity.alarm+json")
				.header("Accept", "application/json")
				.build("PUT", Entity.json(jsonNode))
				.submit();
	}
	
	/**
	 * Create an alarm </br>
	 * An alarm must be associated with a source (managed object) identified by ID.<br> In general, each alarm may consist of:  *  A status showing whether the alarm is ACTIVE, ACKNOWLEDGED or CLEARED. *  A time stamp to indicate when the alarm was last updated. *  The severity of the alarm: CRITICAL, MAJOR, MINOR or WARNING. *  A history of changes to the event in form of audit logs.  ### Alarm suppression  If the source device is in maintenance mode, the alarm is not created and not reported to the Cumulocity IoT event processing engine. When sending a POST request to create a new alarm and if the source device is in maintenance mode, the self link of the alarm will be:  ```json "self": "https://<TENANT_DOMAIN>/alarm/alarms/null" ```  ### Alarm de-duplication  If an ACTIVE or ACKNOWLEDGED alarm with the same source and type exists, no new alarm is created. Instead, the existing alarm is updated by incrementing the `count` property; the `time` property is also updated. Any other changes are ignored, and the alarm history is not updated. Alarms with status CLEARED are not de-duplicated. The first occurrence of the alarm is recorded in the `firstOccurrenceTime` property.  <section><h5>Required roles</h5> ROLE_ALARM_ADMIN <b>OR</b> owner of the source <b>OR</b> ALARM_ADMIN permission on the source </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>201 An alarm was created.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not authorized to perform this operation.</li>
	 * <li>422 Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 * <p>
	 * @param body 
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 * @return
	 */
	public Future<Alarm> createAlarm(final Alarm body, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "firstOccurrenceTime");
		removeFromNode(jsonNode, "lastUpdated");
		removeFromNode(jsonNode, "creationTime");
		removeFromNode(jsonNode, "count");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "id");
		removeFromNode(jsonNode, "source", "self");
		return getRootTarget().path("alarm").path("alarms")
				.request()
				.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
				.header("Content-Type", "application/vnd.com.nsn.cumulocity.alarm+json")
				.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.alarm+json")
				.build("POST", Entity.json(jsonNode))
				.submit(Alarm.class);
	}
	
	/**
	 * Remove alarm collections </br>
	 * Remove alarm collections specified by query parameters.  > **⚠️ Important:** Note that it is possible to call this endpoint without providing any parameter - it will result in deleting all alarms and it is not recommended. > Also note that DELETE requests are not synchronous. The response could be returned before the delete request has been completed.  <section><h5>Required roles</h5> ROLE_ALARM_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>204 A collection of alarms was removed.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not authorized to perform this operation.</li>
	 * </ul>
	 * <p>
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 * @param createdFrom Start date or date and time of the alarm creation.
	 * @param createdTo End date or date and time of the alarm creation.
	 * @param dateFrom Start date or date and time of the alarm occurrence.
	 * @param dateTo End date or date and time of the alarm occurrence.
	 * @param resolved When set to `true` only alarms with status CLEARED will be fetched, whereas `false` will fetch all alarms with status ACTIVE or ACKNOWLEDGED.
	 * @param severity The severity of the alarm to search for.
	 * @param source The managed object ID to which the alarm is associated.
	 * @param status The status of the alarm to search for.
	 * @param type The types of alarm to search for (comma separated).
	 * @param withSourceAssets When set to `true` also alarms for related source assets will be included in the request. When this parameter is provided a `source` must be specified.
	 * @param withSourceDevices When set to `true` also alarms for related source devices will be included in the request. When this parameter is provided a `source` must be specified.
	 */
	public Future<Response> deleteAlarms(final String xCumulocityProcessingMode, final String createdFrom, final String createdTo, final String dateFrom, final String dateTo, final boolean resolved, final String severity, final String source, final String status, final String[] type, final boolean withSourceAssets, final boolean withSourceDevices) {
		return getRootTarget().path("alarm").path("alarms")
			.queryParam("createdFrom", createdFrom)
			.queryParam("createdTo", createdTo)
			.queryParam("dateFrom", dateFrom)
			.queryParam("dateTo", dateTo)
			.queryParam("resolved", valueOf(resolved))
			.queryParam("severity", severity)
			.queryParam("source", source)
			.queryParam("status", status)
			.queryParam("type", valueOf(type))
			.queryParam("withSourceAssets", valueOf(withSourceAssets))
			.queryParam("withSourceDevices", valueOf(withSourceDevices))
				.request()
				.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
				.header("Accept", "application/json")
				.build("DELETE")
				.submit();
	}
	
	/**
	 * Retrieve a specific alarm </br>
	 * Retrieve a specific alarm by a given ID.  <section><h5>Required roles</h5> ROLE_ALARM_READ <b>OR</b> owner of the source <b>OR</b> ALARM_READ permission on the source </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the alarm is sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not authorized to perform this operation.</li>
	 * <li>404 Alarm not found.</li>
	 * </ul>
	 * <p>
	 * @param id Unique identifier of the alarm.
	 * @return
	 */
	public Future<Alarm> getAlarm(final String id) {
		return getRootTarget().path("alarm").path("alarms").path(valueOf(id))
				.request()
				.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.alarm+json")
				.build("GET")
				.submit(Alarm.class);
	}
	
	/**
	 * Update a specific alarm </br>
	 * Update a specific alarm by a given ID. Only text, status, severity and custom properties can be modified. A request will be rejected when non-modifiable properties are provided in the request body.  > **&#9432; Info:** Changes to alarms will generate a new audit record. The audit record will include the username and application that triggered the update, if applicable. If the update operation doesn’t change anything (that is, the request body contains data that is identical to the already present in the database), there will be no audit record added and no notifications will be sent.  <section><h5>Required roles</h5> ROLE_ALARM_ADMIN <b>OR</b> owner of the source <b>OR</b> ALARM_ADMIN permission on the source </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 An alarm was updated.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not authorized to perform this operation.</li>
	 * <li>404 Alarm not found.</li>
	 * <li>422 Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 * <p>
	 * @param body 
	 * @param id Unique identifier of the alarm.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 * @return
	 */
	public Future<Alarm> updateAlarm(final Alarm body, final String id, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "firstOccurrenceTime");
		removeFromNode(jsonNode, "lastUpdated");
		removeFromNode(jsonNode, "creationTime");
		removeFromNode(jsonNode, "count");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "id");
		removeFromNode(jsonNode, "source");
		removeFromNode(jsonNode, "time");
		removeFromNode(jsonNode, "type");
		return getRootTarget().path("alarm").path("alarms").path(valueOf(id))
				.request()
				.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
				.header("Content-Type", "application/vnd.com.nsn.cumulocity.alarm+json")
				.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.alarm+json")
				.build("PUT", Entity.json(jsonNode))
				.submit(Alarm.class);
	}
	
	/**
	 * Retrieve the total number of alarms </br>
	 * Count the total number of active alarms on your tenant.  <section><h5>Required roles</h5> The role ROLE_ALARM_READ is not required, but if a user has this role, all the alarms on the tenant are counted. Otherwise, inventory role permissions are used to count the alarms and the limit is 100. </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the number of active alarms is sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 * <p>
	 * @param dateFrom Start date or date and time of the alarm occurrence.
	 * @param dateTo End date or date and time of the alarm occurrence.
	 * @param resolved When set to `true` only alarms with status CLEARED will be fetched, whereas `false` will fetch all alarms with status ACTIVE or ACKNOWLEDGED.
	 * @param severity The severity of the alarm to search for.
	 * @param source The managed object ID to which the alarm is associated.
	 * @param status The status of the alarm to search for.
	 * @param type The types of alarm to search for (comma separated).
	 * @param withSourceAssets When set to `true` also alarms for related source assets will be included in the request. When this parameter is provided a `source` must be specified.
	 * @param withSourceDevices When set to `true` also alarms for related source devices will be included in the request. When this parameter is provided a `source` must be specified.
	 * @return
	 */
	public Future<Integer> getNumberOfAlarms(final String dateFrom, final String dateTo, final boolean resolved, final String severity, final String source, final String status, final String[] type, final boolean withSourceAssets, final boolean withSourceDevices) {
		return getRootTarget().path("alarm").path("alarms").path("count")
			.queryParam("dateFrom", dateFrom)
			.queryParam("dateTo", dateTo)
			.queryParam("resolved", valueOf(resolved))
			.queryParam("severity", severity)
			.queryParam("source", source)
			.queryParam("status", status)
			.queryParam("type", valueOf(type))
			.queryParam("withSourceAssets", valueOf(withSourceAssets))
			.queryParam("withSourceDevices", valueOf(withSourceDevices))
				.request()
				.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, text/plain, application/json")
				.build("GET")
				.submit(Integer.class);
	}
}
