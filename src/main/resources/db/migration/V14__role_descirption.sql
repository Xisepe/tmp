create table role_description(
    id serial primary key,
    role_id int references role(id),
    description text not null
);