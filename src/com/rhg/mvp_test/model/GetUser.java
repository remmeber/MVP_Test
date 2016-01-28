package com.rhg.mvp_test.model;
/**
 * Model 获取用户信息的接口，
 * @author Administrator
 *
 */
public interface GetUser {
	/**
	 * 根据用户id来获取用户的信息
	 * @param id
	 * @param listener 获取信息的回调接口
	 */
	public void getUser(int id, OnUserInfoListener listener);
}
