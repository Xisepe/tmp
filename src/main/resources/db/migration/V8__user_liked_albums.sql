create table user_liked_album
(
    user_id  int references users (id) on delete cascade ,
    album_id int references album (id) on delete cascade ,
    primary key (user_id, album_id)
);