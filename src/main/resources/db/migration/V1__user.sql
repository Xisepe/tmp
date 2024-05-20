create table users
(
    id         serial primary key,
    email      varchar(255) not null unique,
    password   varchar(60)  not null,
    username   varchar(64) unique check ( username = lower(username) ),
    created_on timestamp default current_timestamp,
    metadata   jsonb
);

create index idx_users_email on users (email);
create index idx_users_username on users (username);
create index idx_users_created_on on users (created_on);
create index idx_users_metadata on users (metadata);

create or replace function enforce_username_lowercase()
    returns trigger as
$$
begin
    new.username = lower(new.username); -- Convert username to lowercase
    return new;
end;
$$ language plpgsql;

create trigger enforce_username_lowercase_trigger
    before insert
    on users
    for each row
execute function enforce_username_lowercase();