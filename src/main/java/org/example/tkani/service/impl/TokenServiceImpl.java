package org.example.tkani.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.tkani.model.User;
import org.example.tkani.service.TokenService;
import org.example.tkani.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final UserService userService;
    private final AuthUserDetailServiceImpl authUserDetailService;
    @Override
    public void addToken(String token, User user) {
        user.setToken(token);
        userService.save(user);
    }

    @Override
    public boolean containsKey(String token) {
        return userService.checkToken(token);
    }

    @Override
    public UserDetails getUserDetailsByToken(String token) {
        User user = userService.getUserByToken(token);
        return authUserDetailService.loadUserByUsername(user.getEmail());
    }

    @Override
    public void deleteToken(String token) {
        User user = userService.getUserByToken(token);
        user.setToken(null);
        userService.save(user);
    }
}
