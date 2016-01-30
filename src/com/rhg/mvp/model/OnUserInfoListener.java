package com.rhg.mvp.model;

import java.util.ArrayList;

import com.rhg.mvp.bean.User;

/**
 * 
<<<<<<< HEAD
 * < Model层，用户信息获取的回调接口>
 * 
 * @author rhg 1013773046@qq.com
 *
 *         2016年1月28日
=======
 * <Model层，用户信息获取的回调接口>
 * 
 * @author rhg 1013773046@qq.com
 * @version [版本号, 2016年1月31日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
>>>>>>> rhg2
 */
public interface OnUserInfoListener {
    void getUsersInfoSuccess(ArrayList<User> users);

    void getUserInfoSuccess(User user);

    void getUserInfoFailed();
}
