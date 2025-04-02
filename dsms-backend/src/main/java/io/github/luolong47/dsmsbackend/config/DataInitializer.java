package io.github.luolong47.dsmsbackend.config;

import cn.hutool.crypto.SecureUtil;
import io.github.luolong47.dsmsbackend.model.entity.User;
import io.github.luolong47.dsmsbackend.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... args) {
        // 如果数据库中没有用户，则创建初始用户
        if (userRepository.count() == 0) {
            log.info("开始初始化用户数据...");

            List<User> users = Arrays.asList(
                createUser("admin", "123456", "系统管理员", "ADMIN"),
                createUser("teacher", "123456", "教师", "TEACHER"),
                createUser("student", "123456", "学生", "STUDENT")
            );

            userRepository.saveAll(users);
            log.info("用户数据初始化完成，共创建 {} 个用户", users.size());
        }
    }

    private User createUser(String username, String password, String nickname, String role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(SecureUtil.md5(password));
        user.setNickname(nickname);
        user.setRole(role);
        return user;
    }
}
