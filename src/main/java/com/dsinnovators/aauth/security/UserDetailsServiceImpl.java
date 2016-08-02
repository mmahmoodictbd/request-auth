package com.dsinnovators.aauth.security;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailsServiceImpl extends JdbcDaoImpl implements UserDetailsService {

	public static final String DEF_USERS_BY_USERNAME_QUERY = "select username, password, enabled from users where username=?";
	public static final String DEF_AUTHORITIES_BY_USERNAME_QUERY = "select username, role from user_roles where username=?";

	@Autowired
	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
		setUsersByUsernameQuery(DEF_USERS_BY_USERNAME_QUERY);
		setAuthoritiesByUsernameQuery(DEF_AUTHORITIES_BY_USERNAME_QUERY);
	}

}
