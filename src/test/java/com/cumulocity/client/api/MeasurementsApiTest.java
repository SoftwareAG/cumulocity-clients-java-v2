// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.concurrent.ExecutionException;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;

public class MeasurementsApiTest {

	private static MeasurementsApi service;

	@BeforeAll
	static void setup() {
		final ClientConfig clientConfig = new ClientConfig();
		final HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("userName", "password");
		clientConfig.register(feature);
		final Client client = ClientBuilder.newClient(clientConfig);
		final WebTarget webTarget = client.target("https://endpoint");
		service = new MeasurementsApi(webTarget);
	}

    @Test
    public void testGetMeasurements() {
    	Object response = null;
    	try {
    		response = service.getMeasurements(1, null, null, 5, false, null, null, null, null, false, false).toCompletableFuture().get();
    	} catch (InterruptedException | ExecutionException e) {
    		e.printStackTrace();
    	}
    	assertNotNull(response);
    }
}
