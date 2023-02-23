// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import jakarta.ws.rs.client.WebTarget;
import com.cumulocity.client.supplementary.AdaptableApi;

/**
 * API methods to obtain access tokens to the Cumulocity IoT platform in case of OAI-Secure or SSO authentication. </br>
 * 
 */
public class LoginTokensApi extends AdaptableApi {

	public LoginTokensApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

}
