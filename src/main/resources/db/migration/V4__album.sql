create table album
(
    id          serial primary key,
    title       varchar(64) not null,
    artist_id   int references artist(id) on delete cascade ,
    released_on date,
    genre       varchar(32),
    total_songs int         not null default 0
);

create index idx_album_title on album (title);
create index idx_album_genre on album (genre);
create index idx_album_released_on on album (released_on);