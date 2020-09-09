package com.github.kingjava.common.basic.vo;

import com.github.kingjava.common.core.persistence.BaseEntity;
import lombok.Data;

/**
 * 部门vo
 *
 * @author kingjava
 * @date 2018/12/31 22:02
 */
@Data
public class DeptVo extends BaseEntity<DeptVo> {

    private String deptName;
}
