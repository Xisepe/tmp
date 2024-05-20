insert into users (id, email, password, username, metadata)
values (1, 'maxim.golubeff2014@gmail.com', 'test12345', 'xisepe', '{
  "name": "Maxim"
}'),
       (2, 'm.golubev1@g.nsu.ru', 'test12345', null, null);

insert into user_role (user_id, role_id)
values (1, (select role_id from role where name = 'ROLE_ADMIN' limit 1)),
       (2, (select role_id from role where name = 'ROLE_USER' limit 1));

insert into artist (id, artist_name, description, genre)
select id,
       'artist_test_' || id               as artist_name,
       substr(md5(random()::text), 0, 32) as description,
       'test_genre_' || id % 6            as genre
from generate_series(1, 15) as id;

insert into album (id, title, artist_id, genre, total_songs)
select id + 1,
       'title_test_' || id + 1            as title,
       id % 15 + 1                        as artist_id,
       substr(md5(random()::text), 0, 32) as description,
       'test_genre_' || id % 6            as genre
from generate_series(0, 19) as id;