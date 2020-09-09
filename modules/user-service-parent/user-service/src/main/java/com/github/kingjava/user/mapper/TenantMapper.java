package com.github.kingjava.user.mapper;

import com.github.kingjava.common.core.persistence.CrudMapper;
import com.github.kingjava.user.api.module.Tenant;
import org.apache.ibatis.annotations.Mapper;

/**
 * 租户Mapper
 *
 * @author kingjava
 * @date 2019/5/22 22:50
 */
@Mapper
public interface TenantMapper extends CrudMapper<Tenant> {

    /**
     * 根据租户标识获取
     *
     * @param tenantCode tenantCode
     * @return Tenant
     * @author kingjava
     * @date 2019/05/26 10:29
     */
    Tenant getByTenantCode(String tenantCode);

	/**
	 * 查询租户数量
	 *
	 * @return Integer
	 */
	Integer tenantCount();
}
