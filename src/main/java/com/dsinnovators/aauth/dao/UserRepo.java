package com.dsinnovators.aauth.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dsinnovators.aauth.model.User;

public interface UserRepo extends JpaRepository<User, Long> {

	public User findByUsername(String username);

}