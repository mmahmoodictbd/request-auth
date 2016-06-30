package com.dsinnovators.aauth.publiccontroller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsinnovators.aauth.service.OneTimePassService;

@Controller
public class OTPController {

	@Autowired
	private OneTimePassService otpService;

	@RequestMapping("/otp")
	public String showOTPForm() {
		otpService.sendSMS();
		return "otp";
	}

	@RequestMapping(value = "/otp", method = RequestMethod.POST)
	public String verifyOTP(HttpServletRequest request) {

		String userOTP = request.getParameter("otp");
		if (otpService.verifyOTP(userOTP)) {
			request.getSession().setAttribute("OTP_VERIFIED", true);
		} else {
			request.getSession().setAttribute("OTP_VERIFIED", false);
		}

		return "redirect:dashboard";
	}

	@RequestMapping(value = "/otp/sendSMS", method = RequestMethod.POST)
	@ResponseBody
	public String sendSMS() {
		otpService.sendSMS();
		return "redirect:otp";
	}
}
