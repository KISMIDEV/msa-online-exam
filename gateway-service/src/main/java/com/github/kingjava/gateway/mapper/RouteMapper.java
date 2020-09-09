package com.github.kingjava.gateway.mapper;

import com.github.kingjava.common.core.persistence.CrudMapper;
import com.github.kingjava.gateway.module.Route;
import org.apache.ibatis.annotations.Mapper;

/**
 * Route mapper
 *
 * @author kingjava
 * @date 2019/4/2 15:00
 */
@Mapper
public interface RouteMapper extends CrudMapper<Route> {
}
