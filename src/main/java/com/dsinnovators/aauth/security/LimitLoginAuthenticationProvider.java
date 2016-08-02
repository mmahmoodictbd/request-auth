package com.dsinnovators.aauth.security;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.dsinnovators.aauth.service.LoginAttemptService;
import com.dsinnovators.aauth.util.HttpUtil;

@Component("authenticationProvider")
public class LimitLoginAuthenticationProvider extends DaoAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private LoginAttemptService loginAttemptService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserDetailsService userDetailsService;

	@PostConstruct
	private void initialize() {
		setPasswordEncoder(passwordEncoder);
		setUserDetailsService(userDetailsService);
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		try {
			Authentication auth = super.authenticate(authentication);
			loginAttemptService.loginSucceeded(HttpUtil.getRemoteIP());
			return auth;
		} catch (BadCredentialsException e) {
			loginAttemptService.loginFailed(HttpUtil.getRemoteIP());
			throw e;
		}

	}

}
