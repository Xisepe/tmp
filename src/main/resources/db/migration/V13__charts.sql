create table chart (
    id serial primary key,
    description text
);

create table rated_chart(
    chart_id int references chart(id),
    avg_rating float8 default 0.0
);

create table voted_chart(
    chart_id int references chart(id),
    votes int default 0
);

create table chart_song(
    chart_id int references chart(id),
    song_id int references song(id),
    primary key (chart_id, song_id)
);