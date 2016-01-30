package com.rhg.mvp.model;

/**
 * 
 * <Model 用户获取信息>
 * 
 * @author rhg 1013773046@qq.com
 * @version [版本号, 2016年1月31日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface GetUser {
    /**
     * 用于获取用户信息的接口
     * 
     * @param id
     * @param listener 获取信息回调
     */
    public void getUser(int id, OnUserInfoListener listener);

    public void getUsers(OnUserInfoListener listener);
}
