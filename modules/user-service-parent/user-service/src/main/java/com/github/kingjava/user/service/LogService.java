package com.github.kingjava.user.service;

import com.github.kingjava.common.basic.model.Log;
import com.github.kingjava.common.core.service.CrudService;
import com.github.kingjava.user.mapper.LogMapper;
import org.springframework.stereotype.Service;

/**
 * 日志service
 *
 * @author kingjava
 * @date 2018/10/31 20:43
 */
@Service
public class LogService extends CrudService<LogMapper, Log> {
}
