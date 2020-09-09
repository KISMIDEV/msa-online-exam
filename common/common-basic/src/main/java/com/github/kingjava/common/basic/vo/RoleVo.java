package com.github.kingjava.common.basic.vo;

import com.github.kingjava.common.core.persistence.BaseEntity;
import lombok.Data;

/**
 * 角色
 *
 * @author kingjava
 * @date 2018-08-25 13:58
 */
@Data
public class RoleVo extends BaseEntity<RoleVo> {

    private String roleName;

    private String roleCode;

    private String roleDesc;

}
