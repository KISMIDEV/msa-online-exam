package com.github.kingjava.auth.service;

import com.github.kingjava.auth.api.module.OauthClientDetails;
import com.github.kingjava.auth.mapper.OauthClientDetailsMapper;
import com.github.kingjava.common.core.service.CrudService;
import org.springframework.stereotype.Service;

/**
 * Oauth2客户端Service
 *
 * @author kingjava
 * @date 2019/3/30 16:48
 */
@Service
public class OauthClientDetailsService extends CrudService<OauthClientDetailsMapper, OauthClientDetails> {
}
