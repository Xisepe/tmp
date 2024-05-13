create table user_role(
    user_id int references users(id) on delete cascade ,
    role_id int references role(id) on delete cascade ,
    primary key (user_id, role_id)
);