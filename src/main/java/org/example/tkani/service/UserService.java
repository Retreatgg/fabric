package org.example.tkani.service;

import org.example.tkani.dto.TokenDto;
import org.example.tkani.dto.UserLoginDto;
import org.example.tkani.model.User;

public interface UserService {
    TokenDto login(UserLoginDto userLoginDto);
    void save(User user);

    boolean checkToken(String token);

    User getUserByToken(String token);
    User findByEmail(String username);
}
