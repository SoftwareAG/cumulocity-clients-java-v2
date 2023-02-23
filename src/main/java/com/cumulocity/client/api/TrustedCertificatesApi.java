// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api;

import java.util.concurrent.CompletionStage;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import com.cumulocity.client.supplementary.AdaptableApi;
import com.cumulocity.client.model.TrustedCertificate;
import com.cumulocity.client.model.TrustedCertificateCollection;
import com.cumulocity.client.model.UploadedTrustedCertSignedVerificationCode;

/**
 * API methods for managing trusted certificates used to establish device connections via MQTT.
 * 
 * More detailed information about trusted certificates and their role can be found in [Device management > Managing device data](https://cumulocity.com/guides/users-guide/device-management/#managing-device-data) in the *User guide*.
 * 
 * > **&#9432; Info:** The Accept header must be provided in all POST/PUT requests, otherwise an empty response body will be returned.
 *  </br>
 * 
 */
public class TrustedCertificatesApi extends AdaptableApi {

	public TrustedCertificatesApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * Retrieve all stored certificates
	 * Retrieve all the trusted certificates of a specific tenant (by a given ID).
	 * 
	 * <section><h5>Required roles</h5>
	 * (ROLE_TENANT_MANAGEMENT_ADMIN <b>OR</b> ROLE_TENANT_ADMIN) <b>AND</b> (is the current tenant)
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The request has succeeded and the trusted certificates are sent in the response.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 403 - Not authorized to perform this operation.</li>
	 *     <li>HTTP 404 - Tenant not found., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param currentPage The current page of the paginated results.
	 * @param pageSize Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param withTotalElements When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @param withTotalPages When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @return
	 */
	public CompletionStage<TrustedCertificateCollection> getTrustedCertificates(final String tenantId, final int currentPage, final int pageSize, final boolean withTotalElements, final boolean withTotalPages) {
		return adapt().path("tenant").path("tenants").path(valueOf(tenantId)).path("trusted-certificates")
			.queryParam("currentPage", currentPage)
			.queryParam("pageSize", pageSize)
			.queryParam("withTotalElements", withTotalElements)
			.queryParam("withTotalPages", withTotalPages)
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("GET", TrustedCertificateCollection.class);
	}
	
	/**
	 * Add a new certificate
	 * Add a new trusted certificate to a specific tenant (by a given ID) which can be further used by the devices to establish connections with the Cumulocity IoT platform.
	 * 
	 * <section><h5>Required roles</h5>
	 * (ROLE_TENANT_MANAGEMENT_ADMIN <b>OR</b> ROLE_TENANT_ADMIN) <b>AND</b> (is the current tenant)
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 201 - The certificate was added to the tenant.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - Tenant not found., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 409 - Duplicate – A certificate with the same fingerprint already exists.</li>
	 *     <li>HTTP 422 - Unprocessable Entity – Invalid certificate data.</li>
	 * </ul>
	 * @param body 
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @return
	 */
	public CompletionStage<TrustedCertificate> addTrustedCertificate(final TrustedCertificate body, final String tenantId) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "notAfter");
		removeFromNode(jsonNode, "serialNumber");
		removeFromNode(jsonNode, "subject");
		removeFromNode(jsonNode, "fingerprint");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "algorithmName");
		removeFromNode(jsonNode, "version");
		removeFromNode(jsonNode, "issuer");
		removeFromNode(jsonNode, "notBefore");
		return adapt().path("tenant").path("tenants").path(valueOf(tenantId)).path("trusted-certificates")
			.request()
			.header("Content-Type", "application/json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("POST", Entity.json(jsonNode), TrustedCertificate.class);
	}
	
	/**
	 * Add multiple certificates
	 * Add multiple trusted certificates to a specific tenant (by a given ID) which can be further used by the devices to establish connections with the Cumulocity IoT platform.
	 * 
	 * <section><h5>Required roles</h5>
	 * (ROLE_TENANT_MANAGEMENT_ADMIN <b>OR</b> ROLE_TENANT_ADMIN) <b>AND</b> (is the current tenant)
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 201 - The certificates were added to the tenant.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - Tenant not found., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 409 - Duplicate – A certificate with the same fingerprint already exists.</li>
	 *     <li>HTTP 422 - Unprocessable Entity – Invalid certificates data.</li>
	 * </ul>
	 * @param body 
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @return
	 */
	public CompletionStage<TrustedCertificateCollection> addTrustedCertificates(final TrustedCertificateCollection body, final String tenantId) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "next");
		removeFromNode(jsonNode, "prev");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "statistics");
		return adapt().path("tenant").path("tenants").path(valueOf(tenantId)).path("trusted-certificates").path("bulk")
			.request()
			.header("Content-Type", "application/json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("POST", Entity.json(jsonNode), TrustedCertificateCollection.class);
	}
	
	/**
	 * Retrieve a stored certificate
	 * Retrieve the data of a stored trusted certificate (by a given fingerprint) of a specific tenant (by a given ID).
	 * 
	 * <section><h5>Required roles</h5>
	 * (ROLE_TENANT_MANAGEMENT_ADMIN <b>OR</b> ROLE_TENANT_ADMIN) <b>AND</b> (is the current tenant <b>OR</b> is the management tenant)
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The request has succeeded and the trusted certificate is sent in the response.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param fingerprint Unique identifier of a trusted certificate.
	 * @return
	 */
	public CompletionStage<TrustedCertificate> getTrustedCertificate(final String tenantId, final String fingerprint) {
		return adapt().path("tenant").path("tenants").path(valueOf(tenantId)).path("trusted-certificates").path(valueOf(fingerprint))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("GET", TrustedCertificate.class);
	}
	
	/**
	 * Update a stored certificate
	 * Update the data of a stored trusted certificate (by a given fingerprint) of a specific tenant (by a given ID).
	 * 
	 * <section><h5>Required roles</h5>
	 * (ROLE_TENANT_MANAGEMENT_ADMIN <b>OR</b> ROLE_TENANT_ADMIN) <b>AND</b> (is the current tenant <b>OR</b> is the management tenant)
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The certificate was updated on the tenant.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - Certificate not found.</li>
	 *     <li>HTTP 422 - Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 * @param body 
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param fingerprint Unique identifier of a trusted certificate.
	 * @return
	 */
	public CompletionStage<TrustedCertificate> updateTrustedCertificate(final TrustedCertificate body, final String tenantId, final String fingerprint) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "notAfter");
		removeFromNode(jsonNode, "serialNumber");
		removeFromNode(jsonNode, "subject");
		removeFromNode(jsonNode, "fingerprint");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "certInPemFormat");
		removeFromNode(jsonNode, "algorithmName");
		removeFromNode(jsonNode, "version");
		removeFromNode(jsonNode, "issuer");
		removeFromNode(jsonNode, "notBefore");
		return adapt().path("tenant").path("tenants").path(valueOf(tenantId)).path("trusted-certificates").path(valueOf(fingerprint))
			.request()
			.header("Content-Type", "application/json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("PUT", Entity.json(jsonNode), TrustedCertificate.class);
	}
	
	/**
	 * Remove a stored certificate
	 * Remove a stored trusted certificate (by a given fingerprint) from a specific tenant (by a given ID).
	 * 
	 * <section><h5>Required roles</h5>
	 * (ROLE_TENANT_MANAGEMENT_ADMIN <b>OR</b> ROLE_TENANT_ADMIN) <b>AND</b> (is the current tenant <b>OR</b> is the management tenant)
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 204 - The trusted certificate was removed.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - Certificate not found.</li>
	 * </ul>
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param fingerprint Unique identifier of a trusted certificate.
	 */
	public CompletionStage<Response> removeTrustedCertificate(final String tenantId, final String fingerprint) {
		return adapt().path("tenant").path("tenants").path(valueOf(tenantId)).path("trusted-certificates").path(valueOf(fingerprint))
			.request()
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
	
	/**
	 * Provide the proof of possession for an already uploaded certificate
	 * Provide the proof of possession for a specific uploaded certificate (by a given fingerprint) for a specific tenant (by a given ID).
	 * 
	 * <div class="reqRoles"><div><h5></h5></div><div>
	 * (ROLE_TENANT_MANAGEMENT_ADMIN <b>OR</b> ROLE_TENANT_ADMIN) <b>AND</b> is the current tenant
	 * </div></div>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The provided signed verification code check was successful.</li>
	 *     <li>HTTP 400 - The provided signed verification code is not correct., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - Trusted certificate not found., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 422 - Proof of possession for the certificate was not confirmed., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param body 
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param fingerprint Unique identifier of a trusted certificate.
	 * @return
	 */
	public CompletionStage<TrustedCertificate> proveCertificatePossession(final UploadedTrustedCertSignedVerificationCode body, final String tenantId, final String fingerprint) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("tenant").path("tenants").path(valueOf(tenantId)).path("trusted-certificates-pop").path(valueOf(fingerprint)).path("pop")
			.request()
			.header("Content-Type", "application/json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("POST", Entity.json(jsonNode), TrustedCertificate.class);
	}
	
	/**
	 * Confirm an already uploaded certificate
	 * Confirm an already uploaded certificate (by a given fingerprint) for a specific tenant (by a given ID).
	 * 
	 * <div class="reqRoles"><div><h5></h5></div><div>
	 * (ROLE_TENANT_MANAGEMENT_ADMIN <b>OR</b> ROLE_TENANT_ADMIN) <b>AND</b> is the management tenant
	 * </div></div>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The certificate is confirmed.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - Trusted certificate not found., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 422 - The verification was not successful. Certificate not confirmed., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param fingerprint Unique identifier of a trusted certificate.
	 * @return
	 */
	public CompletionStage<TrustedCertificate> confirmCertificate(final String tenantId, final String fingerprint) {
		return adapt().path("tenant").path("tenants").path(valueOf(tenantId)).path("trusted-certificates-pop").path(valueOf(fingerprint)).path("confirmed")
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("POST", TrustedCertificate.class);
	}
	
	/**
	 * Generate a verification code for the proof of possession operation for the given certificate
	 * Generate a verification code for the proof of possession operation for the certificate (by a given fingerprint).
	 * 
	 * <div class="reqRoles"><div><h5></h5></div><div>
	 * (ROLE_TENANT_MANAGEMENT_ADMIN <b>OR</b> ROLE_TENANT_ADMIN) <b>AND</b> is the current tenant
	 * </div></div>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The verification code was generated.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - Trusted certificate not found., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param fingerprint Unique identifier of a trusted certificate.
	 * @return
	 */
	public CompletionStage<TrustedCertificate> generateVerificationCode(final String tenantId, final String fingerprint) {
		return adapt().path("tenant").path("tenants").path(valueOf(tenantId)).path("trusted-certificates-pop").path(valueOf(fingerprint)).path("verification-code")
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("POST", TrustedCertificate.class);
	}
}
