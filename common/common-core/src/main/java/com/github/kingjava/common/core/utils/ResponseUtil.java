package com.github.kingjava.common.core.utils;

import com.github.kingjava.common.core.constant.ApiMsg;
import com.github.kingjava.common.core.model.ResponseBean;

/**
 * @author kingjava
 * @date 2019-10-08 12:03
 */
public class ResponseUtil {

	private ResponseUtil() {
	}

	/**
	 * 是否成功
	 * @param responseBean responseBean
	 * @return boolean
	 */
	public static boolean isSuccess(ResponseBean<?> responseBean) {
		return responseBean != null && responseBean.getCode() == ApiMsg.KEY_SUCCESS;
	}
}
