package com.rhg.mvp.model;

import com.rhg.mvp.bean.User;

/**
 * Model层，用户信息获取的回调接口
 * 
 * @author RHG
 *
 */
public interface OnUserInfoListener {
	void getUserInfoSuccess(User user);

	void getUserInfoFailed();
}
