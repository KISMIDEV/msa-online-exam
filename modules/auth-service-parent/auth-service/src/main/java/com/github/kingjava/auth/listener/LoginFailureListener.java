package com.github.kingjava.auth.listener;

import com.github.kingjava.common.security.event.CustomAuthenticationFailureEvent;
import com.github.kingjava.user.api.feign.UserServiceClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 *
 * 处理로그인失败事件
 *
 * @author kingjava
 * @date 2019-11-11 23:52
 */
@Slf4j
@AllArgsConstructor
@Component
public class LoginFailureListener implements ApplicationListener<CustomAuthenticationFailureEvent> {

	private final UserServiceClient userServiceClient;

	@Override
	public void onApplicationEvent(CustomAuthenticationFailureEvent event) {
		// 로그인失败后的处理

	}
}
