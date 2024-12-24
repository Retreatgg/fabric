package org.example.tkani.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.tkani.dto.TokenDto;
import org.example.tkani.dto.UserLoginDto;
import org.example.tkani.model.User;
import org.example.tkani.repository.UserRepository;
import org.example.tkani.service.UserService;
import org.example.tkani.utils.TokenUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public TokenDto login(UserLoginDto userLoginDto) {
        Optional<User> userOptional = userRepository.findByEmail(userLoginDto.getEmail());
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        User user = userOptional.get();
        boolean isCorrectPassword = encoder.matches(userLoginDto.getPassword(), user.getPassword());
        if (!isCorrectPassword) {
            throw new IllegalArgumentException("Неверный логин или пароль");
        }

        return TokenDto.builder()
                .token(TokenUtils.generateToken(user))
                .build();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public boolean checkToken(String token) {
        Optional<User> userOptional = userRepository.findByToken(token);
        return userOptional.isEmpty();
    }

    @Override
    public User getUserByToken(String token) {
        return userRepository.findByToken(token).orElseThrow();
    }

    @Override
    public User findByEmail(String username) {
        return userRepository.findByEmail(username).orElseThrow();
    }
}
