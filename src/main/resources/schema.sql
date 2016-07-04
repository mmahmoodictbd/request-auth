drop table if exists oauth_client_details;
create table oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(255)
);

INSERT INTO `oauth_client_details` (`client_id`, `client_secret`, `scope`, `authorized_grant_types`) VALUES
('9v6fvn7221bniao2vc6jnvp31l', '2oe11m1ojh6fgqrn503sfp3r5h', 'read', 'password,authorization_code,refresh_token');
INSERT INTO `oauth_client_details` (`client_id`, `client_secret`, `scope`, `authorized_grant_types`) VALUES
('bniao2vc6jnvp31l9v6fvn7221', 'qrn503sfp3r5h2oe11m1ojh6fg', 'read', 'password,authorization_code,refresh_token');
 
drop table if exists oauth_client_token;
create table oauth_client_token (
  token_id VARCHAR(255),
  token LONG VARBINARY,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255)
);
 
drop table if exists oauth_access_token;
create table oauth_access_token (
  token_id VARCHAR(255),
  token LONG VARBINARY,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication LONG VARBINARY,
  refresh_token VARCHAR(255)
);
 
drop table if exists oauth_refresh_token;
create table oauth_refresh_token (
  token_id VARCHAR(255),
  token LONG VARBINARY,
  authentication LONG VARBINARY
);
 
drop table if exists oauth_code;
create table oauth_code (
  code VARCHAR(255), authentication LONG VARBINARY
);
 
drop table if exists oauth_approvals;
create table oauth_approvals (
    userId VARCHAR(255),
    clientId VARCHAR(255),
    scope VARCHAR(255),
    status VARCHAR(10),
    expiresAt TIMESTAMP,
    lastModifiedAt TIMESTAMP
);
 
drop table if exists ClientDetails;
create table ClientDetails (
  appId VARCHAR(255) PRIMARY KEY,
  resourceIds VARCHAR(255),
  appSecret VARCHAR(255),
  scope VARCHAR(255),
  grantTypes VARCHAR(255),
  redirectUrl VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation VARCHAR(4096),
  autoApproveScopes VARCHAR(255)
);

drop table if exists user_roles;
drop table if exists users;

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `enabled` tinyint(4) NOT NULL DEFAULT '1',
  `enable2fa` tinyint(4) NOT NULL DEFAULT '1',
  `oauth_client_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `users` (`username`, `password`, `enabled`, `enable2fa`, `oauth_client_id`) VALUES
('admin', '$2a$10$LM7l9extPhwS7Uuy3hPsW.b899Kmnxe0B9YsE7UVXKhC5OKLbMwsW', 1, 0, '9v6fvn7221bniao2vc6jnvp31l');
INSERT INTO `users` (`username`, `password`, `enabled`, `enable2fa`, `oauth_client_id`) VALUES
('admin1', '$2a$10$LM7l9extPhwS7Uuy3hPsW.b899Kmnxe0B9YsE7UVXKhC5OKLbMwsW', 1, 1, 'bniao2vc6jnvp31l9v6fvn7221');

DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE IF NOT EXISTS `user_roles` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`user_role_id`),
  UNIQUE KEY `uni_username_role` (`role`,`username`),
  KEY `fk_username_idx` (`username`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

INSERT INTO `user_roles` (`user_role_id`, `username`, `role`) VALUES (1, 'admin', 'ROLE_USER');
INSERT INTO `user_roles` (`user_role_id`, `username`, `role`) VALUES (2, 'admin1', 'ROLE_USER');