package com.github.kingjava.exam.mapper;

import com.github.kingjava.common.core.persistence.CrudMapper;
import com.github.kingjava.exam.api.module.SubjectChoices;
import org.apache.ibatis.annotations.Mapper;

/**
 * 选择题Mapper
 *
 * @author kingjava
 * @date 2018/11/8 21:15
 */
@Mapper
public interface SubjectChoicesMapper extends CrudMapper<SubjectChoices> {

    /**
     * 物理删除
     *
     * @param subjectChoices subjectChoices
     * @return int
     * @author kingjava
     * @date 2019/06/16 22:44
     */
    int physicalDelete(SubjectChoices subjectChoices);

    /**
     * 物理批量删除
     *
     * @param ids ids
     * @return int
     * @author kingjava
     * @date 2019/06/16 22:44
     */
    int physicalDeleteAll(Long[] ids);
}
