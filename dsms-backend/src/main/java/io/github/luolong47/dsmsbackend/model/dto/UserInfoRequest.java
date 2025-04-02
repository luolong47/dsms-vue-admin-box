package io.github.luolong47.dsmsbackend.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserInfoRequest {
    @NotBlank(message = "token不能为空")
    private String token;
} 