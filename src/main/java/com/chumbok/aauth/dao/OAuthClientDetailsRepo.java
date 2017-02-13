package com.chumbok.aauth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.chumbok.aauth.model.OAuthClientDetails;

public interface OAuthClientDetailsRepo extends JpaRepository<OAuthClientDetails, Long> {

	@Query("SELECT acd FROM OAuthClientDetails acd where acd.clientId = ?1")
	public OAuthClientDetails findByClientId(String clientId);

}