create table RVC_SERVER_ROLES
(
    id        uuid    not null,
    name      varchar not null,
    color     varchar,
    SERVER_ID uuid    not null,
    primary key (id),
    constraint RVC_SERVER_ROLES_SERVER foreign key (SERVER_ID) references RVC_SERVER DEFERRABLE
);

create table RVC_ROLE
(
    id        uuid not null,
    entity_id uuid,
    role_id   uuid,
    risk_type text,
    primary key (id),
    constraint RVC_ROLE_ROLE_ID foreign key (role_id) references RVC_SERVER_ROLES DEFERRABLE
);

create table RVC_USER_SERVER_ROLES
(
    server_role_id uuid not null,
    user_id        uuid not null,
    primary key (server_role_id, user_id),
    constraint RVC_USER_SERVER_ROLES_USER foreign key (user_id) references RVC_USER DEFERRABLE,
    constraint RVC_USER_SERVER_ROLES_SERVER_ROLE foreign key (server_role_id) references RVC_SERVER_ROLES DEFERRABLE
);