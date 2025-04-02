package io.github.luolong47.dsmsbackend.model.repository;

import io.github.luolong47.dsmsbackend.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
