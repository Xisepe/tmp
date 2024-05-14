create table song
(
    id       serial primary key,
    title    varchar(128) not null,
    duration int          not null check ( duration > 0 ),
    song_url varchar(255)
);

create index idx_song_title on song (title);
create index idx_song_under_90_sec on song (title)
    where duration < 90;