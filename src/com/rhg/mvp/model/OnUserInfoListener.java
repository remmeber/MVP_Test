package com.rhg.mvp.model;

import com.rhg.mvp.bean.User;

/**
 * Model�㣬�û���Ϣ��ȡ�Ļص��ӿ�
 * 
 * @author RHG
 *
 */
public interface OnUserInfoListener {
	void getUserInfoSuccess(User user);

	void getUserInfoFailed();
}
