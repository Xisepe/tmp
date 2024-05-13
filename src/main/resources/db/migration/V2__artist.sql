create table artist(
    id serial primary key,
    artist_name varchar(128) not null,
    description varchar(255),
    genre varchar(32)
);

create index idx_artist_artist_name on artist(artist_name);
create index idx_artist_genre on artist(genre);