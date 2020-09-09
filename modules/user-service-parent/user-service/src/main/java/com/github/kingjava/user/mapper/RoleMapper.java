package com.github.kingjava.user.mapper;

import com.github.kingjava.common.core.persistence.CrudMapper;
import com.github.kingjava.user.api.module.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色mapper
 *
 * @author kingjava
 * @date 2018/8/26 09:33
 */
@Mapper
public interface RoleMapper extends CrudMapper<Role> {

    /**
     * 根据角色code查询
     *
     * @param role role
     * @return Role
     * @author kingjava
     * @date 2019/09/21 12:08:29
     */
    Role findByRoleCode(Role role);
}
