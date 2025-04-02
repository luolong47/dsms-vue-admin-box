package io.github.luolong47.dsmsbackend.model.dto;

import lombok.Data;
import java.util.List;
import java.util.Map;

/**
 * 菜单DTO
 */
@Data
public class MenuDTO {
    
    /**
     * 菜单路径
     */
    private String path;
    
    /**
     * 重定向路径
     */
    private String redirect;
    
    /**
     * 组件名称
     */
    private String component;
    
    /**
     * 菜单元数据
     */
    private MenuMeta meta;
    
    /**
     * 是否总是显示
     */
    private Boolean alwayShow;
    
    /**
     * 是否隐藏菜单
     */
    private Boolean hideMenu;
    
    /**
     * 子菜单列表
     */
    private List<MenuDTO> children;
    
    /**
     * 菜单元数据
     */
    @Data
    public static class MenuMeta {
        /**
         * 菜单标题
         */
        private String title;
        
        /**
         * 菜单图标
         */
        private String icon;
        
        /**
         * 隐藏关闭按钮
         */
        private Boolean hideClose;
    }
} 