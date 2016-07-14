package com.dsinnovators.oauthclient;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;

@WebServlet(urlPatterns = { "/*" }, loadOnStartup = 1)
public class OAuthClientServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());

		OAuthClientRequest oauthRequest = null;
		try {
			oauthRequest = OAuthClientRequest.tokenLocation("http://localhost:8080/oauth/token").setUsername("admin").setPassword("admin")
					.setGrantType(GrantType.PASSWORD).setClientId("9v6fvn7221bniao2vc6jnvp31l")
					.setClientSecret("2oe11m1ojh6fgqrn503sfp3r5h").buildBodyMessage();
		} catch (OAuthSystemException e1) {
			e1.printStackTrace();
		}

		OAuthAccessTokenResponse oAuthResponse = null;
		try {
			oAuthResponse = oAuthClient.accessToken(oauthRequest);
		} catch (OAuthSystemException e) {
			e.printStackTrace();
		} catch (OAuthProblemException e) {
			e.printStackTrace();
		}

		System.out.println(oAuthResponse.getAccessToken());

		response.getOutputStream().print("Hello World");
	}
}