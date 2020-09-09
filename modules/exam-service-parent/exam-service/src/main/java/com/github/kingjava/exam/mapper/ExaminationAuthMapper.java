package com.github.kingjava.exam.mapper;

import com.github.kingjava.common.core.persistence.CrudMapper;
import com.github.kingjava.exam.api.module.ExaminationAuth;
import org.apache.ibatis.annotations.Mapper;

/**
 * 考试权限Mapper
 *
 * @author kingjava
 * @date 2019-07-16 14:03
 */
@Mapper
public interface ExaminationAuthMapper extends CrudMapper<ExaminationAuth> {
}
