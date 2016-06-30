package com.dsinnovators.aauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.dsinnovators.aauth.otp.Clock;
import com.dsinnovators.aauth.otp.TOTPGenerator;

@Service
public class OneTimePassService {

	@Autowired
	private SmsSender smsSender;

	public void sendSMS() {

		Clock clock = new Clock(30);
		TOTPGenerator generator = new TOTPGenerator(clock);

		String pass = generator.getPassword();
		// TODO: Get mobile number from user service
		smsSender.send("+8801558755926", pass);

		ServletRequestAttributes sra = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes());
		sra.getRequest().getSession().setAttribute("OTP_PASS", pass);

	}

	public boolean verifyOTP(String userOTP) {

		ServletRequestAttributes sra = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes());
		String originalOTP = (String) sra.getRequest().getSession().getAttribute("OTP_PASS");
		if (userOTP != null && userOTP.equals(originalOTP)) {
			return true;
		} else {
			return false;
		}
	}
}