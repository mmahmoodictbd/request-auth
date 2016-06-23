package com.dsinnovators.aauth.publiccontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicController {

	@RequestMapping("/public")
	@ResponseBody
	public String sayHello() {
		return "hello";
	}
}
