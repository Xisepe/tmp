create table album_song(
    album_id int references album(id) on delete cascade ,
    song_id int references song(id) on delete cascade ,
    primary key (album_id, song_id)
);
