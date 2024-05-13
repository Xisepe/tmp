create table playlist(
    id serial primary key,
    user_id int references users(id) on delete cascade,
    title varchar(64) not null ,
    description varchar(255)
);
create index idx_playlist_title on playlist(title);