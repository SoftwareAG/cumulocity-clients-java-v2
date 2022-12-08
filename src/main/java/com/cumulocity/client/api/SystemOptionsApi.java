// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api;

import java.util.concurrent.Future;
import jakarta.ws.rs.client.WebTarget;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.SystemOptionCollection;
import com.cumulocity.client.model.SystemOption;

/**
 * API methods to retrieve the read-only properties predefined in the platform's configuration. </br>
 * 
 */ 
public class SystemOptionsApi extends AdaptableApi {

	public SystemOptionsApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * Retrieve all system options </br>
	 * Retrieve all the system options available on the tenant.
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the system options are sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 * @return
	 */
	public Future<SystemOptionCollection> getSystemOptions() {
		return getRootTarget().path("tenant").path("system").path("options")
				.request()
				.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.optioncollection+json")
				.build("GET")
				.submit(SystemOptionCollection.class);
	}
	
	/**
	 * Retrieve a specific system option </br>
	 * Retrieve a specific system option (by a given category and key) on your tenant.
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the system option is sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 * <p>
	 * @param category The category of the system options.
	 * @param key The key of a system option.
	 * @return
	 */
	public Future<SystemOption> getSystemOption(final String category, final String key) {
		return getRootTarget().path("tenant").path("system").path("options").path(valueOf(category)).path(valueOf(key))
				.request()
				.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.option+json")
				.build("GET")
				.submit(SystemOption.class);
	}
}
