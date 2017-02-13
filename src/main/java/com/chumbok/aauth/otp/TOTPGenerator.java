package com.chumbok.aauth.otp;

import com.chumbok.aauth.util.StringUtil;

public class TOTPGenerator {

	private final String secret;
	private final Clock clock;

	public TOTPGenerator(Clock clock) {
		this.secret = getRamdomSecret();
		this.clock = clock;
	}

	public String getPassword() {
		return TOTP.getTOTPCode(this.secret, this.clock.getCurrentInterval());
	}

	private String getRamdomSecret() {
		return StringUtil.secureRandomString();
	}

}
