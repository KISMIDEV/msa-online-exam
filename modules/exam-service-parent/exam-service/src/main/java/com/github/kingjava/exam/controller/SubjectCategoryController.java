package com.github.kingjava.exam.controller;


import com.github.kingjava.common.core.model.ResponseBean;
import com.github.kingjava.common.core.web.BaseController;
import com.github.kingjava.common.log.annotation.Log;
import com.github.kingjava.common.security.annotations.AdminTenantTeacherAuthorization;
import com.github.kingjava.common.security.utils.SysUtil;
import com.github.kingjava.exam.api.constants.ExamSubjectConstant;
import com.github.kingjava.exam.api.dto.SubjectCategoryDto;
import com.github.kingjava.exam.api.module.SubjectCategory;
import com.github.kingjava.exam.service.SubjectCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 문제분류controller
 *
 * @author kingjava
 * @date 2018/12/4 21:57
 */
@AllArgsConstructor
@Api("题库分类信息管理")
@RestController
@RequestMapping("/v1/subjectCategory")
public class SubjectCategoryController extends BaseController {

    private final SubjectCategoryService categoryService;

    /**
     * 返回树形分类集合
     *
     * @return List
     * @author kingjava
     * @date 2018/12/04 22:03
     */
    @GetMapping(value = "categories")
    @ApiOperation(value = "获取分类列表")
    public List<SubjectCategoryDto> menus() {
       return categoryService.menus();
    }

    /**
     * 根据ID获取
     *
     * @param id id
     * @return ResponseBean
     * @author kingjava
     * @date 2018/12/04 21:59
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "获取分类信息", notes = "根据分类id获取分类详细信息")
    @ApiImplicitParam(name = "id", value = "分类ID", required = true, dataType = "Long", paramType = "path")
    public ResponseBean<SubjectCategory> subjectCategory(@PathVariable Long id) {
        return new ResponseBean<>(categoryService.get(id));
    }

    /**
     * 新增分类
     *
     * @param subjectCategory subjectCategory
     * @return ResponseBean
     * @author kingjava
     * @date 2018/12/04 22:00
     */
    @PostMapping
    @AdminTenantTeacherAuthorization
    @ApiOperation(value = "创建分类", notes = "创建分类")
    @ApiImplicitParam(name = "subjectCategory", value = "分类实体subjectCategory", required = true, dataType = "SubjectCategory")
    @Log("문제분류신규추가")
    public ResponseBean<Boolean> addSubjectCategory(@RequestBody @Valid SubjectCategory subjectCategory) {
        subjectCategory.setCommonValue(SysUtil.getUser(), SysUtil.getSysCode(), SysUtil.getTenantCode());
        subjectCategory.setType(ExamSubjectConstant.PUBLIC_CATEGORY);
        return new ResponseBean<>(categoryService.insert(subjectCategory) > 0);
    }

    /**
     * 更新分类
     *
     * @param subjectCategory subjectCategory
     * @return ResponseBean
     * @author kingjava
     * @date 2018/12/04 22:01
     */
    @PutMapping
    @AdminTenantTeacherAuthorization
    @ApiOperation(value = "분류정보수정", notes = "根据分类id更新分类的基本信息")
    @ApiImplicitParam(name = "subjectCategory", value = "分类实体subjectCategory", required = true, dataType = "SubjectCategory")
    @Log("문제분류수정")
    public ResponseBean<Boolean> updateSubjectCategory(@RequestBody @Valid SubjectCategory subjectCategory) {
        subjectCategory.setCommonValue(SysUtil.getUser(), SysUtil.getSysCode(), SysUtil.getTenantCode());
        return new ResponseBean<>(categoryService.update(subjectCategory) > 0);
    }

    /**
     * 根据ID删除
     *
     * @param id id
     * @return ResponseBean
     * @author kingjava
     * @date 2018/12/04 22:02
     */
    @DeleteMapping("/{id}")
    @AdminTenantTeacherAuthorization
    @ApiOperation(value = "분류삭제", notes = "根据ID删除分类")
    @ApiImplicitParam(name = "id", value = "分类ID", required = true, paramType = "path")
    @Log("문제분류삭제")
    public ResponseBean<Boolean> deleteSubjectCategory(@PathVariable Long id) {
        SubjectCategory subjectCategory = new SubjectCategory();
        subjectCategory.setId(id);
        return new ResponseBean<>(categoryService.delete(subjectCategory) > 0);
    }
}
