// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
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
import com.cumulocity.client.model.UploadedTrustedCertificate;
import com.cumulocity.client.model.UploadedTrustedCertificateCollection;
import com.cumulocity.client.model.TrustedCertificate;
import com.cumulocity.client.model.UploadedTrustedCertSignedVerificationCode;
import com.cumulocity.client.model.TrustedCertificateCollection;
import com.cumulocity.client.model.VerifyCertificateChain;

/**
 * <p>API methods for managing trusted certificates used to establish device connections via MQTT.</p>
 * <p>More detailed information about trusted certificates and their role can be found in <a href="https://cumulocity.com/guides/users-guide/device-management/#managing-device-data">Device management > Managing device data</a> in the <em>User guide</em>.</p>
 * <blockquote>
 * <p><strong>ⓘ Info:</strong> The Accept header must be provided in all POST/PUT requests, otherwise an empty response body will be returned.</p>
 * </blockquote>
 */
public class TrustedCertificatesApi extends AdaptableApi {

	public TrustedCertificatesApi(final WebTarget rootTarget) {
		super(rootTarget);
	}

	/**
	 * <p>Retrieve all stored certificates</p>
	 * <p>Retrieve all the trusted certificates of a specific tenant (by a given ID).</p>
	 * <section><h5>Required roles</h5>
	 * (ROLE_TENANT_MANAGEMENT_ADMIN <b>OR</b> ROLE_TENANT_ADMIN) <b>AND</b> (is the current tenant)
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the trusted certificates are sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not authorized to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Tenant not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param tenantId
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
	 * @param currentPage
	 * <p>The current page of the paginated results.</p>
	 * @param pageSize
	 * <p>Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.</p>
	 * @param withTotalElements
	 * <p>When set to <code>true</code>, the returned result will contain in the statistics object the total number of elements. Only applicable on <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a>.</p>
	 * @param withTotalPages
	 * <p>When set to <code>true</code>, the returned result will contain in the statistics object the total number of pages. Only applicable on <a href="https://en.wikipedia.org/wiki/Range_query_(database)">range queries</a>.</p>
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
	 * <p>Add a new certificate</p>
	 * <p>Add a new trusted certificate to a specific tenant (by a given ID) which can be further used by the devices to establish connections with the Cumulocity IoT platform.</p>
	 * <section><h5>Required roles</h5>
	 * (ROLE_TENANT_MANAGEMENT_ADMIN <b>OR</b> ROLE_TENANT_ADMIN) <b>AND</b> (is the current tenant)
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>The certificate was added to the tenant.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Tenant not found.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 409 <p>Duplicate – A certificate with the same fingerprint already exists.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Unprocessable Entity – Invalid certificate data.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param tenantId
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
	 * @param xCumulocityProcessingMode
	 * <p>Used to explicitly control the processing mode of the request. See <a href="#processing-mode">Processing mode</a> for more details.</p>
	 * @param addToTrustStore
	 * <p>If set to <code>true</code> the certificate is added to the truststore.</p>
	 * <p>The truststore contains all trusted certificates. A connection to a device is only established if it connects to Cumulocity IoT with a certificate in the truststore.</p>
	 */
	public CompletionStage<TrustedCertificate> addTrustedCertificate(final UploadedTrustedCertificate body, final String tenantId, final String xCumulocityProcessingMode, final boolean addToTrustStore) {
		final JsonNode jsonNode = toJsonNode(body);
		return adapt().path("tenant").path("tenants").path(valueOf(tenantId)).path("trusted-certificates")
			.queryParam("addToTrustStore", addToTrustStore)
			.request()
			.header("X-Cumulocity-Processing-Mode", xCumulocityProcessingMode)
			.header("Content-Type", "application/json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("POST", Entity.json(jsonNode), TrustedCertificate.class);
	}
	
	/**
	 * <p>Add multiple certificates</p>
	 * <p>Add multiple trusted certificates to a specific tenant (by a given ID) which can be further used by the devices to establish connections with the Cumulocity IoT platform.</p>
	 * <section><h5>Required roles</h5>
	 * (ROLE_TENANT_MANAGEMENT_ADMIN <b>OR</b> ROLE_TENANT_ADMIN) <b>AND</b> (is the current tenant)
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 201 <p>The certificates were added to the tenant.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Tenant not found.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 409 <p>Duplicate – A certificate with the same fingerprint already exists.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Unprocessable Entity – Invalid certificates data.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param tenantId
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
	 * @param addToTrustStore
	 * <p>If set to <code>true</code> the certificate is added to the truststore.</p>
	 * <p>The truststore contains all trusted certificates. A connection to a device is only established if it connects to Cumulocity IoT with a certificate in the truststore.</p>
	 */
	public CompletionStage<TrustedCertificateCollection> addTrustedCertificates(final UploadedTrustedCertificateCollection body, final String tenantId, final boolean addToTrustStore) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "next");
		removeFromNode(jsonNode, "prev");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "statistics");
		return adapt().path("tenant").path("tenants").path(valueOf(tenantId)).path("trusted-certificates").path("bulk")
			.queryParam("addToTrustStore", addToTrustStore)
			.request()
			.header("Content-Type", "application/json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("POST", Entity.json(jsonNode), TrustedCertificateCollection.class);
	}
	
	/**
	 * <p>Retrieve a stored certificate</p>
	 * <p>Retrieve the data of a stored trusted certificate (by a given fingerprint) of a specific tenant (by a given ID).</p>
	 * <section><h5>Required roles</h5>
	 * (ROLE_TENANT_MANAGEMENT_ADMIN <b>OR</b> ROLE_TENANT_ADMIN) <b>AND</b> (is the current tenant <b>OR</b> is the management tenant)
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the trusted certificate is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param tenantId
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
	 * @param fingerprint
	 * <p>Unique identifier of a trusted certificate.</p>
	 */
	public CompletionStage<TrustedCertificate> getTrustedCertificate(final String tenantId, final String fingerprint) {
		return adapt().path("tenant").path("tenants").path(valueOf(tenantId)).path("trusted-certificates").path(valueOf(fingerprint))
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("GET", TrustedCertificate.class);
	}
	
	/**
	 * <p>Update a stored certificate</p>
	 * <p>Update the data of a stored trusted certificate (by a given fingerprint) of a specific tenant (by a given ID).</p>
	 * <section><h5>Required roles</h5>
	 * (ROLE_TENANT_MANAGEMENT_ADMIN <b>OR</b> ROLE_TENANT_ADMIN) <b>AND</b> (is the current tenant <b>OR</b> is the management tenant)
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The certificate was updated on the tenant.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Certificate not found.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Unprocessable Entity – invalid payload.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param tenantId
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
	 * @param fingerprint
	 * <p>Unique identifier of a trusted certificate.</p>
	 */
	public CompletionStage<TrustedCertificate> updateTrustedCertificate(final TrustedCertificate body, final String tenantId, final String fingerprint) {
		final JsonNode jsonNode = toJsonNode(body);
		removeFromNode(jsonNode, "proofOfPossessionValid");
		removeFromNode(jsonNode, "notAfter");
		removeFromNode(jsonNode, "serialNumber");
		removeFromNode(jsonNode, "proofOfPossessionVerificationCodeUsableUntil");
		removeFromNode(jsonNode, "subject");
		removeFromNode(jsonNode, "algorithmName");
		removeFromNode(jsonNode, "version");
		removeFromNode(jsonNode, "issuer");
		removeFromNode(jsonNode, "notBefore");
		removeFromNode(jsonNode, "proofOfPossessionUnsignedVerificationCode");
		removeFromNode(jsonNode, "fingerprint");
		removeFromNode(jsonNode, "self");
		removeFromNode(jsonNode, "certInPemFormat");
		return adapt().path("tenant").path("tenants").path(valueOf(tenantId)).path("trusted-certificates").path(valueOf(fingerprint))
			.request()
			.header("Content-Type", "application/json")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("PUT", Entity.json(jsonNode), TrustedCertificate.class);
	}
	
	/**
	 * <p>Remove a stored certificate</p>
	 * <p>Remove a stored trusted certificate (by a given fingerprint) from a specific tenant (by a given ID).When a trusted certificate is deleted, the established MQTT connection to all devices that are using the corresponding certificate are closed.</p>
	 * <section><h5>Required roles</h5>
	 * (ROLE_TENANT_MANAGEMENT_ADMIN <b>OR</b> ROLE_TENANT_ADMIN) <b>AND</b> (is the current tenant <b>OR</b> is the management tenant)
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 204 <p>The trusted certificate was removed.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Certificate not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param tenantId
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
	 * @param fingerprint
	 * <p>Unique identifier of a trusted certificate.</p>
	 */
	public CompletionStage<Response> removeTrustedCertificate(final String tenantId, final String fingerprint) {
		return adapt().path("tenant").path("tenants").path(valueOf(tenantId)).path("trusted-certificates").path(valueOf(fingerprint))
			.request()
			.header("Accept", "application/json")
			.rx()
			.method("DELETE");
	}
	
	/**
	 * <p>Provide the proof of possession for an already uploaded certificate</p>
	 * <p>Provide the proof of possession for a specific uploaded certificate (by a given fingerprint) for a specific tenant (by a given ID).</p>
	 * <div class="reqRoles"><div><h5></h5></div><div>
	 * (ROLE_TENANT_MANAGEMENT_ADMIN <b>OR</b> ROLE_TENANT_ADMIN) <b>AND</b> is the current tenant
	 * </div></div>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The provided signed verification code check was successful.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 400 <p>The provided signed verification code is not correct.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Trusted certificate not found.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>Proof of possession for the certificate was not confirmed.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param body
	 * @param tenantId
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
	 * @param fingerprint
	 * <p>Unique identifier of a trusted certificate.</p>
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
	 * <p>Confirm an already uploaded certificate</p>
	 * <p>Confirm an already uploaded certificate (by a given fingerprint) for a specific tenant (by a given ID).</p>
	 * <div class="reqRoles"><div><h5></h5></div><div>
	 * (ROLE_TENANT_MANAGEMENT_ADMIN <b>OR</b> ROLE_TENANT_ADMIN) <b>AND</b> is the management tenant
	 * </div></div>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The certificate is confirmed.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Trusted certificate not found.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 422 <p>The verification was not successful. Certificate not confirmed.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param tenantId
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
	 * @param fingerprint
	 * <p>Unique identifier of a trusted certificate.</p>
	 */
	public CompletionStage<TrustedCertificate> confirmCertificate(final String tenantId, final String fingerprint) {
		return adapt().path("tenant").path("tenants").path(valueOf(tenantId)).path("trusted-certificates-pop").path(valueOf(fingerprint)).path("confirmed")
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("POST", TrustedCertificate.class);
	}
	
	/**
	 * <p>Generate a verification code for the proof of possession operation for the given certificate</p>
	 * <p>Generate a verification code for the proof of possession operation for the certificate (by a given fingerprint).</p>
	 * <div class="reqRoles"><div><h5></h5></div><div>
	 * (ROLE_TENANT_MANAGEMENT_ADMIN <b>OR</b> ROLE_TENANT_ADMIN) <b>AND</b> is the current tenant
	 * </div></div>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The verification code was generated.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 401 <p>Authentication information is missing or invalid.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>Trusted certificate not found.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param tenantId
	 * <p>Unique identifier of a Cumulocity IoT tenant.</p>
	 * @param fingerprint
	 * <p>Unique identifier of a trusted certificate.</p>
	 */
	public CompletionStage<TrustedCertificate> generateVerificationCode(final String tenantId, final String fingerprint) {
		return adapt().path("tenant").path("tenants").path(valueOf(tenantId)).path("trusted-certificates-pop").path(valueOf(fingerprint)).path("verification-code")
			.request()
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("POST", TrustedCertificate.class);
	}
	
	/**
	 * <p>Verify a certificate chain via file upload</p>
	 * <p>Verify a device certificate chain against a specific tenant. Max chain length support is <b>10</b>.The tenant ID is <code>optional</code> and this api will be further enhanced to resolve the tenant from the chain in future release.</p>
	 * <section><h5>Required roles</h5>
	 * (ROLE_TENANT_MANAGEMENT_ADMIN) <b>AND</b> (is the current tenant <b>OR</b> is current management tenant)
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the validation result is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 400 <p>Unable to parse certificate chain.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not enough permissions/roles to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>The tenant ID does not exist.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param tenantId
	 * @param file
	 * <p>File to be uploaded.</p>
	 */
	public CompletionStage<VerifyCertificateChain> validateChainByFileUpload(final String tenantId, final byte[] file) {
		final FormDataMultiPart multiPartEntity = new FormDataMultiPart();
		multiPartEntity.field("tenantId", tenantId, MediaType.valueOf("text/plain"));
		multiPartEntity.field("file", file, MediaType.valueOf("text/plain"));
		return adapt().path("tenant").path("tenants").path("verify-cert-chain").path("fileUpload")
			.request()
			.header("Content-Type", "multipart/form-data")
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("POST", Entity.entity(multiPartEntity, "multipart/form-data"), VerifyCertificateChain.class);
	}
	
	/**
	 * <p>Verify a certificate chain via HTTP header</p>
	 * <p>Verify a device certificate chain against a specific tenant. Max chain length support is <b>6</b>.The tenant ID is <code>optional</code> and this api will be further enhanced to resolve the tenant from the chain in future release.</p>
	 * <section><h5>Required roles</h5>
	 * (ROLE_TENANT_MANAGEMENT_ADMIN) <b>AND</b> (is the current tenant <b>OR</b> is current management tenant)
	 * </section>
	 * <h5>Response Codes</h5>
	 * <p>The following table gives an overview of the possible response codes and their meanings:</p>
	 * <ul>
	 * 	<li><p>HTTP 200 <p>The request has succeeded and the validation result is sent in the response.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 400 <p>Unable to parse certificate chain.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 403 <p>Not enough permissions/roles to perform this operation.</p></p>
	 * 	</li>
	 * 	<li><p>HTTP 404 <p>The tenant ID does not exist.</p></p>
	 * 	</li>
	 * </ul>
	 * 
	 * @param xCumulocityTenantId
	 * <p>Used to send a tenant ID.</p>
	 * @param xCumulocityClientCertChain
	 * <p>Used to send a certificate chain in the header. Separate the chain with <code>,</code> and also each 64 bit block with <code> </code> (a space character).</p>
	 */
	public CompletionStage<VerifyCertificateChain> validateChainByHeader(final String xCumulocityTenantId, final String xCumulocityClientCertChain) {
		return adapt().path("tenant").path("tenants").path("verify-cert-chain")
			.request()
			.header("X-Cumulocity-TenantId", xCumulocityTenantId)
			.header("X-Cumulocity-Client-Cert-Chain", xCumulocityClientCertChain)
			.header("Accept", "application/vnd.com.nsn.cumulocity.error+json, application/json")
			.rx()
			.method("POST", VerifyCertificateChain.class);
	}
}
