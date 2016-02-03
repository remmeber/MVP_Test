package com.rhg.mvp.model;

import com.rhg.mvp.bean.User;

import rx.Observable;
/**
 * 
 * <获取用户信息的Model层接口>
 *  
 * @author  rhg 1013773046@qq.com
 * @version [版本号, 2016年2月3日]
 * @see    [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface IUserInfoModel {
    Observable<User> getUserInfo();
}
