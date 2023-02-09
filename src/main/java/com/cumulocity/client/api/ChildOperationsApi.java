// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api;

import java.util.concurrent.Future;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.ChildOperationsAddOne;
import com.cumulocity.client.model.ChildOperationsAddMultiple;
import com.cumulocity.client.model.ManagedObject;
import com.cumulocity.client.model.ManagedObjectReferenceCollection;
import com.cumulocity.client.model.ManagedObjectReference;

/**
 * Managed objects can contain collections of references to child devices, additions and assets.
 * 
 * > **&#9432; Info:** The Accept header should be provided in all POST requests, otherwise an empty response body will be returned.
 *  </br>
 * 
 */ 
public class ChildOperationsApi extends AdaptableApi {

	public ChildOperationsApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * Retrieve all child additions of a specific managed object </br>
	 * Retrieve all child additions of a specific managed object by a given ID, or a subset based on queries.  <section><h5>Required roles</h5> ROLE_INVENTORY_READ <b>OR</b> owner of the source <b>OR</b> MANAGE_OBJECT_READ permission on the source </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and all child additions are sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Managed object not found.</li>
	 * <li>422 Invalid data was sent.</li>
	 * </ul>
	 * <p>
	 * @param id Unique identifier of the managed object.
	 * @param currentPage The current page of the paginated results.
	 * @param pageSize Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param query Use query language to perform operations and/or filter the results. Details about the properties and supported operations can be found in [Query language](#tag/Query-language).
	 * @param withChildren Determines if children with ID and name should be returned when fetching the managed object. Set it to `false` to improve query performance.
	 * @param withChildrenCount When set to `true`, the returned result will contain the total number of children in the respective objects (`childAdditions`, `childAssets` and `childDevices`).
	 * @param withTotalElements When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @param withTotalPages When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @return
	 */
	public Future<ManagedObjectReferenceCollection> getChildAdditions(final String id, final int currentPage, final int pageSize, final String query, final boolean withChildren, final boolean withChildrenCount, final boolean withTotalElements, final boolean withTotalPages) {
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("childAdditions")
			.queryParam("currentPage", currentPage)
			.queryParam("pageSize", pageSize)
			.queryParam("query", query)
			.queryParam("withChildren", withChildren)
			.queryParam("withChildrenCount", withChildrenCount)
			.queryParam("withTotalElements", withTotalElements)
			.queryParam("withTotalPages", withTotalPages)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.managedobjectreferencecollection+json")
			.build("GET")
			.submit(ManagedObjectReferenceCollection.class);
	}
	
	/**
	 * Assign a managed object as child addition </br>
	 * The possible ways to assign child objects are:  *  Assign an existing managed object (by a given child ID) as child addition of another managed object (by a given ID). *  Assign multiple existing managed objects (by given child IDs) as child additions of another managed object (by a given ID). *  Create a managed object in the inventory and assign it as a child addition to another managed object (by a given ID).  <section><h5>Required roles</h5> ROLE_INVENTORY_ADMIN <b>OR</b> ((owner of the source <b>OR</b> MANAGE_OBJECT_ADMIN permission on the source) <b>AND</b> (owner of the child <b>OR</b> MANAGE_OBJECT_ADMIN permission on the child)) </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>201 A managed object was assigned as child addition.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Managed object not found.</li>
	 * </ul>
	 * <p>
	 * @param body 
	 * @param id Unique identifier of the managed object.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	public Future<Response> assignAsChildAddition(final ChildOperationsAddOne body, final String id, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("childAdditions")
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.managedobjectreference+json")
			.header("Accept", "application/json")
			.build("POST", Entity.json(jsonNode))
			.submit();
	}
	
	/**
	 * Assign a managed object as child addition </br>
	 * The possible ways to assign child objects are:  *  Assign an existing managed object (by a given child ID) as child addition of another managed object (by a given ID). *  Assign multiple existing managed objects (by given child IDs) as child additions of another managed object (by a given ID). *  Create a managed object in the inventory and assign it as a child addition to another managed object (by a given ID).  <section><h5>Required roles</h5> ROLE_INVENTORY_ADMIN <b>OR</b> ((owner of the source <b>OR</b> MANAGE_OBJECT_ADMIN permission on the source) <b>AND</b> (owner of the child <b>OR</b> MANAGE_OBJECT_ADMIN permission on the child)) </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>201 A managed object was assigned as child addition.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Managed object not found.</li>
	 * </ul>
	 * <p>
	 * @param body 
	 * @param id Unique identifier of the managed object.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	public Future<Response> assignAsChildAddition(final ChildOperationsAddMultiple body, final String id, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("childAdditions")
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.managedobjectreferencecollection+json")
			.header("Accept", "application/json")
			.build("POST", Entity.json(jsonNode))
			.submit();
	}
	
	/**
	 * Assign a managed object as child addition </br>
	 * The possible ways to assign child objects are:  *  Assign an existing managed object (by a given child ID) as child addition of another managed object (by a given ID). *  Assign multiple existing managed objects (by given child IDs) as child additions of another managed object (by a given ID). *  Create a managed object in the inventory and assign it as a child addition to another managed object (by a given ID).  <section><h5>Required roles</h5> ROLE_INVENTORY_ADMIN <b>OR</b> ((owner of the source <b>OR</b> MANAGE_OBJECT_ADMIN permission on the source) <b>AND</b> (owner of the child <b>OR</b> MANAGE_OBJECT_ADMIN permission on the child)) </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>201 A managed object was assigned as child addition.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Managed object not found.</li>
	 * </ul>
	 * <p>
	 * @param body 
	 * @param id Unique identifier of the managed object.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	public Future<Response> assignAsChildAddition(final ManagedObject body, final String id, final String xCumulocityProcessingMode) {
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
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("childAdditions")
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.managedobject+json")
			.header("Accept", "application/json")
			.build("POST", Entity.json(jsonNode))
			.submit();
	}
	
	/**
	 * Remove specific child additions from its parent </br>
	 * Remove specific child additions (by given child IDs) from its parent (by a given ID).  <section><h5>Required roles</h5> ROLE_INVENTORY_ADMIN <b>OR</b> owner of the source (parent) <b>OR</b> owner of the child <b>OR</b> MANAGE_OBJECT_ADMIN permission on the source (parent) </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>204 Child additions were removed.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Managed object not found.</li>
	 * <li>422 Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 * <p>
	 * @param body 
	 * @param id Unique identifier of the managed object.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	public Future<Response> unassignChildAdditions(final ChildOperationsAddMultiple body, final String id, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("childAdditions")
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.managedobjectreferencecollection+json")
			.header("Accept", "application/json")
			.build("DELETE", Entity.json(jsonNode))
			.submit();
	}
	
	/**
	 * Retrieve a specific child addition of a specific managed object </br>
	 * Retrieve a specific child addition (by a given child ID) of a specific managed object (by a given ID).  <section><h5>Required roles</h5> ROLE_INVENTORY_READ <b>OR</b> MANAGE_OBJECT_READ permission on the source (parent) </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the child addition is sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Managed object not found.</li>
	 * <li>422 Invalid data was sent.</li>
	 * </ul>
	 * <p>
	 * @param id Unique identifier of the managed object.
	 * @param childId Unique identifier of the child object.
	 * @return
	 */
	public Future<ManagedObjectReference> getChildAddition(final String id, final String childId) {
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("childAdditions").path(valueOf(childId))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.managedobjectreference+json, application/vnd.com.nsn.cumulocity.error+json")
			.build("GET")
			.submit(ManagedObjectReference.class);
	}
	
	/**
	 * Remove a specific child addition from its parent </br>
	 * Remove a specific child addition (by a given child ID) from its parent (by a given ID).  <section><h5>Required roles</h5> ROLE_INVENTORY_ADMIN <b>OR</b> owner of the source (parent) <b>OR</b> owner of the child <b>OR</b> MANAGE_OBJECT_ADMIN permission on the source (parent) </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>204 A child addition was removed.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Managed object not found.</li>
	 * <li>422 Invalid data was sent.</li>
	 * </ul>
	 * <p>
	 * @param id Unique identifier of the managed object.
	 * @param childId Unique identifier of the child object.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	public Future<Response> unassignChildAddition(final String id, final String childId, final String xCumulocityProcessingMode) {
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("childAdditions").path(valueOf(childId))
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Accept", "application/json")
			.build("DELETE")
			.submit();
	}
	
	/**
	 * Retrieve all child assets of a specific managed object </br>
	 * Retrieve all child assets of a specific managed object by a given ID, or a subset based on queries.  <section><h5>Required roles</h5> ROLE_INVENTORY_READ <b>OR</b> owner of the source <b>OR</b> MANAGE_OBJECT_READ permission on the source </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and all child assets are sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Managed object not found.</li>
	 * <li>422 Invalid data was sent.</li>
	 * </ul>
	 * <p>
	 * @param id Unique identifier of the managed object.
	 * @param currentPage The current page of the paginated results.
	 * @param pageSize Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param query Use query language to perform operations and/or filter the results. Details about the properties and supported operations can be found in [Query language](#tag/Query-language).
	 * @param withChildren Determines if children with ID and name should be returned when fetching the managed object. Set it to `false` to improve query performance.
	 * @param withChildrenCount When set to `true`, the returned result will contain the total number of children in the respective objects (`childAdditions`, `childAssets` and `childDevices`).
	 * @param withTotalElements When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @param withTotalPages When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @return
	 */
	public Future<ManagedObjectReferenceCollection> getChildAssets(final String id, final int currentPage, final int pageSize, final String query, final boolean withChildren, final boolean withChildrenCount, final boolean withTotalElements, final boolean withTotalPages) {
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("childAssets")
			.queryParam("currentPage", currentPage)
			.queryParam("pageSize", pageSize)
			.queryParam("query", query)
			.queryParam("withChildren", withChildren)
			.queryParam("withChildrenCount", withChildrenCount)
			.queryParam("withTotalElements", withTotalElements)
			.queryParam("withTotalPages", withTotalPages)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.managedobjectreferencecollection+json")
			.build("GET")
			.submit(ManagedObjectReferenceCollection.class);
	}
	
	/**
	 * Assign a managed object as child asset </br>
	 * The possible ways to assign child objects are:  *  Assign an existing managed object (by a given child ID) as child asset of another managed object (by a given ID). *  Assign multiple existing managed objects (by given child IDs) as child assets of another managed object (by a given ID). *  Create a managed object in the inventory and assign it as a child asset to another managed object (by a given ID).  <section><h5>Required roles</h5> ROLE_INVENTORY_ADMIN <b>OR</b> ((owner of the source <b>OR</b> MANAGE_OBJECT_ADMIN permission on the source) <b>AND</b> (owner of the child <b>OR</b> MANAGE_OBJECT_ADMIN permission on the child)) </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>201 A managed object was assigned as child asset.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Managed object not found.</li>
	 * </ul>
	 * <p>
	 * @param body 
	 * @param id Unique identifier of the managed object.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	public Future<Response> assignAsChildAsset(final ChildOperationsAddOne body, final String id, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("childAssets")
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.managedobjectreference+json")
			.header("Accept", "application/json")
			.build("POST", Entity.json(jsonNode))
			.submit();
	}
	
	/**
	 * Assign a managed object as child asset </br>
	 * The possible ways to assign child objects are:  *  Assign an existing managed object (by a given child ID) as child asset of another managed object (by a given ID). *  Assign multiple existing managed objects (by given child IDs) as child assets of another managed object (by a given ID). *  Create a managed object in the inventory and assign it as a child asset to another managed object (by a given ID).  <section><h5>Required roles</h5> ROLE_INVENTORY_ADMIN <b>OR</b> ((owner of the source <b>OR</b> MANAGE_OBJECT_ADMIN permission on the source) <b>AND</b> (owner of the child <b>OR</b> MANAGE_OBJECT_ADMIN permission on the child)) </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>201 A managed object was assigned as child asset.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Managed object not found.</li>
	 * </ul>
	 * <p>
	 * @param body 
	 * @param id Unique identifier of the managed object.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	public Future<Response> assignAsChildAsset(final ChildOperationsAddMultiple body, final String id, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("childAssets")
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.managedobjectreferencecollection+json")
			.header("Accept", "application/json")
			.build("POST", Entity.json(jsonNode))
			.submit();
	}
	
	/**
	 * Assign a managed object as child asset </br>
	 * The possible ways to assign child objects are:  *  Assign an existing managed object (by a given child ID) as child asset of another managed object (by a given ID). *  Assign multiple existing managed objects (by given child IDs) as child assets of another managed object (by a given ID). *  Create a managed object in the inventory and assign it as a child asset to another managed object (by a given ID).  <section><h5>Required roles</h5> ROLE_INVENTORY_ADMIN <b>OR</b> ((owner of the source <b>OR</b> MANAGE_OBJECT_ADMIN permission on the source) <b>AND</b> (owner of the child <b>OR</b> MANAGE_OBJECT_ADMIN permission on the child)) </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>201 A managed object was assigned as child asset.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Managed object not found.</li>
	 * </ul>
	 * <p>
	 * @param body 
	 * @param id Unique identifier of the managed object.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	public Future<Response> assignAsChildAsset(final ManagedObject body, final String id, final String xCumulocityProcessingMode) {
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
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("childAssets")
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.managedobject+json")
			.header("Accept", "application/json")
			.build("POST", Entity.json(jsonNode))
			.submit();
	}
	
	/**
	 * Remove specific child assets from its parent </br>
	 * Remove specific child assets (by given child IDs) from its parent (by a given ID).  <section><h5>Required roles</h5> ROLE_INVENTORY_ADMIN <b>OR</b> owner of the source (parent) <b>OR</b> owner of the child <b>OR</b> MANAGE_OBJECT_ADMIN permission on the source (parent) </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>204 Child assets were removed.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Managed object not found.</li>
	 * <li>422 Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 * <p>
	 * @param body 
	 * @param id Unique identifier of the managed object.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	public Future<Response> unassignChildAssets(final ChildOperationsAddMultiple body, final String id, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("childAssets")
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.managedobjectreferencecollection+json")
			.header("Accept", "application/json")
			.build("DELETE", Entity.json(jsonNode))
			.submit();
	}
	
	/**
	 * Retrieve a specific child asset of a specific managed object </br>
	 * Retrieve a specific child asset (by a given child ID) of a specific managed object (by a given ID).  <section><h5>Required roles</h5> ROLE_INVENTORY_READ <b>OR</b> MANAGE_OBJECT_READ permission on the source (parent) </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the child asset is sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Managed object not found.</li>
	 * <li>422 Invalid data was sent.</li>
	 * </ul>
	 * <p>
	 * @param id Unique identifier of the managed object.
	 * @param childId Unique identifier of the child object.
	 * @return
	 */
	public Future<ManagedObjectReference> getChildAsset(final String id, final String childId) {
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("childAssets").path(valueOf(childId))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.managedobjectreference+json, application/vnd.com.nsn.cumulocity.error+json")
			.build("GET")
			.submit(ManagedObjectReference.class);
	}
	
	/**
	 * Remove a specific child asset from its parent </br>
	 * Remove a specific child asset (by a given child ID) from its parent (by a given ID).  <section><h5>Required roles</h5> ROLE_INVENTORY_ADMIN <b>OR</b> owner of the source (parent) <b>OR</b> owner of the child <b>OR</b> MANAGE_OBJECT_ADMIN permission on the source (parent) </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>204 A child asset was removed.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Managed object not found.</li>
	 * <li>422 Invalid data was sent.</li>
	 * </ul>
	 * <p>
	 * @param id Unique identifier of the managed object.
	 * @param childId Unique identifier of the child object.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	public Future<Response> unassignChildAsset(final String id, final String childId, final String xCumulocityProcessingMode) {
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("childAssets").path(valueOf(childId))
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Accept", "application/json")
			.build("DELETE")
			.submit();
	}
	
	/**
	 * Retrieve all child devices of a specific managed object </br>
	 * Retrieve all child devices of a specific managed object by a given ID, or a subset based on queries.  <section><h5>Required roles</h5> ROLE_INVENTORY_READ <b>OR</b> owner of the source <b>OR</b> MANAGE_OBJECT_READ permission on the source </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and all child devices are sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Managed object not found.</li>
	 * <li>422 Invalid data was sent.</li>
	 * </ul>
	 * <p>
	 * @param id Unique identifier of the managed object.
	 * @param currentPage The current page of the paginated results.
	 * @param pageSize Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param query Use query language to perform operations and/or filter the results. Details about the properties and supported operations can be found in [Query language](#tag/Query-language).
	 * @param withChildren Determines if children with ID and name should be returned when fetching the managed object. Set it to `false` to improve query performance.
	 * @param withChildrenCount When set to `true`, the returned result will contain the total number of children in the respective objects (`childAdditions`, `childAssets` and `childDevices`).
	 * @param withTotalElements When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @param withTotalPages When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @return
	 */
	public Future<ManagedObjectReferenceCollection> getChildDevices(final String id, final int currentPage, final int pageSize, final String query, final boolean withChildren, final boolean withChildrenCount, final boolean withTotalElements, final boolean withTotalPages) {
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("childDevices")
			.queryParam("currentPage", currentPage)
			.queryParam("pageSize", pageSize)
			.queryParam("query", query)
			.queryParam("withChildren", withChildren)
			.queryParam("withChildrenCount", withChildrenCount)
			.queryParam("withTotalElements", withTotalElements)
			.queryParam("withTotalPages", withTotalPages)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.managedobjectreferencecollection+json")
			.build("GET")
			.submit(ManagedObjectReferenceCollection.class);
	}
	
	/**
	 * Assign a managed object as child device </br>
	 * The possible ways to assign child objects are:  *  Assign an existing managed object (by a given child ID) as child device of another managed object (by a given ID). *  Assign multiple existing managed objects (by given child IDs) as child devices of another managed object (by a given ID). *  Create a managed object in the inventory and assign it as a child device to another managed object (by a given ID).  <section><h5>Required roles</h5> ROLE_INVENTORY_ADMIN <b>OR</b> ((owner of the source <b>OR</b> MANAGE_OBJECT_ADMIN permission on the source) <b>AND</b> (owner of the child <b>OR</b> MANAGE_OBJECT_ADMIN permission on the child)) </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>201 A managed object was assigned as child device.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Managed object not found.</li>
	 * </ul>
	 * <p>
	 * @param body 
	 * @param id Unique identifier of the managed object.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	public Future<Response> assignAsChildDevice(final ChildOperationsAddOne body, final String id, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("childDevices")
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.managedobjectreference+json")
			.header("Accept", "application/json")
			.build("POST", Entity.json(jsonNode))
			.submit();
	}
	
	/**
	 * Assign a managed object as child device </br>
	 * The possible ways to assign child objects are:  *  Assign an existing managed object (by a given child ID) as child device of another managed object (by a given ID). *  Assign multiple existing managed objects (by given child IDs) as child devices of another managed object (by a given ID). *  Create a managed object in the inventory and assign it as a child device to another managed object (by a given ID).  <section><h5>Required roles</h5> ROLE_INVENTORY_ADMIN <b>OR</b> ((owner of the source <b>OR</b> MANAGE_OBJECT_ADMIN permission on the source) <b>AND</b> (owner of the child <b>OR</b> MANAGE_OBJECT_ADMIN permission on the child)) </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>201 A managed object was assigned as child device.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Managed object not found.</li>
	 * </ul>
	 * <p>
	 * @param body 
	 * @param id Unique identifier of the managed object.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	public Future<Response> assignAsChildDevice(final ChildOperationsAddMultiple body, final String id, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("childDevices")
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.managedobjectreferencecollection+json")
			.header("Accept", "application/json")
			.build("POST", Entity.json(jsonNode))
			.submit();
	}
	
	/**
	 * Assign a managed object as child device </br>
	 * The possible ways to assign child objects are:  *  Assign an existing managed object (by a given child ID) as child device of another managed object (by a given ID). *  Assign multiple existing managed objects (by given child IDs) as child devices of another managed object (by a given ID). *  Create a managed object in the inventory and assign it as a child device to another managed object (by a given ID).  <section><h5>Required roles</h5> ROLE_INVENTORY_ADMIN <b>OR</b> ((owner of the source <b>OR</b> MANAGE_OBJECT_ADMIN permission on the source) <b>AND</b> (owner of the child <b>OR</b> MANAGE_OBJECT_ADMIN permission on the child)) </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>201 A managed object was assigned as child device.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Managed object not found.</li>
	 * </ul>
	 * <p>
	 * @param body 
	 * @param id Unique identifier of the managed object.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	public Future<Response> assignAsChildDevice(final ManagedObject body, final String id, final String xCumulocityProcessingMode) {
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
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("childDevices")
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.managedobject+json")
			.header("Accept", "application/json")
			.build("POST", Entity.json(jsonNode))
			.submit();
	}
	
	/**
	 * Remove specific child devices from its parent </br>
	 * Remove specific child devices (by given child IDs) from its parent (by a given ID).  <section><h5>Required roles</h5> ROLE_INVENTORY_ADMIN <b>OR</b> owner of the source (parent) <b>OR</b> owner of the child <b>OR</b> MANAGE_OBJECT_ADMIN permission on the source (parent) </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>204 Child devices were removed.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Managed object not found.</li>
	 * <li>422 Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 * <p>
	 * @param body 
	 * @param id Unique identifier of the managed object.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	public Future<Response> unassignChildDevices(final ChildOperationsAddMultiple body, final String id, final String xCumulocityProcessingMode) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("childDevices")
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/vnd.com.nsn.cumulocity.managedobjectreferencecollection+json")
			.header("Accept", "application/json")
			.build("DELETE", Entity.json(jsonNode))
			.submit();
	}
	
	/**
	 * Retrieve a specific child device of a specific managed object </br>
	 * Retrieve a specific child device (by a given child ID) of a specific managed object (by a given ID).  <section><h5>Required roles</h5> ROLE_INVENTORY_READ <b>OR</b> MANAGE_OBJECT_READ permission on the source (parent) </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the child device is sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Managed object not found.</li>
	 * <li>422 Invalid data was sent.</li>
	 * </ul>
	 * <p>
	 * @param id Unique identifier of the managed object.
	 * @param childId Unique identifier of the child object.
	 * @return
	 */
	public Future<ManagedObjectReference> getChildDevice(final String id, final String childId) {
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("childDevices").path(valueOf(childId))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.managedobjectreference+json, application/vnd.com.nsn.cumulocity.error+json")
			.build("GET")
			.submit(ManagedObjectReference.class);
	}
	
	/**
	 * Remove a specific child device from its parent </br>
	 * Remove a specific child device (by a given child ID) from its parent (by a given ID).  <section><h5>Required roles</h5> ROLE_INVENTORY_ADMIN <b>OR</b> owner of the source (parent) <b>OR</b> owner of the child <b>OR</b> MANAGE_OBJECT_ADMIN permission on the source (parent) </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>204 A child device was removed.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Managed object not found.</li>
	 * <li>422 Invalid data was sent.</li>
	 * </ul>
	 * <p>
	 * @param id Unique identifier of the managed object.
	 * @param childId Unique identifier of the child object.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	public Future<Response> unassignChildDevice(final String id, final String childId, final String xCumulocityProcessingMode) {
		return adapt().path("inventory").path("managedObjects").path(valueOf(id)).path("childDevices").path(valueOf(childId))
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Accept", "application/json")
			.build("DELETE")
			.submit();
	}
}
