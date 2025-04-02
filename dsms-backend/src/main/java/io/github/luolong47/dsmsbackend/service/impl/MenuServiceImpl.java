package io.github.luolong47.dsmsbackend.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import io.github.luolong47.dsmsbackend.model.dto.MenuDTO;
import io.github.luolong47.dsmsbackend.model.entity.Menu;
import io.github.luolong47.dsmsbackend.model.repository.MenuRepository;
import io.github.luolong47.dsmsbackend.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 菜单服务实现类
 */
@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    @Override
    public List<MenuDTO> getMenuTree() {
        // 获取所有顶级菜单
        List<Menu> topMenus = menuRepository.findByParentIdIsNullOrderByOrderNumAsc();
        if (CollUtil.isEmpty(topMenus)) {
            return new ArrayList<>();
        }
        
        // 构建树形结构
        return topMenus.stream()
                .map(this::buildMenuTree)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Menu saveMenu(Menu menu) {
        LocalDateTime now = LocalDateTime.now();
        if (menu.getId() == null) {
            menu.setCreateTime(now);
        }
        menu.setUpdateTime(now);
        return menuRepository.save(menu);
    }

    @Override
    @Transactional
    public void deleteMenu(Long id) {
        menuRepository.deleteById(id);
    }

    @Override
    public Menu getMenuById(Long id) {
        return menuRepository.findById(id).orElse(null);
    }

    /**
     * 构建菜单树
     * @param menu 菜单
     * @return 菜单DTO
     */
    private MenuDTO buildMenuTree(Menu menu) {
        MenuDTO menuDTO = convertToDTO(menu);
        
        // 递归查询子菜单
        List<Menu> children = menuRepository.findByParentIdOrderByOrderNumAsc(menu.getId());
        if (CollUtil.isNotEmpty(children)) {
            menuDTO.setChildren(children.stream()
                    .map(this::buildMenuTree)
                    .collect(Collectors.toList()));
        }
        
        return menuDTO;
    }

    @Override
    public MenuDTO convertToDTO(Menu menu) {
        if (menu == null) {
            return null;
        }
        
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setPath(menu.getPath());
        menuDTO.setRedirect(menu.getRedirect());
        menuDTO.setComponent(menu.getComponent());
        menuDTO.setAlwayShow(menu.getAlwayShow());
        menuDTO.setHideMenu(menu.getHideMenu());
        
        // 设置元数据
        MenuDTO.MenuMeta meta = new MenuDTO.MenuMeta();
        meta.setTitle(menu.getTitle());
        meta.setIcon(menu.getIcon());
        meta.setHideClose(menu.getHideClose());
        menuDTO.setMeta(meta);
        
        return menuDTO;
    }
} 