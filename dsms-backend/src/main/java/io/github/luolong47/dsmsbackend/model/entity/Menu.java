package io.github.luolong47.dsmsbackend.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 菜单实体类
 */
@Data
@Entity
@Table(name = "t_menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 菜单路径
     */
    @Column(length = 100, nullable = false)
    private String path;

    /**
     * 重定向路径
     */
    @Column(length = 100)
    private String redirect;

    /**
     * 组件名称
     */
    @Column(length = 100)
    private String component;

    /**
     * 菜单名称
     */
    @Column(length = 50, nullable = false)
    private String title;

    /**
     * 菜单图标
     */
    @Column(length = 50)
    private String icon;

    /**
     * 父菜单ID
     */
    @Column
    private Long parentId;

    /**
     * 排序号
     */
    @Column
    private Integer orderNum;

    /**
     * 是否总是显示
     */
    @Column
    private Boolean alwayShow;

    /**
     * 是否隐藏菜单
     */
    @Column
    private Boolean hideMenu;

    /**
     * 隐藏关闭按钮
     */
    @Column
    private Boolean hideClose;

    /**
     * 创建时间
     */
    @Column
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Column
    private LocalDateTime updateTime;

    /**
     * 子菜单列表，不映射到数据库
     */
    @Transient
    private List<Menu> children;
}
