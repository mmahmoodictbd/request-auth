package com.dsinnovators.oauthclient;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;

@WebServlet(urlPatterns = { "/*" }, loadOnStartup = 1)
public class OAuthClientServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private OAuthClient oAuthClient;

	@Override
	public void init() throws javax.servlet.ServletException {
		oAuthClient = new OAuthClient(new URLConnectionClient());
	};

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		StringBuilder httpResp = new StringBuilder();
		httpResp.append("Connecting test resource (");
		httpResp.append(OAuthConfig.getTestResource());
		httpResp.append(") ...\n");

		String accessToken = getNewAccessToken();

		OAuthClientRequest bearerClientRequest = null;
		try {
			bearerClientRequest = new OAuthBearerClientRequest(OAuthConfig.getTestResource()).setAccessToken(
					accessToken).buildQueryMessage();
		} catch (OAuthSystemException e) {
			e.printStackTrace();
		}

		try {
			OAuthResourceResponse resourceResponse = oAuthClient.resource(bearerClientRequest, OAuth.HttpMethod.GET,
					OAuthResourceResponse.class);
			httpResp.append("Test resource response body:\n");
			httpResp.append(resourceResponse.getBody());
			httpResp.append("\nResponse body ends here.\n");
		} catch (OAuthSystemException e) {
			e.printStackTrace();
		} catch (OAuthProblemException e) {
			e.printStackTrace();
		}

		response.getOutputStream().print(httpResp.toString());
	}

	private String getNewAccessToken() {

		OAuthClientRequest oauthRequest = null;
		try {
			oauthRequest = OAuthClientRequest.tokenLocation(OAuthConfig.getTokenLocation())
					.setUsername(OAuthConfig.getUsername()).setPassword(OAuthConfig.getPassword())
					.setGrantType(GrantType.PASSWORD).setClientId(OAuthConfig.getClientId())
					.setClientSecret(OAuthConfig.getClientSecret()).buildBodyMessage();
		} catch (OAuthSystemException e) {
			e.printStackTrace();
		}

		OAuthAccessTokenResponse oAuthResponse = null;
		try {
			oAuthResponse = oAuthClient.accessToken(oauthRequest);
		} catch (OAuthSystemException e) {
			e.printStackTrace();
		} catch (OAuthProblemException e) {
			e.printStackTrace();
		}

		if (oAuthResponse == null || oAuthResponse.getAccessToken() == null) {
			throw new IllegalStateException("Could not get access token.");
		}

		return oAuthResponse.getAccessToken();
	}
}