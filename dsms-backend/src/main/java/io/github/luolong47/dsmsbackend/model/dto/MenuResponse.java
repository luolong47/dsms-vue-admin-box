package io.github.luolong47.dsmsbackend.model.dto;

import lombok.Data;
import java.util.List;

/**
 * 菜单响应DTO
 */
@Data
public class MenuResponse {
    /**
     * 菜单列表
     */
    private List<MenuDTO> list;
} 