package com.dsinnovators.aauth.security;

import com.dsinnovators.aauth.exception.BlockedAccountException;
import com.dsinnovators.aauth.service.LoginAttemptService;
import com.dsinnovators.aauth.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

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
        setUserDetailsService(userDetailsService);
        setPasswordEncoder(passwordEncoder);
    }

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String remoteIP = HttpUtil.getRemoteIP();
        try {
            if (loginAttemptService.isBlocked(remoteIP)) {
                throw new BlockedAccountException("Account is blocked for 1 minute.");
            }

            Authentication auth = super.authenticate(authentication);
            loginAttemptService.loginSucceeded(remoteIP);
            return auth;

        } catch (BadCredentialsException e) {
            loginAttemptService.loginFailed(remoteIP);
            throw e;
        }

    }
}
