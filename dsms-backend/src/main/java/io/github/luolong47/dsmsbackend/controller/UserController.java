package io.github.luolong47.dsmsbackend.controller;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import io.github.luolong47.dsmsbackend.model.common.R;
import io.github.luolong47.dsmsbackend.model.dto.LoginRequest;
import io.github.luolong47.dsmsbackend.model.dto.LoginResponse;
import io.github.luolong47.dsmsbackend.model.dto.UserInfoRequest;
import io.github.luolong47.dsmsbackend.model.entity.User;
import io.github.luolong47.dsmsbackend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private static final String JWT_SECRET = "your-secret-key";

    @PostMapping("/login")
    public Mono<R<LoginResponse>> login(@Valid @RequestBody LoginRequest request) {
        return Mono.fromCallable(() -> {
            User user = userService.findByUsername(request.getName());
            if (user != null && SecureUtil.md5(request.getPassword()).equals(user.getPassword())) {
                Map<String, Object> claims = new HashMap<>();
                claims.put("userId", user.getId());
                claims.put("username", user.getUsername());
                claims.put("role", user.getRole());

                String token = JWTUtil.createToken(claims, JWT_SECRET.getBytes());

                LoginResponse response = new LoginResponse()
                    .setToken(token)
                    .setUser(user);

                return R.ok(response);
            }
            return R.fail("用户名或密码错误");
        });
    }

    @PostMapping("/info")
    public Mono<R<User>> getUserInfo(@Valid @RequestBody UserInfoRequest request) {
        return Mono.fromCallable(() -> {
            try {
                JWT jwt = JWTUtil.parseToken(request.getToken());
                String username = (String) jwt.getPayload("username");
                User user = userService.findByUsername(username);

                if (user == null) {
                    return R.fail("用户不存在");
                }

                // 清除密码信息
                user.setPassword(null);
                return R.ok(user);
            } catch (Exception e) {
                return R.fail("token解析失败");
            }
        });
    }

    @PostMapping("/out")
    public Mono<R<Void>> logout() {
        return Mono.fromCallable(() -> {
            // 由于我们使用的是JWT，服务端不需要维护会话状态
            // 客户端只需要清除本地存储的token即可
            return R.ok();
        });
    }
}
