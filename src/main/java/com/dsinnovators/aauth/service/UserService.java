package com.dsinnovators.aauth.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.dsinnovators.aauth.dao.UserRepo;
import com.dsinnovators.aauth.model.User;

@Service
@Transactional
public class UserService {

	private static final int PAGE_SIZE = 50;

	@Autowired
	private UserRepo userRepo;

	public Page<User> getUsers(Integer pageNumber) {
		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.ASC, "username");
		return userRepo.findAll(request);
	}

	public User getUserByUsername(String username) {
		return userRepo.findByUsername(username);
	}
}