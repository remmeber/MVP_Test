package com.rhg.mvp.model;
/**
 * 
 * <Model层，获取用户信息接口>
 * 
 *@author  rhg 1013773046@qq.com
 *
 * 2016年1月28日
 */
public interface GetUser {
	/**
	 * 获取用户信息
	 * @param id
	 * @param listener 获取信息的回调
	 */
	public void getUser(int id, OnUserInfoListener listener);
}
