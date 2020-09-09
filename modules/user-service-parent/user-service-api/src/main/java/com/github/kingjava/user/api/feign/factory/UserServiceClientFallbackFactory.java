package com.github.kingjava.user.api.feign.factory;

import com.github.kingjava.user.api.feign.fallback.UserServiceClientFallbackImpl;
import com.github.kingjava.user.api.feign.UserServiceClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 用户断路器工厂
 *
 * @author kingjava
 * @date 2019/3/23 23:38
 */
@Component
public class UserServiceClientFallbackFactory implements FallbackFactory<UserServiceClient> {

    @Override
    public UserServiceClient create(Throwable throwable) {
        UserServiceClientFallbackImpl userServiceClientFallback = new UserServiceClientFallbackImpl();
        userServiceClientFallback.setThrowable(throwable);
        return userServiceClientFallback;
    }
}
