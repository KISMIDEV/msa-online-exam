package com.github.kingjava.user.mapper;

import com.github.kingjava.common.core.persistence.CrudMapper;
import com.github.kingjava.user.api.module.Attachment;
import org.apache.ibatis.annotations.Mapper;

/**
 * 附件mapper
 *
 * @author kingjava
 * @date 2018/10/30 20:55
 */
@Mapper
public interface AttachmentMapper extends CrudMapper<Attachment> {
}
