package com.github.kingjava.user.service;

import com.github.kingjava.common.core.service.CrudService;
import com.github.kingjava.user.mapper.UserRoleMapper;
import com.github.kingjava.user.api.module.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kingjava
 * @date 2018/8/26 14:55
 */
@AllArgsConstructor
@Service
public class UserRoleService extends CrudService<UserRoleMapper, UserRole> {

    private final UserRoleMapper userRoleMapper;

    /**
     * 根据用户ID查询
     *
     * @param userId 用户ID
     * @return List
     */
    public List<UserRole> getByUserId(Long userId) {
        return userRoleMapper.getByUserId(userId);
    }

    /**
     * 根据用户ID查询
     *
     * @param userIds 用户ID
     * @return List
     */
    public List<UserRole> getByUserIds(List<Long> userIds) {
        return userRoleMapper.getByUserIds(userIds);
    }

    /**
     * 批量插入
     *
     * @param userRoles userRoles
     * @return int
     * @author kingjava
     * @date 2019-09-03 13:13
     */
    public int insertBatch(List<UserRole> userRoles) {
        return userRoleMapper.insertBatch(userRoles);
    }
}
