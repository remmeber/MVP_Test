package com.rhg.mvp.view;

import com.rhg.mvp.bean.User;

/**
 * View层显示用户信息的接口，供Activity继承
 * 
 * @author RHG
 *
 */
public interface ShowUserView {
	/**
	 * 用于显示进度条
	 */
	void showLoading();

	/**
	 * 用于隐藏进度条
	 */
	void hideLoading();

	/**
	 * 将用户信息展示
	 * 
	 * @param user
	 */
	void toMainActivity(User user);

	/**
	 * 显示失败
	 */
	void showFailedError();
}
