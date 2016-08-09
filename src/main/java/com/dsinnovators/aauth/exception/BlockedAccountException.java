package com.dsinnovators.aauth.exception;

import org.springframework.security.core.AuthenticationException;

public class BlockedAccountException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	public BlockedAccountException(String msg) {
		super(msg);
	}

	public BlockedAccountException(String msg, Throwable t) {
		super(msg, t);
	}

}
