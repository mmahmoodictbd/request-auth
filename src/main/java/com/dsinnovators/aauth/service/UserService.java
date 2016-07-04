package com.dsinnovators.aauth.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dsinnovators.aauth.dao.OAuthClientDetailsRepo;
import com.dsinnovators.aauth.dao.UserRepo;
import com.dsinnovators.aauth.dao.UserRoleRepo;
import com.dsinnovators.aauth.model.OAuthClientDetails;
import com.dsinnovators.aauth.model.User;
import com.dsinnovators.aauth.model.UserRole;
import com.dsinnovators.aauth.util.StringUtil;

@Service
@Transactional
public class UserService {

	private static final int PAGE_SIZE = 50;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private UserRoleRepo userRoleRepo;

	@Autowired
	private OAuthClientDetailsRepo oauthRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public Page<User> getUsers(Integer pageNumber) {

		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.ASC, "username");
		Page<User> userPage = userRepo.findAll(request);

		for (User user : userPage.getContent()) {
			if (user.getOAuthClientId() != null && user.getOAuthClientId().length() > 0) {
				user.setoAuthClientDetails(oauthRepo.findByClientId(user.getOAuthClientId()));
			}
		}

		return userPage;
	}

	public User getUserByUsername(String username) {

		User user = userRepo.findByUsername(username);
		if (user.getOAuthClientId() != null && user.getOAuthClientId().length() > 0) {
			user.setoAuthClientDetails(oauthRepo.findByClientId(user.getOAuthClientId()));
		}

		return userRepo.findByUsername(username);
	}

	public User saveUser(User user) {

		if (user.getPassword() != null && user.getPassword().length() > 0) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}

		if (user.getId() == 0) {
			// Create OAuth account
			String clientId = StringUtil.secureRandomString();
			OAuthClientDetails oAuthClientDetails = new OAuthClientDetails();
			oAuthClientDetails.setClientId(clientId);
			oAuthClientDetails.setClientSecret(StringUtil.secureRandomString());
			oAuthClientDetails.setScope("read");
			oAuthClientDetails.setAuthorizedGrantTypes("password,authorization_code,refresh_token");
			oauthRepo.save(oAuthClientDetails);

			user.setOAuthClientId(clientId);
		}

		userRepo.save(user);

		UserRole role = userRoleRepo.findByUsername(user.getUsername());

		if (role == null) {
			role = new UserRole();
			role.setUsername(user.getUsername());
			role.setRole("ROLE_USER");
			userRoleRepo.save(role);
		}

		return user;
	}
}