truncate users cascade ;
truncate playlist cascade;
insert into users (id, email, password, username, metadata)
values (1,'test1@test.test','test','test1', '{"name":"Maxim"}'),
       (2,'test2@test.test','test','test2', null),
       (3, 'test3@test.test','test','test3', '{"name":"Test"}');
insert into playlist (id, user_id, title, description)
values (1,1,'Like',null),
       (2,2,'Test',null);