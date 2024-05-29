package ru.ccfit.golubevm.musicdbapp.core.repository.custom;

import ru.ccfit.golubevm.musicdbapp.core.entity.User;

import java.util.List;

public interface UserRepo extends Repo<User>{
    User find_by_id_eq_one(Integer id);
    User find_by_email_eq_one(String email);
    List<User> find_by_id_lessThan_all(Integer id);
    List<User> find_by_id_lessThan_orderBy_id_asc_all(Integer id);
}
