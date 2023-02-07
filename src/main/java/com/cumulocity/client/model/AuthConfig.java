// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Parameters determining the authentication process.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class AuthConfig {

	/**
	 * SSO specific. Describes the fields in the access token from the external server containing user information.
	 */
	private AccessTokenToUserDataMapping accessTokenToUserDataMapping;

	/**
	 * SSO specific. Token audience.
	 */
	private String audience;

	private RequestRepresentation authorizationRequest;

	/**
	 * For basic authentication case only.
	 */
	private BasicAuthenticationRestrictions authenticationRestrictions;

	/**
	 * SSO specific. Information for the UI about the name displayed on the external server login button.
	 */
	private String buttonName;

	/**
	 * SSO specific. The identifier of the Cumulocity IoT tenant on the external authorization server.
	 */
	private String clientId;

	/**
	 * The authentication configuration grant type identifier.
	 */
	private GrantType grantType;

	/**
	 * Unique identifier of this login option.
	 */
	private String id;

	/**
	 * SSO specific. External token issuer.
	 */
	private String issuer;

	private RequestRepresentation logoutRequest;

	/**
	 * Indicates whether the configuration is only accessible to the management tenant.
	 */
	private boolean onlyManagementTenantAccess;

	/**
	 * SSO specific. Describes the process of internal user creation during login with the external authorization server.
	 */
	private OnNewUser onNewUser;

	/**
	 * The name of the authentication provider.
	 */
	private String providerName;

	/**
	 * SSO specific. URL used for redirecting to the Cumulocity IoT platform.
	 */
	private String redirectToPlatform;

	private RequestRepresentation refreshRequest;

	/**
	 * A URL linking to this resource.
	 */
	private String self;

	/**
	 * The session configuration properties are only available for OAuth internal. See [Changing settings > OAuth internal](https://cumulocity.com/guides/users-guide/administration/#oauth-internal) for more details.
	 */
	private OAuthSessionConfiguration sessionConfiguration;

	/**
	 * SSO specific and authorization server dependent. Describes the method of access token signature verification on the Cumulocity IoT platform.
	 */
	private SignatureVerificationConfig signatureVerificationConfig;

	/**
	 * SSO specific. Template name used by the UI.
	 */
	private String template;

	private RequestRepresentation tokenRequest;

	/**
	 * The authentication configuration type. Note that the value is case insensitive.
	 */
	private Type type;

	/**
	 * SSO specific. Points to the field in the obtained JWT access token that should be used as the username in the Cumulocity IoT platform.
	 */
	private UserIdConfig userIdConfig;

	/**
	 * Indicates whether user data are managed internally by the Cumulocity IoT platform or by an external server. Note that the value is case insensitive.
	 */
	private UserManagementSource userManagementSource;

	/**
	 * Information for the UI if the respective authentication form should be visible for the user.
	 */
	private boolean visibleOnLoginPage;

	public AuthConfig() {
	}

	public AuthConfig(final String providerName, final Type type) {
		this.providerName = providerName;
		this.type = type;
	}

	public AccessTokenToUserDataMapping getAccessTokenToUserDataMapping() {
		return accessTokenToUserDataMapping;
	}
	
	public void setAccessTokenToUserDataMapping(final AccessTokenToUserDataMapping accessTokenToUserDataMapping) {
		this.accessTokenToUserDataMapping = accessTokenToUserDataMapping;
	}

	public String getAudience() {
		return audience;
	}
	
	public void setAudience(final String audience) {
		this.audience = audience;
	}

	public RequestRepresentation getAuthorizationRequest() {
		return authorizationRequest;
	}
	
	public void setAuthorizationRequest(final RequestRepresentation authorizationRequest) {
		this.authorizationRequest = authorizationRequest;
	}

	public BasicAuthenticationRestrictions getAuthenticationRestrictions() {
		return authenticationRestrictions;
	}
	
	public void setAuthenticationRestrictions(final BasicAuthenticationRestrictions authenticationRestrictions) {
		this.authenticationRestrictions = authenticationRestrictions;
	}

	public String getButtonName() {
		return buttonName;
	}
	
	public void setButtonName(final String buttonName) {
		this.buttonName = buttonName;
	}

	public String getClientId() {
		return clientId;
	}
	
	public void setClientId(final String clientId) {
		this.clientId = clientId;
	}

	public GrantType getGrantType() {
		return grantType;
	}
	
	public void setGrantType(final GrantType grantType) {
		this.grantType = grantType;
	}

	public String getId() {
		return id;
	}
	
	public void setId(final String id) {
		this.id = id;
	}

	public String getIssuer() {
		return issuer;
	}
	
	public void setIssuer(final String issuer) {
		this.issuer = issuer;
	}

	public RequestRepresentation getLogoutRequest() {
		return logoutRequest;
	}
	
	public void setLogoutRequest(final RequestRepresentation logoutRequest) {
		this.logoutRequest = logoutRequest;
	}

	public boolean getOnlyManagementTenantAccess() {
		return onlyManagementTenantAccess;
	}
	
	public void setOnlyManagementTenantAccess(final boolean onlyManagementTenantAccess) {
		this.onlyManagementTenantAccess = onlyManagementTenantAccess;
	}

	public OnNewUser getOnNewUser() {
		return onNewUser;
	}
	
	public void setOnNewUser(final OnNewUser onNewUser) {
		this.onNewUser = onNewUser;
	}

	public String getProviderName() {
		return providerName;
	}
	
	public void setProviderName(final String providerName) {
		this.providerName = providerName;
	}

	public String getRedirectToPlatform() {
		return redirectToPlatform;
	}
	
	public void setRedirectToPlatform(final String redirectToPlatform) {
		this.redirectToPlatform = redirectToPlatform;
	}

	public RequestRepresentation getRefreshRequest() {
		return refreshRequest;
	}
	
	public void setRefreshRequest(final RequestRepresentation refreshRequest) {
		this.refreshRequest = refreshRequest;
	}

	public String getSelf() {
		return self;
	}
	
	public void setSelf(final String self) {
		this.self = self;
	}

	public OAuthSessionConfiguration getSessionConfiguration() {
		return sessionConfiguration;
	}
	
	public void setSessionConfiguration(final OAuthSessionConfiguration sessionConfiguration) {
		this.sessionConfiguration = sessionConfiguration;
	}

	public SignatureVerificationConfig getSignatureVerificationConfig() {
		return signatureVerificationConfig;
	}
	
	public void setSignatureVerificationConfig(final SignatureVerificationConfig signatureVerificationConfig) {
		this.signatureVerificationConfig = signatureVerificationConfig;
	}

	public String getTemplate() {
		return template;
	}
	
	public void setTemplate(final String template) {
		this.template = template;
	}

	public RequestRepresentation getTokenRequest() {
		return tokenRequest;
	}
	
	public void setTokenRequest(final RequestRepresentation tokenRequest) {
		this.tokenRequest = tokenRequest;
	}

	public Type getType() {
		return type;
	}
	
	public void setType(final Type type) {
		this.type = type;
	}

	public UserIdConfig getUserIdConfig() {
		return userIdConfig;
	}
	
	public void setUserIdConfig(final UserIdConfig userIdConfig) {
		this.userIdConfig = userIdConfig;
	}

	public UserManagementSource getUserManagementSource() {
		return userManagementSource;
	}
	
	public void setUserManagementSource(final UserManagementSource userManagementSource) {
		this.userManagementSource = userManagementSource;
	}

	public boolean getVisibleOnLoginPage() {
		return visibleOnLoginPage;
	}
	
	public void setVisibleOnLoginPage(final boolean visibleOnLoginPage) {
		this.visibleOnLoginPage = visibleOnLoginPage;
	}

	
	/**
	 * The authentication configuration grant type identifier.
	 * [AUTHORIZATION_CODE, PASSWORD]
	 */
	public enum GrantType {
		@JsonProperty("AUTHORIZATION_CODE")
		AUTHORIZATIONCODE("AUTHORIZATION_CODE"),
		@JsonProperty("PASSWORD")
		PASSWORD("PASSWORD");
	
		private String value;
	
		GrantType(final String value) {
			this.value = value;
		}
	
		public String getValue() {
			return value;
		}
	}

	
	/**
	 * The authentication configuration type. Note that the value is case insensitive.
	 * [BASIC, OAUTH2, OAUTH2_INTERNAL]
	 */
	public enum Type {
		@JsonProperty("BASIC")
		BASIC("BASIC"),
		@JsonProperty("OAUTH2")
		OAUTH2("OAUTH2"),
		@JsonProperty("OAUTH2_INTERNAL")
		OAUTH2INTERNAL("OAUTH2_INTERNAL");
	
		private String value;
	
		Type(final String value) {
			this.value = value;
		}
	
		public String getValue() {
			return value;
		}
	}

	
	/**
	 * Indicates whether user data are managed internally by the Cumulocity IoT platform or by an external server. Note that the value is case insensitive.
	 * [INTERNAL, REMOTE]
	 */
	public enum UserManagementSource {
		@JsonProperty("INTERNAL")
		INTERNAL("INTERNAL"),
		@JsonProperty("REMOTE")
		REMOTE("REMOTE");
	
		private String value;
	
		UserManagementSource(final String value) {
			this.value = value;
		}
	
		public String getValue() {
			return value;
		}
	}

	/**
	 * SSO specific. Describes the fields in the access token from the external server containing user information.
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class AccessTokenToUserDataMapping {
	
		/**
		 * The name of the field containing the user's email.
		 */
		private String emailClaimName;
	
		/**
		 * The name of the field containing the user's first name.
		 */
		private String firstNameClaimName;
	
		/**
		 * The name of the field containing the user's last name.
		 */
		private String lastNameClaimName;
	
		/**
		 * The name of the field containing the user's phone number.
		 */
		private String phoneNumberClaimName;
	
		public String getEmailClaimName() {
			return emailClaimName;
		}
		
		public void setEmailClaimName(final String emailClaimName) {
			this.emailClaimName = emailClaimName;
		}
	
		public String getFirstNameClaimName() {
			return firstNameClaimName;
		}
		
		public void setFirstNameClaimName(final String firstNameClaimName) {
			this.firstNameClaimName = firstNameClaimName;
		}
	
		public String getLastNameClaimName() {
			return lastNameClaimName;
		}
		
		public void setLastNameClaimName(final String lastNameClaimName) {
			this.lastNameClaimName = lastNameClaimName;
		}
	
		public String getPhoneNumberClaimName() {
			return phoneNumberClaimName;
		}
		
		public void setPhoneNumberClaimName(final String phoneNumberClaimName) {
			this.phoneNumberClaimName = phoneNumberClaimName;
		}
	
		@Override
		public String toString() {
			try {
				// TODO thats an extensive operation, which only helps debugging
				return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
			} catch (final JsonProcessingException e) {
			}
			return super.toString();
		}
	
		@Override
		public boolean equals(final Object r) {
			if (r != null && r instanceof AccessTokenToUserDataMapping) {
				AccessTokenToUserDataMapping comparer = (AccessTokenToUserDataMapping) r;
				if (String.valueOf(comparer.getEmailClaimName()).equals(String.valueOf(this.getEmailClaimName())) && String.valueOf(comparer.getFirstNameClaimName()).equals(String.valueOf(this.getFirstNameClaimName())) && String.valueOf(comparer.getLastNameClaimName()).equals(String.valueOf(this.getLastNameClaimName())) && String.valueOf(comparer.getPhoneNumberClaimName()).equals(String.valueOf(this.getPhoneNumberClaimName()))) {
					return true;
				}
			}
			return false;
		}
	}


	/**
	 * SSO specific. Describes the process of internal user creation during login with the external authorization server.
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class OnNewUser {
	
		/**
		 * Modern version of configuration of default groups and applications. This ensures backward compatibility.
		 */
		private DynamicMapping dynamicMapping;
	
		public DynamicMapping getDynamicMapping() {
			return dynamicMapping;
		}
		
		public void setDynamicMapping(final DynamicMapping dynamicMapping) {
			this.dynamicMapping = dynamicMapping;
		}
	
		/**
		 * Modern version of configuration of default groups and applications. This ensures backward compatibility.
		 */
		@JsonIgnoreProperties(ignoreUnknown = true)
		@JsonInclude(Include.NON_NULL)
		public static class DynamicMapping {
		
			/**
			 * Configuration of the mapping.
			 */
			private Configuration configuration;
		
			/**
			 * Represents rules used to assign groups and applications.
			 */
			private Mappings[] mappings;
		
			public Configuration getConfiguration() {
				return configuration;
			}
			
			public void setConfiguration(final Configuration configuration) {
				this.configuration = configuration;
			}
		
			public Mappings[] getMappings() {
				return mappings;
			}
			
			public void setMappings(final Mappings[] mappings) {
				this.mappings = mappings;
			}
		
			/**
			 * Configuration of the mapping.
			 */
			@JsonIgnoreProperties(ignoreUnknown = true)
			@JsonInclude(Include.NON_NULL)
			public static class Configuration {
			
				/**
				 * Indicates whether the mapping should be evaluated always or only during the first external login when the internal user is created.
				 */
				private boolean mapRolesOnlyForNewUser;
			
				public boolean getMapRolesOnlyForNewUser() {
					return mapRolesOnlyForNewUser;
				}
				
				public void setMapRolesOnlyForNewUser(final boolean mapRolesOnlyForNewUser) {
					this.mapRolesOnlyForNewUser = mapRolesOnlyForNewUser;
				}
			
				@Override
				public String toString() {
					try {
						// TODO thats an extensive operation, which only helps debugging
						return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
					} catch (final JsonProcessingException e) {
					}
					return super.toString();
				}
			
				@Override
				public boolean equals(final Object r) {
					if (r != null && r instanceof Configuration) {
						Configuration comparer = (Configuration) r;
						if (Boolean.valueOf(comparer.getMapRolesOnlyForNewUser()).equals(Boolean.valueOf(this.getMapRolesOnlyForNewUser()))) {
							return true;
						}
					}
					return false;
				}
			}
		
			/**
			 * Represents information of mapping access to groups and applications.
			 */
			@JsonIgnoreProperties(ignoreUnknown = true)
			@JsonInclude(Include.NON_NULL)
			public static class Mappings {
			
				/**
				 * Represents a predicate for verification. It acts as a condition which is necessary to assign a user to the given groups and permit access to the specified applications.
				 */
				private JSONPredicateRepresentation when;
			
				/**
				 * List of the applications' identifiers.
				 */
				private int[] thenApplications;
			
				/**
				 * List of the groups' identifiers.
				 */
				private int[] thenGroups;
			
				public JSONPredicateRepresentation getWhen() {
					return when;
				}
				
				public void setWhen(final JSONPredicateRepresentation when) {
					this.when = when;
				}
			
				public int[] getThenApplications() {
					return thenApplications;
				}
				
				public void setThenApplications(final int[] thenApplications) {
					this.thenApplications = thenApplications;
				}
			
				public int[] getThenGroups() {
					return thenGroups;
				}
				
				public void setThenGroups(final int[] thenGroups) {
					this.thenGroups = thenGroups;
				}
			
				@Override
				public String toString() {
					try {
						// TODO thats an extensive operation, which only helps debugging
						return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
					} catch (final JsonProcessingException e) {
					}
					return super.toString();
				}
			
				@Override
				public boolean equals(final Object r) {
					if (r != null && r instanceof Mappings) {
						Mappings comparer = (Mappings) r;
						if (comparer.getWhen().equals(this.getWhen()) && comparer.getThenApplications().equals(this.getThenApplications()) && comparer.getThenGroups().equals(this.getThenGroups())) {
							return true;
						}
					}
					return false;
				}
			}
		
			@Override
			public String toString() {
				try {
					// TODO thats an extensive operation, which only helps debugging
					return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
				} catch (final JsonProcessingException e) {
				}
				return super.toString();
			}
		
			@Override
			public boolean equals(final Object r) {
				if (r != null && r instanceof DynamicMapping) {
					DynamicMapping comparer = (DynamicMapping) r;
					if (comparer.getConfiguration().equals(this.getConfiguration()) && comparer.getMappings().equals(this.getMappings())) {
						return true;
					}
				}
				return false;
			}
		}
	
		@Override
		public String toString() {
			try {
				// TODO thats an extensive operation, which only helps debugging
				return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
			} catch (final JsonProcessingException e) {
			}
			return super.toString();
		}
	
		@Override
		public boolean equals(final Object r) {
			if (r != null && r instanceof OnNewUser) {
				OnNewUser comparer = (OnNewUser) r;
				if (comparer.getDynamicMapping().equals(this.getDynamicMapping())) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * SSO specific and authorization server dependent. Describes the method of access token signature verification on the Cumulocity IoT platform.
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class SignatureVerificationConfig {
	
		/**
		 * AAD signature verification configuration.
		 */
		private Aad aad;
	
		/**
		 * ADFS manifest signature verification configuration.
		 */
		private AdfsManifest adfsManifest;
	
		/**
		 * The address of the endpoint which is used to retrieve the public key used to verify the JWT access token signature.
		 */
		private Jwks jwks;
	
		/**
		 * Describes the process of verification of JWT access token with the public keys embedded in the provided X.509 certificates.
		 */
		private Manual manual;
	
		public Aad getAad() {
			return aad;
		}
		
		public void setAad(final Aad aad) {
			this.aad = aad;
		}
	
		public AdfsManifest getAdfsManifest() {
			return adfsManifest;
		}
		
		public void setAdfsManifest(final AdfsManifest adfsManifest) {
			this.adfsManifest = adfsManifest;
		}
	
		public Jwks getJwks() {
			return jwks;
		}
		
		public void setJwks(final Jwks jwks) {
			this.jwks = jwks;
		}
	
		public Manual getManual() {
			return manual;
		}
		
		public void setManual(final Manual manual) {
			this.manual = manual;
		}
	
		/**
		 * AAD signature verification configuration.
		 */
		@JsonIgnoreProperties(ignoreUnknown = true)
		@JsonInclude(Include.NON_NULL)
		public static class Aad {
		
			/**
			 * URL used to retrieve the public key used for signature verification.
			 */
			private String publicKeyDiscoveryUrl;
		
			public String getPublicKeyDiscoveryUrl() {
				return publicKeyDiscoveryUrl;
			}
			
			public void setPublicKeyDiscoveryUrl(final String publicKeyDiscoveryUrl) {
				this.publicKeyDiscoveryUrl = publicKeyDiscoveryUrl;
			}
		
			@Override
			public String toString() {
				try {
					// TODO thats an extensive operation, which only helps debugging
					return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
				} catch (final JsonProcessingException e) {
				}
				return super.toString();
			}
		
			@Override
			public boolean equals(final Object r) {
				if (r != null && r instanceof Aad) {
					Aad comparer = (Aad) r;
					if (String.valueOf(comparer.getPublicKeyDiscoveryUrl()).equals(String.valueOf(this.getPublicKeyDiscoveryUrl()))) {
						return true;
					}
				}
				return false;
			}
		}
	
		/**
		 * ADFS manifest signature verification configuration.
		 */
		@JsonIgnoreProperties(ignoreUnknown = true)
		@JsonInclude(Include.NON_NULL)
		public static class AdfsManifest {
		
			/**
			 * The URI to the manifest resource.
			 */
			private String manifestUrl;
		
			public String getManifestUrl() {
				return manifestUrl;
			}
			
			public void setManifestUrl(final String manifestUrl) {
				this.manifestUrl = manifestUrl;
			}
		
			@Override
			public String toString() {
				try {
					// TODO thats an extensive operation, which only helps debugging
					return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
				} catch (final JsonProcessingException e) {
				}
				return super.toString();
			}
		
			@Override
			public boolean equals(final Object r) {
				if (r != null && r instanceof AdfsManifest) {
					AdfsManifest comparer = (AdfsManifest) r;
					if (String.valueOf(comparer.getManifestUrl()).equals(String.valueOf(this.getManifestUrl()))) {
						return true;
					}
				}
				return false;
			}
		}
	
		/**
		 * The address of the endpoint which is used to retrieve the public key used to verify the JWT access token signature.
		 */
		@JsonIgnoreProperties(ignoreUnknown = true)
		@JsonInclude(Include.NON_NULL)
		public static class Jwks {
		
			/**
			 * The URI to the public key resource.
			 */
			private String jwksUrl;
		
			public String getJwksUrl() {
				return jwksUrl;
			}
			
			public void setJwksUrl(final String jwksUrl) {
				this.jwksUrl = jwksUrl;
			}
		
			@Override
			public String toString() {
				try {
					// TODO thats an extensive operation, which only helps debugging
					return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
				} catch (final JsonProcessingException e) {
				}
				return super.toString();
			}
		
			@Override
			public boolean equals(final Object r) {
				if (r != null && r instanceof Jwks) {
					Jwks comparer = (Jwks) r;
					if (String.valueOf(comparer.getJwksUrl()).equals(String.valueOf(this.getJwksUrl()))) {
						return true;
					}
				}
				return false;
			}
		}
	
		/**
		 * Describes the process of verification of JWT access token with the public keys embedded in the provided X.509 certificates.
		 */
		@JsonIgnoreProperties(ignoreUnknown = true)
		@JsonInclude(Include.NON_NULL)
		public static class Manual {
		
			/**
			 * The name of the field in the JWT access token containing the certificate identifier.
			 */
			private String certIdField;
		
			/**
			 * Indicates whether the certificate identifier should be read from the JWT access token.
			 */
			private boolean certIdFromField;
		
			/**
			 * Details of the certificates.
			 */
			private Certificates certificates;
		
			public String getCertIdField() {
				return certIdField;
			}
			
			public void setCertIdField(final String certIdField) {
				this.certIdField = certIdField;
			}
		
			public boolean getCertIdFromField() {
				return certIdFromField;
			}
			
			public void setCertIdFromField(final boolean certIdFromField) {
				this.certIdFromField = certIdFromField;
			}
		
			public Certificates getCertificates() {
				return certificates;
			}
			
			public void setCertificates(final Certificates certificates) {
				this.certificates = certificates;
			}
		
			/**
			 * Details of the certificates.
			 */
			@JsonIgnoreProperties(ignoreUnknown = true)
			@JsonInclude(Include.NON_NULL)
			public static class Certificates {
			
				/**
				 * The signing algorithm of the JWT access token.
				 */
				private Alg alg;
			
				/**
				 * The public key certificate.
				 */
				private String publicKey;
			
				/**
				 * The validity start date of the certificate.
				 */
				private String validFrom;
			
				/**
				 * The expiry date of the certificate.
				 */
				private String validTill;
			
				public Alg getAlg() {
					return alg;
				}
				
				public void setAlg(final Alg alg) {
					this.alg = alg;
				}
			
				public String getPublicKey() {
					return publicKey;
				}
				
				public void setPublicKey(final String publicKey) {
					this.publicKey = publicKey;
				}
			
				public String getValidFrom() {
					return validFrom;
				}
				
				public void setValidFrom(final String validFrom) {
					this.validFrom = validFrom;
				}
			
				public String getValidTill() {
					return validTill;
				}
				
				public void setValidTill(final String validTill) {
					this.validTill = validTill;
				}
			
				
				/**
				 * The signing algorithm of the JWT access token.
				 * [RSA, PCKS]
				 */
				public enum Alg {
					@JsonProperty("RSA")
					RSA("RSA"),
					@JsonProperty("PCKS")
					PCKS("PCKS");
				
					private String value;
				
					Alg(final String value) {
						this.value = value;
					}
				
					public String getValue() {
						return value;
					}
				}
			
			
				@Override
				public String toString() {
					try {
						// TODO thats an extensive operation, which only helps debugging
						return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
					} catch (final JsonProcessingException e) {
					}
					return super.toString();
				}
			
				@Override
				public boolean equals(final Object r) {
					if (r != null && r instanceof Certificates) {
						Certificates comparer = (Certificates) r;
						if (comparer.getAlg().equals(this.getAlg()) && String.valueOf(comparer.getPublicKey()).equals(String.valueOf(this.getPublicKey())) && String.valueOf(comparer.getValidFrom()).equals(String.valueOf(this.getValidFrom())) && String.valueOf(comparer.getValidTill()).equals(String.valueOf(this.getValidTill()))) {
							return true;
						}
					}
					return false;
				}
			}
		
			@Override
			public String toString() {
				try {
					// TODO thats an extensive operation, which only helps debugging
					return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
				} catch (final JsonProcessingException e) {
				}
				return super.toString();
			}
		
			@Override
			public boolean equals(final Object r) {
				if (r != null && r instanceof Manual) {
					Manual comparer = (Manual) r;
					if (String.valueOf(comparer.getCertIdField()).equals(String.valueOf(this.getCertIdField())) && Boolean.valueOf(comparer.getCertIdFromField()).equals(Boolean.valueOf(this.getCertIdFromField())) && comparer.getCertificates().equals(this.getCertificates())) {
						return true;
					}
				}
				return false;
			}
		}
	
		@Override
		public String toString() {
			try {
				// TODO thats an extensive operation, which only helps debugging
				return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
			} catch (final JsonProcessingException e) {
			}
			return super.toString();
		}
	
		@Override
		public boolean equals(final Object r) {
			if (r != null && r instanceof SignatureVerificationConfig) {
				SignatureVerificationConfig comparer = (SignatureVerificationConfig) r;
				if (comparer.getAad().equals(this.getAad()) && comparer.getAdfsManifest().equals(this.getAdfsManifest()) && comparer.getJwks().equals(this.getJwks()) && comparer.getManual().equals(this.getManual())) {
					return true;
				}
			}
			return false;
		}
	}


	/**
	 * SSO specific. Points to the field in the obtained JWT access token that should be used as the username in the Cumulocity IoT platform.
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	public static class UserIdConfig {
	
		/**
		 * Used only when `useConstantValue` is set to `true`.
		 */
		private String constantValue;
	
		/**
		 * The name of the field containing the JWT.
		 */
		private String jwtField;
	
		/**
		 * Not recommended. If set to `true`, all SSO users will share one account in the Cumulocity IoT platform.
		 */
		private boolean useConstantValue;
	
		public String getConstantValue() {
			return constantValue;
		}
		
		public void setConstantValue(final String constantValue) {
			this.constantValue = constantValue;
		}
	
		public String getJwtField() {
			return jwtField;
		}
		
		public void setJwtField(final String jwtField) {
			this.jwtField = jwtField;
		}
	
		public boolean getUseConstantValue() {
			return useConstantValue;
		}
		
		public void setUseConstantValue(final boolean useConstantValue) {
			this.useConstantValue = useConstantValue;
		}
	
		@Override
		public String toString() {
			try {
				// TODO thats an extensive operation, which only helps debugging
				return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
			} catch (final JsonProcessingException e) {
			}
			return super.toString();
		}
	
		@Override
		public boolean equals(final Object r) {
			if (r != null && r instanceof UserIdConfig) {
				UserIdConfig comparer = (UserIdConfig) r;
				if (String.valueOf(comparer.getConstantValue()).equals(String.valueOf(this.getConstantValue())) && String.valueOf(comparer.getJwtField()).equals(String.valueOf(this.getJwtField())) && Boolean.valueOf(comparer.getUseConstantValue()).equals(Boolean.valueOf(this.getUseConstantValue()))) {
					return true;
				}
			}
			return false;
		}
	}


	@Override
	public String toString() {
		try {
			// TODO thats an extensive operation, which only helps debugging
			return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
		} catch (final JsonProcessingException e) {
		}
		return super.toString();
	}

	@Override
	public boolean equals(final Object r) {
		if (r != null && r instanceof AuthConfig) {
			AuthConfig comparer = (AuthConfig) r;
			if (comparer.getAccessTokenToUserDataMapping().equals(this.getAccessTokenToUserDataMapping()) && String.valueOf(comparer.getAudience()).equals(String.valueOf(this.getAudience())) && comparer.getAuthorizationRequest().equals(this.getAuthorizationRequest()) && comparer.getAuthenticationRestrictions().equals(this.getAuthenticationRestrictions()) && String.valueOf(comparer.getButtonName()).equals(String.valueOf(this.getButtonName())) && String.valueOf(comparer.getClientId()).equals(String.valueOf(this.getClientId())) && comparer.getGrantType().equals(this.getGrantType()) && String.valueOf(comparer.getId()).equals(String.valueOf(this.getId())) && String.valueOf(comparer.getIssuer()).equals(String.valueOf(this.getIssuer())) && comparer.getLogoutRequest().equals(this.getLogoutRequest()) && Boolean.valueOf(comparer.getOnlyManagementTenantAccess()).equals(Boolean.valueOf(this.getOnlyManagementTenantAccess())) && comparer.getOnNewUser().equals(this.getOnNewUser()) && String.valueOf(comparer.getProviderName()).equals(String.valueOf(this.getProviderName())) && String.valueOf(comparer.getRedirectToPlatform()).equals(String.valueOf(this.getRedirectToPlatform())) && comparer.getRefreshRequest().equals(this.getRefreshRequest()) && String.valueOf(comparer.getSelf()).equals(String.valueOf(this.getSelf())) && comparer.getSessionConfiguration().equals(this.getSessionConfiguration()) && comparer.getSignatureVerificationConfig().equals(this.getSignatureVerificationConfig()) && String.valueOf(comparer.getTemplate()).equals(String.valueOf(this.getTemplate())) && comparer.getTokenRequest().equals(this.getTokenRequest()) && comparer.getType().equals(this.getType()) && comparer.getUserIdConfig().equals(this.getUserIdConfig()) && comparer.getUserManagementSource().equals(this.getUserManagementSource()) && Boolean.valueOf(comparer.getVisibleOnLoginPage()).equals(Boolean.valueOf(this.getVisibleOnLoginPage()))) {
				return true;
			}
		}
		return false;
	}
}
