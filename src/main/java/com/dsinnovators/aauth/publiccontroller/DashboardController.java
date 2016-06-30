package com.dsinnovators.aauth.publiccontroller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {

	@RequestMapping("/dashboard")
	public String index(HttpServletRequest request) {
		return "dashboard";
	}
}
