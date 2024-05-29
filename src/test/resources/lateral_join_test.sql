truncate album cascade;
truncate artist cascade;

insert into artist (id, artist_name, description, genre)
values (1,'test',null,'rock');
insert into album (id, title, artist_id, released_on, genre)
values (1,'t1',1, date '2001-09-28', 'rock'),
       (2,'t2',1, date '2020-09-28', 'rock')