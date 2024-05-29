truncate artist cascade;
truncate album cascade;
truncate song cascade;

insert into artist (id, artist_name, description, genre) values (1,'test',null,'test');
insert into album (id, title, artist_id, released_on, genre) values (1,'ta',1,null,'rock');
insert into song (id, title, duration) values (1,'t1',5),(2,'t2',5),(3,'t3',10);
insert into album_song (album_id, song_id) values (1,1),(1,2),(1,3);