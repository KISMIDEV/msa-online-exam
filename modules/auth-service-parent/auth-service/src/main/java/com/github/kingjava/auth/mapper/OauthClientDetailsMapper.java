package com.github.kingjava.auth.mapper;

import com.github.kingjava.auth.api.module.OauthClientDetails;
import com.github.kingjava.common.core.persistence.CrudMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * Oauth2客户端mapper
 *
 * @author kingjava
 * @date 2019/3/30 16:39
 */
@Mapper
public interface OauthClientDetailsMapper extends CrudMapper<OauthClientDetails> {
}
