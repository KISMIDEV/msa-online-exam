package com.github.kingjava.user.mapper;

import com.github.kingjava.common.basic.model.Log;
import com.github.kingjava.common.core.persistence.CrudMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 日志
 *
 * @author kingjava
 * @date 2018/10/31 20:38
 */
@Mapper
public interface LogMapper extends CrudMapper<Log> {
}
