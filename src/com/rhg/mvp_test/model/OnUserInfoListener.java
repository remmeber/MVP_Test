package com.rhg.mvp_test.model;

import com.rhg.mvp_test.bean.User;

/**
 * Model层，用户信息获取的回调接口
 * 
 * @author Administrator
 *
 */
public interface OnUserInfoListener {
	void getUserInfoSuccess(User user);

	void getUserInfoFailed();
}
