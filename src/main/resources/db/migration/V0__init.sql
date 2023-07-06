drop table if exists users;

create table users
(
    id         bigint       not null,
    birthday   date         not null,
    email      varchar(255) not null,
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    password   varchar(255) not null,
    role       varchar(255),
    primary key (id)
);

alter table if exists users
    add constraint user_FK unique (email);