package com.github.kingjava.common.security.event;

import lombok.Data;
import org.springframework.context.ApplicationEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * 로그인失败事件
 *
 * @author kingjava
 * @date 2019-11-11 23:46
 */
@Data
public class CustomAuthenticationFailureEvent extends ApplicationEvent {

	private UserDetails userDetails;

	public CustomAuthenticationFailureEvent(Authentication authentication, UserDetails userDetails) {
		super(authentication);
		this.userDetails = userDetails;
	}

}
