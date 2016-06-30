package com.dsinnovators.aauth.publiccontroller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dsinnovators.aauth.model.User;
import com.dsinnovators.aauth.service.UserService;

@Controller
public class LoginController {

	private final String TWOFA_VERIFICATION_REQUIRED = "2FA_VERIFICATION_REQUIRED";

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/loginSuccess", method = RequestMethod.GET)
	public String loginSuccess(HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();

		if (username == null || username.length() == 0) {
			return "redirect:/login";
		}

		User loggedInUser = userService.getUserByUsername(username);

		if (loggedInUser.isTwoFaEnabled()) {
			request.getSession().setAttribute(TWOFA_VERIFICATION_REQUIRED, true);
		} else {
			request.getSession().setAttribute(TWOFA_VERIFICATION_REQUIRED, false);
		}

		return "redirect:/dashboard";
	}
}
