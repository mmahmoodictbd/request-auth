package com.dsinnovators.aauth.publiccontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dsinnovators.aauth.model.User;
import com.dsinnovators.aauth.service.OAuthClientDetailsService;
import com.dsinnovators.aauth.service.UserService;

@Controller
public class UserController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserService userService;

	@Autowired
	private OAuthClientDetailsService oauthClientService;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String index(@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "page", required = false) Integer pageNumber, Model model) {

		User user;
		if (username != null && username.length() > 0) {
			user = userService.getUserByUsername(username);
		} else {
			user = new User();
		}

		model.addAttribute("userForm", user);

		if (pageNumber == null) {
			pageNumber = 1;
		}

		Page<User> page = userService.getUsers(pageNumber);

		int current = page.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		int end = Math.min(begin + 10, page.getTotalPages());

		model.addAttribute("userPage", page);
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);

		return "users";
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public String createUpdateUser(User user) {

		if (user.getPassword() != null && user.getPassword().length() > 0
				&& user.getPassword().equals(user.getRepeatPassword())) {
			userService.saveUser(user);
		} else {
			logger.debug("Either password is empty or password not matched with repeat password.");
		}

		return "redirect:users";
	}

}
