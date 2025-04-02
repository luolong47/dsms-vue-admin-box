package io.github.luolong47.dsmsbackend.service.impl;

import io.github.luolong47.dsmsbackend.model.entity.User;
import io.github.luolong47.dsmsbackend.model.repository.UserRepository;
import io.github.luolong47.dsmsbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
