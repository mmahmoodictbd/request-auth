package com.dsinnovators.aauth.otp;

import java.math.BigInteger;
import java.security.SecureRandom;

public class TOTPGenerator {
	
	private SecureRandom random = new SecureRandom();
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
		return new BigInteger(130, random).toString(32);
	}

}
