create table RVC_BLACKLISTED_TOKEN
(
    token     text         not null,
    expiresAt timestamp(6) not null,
    primary key (token)
);