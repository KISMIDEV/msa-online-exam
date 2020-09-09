package com.github.kingjava.exam.service;

import com.github.kingjava.exam.api.dto.SubjectDto;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 题目通用接口
 *
 * @author kingjava
 * @date 2019/6/16 17:30
 */
public interface ISubjectService {

    /**
     * 根据ID查询
     *
     * @param id id
     * @return SubjectDto
     * @author kingjava
     * @date 2019/06/16 17:35
     */
    SubjectDto getSubject(Long id);

    /**
     * 根据ID查询上一题、下一题
     *
     * @param examinationId examinationId
     * @param previousId    previousId
     * @param nextType      -1：当前题目，0：下一题，1：上一题
     * @return SubjectDto
     * @author kingjava
     * @date 2019-09-14 16:33
     */
    SubjectDto getNextByCurrentIdAndType(Long examinationId, Long previousId, Integer nextType);

    /**
     * 查询题目列表
     *
     * @param subjectDto subjectDto
     * @return List
     * @author kingjava
     * @date 2019/06/16 18:10
     */
    List<SubjectDto> findSubjectList(SubjectDto subjectDto);

    /**
     * 查询题目分页列表
     *
     * @param pageInfo   pageInfo
     * @param subjectDto subjectDto
     * @return List
     * @author kingjava
     * @date 2019/06/16 18:10
     */
    PageInfo<SubjectDto> findSubjectPage(PageInfo pageInfo, SubjectDto subjectDto);

    /**
     * 根据ID批量查询
     *
     * @param ids ids
     * @return List
     * @author kingjava
     * @date 2019/06/16 18:10
     */
    List<SubjectDto> findSubjectListById(Long[] ids);

    /**
     * 저장
     *
     * @param subjectDto subjectDto
     * @return int
     * @author kingjava
     * @date 2019/06/16 17:47
     */
    int insertSubject(SubjectDto subjectDto);

    /**
     * 更新
     *
     * @param subjectDto subjectDto
     * @return int
     * @author kingjava
     * @date 2019/06/16 17:47
     */
    int updateSubject(SubjectDto subjectDto);

    /**
     * 删除
     *
     * @param subjectDto subjectDto
     * @return int
     * @author kingjava
     * @date 2019/06/16 17:48
     */
    int deleteSubject(SubjectDto subjectDto);

    /**
     * 批量删除
     *
     * @param ids ids
     * @return int
     * @author kingjava
     * @date 2019/06/16 17:49
     */
    int deleteAllSubject(Long[] ids);

    /**
     * 物理删除
     *
     * @param subjectDto subjectDto
     * @return int
     * @author kingjava
     * @date 2019/06/16 22:48
     */
    int physicalDeleteSubject(SubjectDto subjectDto);

    /**
     * 物理批量删除
     *
     * @param ids ids
     * @return int
     * @author kingjava
     * @date 2019/06/16 22:49
     */
    int physicalDeleteAllSubject(Long[] ids);
}
