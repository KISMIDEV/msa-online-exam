package com.github.kingjava.exam.mapper;

import com.github.kingjava.common.core.persistence.CrudMapper;
import com.github.kingjava.exam.api.module.Knowledge;
import org.apache.ibatis.annotations.Mapper;

/**
 * 知识库Mapper
 *
 * @author kingjava
 * @date 2019/1/1 15:03
 */
@Mapper
public interface KnowledgeMapper extends CrudMapper<Knowledge> {
}
