package io.github.luolong47.dsmsbackend.service;

import io.github.luolong47.dsmsbackend.model.entity.User;

public interface UserService {
    User findByUsername(String username);
}
