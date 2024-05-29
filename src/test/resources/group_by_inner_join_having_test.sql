truncate artist cascade ;
truncate album cascade ;
insert into artist (id, artist_name, description, genre)
values (1, 'The Rolling Notes', 'A legendary rock band known for their electrifying performances and timeless hits.',
        'Rock'),
       (2, 'Synth Surge', 'An electronic music duo that revolutionizes the genre with their futuristic soundscapes.',
        'Electronic'),
       (3, 'Harmony Heights', 'A pop group famous for their catchy tunes and harmonious vocals.', 'Pop'),
       (4, 'Jazz Junction', 'A classic jazz ensemble renowned for their smooth melodies and intricate improvisations.',
        'Jazz'),
       (5, 'Classical Crescendo', 'An orchestra that brings the timeless beauty of classical music to life.',
        'Classical');
insert into album (id, title, artist_id, released_on, genre, total_songs)
values
-- Albums for The Rolling Notes
(1, 'Rolling Thunder', 1, '1996-05-20', 'Rock', 10),
(2, 'Electric Storm', 1, '1998-09-15', 'Rock', 12),
(3, 'Rock Revival', 1, '2001-11-03', 'Rock', 11),

-- Albums for Synth Surge
(6, 'Neon Dreams', 2, '2011-08-15', 'Electronic', 9),
(7, 'Future Waves', 2, '2013-11-11', 'Electronic', 10),
(8, 'Synth Symphony', 2, '2015-04-20', 'Electronic', 12),

-- Albums for Harmony Heights
(11, 'Pop Paradise', 3, '2016-07-23', 'Pop', 10),
(12, 'Harmonic Bliss', 3, '2018-02-14', 'Pop', 12),
(13, 'Heights of Harmony', 3, '2019-10-19', 'Pop', 11),
(14, 'Catchy Tunes', 3, '2021-01-30', 'Pop', 10),
(15, 'Melody Magic', 3, '2022-06-11', 'Pop', 12),

-- Albums for Jazz Junction
(16, 'Smooth Grooves', 4, '1982-11-20', 'Jazz', 8),
(17, 'Jazz Classics', 4, '1985-03-15', 'Jazz', 9),
(18, 'Melodic Journeys', 4, '1990-07-28', 'Jazz', 10),
(19, 'Junction Jive', 4, '1995-10-05', 'Jazz', 12),
(20, 'Timeless Tunes', 4, '2000-12-22', 'Jazz', 11),

-- Albums for Classical Crescendo
(21, 'Symphonic Serenity', 5, '2003-04-20', 'Classical', 7),
(22, 'Orchestral Opulence', 5, '2006-09-11', 'Classical', 8),
(23, 'Timeless Classics', 5, '2010-12-15', 'Classical', 9),
(24, 'Grand Overtures', 5, '2013-03-30', 'Classical', 10),
(25, 'Crescendo of Classics', 5, '2016-08-25', 'Classical', 7),
(26, 'Majestic Melodies', 5, '2019-11-05', 'Classical', 8),
(27, 'Eternal Elegance', 5, '2021-05-17', 'Classical', 9);
