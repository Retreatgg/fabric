package org.example.tkani.utils;


import org.example.tkani.model.User;
import org.example.tkani.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TokenUtils {

    private static TokenService tokenService;

    @Autowired
    public void init(TokenService tokenService) {
        TokenUtils.tokenService = tokenService;
    }

    public static String generateToken(User user) {
        String token = UUID.randomUUID().toString();
        tokenService.addToken(token, user);
        return token;
    }

    public static boolean validateToken(String token) {
        return tokenService.containsKey(token);
    }

    public static UserDetails getUserDetailsFromToken(String token) {
        return tokenService.getUserDetailsByToken(token);
    }

    public static void invalidateToken(String token) {
        tokenService.deleteToken(token);
    }

}
