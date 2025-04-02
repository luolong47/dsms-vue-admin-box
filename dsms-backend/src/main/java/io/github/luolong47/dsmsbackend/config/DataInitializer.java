package io.github.luolong47.dsmsbackend.config;

import cn.hutool.crypto.SecureUtil;
import io.github.luolong47.dsmsbackend.model.entity.Menu;
import io.github.luolong47.dsmsbackend.model.entity.User;
import io.github.luolong47.dsmsbackend.model.repository.MenuRepository;
import io.github.luolong47.dsmsbackend.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final MenuRepository menuRepository;

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
        
        // 清空现有菜单数据
        menuRepository.deleteAll();
        log.info("开始初始化菜单数据...");
        
        // 创建首页菜单 - 根菜单使用Layout组件
        Menu dashboard = createMenu("/", "/dashboard", null, "首页", "sfont system-home", null, 1, false, false, false);
        dashboard = menuRepository.save(dashboard);
        
        // 子菜单配置为实际页面组件
        Menu dashboardIndex = createMenu("dashboard", null, "dashboard_dashboard", "首页", "sfont system-home", dashboard.getId(), 1, false, false, true);
        menuRepository.save(dashboardIndex);
        
        log.info("菜单数据初始化完成");
    }

    private User createUser(String username, String password, String nickname, String role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(SecureUtil.md5(password));
        user.setNickname(nickname);
        user.setRole(role);
        return user;
    }
    
    private Menu createMenu(String path, String redirect, String component, String title, String icon, 
                           Long parentId, Integer orderNum, Boolean alwayShow, Boolean hideMenu, Boolean hideClose) {
        Menu menu = new Menu();
        menu.setPath(path);
        menu.setRedirect(redirect);
        menu.setComponent(component);
        menu.setTitle(title);
        menu.setIcon(icon);
        menu.setParentId(parentId);
        menu.setOrderNum(orderNum);
        menu.setAlwayShow(alwayShow);
        menu.setHideMenu(hideMenu);
        menu.setHideClose(hideClose);
        menu.setCreateTime(LocalDateTime.now());
        menu.setUpdateTime(LocalDateTime.now());
        return menu;
    }
}
