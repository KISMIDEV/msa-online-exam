package com.github.kingjava.user.api.module;

import com.github.kingjava.common.core.persistence.BaseEntity;
import lombok.Data;

/**
 * 角色菜单关联
 *
 * @author kingjava
 * @date 2018/8/26 22:24
 */
@Data
public class RoleMenu extends BaseEntity<RoleMenu> {

    private Long roleId;

    private Long menuId;
}
