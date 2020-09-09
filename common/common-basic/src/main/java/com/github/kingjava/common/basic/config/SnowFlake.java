package com.github.kingjava.common.basic.config;

import com.github.kingjava.common.basic.properties.SnowflakeProperties;
import com.github.kingjava.common.core.utils.SnowflakeIdWorker;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ID生成配置
 *
 * @author kingjava
 * @date 2019/4/26 11:17
 */
@Configuration
@AllArgsConstructor
public class SnowFlake {

	private final SnowflakeProperties snowflakeProperties;

	@Bean
	public SnowflakeIdWorker initTokenWorker() {
		return new SnowflakeIdWorker(Integer.parseInt(snowflakeProperties.getWorkId()),
				Integer.parseInt(snowflakeProperties.getDataCenterId()));
	}
}
