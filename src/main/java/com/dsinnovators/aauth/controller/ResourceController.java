package com.dsinnovators.aauth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("resources")
@EnableResourceServer
public class ResourceController {

	@PreAuthorize("#oauth2.hasScope('read')")
	@RequestMapping(method = RequestMethod.GET, value = "/foos/{id}")
	@ResponseBody
	public String findById(@PathVariable long id) {
		return String.valueOf(id);
	}

	@RequestMapping("/hello")
	@ResponseBody
	public String sayHello() {
		return "hello";
	}
}
