package com.rhg.mvp.model;

import com.rhg.mvp.bean.User;

/**
 * 
 * < Model层，用户信息获取的回调接口>
 * 
 * @author rhg 1013773046@qq.com
 *
 *         2016年1月28日
 */
public interface OnUserInfoListener {
	void getUserInfoSuccess(User user);

	void getUserInfoFailed();
}
