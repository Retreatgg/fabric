package org.example.tkani.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.tkani.model.Role;
import org.example.tkani.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthUserDetailServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Transactional
    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) {
        var user = userService.findByEmail(username);
        return new User(
                user.getEmail(),
                user.getPassword(),
                user.getEnabled(),
                true,
                true,
                true,
                getRoles(user.getRoles())
        );
    }

    private Collection<? extends GrantedAuthority> getRoles(Set<Role> roleList) {
        return roleList.stream()
                .map(e -> new SimpleGrantedAuthority(e.getRole()))
                .toList();
    }
}
