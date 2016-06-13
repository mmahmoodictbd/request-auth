
create table account
(
  id bigint not null default nextval('account_id_seq') constraint account_pkey primary key,
  email varchar(500) not null,
  password varchar(100) not null,
  created_at timestamp not null default current_timestamp,
  constraint account_mail_address_key unique (email)
);

insert into account(email, password) values ('bob@example.com', '48181acd22b3edaebc8a447868a7df7ce629920a'); -- password:bob
insert into account(email, password) values ('alice@example.com', '522b276a356bdf39013dfabea2cd43e141ecc9e8'); -- password:alice
