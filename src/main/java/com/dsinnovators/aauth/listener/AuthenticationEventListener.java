package com.dsinnovators.aauth.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import com.dsinnovators.aauth.service.LoginAttemptService;

@Component
public class AuthenticationEventListener {

	@Autowired
	private LoginAttemptService loginAttemptService;

	@EventListener
	public void authenticationFailedAtteptLogger(AuthenticationFailureBadCredentialsEvent event) {
		
		// loginAttemptService.loginFailed(RequestResponseHolder.getRequest().get);
	}

}