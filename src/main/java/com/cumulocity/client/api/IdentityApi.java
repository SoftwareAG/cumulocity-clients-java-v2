// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api;

import java.util.concurrent.Future;
import jakarta.ws.rs.client.WebTarget;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.IdentityApiResource;

/**
 * Cumulocity IoT can associate devices and assets with multiple external identities.
 * For instance, devices can often be identified by the IMEI of their modem, by a micro-controller serial number or by an asset tag.
 * This is useful, for example, when you have non-functional hardware and must replace the hardware without losing the data that was recorded.
 * 
 * The identity API resource returns URIs and URI templates for associating external identifiers with unique identifiers.
 *  </br>
 * 
 */ 
public class IdentityApi extends AdaptableApi {

	public IdentityApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * Retrieve URIs to collections of external IDs </br>
	 * Retrieve URIs and URI templates for associating external identifiers with unique identifiers.  <section><h5>Required roles</h5> ROLE_IDENTITY_READ </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the URIs are sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 * @return
	 */
	public Future<IdentityApiResource> getIdentityApiResource() {
		return adapt().path("identity")
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.identityapi+json, application/vnd.com.nsn.cumulocity.error+json")
			.build("GET")
			.submit(IdentityApiResource.class);
	}
}
