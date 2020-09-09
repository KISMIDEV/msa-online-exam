package com.github.kingjava.user.api.module;

import com.github.kingjava.common.core.persistence.BaseEntity;
import lombok.Data;

/**
 * 用户部门关系
 *
 * @author kingjava
 * @date 2018/10/27 10:23
 */
@Data
public class UserDept extends BaseEntity<UserDept> {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 部门ID
     */
    private String deptId;
}
