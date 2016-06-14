# --- !Ups

create table account
(
  id bigint not null AUTO_INCREMENT,
  email varchar(500) not null,
  password varchar(100) not null,
  created_at datetime not null default current_timestamp,
  primary key (id)
);

insert into account(email, password) values ('bob@example.com', '48181acd22b3edaebc8a447868a7df7ce629920a'); -- password:bob
insert into account(email, password) values ('alice@example.com', '522b276a356bdf39013dfabea2cd43e141ecc9e8'); -- password:alice


create table oauth_client
(
  id bigint not null AUTO_INCREMENT,
  owner_id bigint not null,
  grant_type varchar(20) not null,
  client_id varchar(100) not null,
  client_secret varchar(100) not null,
  redirect_uri varchar(2000),
  created_at datetime not null default current_timestamp,
  primary key (id)
);

INSERT INTO oauth_client( owner_id, grant_type, client_id, client_secret ) 
VALUES ( 1,  'client_credentials',  'bob_client_id',  'bob_client_secret' ) ;
INSERT INTO oauth_client( owner_id, grant_type, client_id, client_secret, redirect_uri ) 
VALUES ( 2,  'authorization_code',  'alice_client_id',  'alice_client_secret',  'http://localhost:3000/callback' ) ;# 1 row affected.
INSERT INTO oauth_client( owner_id, grant_type, client_id, client_secret ) 
VALUES ( 2,  'password',  'alice_client_id2',  'alice_client_secret2' ) ;


create table oauth_authorization_code
(
  id bigint not null AUTO_INCREMENT,
  account_id bigint not null,
  oauth_client_id bigint not null,
  code varchar(100) not null,
  redirect_uri varchar(2000) not null,
  created_at datetime not null default current_timestamp,
  primary key (id)
);

INSERT INTO oauth_authorization_code( account_id, oauth_client_id, code, redirect_uri ) 
VALUES ( 1, 2,  'bob_code',  'http://localhost:3000/callback' );

CREATE TABLE oauth_access_token(
	id BIGINT NOT NULL AUTO_INCREMENT ,
	account_id BIGINT NOT NULL ,
	oauth_client_id bigint not null,
	access_token VARCHAR( 100 ) NOT NULL ,
	refresh_token VARCHAR( 100 ) NOT NULL ,
	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ,
	PRIMARY KEY ( id )
)

# --- !Downs


drop table oauth_access_token;
drop table oauth_authorization_code;
drop table oauth_client;
drop table account;
