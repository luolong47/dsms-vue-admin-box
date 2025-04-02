package io.github.luolong47.dsmsbackend.model.dto;

import io.github.luolong47.dsmsbackend.model.entity.User;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LoginResponse {
    private String token;
    private User user;
} 