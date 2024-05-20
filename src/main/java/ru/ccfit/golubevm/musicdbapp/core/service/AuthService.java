package ru.ccfit.golubevm.musicdbapp.core.service;

import ru.ccfit.golubevm.musicdbapp.core.entity.User;
import ru.ccfit.golubevm.musicdbapp.core.model.LoginInfo;

public interface AuthService {
    String registerUser(User user);
    LoginInfo loginUser(String email, String password);
    String getLogoutCookie();
}
