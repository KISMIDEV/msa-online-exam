package com.github.kingjava.exam.mapper;

import com.github.kingjava.common.core.persistence.CrudMapper;
import com.github.kingjava.exam.api.module.SubjectJudgement;
import org.apache.ibatis.annotations.Mapper;

/**
 * 判断题Mapper
 *
 * @author kingjava
 * @date 2019-07-16 13:00
 */
@Mapper
public interface SubjectJudgementMapper extends CrudMapper<SubjectJudgement> {

    /**
     * 物理删除
     *
     * @param subjectJudgement subjectJudgement
     * @return int
     * @author kingjava
     * @date 2019/06/16 22:54
     */
    int physicalDelete(SubjectJudgement subjectJudgement);

    /**
     * 物理批量删除
     *
     * @param ids ids
     * @return int
     * @author kingjava
     * @date 2019/06/16 22:54
     */
    int physicalDeleteAll(Long[] ids);
}
