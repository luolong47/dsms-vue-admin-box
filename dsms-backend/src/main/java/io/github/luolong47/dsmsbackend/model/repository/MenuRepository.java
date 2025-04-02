package io.github.luolong47.dsmsbackend.model.repository;

import io.github.luolong47.dsmsbackend.model.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单仓库接口
 */
@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    
    /**
     * 根据父ID查询菜单列表
     * @param parentId 父ID
     * @return 菜单列表
     */
    List<Menu> findByParentIdOrderByOrderNumAsc(Long parentId);
    
    /**
     * 查询所有顶级菜单
     * @return 菜单列表
     */
    List<Menu> findByParentIdIsNullOrderByOrderNumAsc();
} 