package com.dsinnovators.aauth.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class HttpUtil {

	private HttpUtil() {
		throw new IllegalStateException("Util class need not to initialized.");
	}

	public static String getRemoteIP() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attr.getRequest().getRemoteAddr();
	}

}
