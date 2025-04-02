package io.github.luolong47.dsmsbackend.controller;

import io.github.luolong47.dsmsbackend.model.common.R;
import io.github.luolong47.dsmsbackend.model.dto.MenuDTO;
import io.github.luolong47.dsmsbackend.model.dto.MenuResponse;
import io.github.luolong47.dsmsbackend.model.entity.Menu;
import io.github.luolong47.dsmsbackend.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 菜单控制器
 */
@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    /**
     * 获取菜单列表
     * @return 菜单列表
     */
    @PostMapping("/list")
    public Mono<R<MenuResponse>> getMenuList() {
        List<MenuDTO> menuTree = menuService.getMenuTree();
        MenuResponse response = new MenuResponse();
        response.setList(menuTree);
        return Mono.just(R.ok(response));
    }

    /**
     * 保存菜单
     * @param menu 菜单信息
     * @return 保存结果
     */
    @PostMapping("/save")
    public Mono<R<Menu>> saveMenu(@RequestBody Menu menu) {
        Menu savedMenu = menuService.saveMenu(menu);
        return Mono.just(R.ok(savedMenu));
    }

    /**
     * 获取菜单详情
     * @param id 菜单ID
     * @return 菜单信息
     */
    @GetMapping("/{id}")
    public Mono<R<Menu>> getMenu(@PathVariable Long id) {
        Menu menu = menuService.getMenuById(id);
        return Mono.just(R.ok(menu));
    }

    /**
     * 删除菜单
     * @param id 菜单ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public Mono<R<Void>> deleteMenu(@PathVariable Long id) {
        menuService.deleteMenu(id);
        return Mono.just(R.ok());
    }
} 