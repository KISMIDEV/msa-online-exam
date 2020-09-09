package com.github.kingjava.exam.mapper;

import com.github.kingjava.common.core.persistence.CrudMapper;
import com.github.kingjava.exam.api.module.Course;
import org.apache.ibatis.annotations.Mapper;

/**
 * 과정Mapper
 *
 * @author kingjava
 * @date 2018/11/8 21:10
 */
@Mapper
public interface CourseMapper extends CrudMapper<Course> {
}
