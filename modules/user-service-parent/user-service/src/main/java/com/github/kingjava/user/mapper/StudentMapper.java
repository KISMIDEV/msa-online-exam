package com.github.kingjava.user.mapper;

import com.github.kingjava.common.core.persistence.CrudMapper;
import com.github.kingjava.user.api.module.Student;
import org.apache.ibatis.annotations.Mapper;

/**
 * 学生Mapper
 *
 * @author kingjava
 * @date 2019/07/09 15:27
 */
@Mapper
public interface StudentMapper extends CrudMapper<Student> {
}
