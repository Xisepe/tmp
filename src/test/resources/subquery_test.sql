truncate song cascade ;
truncate users cascade;
truncate playlist cascade ;
truncate playlist_song cascade ;
INSERT INTO song (id, title, duration)
VALUES
    (1, 't1', 50),
    (2, 't2', 45),
    (3, 't3', 60),
    (4, 't4', 55),
    (5, 't5', 40),
    (6, 't6', 70),
    (7, 't7', 65),
    (8, 't8', 50),
    (9, 't9', 75),
    (10, 't10', 80),
    (11, 't11', 90);
insert into users (id, email, password, username, created_on, metadata) values (1,'test','test','test',null,null),(2,'test2','test2','test2',null,null);
insert into playlist (id, user_id, title, description) values (1,1,'p1',null), (2,2,'p2',null);
insert into playlist_song (playlist_id, song_id) values (1,1),(1,2),(2,7)