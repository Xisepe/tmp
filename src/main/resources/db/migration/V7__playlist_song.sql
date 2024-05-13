create table playlist_song(
    playlist_id int references playlist(id) on delete cascade ,
    song_id int references song(id) on delete cascade ,
    primary key (playlist_id, song_id)
);