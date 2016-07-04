package com.dsinnovators.aauth.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.dsinnovators.aauth.dao.OAuthClientDetailsRepo;
import com.dsinnovators.aauth.model.OAuthClientDetails;

@Service
@Transactional
public class OAuthClientDetailsService {

	private static final int PAGE_SIZE = 10;

	@Autowired
	private OAuthClientDetailsRepo oauthRepo;

	public OAuthClientDetails findByClientId(String clientId) {
		return oauthRepo.findByClientId(clientId);
	}

	public Page<OAuthClientDetails> get(Integer pageNumber) {
		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.ASC, "clientId");
		return oauthRepo.findAll(request);
	}
	
	public OAuthClientDetails create(OAuthClientDetails oAuthClientDetails) {
		return oauthRepo.save(oAuthClientDetails);
	}
	


}