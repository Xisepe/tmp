create function calculate_total_album_duration(
    in fn_album_id int
) returns int as
$$
declare
    total int := 0;
begin
    select sum(s.duration)
    into total
    from album_song al
    join song s
    on al.song_id = s.id
    where al.album_id = fn_album_id;
end;
$$ language plpgsql;