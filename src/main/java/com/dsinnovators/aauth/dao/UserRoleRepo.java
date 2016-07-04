package com.dsinnovators.aauth.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dsinnovators.aauth.model.UserRole;

public interface UserRoleRepo extends JpaRepository<UserRole, Long> {

	public UserRole findByUsername(String username);

}