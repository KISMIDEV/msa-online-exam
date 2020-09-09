package com.github.kingjava.user.mapper;

import com.github.kingjava.common.core.persistence.CrudMapper;
import com.github.kingjava.user.api.module.Dept;
import org.apache.ibatis.annotations.Mapper;

/**
 * 菜单mapper
 *
 * @author kingjava
 * @date 2018/8/26 22:34
 */
@Mapper
public interface DeptMapper extends CrudMapper<Dept> {
}
