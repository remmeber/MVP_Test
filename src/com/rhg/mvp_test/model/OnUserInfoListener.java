package com.rhg.mvp_test.model;

import com.rhg.mvp_test.bean.User;

/**
 * Model�㣬�û���Ϣ��ȡ�Ļص��ӿ�
 * 
 * @author Administrator
 *
 */
public interface OnUserInfoListener {
	void getUserInfoSuccess(User user);

	void getUserInfoFailed();
}
