package com.github.kingjava.exam.mapper;

import com.github.kingjava.common.core.persistence.CrudMapper;
import com.github.kingjava.exam.api.module.Pictures;
import org.apache.ibatis.annotations.Mapper;

/**
 * 图片mapper
 *
 * @author kingjava
 * @date 2019/6/16 14:50
 */
@Mapper
public interface PicturesMapper extends CrudMapper<Pictures> {
}
