create table role (
    id serial primary key ,
    name varchar(32) not null
);

create index idx_role_name on role(name);