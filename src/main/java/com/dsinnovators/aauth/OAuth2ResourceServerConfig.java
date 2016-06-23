package com.dsinnovators.aauth;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

/*@Configuration
 @EnableResourceServer
 @EnableGlobalMethodSecurity(prePostEnabled = true)
 public class OAuth2ResourceServerConfig extends GlobalMethodSecurityConfiguration {

 @Override
 protected MethodSecurityExpressionHandler createExpressionHandler() {
 return new OAuth2MethodSecurityExpressionHandler();
 }
 }*/

@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

	private static final String HU_REST_RESOURCE_ID = "rest_api";

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(HU_REST_RESOURCE_ID).stateless(false);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {

		// define URL patterns to enable OAuth2 security

		http.requestMatchers().antMatchers("/resources/**").and().authorizeRequests().antMatchers("/resources/**")
				.access("#oauth2.hasScope('read') or (!#oauth2.isOAuth() and hasRole('ROLE_USER'))");
	}

}