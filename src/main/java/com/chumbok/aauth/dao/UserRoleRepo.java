package com.chumbok.aauth.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chumbok.aauth.model.UserRole;

public interface UserRoleRepo extends JpaRepository<UserRole, Long> {

	public UserRole findByUsername(String username);

}