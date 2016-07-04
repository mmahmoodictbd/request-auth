package com.dsinnovators.aauth.util;

import java.math.BigInteger;
import java.security.SecureRandom;

public class StringUtil {

	private static SecureRandom random = new SecureRandom();

	public static String secureRandomString() {
		return new BigInteger(130, random).toString(32);
	}
}
