package com.dsinnovators.oauthclient;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class OAuthConfig {

	private static final String tokenLocation = "tokenLocation";
	private static final String username = "username";
	private static final String password = "password";
	private static final String clientId = "clientId";
	private static final String clientSecret = "clientSecret";
	private static final String testResource = "testResource";

	private static Properties properties = new Properties();

	static {

		try {
			InputStream inputStream = OAuthConfig.class.getClassLoader().getResourceAsStream("oauthConfig.properties");
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getTokenLocation() {
		return properties.getProperty(tokenLocation);
	}

	public static String getUsername() {
		return properties.getProperty(username);
	}

	public static String getPassword() {
		return properties.getProperty(password);
	}

	public static String getClientId() {
		return properties.getProperty(clientId);
	}

	public static String getClientSecret() {
		return properties.getProperty(clientSecret);
	}

	public static String getTestResource() {
		return properties.getProperty(testResource);
	}
}