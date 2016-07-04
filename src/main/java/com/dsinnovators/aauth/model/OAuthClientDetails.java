package com.dsinnovators.aauth.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "oauth_client_details")
public class OAuthClientDetails {

	@Id
	@NotNull
	private String clientId;
	
	private String resourceIds;
	private String clientSecret;
	private String scope;
	private String authorizedGrantTypes;
	private String webServerRedirectUri;
	private String authorities;
	private Integer accessTokenValidity;
	private Integer refreshTokenValidity;
	private String additionalInformation;
	private String autoapprove;

	public String getClientId() {
		return clientId;
	}

	public String getResourceIds() {
		return resourceIds;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public String getScope() {
		return scope;
	}

	public String getAuthorizedGrantTypes() {
		return authorizedGrantTypes;
	}

	public String getWebServerRedirectUri() {
		return webServerRedirectUri;
	}

	public String getAuthorities() {
		return authorities;
	}

	public Integer getAccessTokenValidity() {
		return accessTokenValidity;
	}

	public Integer getRefreshTokenValidity() {
		return refreshTokenValidity;
	}

	public String getAdditionalInformation() {
		return additionalInformation;
	}

	public String getAutoapprove() {
		return autoapprove;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
		this.authorizedGrantTypes = authorizedGrantTypes;
	}

	public void setWebServerRedirectUri(String webServerRedirectUri) {
		this.webServerRedirectUri = webServerRedirectUri;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	public void setAccessTokenValidity(Integer accessTokenValidity) {
		this.accessTokenValidity = accessTokenValidity;
	}

	public void setRefreshTokenValidity(Integer refreshTokenValidity) {
		this.refreshTokenValidity = refreshTokenValidity;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	public void setAutoapprove(String autoapprove) {
		this.autoapprove = autoapprove;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OauthClientDetails [clientId=").append(clientId).append(", resourceIds=").append(resourceIds)
				.append(", clientSecret=").append(clientSecret).append(", scope=").append(scope)
				.append(", authorizedGrantTypes=").append(authorizedGrantTypes).append(", webServerRedirectUri=")
				.append(webServerRedirectUri).append(", authorities=").append(authorities)
				.append(", accessTokenValidity=").append(accessTokenValidity).append(", refreshTokenValidity=")
				.append(refreshTokenValidity).append(", additionalInformation=").append(additionalInformation)
				.append(", autoapprove=").append(autoapprove).append("]");
		return builder.toString();
	}

}