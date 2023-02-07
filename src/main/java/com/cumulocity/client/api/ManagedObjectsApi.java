// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api;

import java.util.concurrent.Future;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.ManagedObject;
import com.cumulocity.client.model.ManagedObjectUser;
import com.cumulocity.client.model.ManagedObjectCollection;
import com.cumulocity.client.model.SupportedMeasurements;
import com.cumulocity.client.model.SupportedSeries;

/**
 * The inventory stores devices and other assets relevant to your IoT solution. We refer to them as managed objects and such can be “smart objects”, for example, smart electricity meters, home automation gateways or GPS devices.
 * 
 * > **&#9432; Info:** The Accept header should be provided in all POST/PUT requests, otherwise an empty response body will be returned.
 *  </br>
 * 
 */ 
public class ManagedObjectsApi extends AdaptableApi {

	public ManagedObjectsApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * Retrieve all managed objects </br>
	 * Retrieve all managed objects (for example, devices, assets, etc.) registered in your tenant, or a subset based on queries. 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the collection of objects is sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>422 Invalid data was sent.</li>
	 * </ul>
	 * <p>
	 * @param childAdditionId Search for a specific child addition and list all the groups to which it belongs.
	 * @param childAssetId Search for a specific child asset and list all the groups to which it belongs.
	 * @param childDeviceId Search for a specific child device and list all the groups to which it belongs.
	 * @param currentPage The current page of the paginated results.
	 * @param fragmentType A characteristic which identifies a managed object or event, for example, geolocation, electricity sensor, relay state.
	 * @param ids The managed object IDs to search for (comma separated).
	 * @param onlyRoots When set to `true` it returns managed objects which don't have any parent. If the current user doesn't have access to the parent, this is also root for the user.
	 * @param owner Username of the owner of the managed objects.
	 * @param pageSize Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param q Similar to the parameter `query`, but it assumes that this is a device query request and it adds automatically the search criteria `fragmentType=c8y_IsDevice`.
	 * @param query Use query language to perform operations and/or filter the results. Details about the properties and supported operations can be found in [Query language](#tag/Query-language).
	 * @param skipChildrenNames When set to `true`, the returned references of child devices won't contain their names.
	 * @param text Search for managed objects where any property value is equal to the given one. Only string values are supported.
	 * @param type The type of managed object to search for.
	 * @param withChildren Determines if children with ID and name should be returned when fetching the managed object. Set it to `false` to improve query performance.
	 * @param withChildrenCount When set to `true`, the returned result will contain the total number of children in the respective objects (`childAdditions`, `childAssets` and `childDevices`).
	 * @param withGroups When set to `true` it returns additional information about the groups to which the searched managed object belongs. This results in setting the `assetParents` property with additional information about the groups.
	 * @param withParents When set to `true`, the returned references of child parents will return the device's parents (if any). Otherwise, it will be an empty array.
	 * @param withTotalElements When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @param withTotalPages When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @return
	 */
	public Future<ManagedObjectCollection> getManagedObjects(final String childAdditionId, final String childAssetId, final String childDeviceId, final int currentPage, final String fragmentType, final String[] ids, final boolean onlyRoots, final String owner, final int pageSize, final String q, final String query, final boolean skipChildrenNames, final String text, final String type, final boolean withChildren, final boolean withChildrenCount, final boolean withGroups, final boolean withParents, final boolean withTotalElements, final boolean withTotalPages) {
		return getRootTarget().path("inventory").path("managedObjects")
			.queryParam("childAdditionId", childAdditionId)
			.queryParam("childAssetId", childAssetId)
			.queryParam("childDeviceId", childDeviceId)
			.queryParam("currentPage", valueOf(currentPage))
			.queryParam("fragmentType", fragmentType)
			.queryParam("ids", valueOf(ids))
			.queryParam("onlyRoots", valueOf(onlyRoots))
			.queryParam("owner", owner)
			.queryParam("pageSize", valueOf(pageSize))
			.queryParam("q", q)
			.queryParam("query", query)
			.queryParam("skipChildrenNames", valueOf(skipChildrenNames))
			.queryParam("text", text)
			.queryParam("type", type)
			.queryParam("withChildren", valueOf(withChildren))
			.queryParam("withChildrenCount", valueOf(withChildrenCount))
			.queryParam("withGroups", valueOf(withGroups))
			.queryParam("withParents", valueOf(withParents))
			.queryParam("withTotalElements", valueOf(withTotalElements))
			.queryParam("withTotalPages", valueOf(withTotalPages))
				.request()
				.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.managedobjectcollection+json")
				.build("GET")
				.submit(ManagedObjectCollection.class);
	}
	
	/**
	 * Create a managed object </br>
	 * Create a managed object, for example, a device with temperature measurements support or a binary switch.<br> In general, each managed object may consist of:  *  A unique identifier that references the object. *  The name of the object. *  The most specific type of the managed object. *  A time stamp showing the last update. *  Fragments with specific meanings, for example, `c8y_IsDevice`, `c8y_SupportedOperations`. *  Any additional custom fragments.  Imagine, for example, that you want to describe electric meters from different vendors. Depending on the make of the meter, one may have a relay and one may be capable to measure a single phase or three phases (for example, a three-phase electricity sensor). A fragment `c8y_ThreePhaseElectricitySensor` would identify such an electric meter. Devices' characteristics are identified by storing fragments for each of them.  > **&#9432; Info:** For more details about fragments with specific meanings, review the sections [Device management library](#section/Device-management-library) and [Sensor library](#section/Sensor-library).  <section><h5>Required roles</h5> ROLE_INVENTORY_ADMIN <b>OR</b> ROLE_INVENTORY_CREATE </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>201 A managed object was created.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>422 Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 * <p>
	 * @param body 
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 * @return
	 */
	public Future<ManagedObject> createManagedObject(final ManagedObject body, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "owner");
		removeFromNode(jsonNode, "additionParents");
		removeFromNode(jsonNode, "lastUpdated");
		removeFromNode(jsonNode, "childDevices");
		removeFromNode(jsonNode, "childAssets");
		removeFromNode(jsonNode, "creationTime");
		removeFromNode(jsonNode, "childAdditions");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "assetParents");
		removeFromNode(jsonNode, "deviceParents");
		removeFromNode(jsonNode, "id");
		return getRootTarget().path("inventory").path("managedObjects")
				.request()
				.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
				.header("Content-Type", "application/vnd.com.nsn.cumulocity.managedobject+json")
				.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.managedobject+json")
				.build("POST", Entity.json(jsonNode))
				.submit(ManagedObject.class);
	}
	
	/**
	 * Retrieve the total number of managed objects </br>
	 * Retrieve the total number of managed objects (for example, devices, assets, etc.) registered in your tenant, or a subset based on queries.  <section><h5>Required roles</h5> ROLE_INVENTORY_READ is not required, but if the current user doesn't have this role, the response will contain the number of inventory objects accessible for the user. </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the number of managed objects is sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 * <p>
	 * @param childAdditionId Search for a specific child addition and list all the groups to which it belongs.
	 * @param childAssetId Search for a specific child asset and list all the groups to which it belongs.
	 * @param childDeviceId Search for a specific child device and list all the groups to which it belongs.
	 * @param fragmentType A characteristic which identifies a managed object or event, for example, geolocation, electricity sensor, relay state.
	 * @param ids The managed object IDs to search for (comma separated).
	 * @param owner Username of the owner of the managed objects.
	 * @param text Search for managed objects where any property value is equal to the given one. Only string values are supported.
	 * @param type The type of managed object to search for.
	 * @return
	 */
	public Future<Integer> getNumberOfManagedObjects(final String childAdditionId, final String childAssetId, final String childDeviceId, final String fragmentType, final String[] ids, final String owner, final String text, final String type) {
		return getRootTarget().path("inventory").path("managedObjects").path("count")
			.queryParam("childAdditionId", childAdditionId)
			.queryParam("childAssetId", childAssetId)
			.queryParam("childDeviceId", childDeviceId)
			.queryParam("fragmentType", fragmentType)
			.queryParam("ids", valueOf(ids))
			.queryParam("owner", owner)
			.queryParam("text", text)
			.queryParam("type", type)
				.request()
				.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, text/plain,application/json")
				.build("GET")
				.submit(Integer.class);
	}
	
	/**
	 * Retrieve a specific managed object </br>
	 * Retrieve a specific managed object (for example, device, group, template) by a given ID.  <section><h5>Required roles</h5> ROLE_INVENTORY_READ <b>OR</b> owner of the source <b>OR</b> MANAGE_OBJECT_READ permission on the source </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the object is sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Managed object not found.</li>
	 * </ul>
	 * <p>
	 * @param id Unique identifier of the managed object.
	 * @param skipChildrenNames When set to `true`, the returned references of child devices won't contain their names.
	 * @param withChildren Determines if children with ID and name should be returned when fetching the managed object. Set it to `false` to improve query performance.
	 * @param withChildrenCount When set to `true`, the returned result will contain the total number of children in the respective objects (`childAdditions`, `childAssets` and `childDevices`).
	 * @param withParents When set to `true`, the returned references of child parents will return the device's parents (if any). Otherwise, it will be an empty array.
	 * @return
	 */
	public Future<ManagedObject> getManagedObject(final String id, final boolean skipChildrenNames, final boolean withChildren, final boolean withChildrenCount, final boolean withParents) {
		return getRootTarget().path("inventory").path("managedObjects").path(valueOf(id))
			.queryParam("skipChildrenNames", valueOf(skipChildrenNames))
			.queryParam("withChildren", valueOf(withChildren))
			.queryParam("withChildrenCount", valueOf(withChildrenCount))
			.queryParam("withParents", valueOf(withParents))
				.request()
				.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.managedobject+json")
				.build("GET")
				.submit(ManagedObject.class);
	}
	
	/**
	 * Update a specific managed object </br>
	 * Update a specific managed object (for example, device) by a given ID.  For example, if you want to specify that your managed object is a device, you must add the fragment `c8y_IsDevice`.   The endpoint can also be used as a device availability heartbeat.  If you only specifiy the `id`, it updates the date when the last message was received and no other property.  The response then only contains the `id` instead of the full managed object.  <section><h5>Required roles</h5> ROLE_INVENTORY_ADMIN <b>OR</b> owner of the source <b>OR</b> MANAGE_OBJECT_ADMIN permission on the source </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 A managed object was updated.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Managed object not found.</li>
	 * </ul>
	 * <p>
	 * @param body 
	 * @param id Unique identifier of the managed object.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 * @return
	 */
	public Future<ManagedObject> updateManagedObject(final ManagedObject body, final String id, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "owner");
		removeFromNode(jsonNode, "additionParents");
		removeFromNode(jsonNode, "lastUpdated");
		removeFromNode(jsonNode, "childDevices");
		removeFromNode(jsonNode, "childAssets");
		removeFromNode(jsonNode, "creationTime");
		removeFromNode(jsonNode, "childAdditions");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "assetParents");
		removeFromNode(jsonNode, "deviceParents");
		removeFromNode(jsonNode, "id");
		return getRootTarget().path("inventory").path("managedObjects").path(valueOf(id))
				.request()
				.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
				.header("Content-Type", "application/vnd.com.nsn.cumulocity.managedobject+json")
				.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.managedobject+json")
				.build("PUT", Entity.json(jsonNode))
				.submit(ManagedObject.class);
	}
	
	/**
	 * Remove a specific managed object </br>
	 * Remove a specific managed object (for example, device) by a given ID.  > **&#9432; Info:** Inventory DELETE requests are not synchronous. The response could be returned before the delete request has been completed. This may happen especially when the deleted managed object has a lot of associated data. After sending the request, the platform starts deleting the associated data in an asynchronous way. Finally, the requested managed object is deleted after all associated data has been deleted.  <section><h5>Required roles</h5> ROLE_INVENTORY_ADMIN <b>OR</b> owner of the source <b>OR</b> MANAGE_OBJECT_ADMIN permission on the source </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>204 A managed object was removed.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Managed object not found.</li>
	 * <li>409 Conflict – The managed object is associated to other objects, for example child devices.</li>
	 * </ul>
	 * <p>
	 * @param id Unique identifier of the managed object.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 * @param cascade When set to `true` and the managed object is a device or group, all the hierarchy will be deleted.
	 * @param forceCascade When set to `true` all the hierarchy will be deleted without checking the type of managed object. It takes precedence over the parameter `cascade`.
	 * @param withDeviceUser When set to `true` and the managed object is a device, it deletes the associated device user (credentials).
	 */
	public Future<Response> deleteManagedObject(final String id, final String xCumulocityProcessingMode, final boolean cascade, final boolean forceCascade, final boolean withDeviceUser) {
		return getRootTarget().path("inventory").path("managedObjects").path(valueOf(id))
			.queryParam("cascade", valueOf(cascade))
			.queryParam("forceCascade", valueOf(forceCascade))
			.queryParam("withDeviceUser", valueOf(withDeviceUser))
				.request()
				.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
				.header("Accept", "application/json")
				.build("DELETE")
				.submit();
	}
	
	/**
	 * Retrieve the latest availability date of a specific managed object </br>
	 * Retrieve the date when a specific managed object (by a given ID) sent the last message to Cumulocity IoT.  <section><h5>Required roles</h5> ROLE_INVENTORY_READ </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the date is sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Managed object not found.</li>
	 * </ul>
	 * <p>
	 * @param id Unique identifier of the managed object.
	 * @return
	 */
	public Future<String> getLatestAvailability(final String id) {
		return getRootTarget().path("inventory").path("managedObjects").path(valueOf(id)).path("availability")
				.request()
				.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, text/plain, application/json")
				.build("GET")
				.submit(String.class);
	}
	
	/**
	 * Retrieve all supported measurement fragments of a specific managed object </br>
	 * Retrieve all measurement types of a specific managed object by a given ID.  <section><h5>Required roles</h5> ROLE_INVENTORY_READ <b>OR</b> owner of the source <b>OR</b> MANAGE_OBJECT_READ permission on the source </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and all measurement types are sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Managed object not found.</li>
	 * </ul>
	 * <p>
	 * @param id Unique identifier of the managed object.
	 * @return
	 */
	public Future<SupportedMeasurements> getSupportedMeasurements(final String id) {
		return getRootTarget().path("inventory").path("managedObjects").path(valueOf(id)).path("supportedMeasurements")
				.request()
				.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
				.build("GET")
				.submit(SupportedMeasurements.class);
	}
	
	/**
	 * Retrieve all supported measurement fragments and series of a specific managed object </br>
	 * Retrieve all supported measurement fragments and series of a specific managed object by a given ID.  <section><h5>Required roles</h5> ROLE_INVENTORY_READ <b>OR</b> owner of the source <b>OR</b> MANAGE_OBJECT_READ permission on the source </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and all supported measurement series are sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Managed object not found.</li>
	 * </ul>
	 * <p>
	 * @param id Unique identifier of the managed object.
	 * @return
	 */
	public Future<SupportedSeries> getSupportedSeries(final String id) {
		return getRootTarget().path("inventory").path("managedObjects").path(valueOf(id)).path("supportedSeries")
				.request()
				.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
				.build("GET")
				.submit(SupportedSeries.class);
	}
	
	/**
	 * Retrieve the username and state of a specific managed object </br>
	 * Retrieve the device owner's username and state (enabled or disabled) of a specific managed object (by a given ID).  <section><h5>Required roles</h5> ROLE_INVENTORY_READ <b>OR</b> owner of the source <b>OR</b> MANAGE_OBJECT_READ permission on the source </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the username and state are sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Managed object not found.</li>
	 * </ul>
	 * <p>
	 * @param id Unique identifier of the managed object.
	 * @return
	 */
	public Future<ManagedObjectUser> getManagedObjectUser(final String id) {
		return getRootTarget().path("inventory").path("managedObjects").path(valueOf(id)).path("user")
				.request()
				.header("Accept", "application/vnd.com.nsn.cumulocity.managedobjectuser+json, application/vnd.com.nsn.cumulocity.error+json")
				.build("GET")
				.submit(ManagedObjectUser.class);
	}
	
	/**
	 * Update the user's details of a specific managed object </br>
	 * Update the device owner's state (enabled or disabled) of a specific managed object (by a given ID).  <section><h5>Required roles</h5> ROLE_INVENTORY_ADMIN <b>OR</b> owner of the source <b>OR</b> MANAGE_OBJECT_ADMIN permission on the source </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The user's details of a specific managed object were updated.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Managed object not found.</li>
	 * </ul>
	 * <p>
	 * @param body 
	 * @param id Unique identifier of the managed object.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 * @return
	 */
	public Future<ManagedObjectUser> updateManagedObjectUser(final ManagedObjectUser body, final String id, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "userName");
		return getRootTarget().path("inventory").path("managedObjects").path(valueOf(id)).path("user")
				.request()
				.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
				.header("Content-Type", "application/vnd.com.nsn.cumulocity.managedobjectuser+json")
				.header("Accept", "application/vnd.com.nsn.cumulocity.managedobjectuser+json, application/vnd.com.nsn.cumulocity.error+json")
				.build("PUT", Entity.json(jsonNode))
				.submit(ManagedObjectUser.class);
	}
}
