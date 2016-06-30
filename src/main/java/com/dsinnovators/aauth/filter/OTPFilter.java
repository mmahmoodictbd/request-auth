package com.dsinnovators.aauth.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

@Component
public class OTPFilter extends GenericFilterBean {

	private static final Logger logger = LoggerFactory.getLogger(OTPFilter.class);

	private final String OTP_VERIFIED = "OTP_VERIFIED";
	private final String TWOFA_VERIFICATION_REQUIRED = "2FA_VERIFICATION_REQUIRED";
	private final static String[] SKIP_URLS = { "/", "/login", "/otp", "/sendSMS", "/token", "/loginSuccess" };

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		Boolean twoFaVerificationReq = (Boolean) request.getSession().getAttribute(TWOFA_VERIFICATION_REQUIRED);
		if (twoFaVerificationReq != null && !twoFaVerificationReq) {
			logger.debug("2FA verification is disabled in user configuration. Skipping OTPFilter.");
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}

		String url = request.getRequestURL().toString();
		String urlLastPart = url.substring(url.lastIndexOf("/"));

		logger.debug("--->>" + urlLastPart);

		HttpSession session = request.getSession();

		//TODO: fix with isSkipURL()
		if (!isResourceURL(url) && !isStaticResource(urlLastPart) && session != null && !"/".equals(urlLastPart)
				&& !"/login".equals(urlLastPart) && !"/otp".equals(urlLastPart) && !"/sendSMS".equals(urlLastPart)
				&& !"/token".equals(urlLastPart) && !"/loginSuccess".equals(urlLastPart)) {

			boolean otpVerified = session.getAttribute(OTP_VERIFIED) != null ? (Boolean) session
					.getAttribute(OTP_VERIFIED) : false;
			if (!otpVerified) {
				logger.debug("Session found. OTP not verified yet. Redirecting to /otp");
				response.sendRedirect("/otp");
			}

		}

		filterChain.doFilter(servletRequest, servletResponse);
	}

	private boolean isStaticResource(String url) {
		if (url.contains(".")) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isResourceURL(String url) {
		if (url.contains("/resources/")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isSkipURL(String url) {
		for (int i = 0; i < SKIP_URLS.length; i++) {
			if (url.equals(SKIP_URLS[i])) {
				return true;
			}
		}
		return false;
	}
}