package com.github.kingjava.exam.mapper;

import com.github.kingjava.common.core.persistence.CrudMapper;
import com.github.kingjava.exam.api.module.SubjectCategory;
import org.apache.ibatis.annotations.Mapper;

/**
 * 문제분류Mapper
 *
 * @author kingjava
 * @date 2018/12/4 21:48
 */
@Mapper
public interface SubjectCategoryMapper extends CrudMapper<SubjectCategory> {
}
