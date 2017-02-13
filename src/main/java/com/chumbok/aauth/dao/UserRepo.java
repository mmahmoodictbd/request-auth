package com.chumbok.aauth.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chumbok.aauth.model.User;

public interface UserRepo extends JpaRepository<User, Long> {

	public User findByUsername(String username);

}