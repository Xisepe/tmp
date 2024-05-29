CREATE OR REPLACE PROCEDURE calculate_total_album_duration(
    IN fn_album_id INT,
    OUT total INT
)
    LANGUAGE plpgsql
AS $$
BEGIN
    SELECT SUM(s.duration)
    INTO total
    FROM album_song al
             JOIN song s ON al.song_id = s.id
    WHERE al.album_id = fn_album_id;
END;
$$;
