package io.github.luolong47.dsmsbackend.service;

import io.github.luolong47.dsmsbackend.model.dto.MenuDTO;
import io.github.luolong47.dsmsbackend.model.entity.Menu;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 菜单服务接口
 */
public interface MenuService {
    
    /**
     * 获取菜单树形结构
     * @return 菜单树
     */
    List<MenuDTO> getMenuTree();
    
    /**
     * 保存菜单
     * @param menu 菜单信息
     * @return 保存结果
     */
    Menu saveMenu(Menu menu);
    
    /**
     * 删除菜单
     * @param id 菜单ID
     */
    void deleteMenu(Long id);
    
    /**
     * 获取菜单详情
     * @param id 菜单ID
     * @return 菜单信息
     */
    Menu getMenuById(Long id);
    
    /**
     * 将实体转换为DTO
     * @param menu 菜单实体
     * @return 菜单DTO
     */
    MenuDTO convertToDTO(Menu menu);
} 