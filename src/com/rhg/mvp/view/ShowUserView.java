package com.rhg.mvp.view;

import java.util.ArrayList;

import com.rhg.mvp.bean.User;

/**
 * 
 * <View层显示用户信息的接口，供Activity继承>
 * 
 * @author rhg 1013773046@qq.com
 * @version [版本号, 2016年1月31日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
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
    void showUserinActivity(User user);

    /**
     * 显示用户组 <一句话功能简述>
     * 
     * @param users
     * @see [类、类#方法、类#成员]
     */
    void showUsersinActivity(ArrayList<User> users);

    /**
     * 显示失败
     */
    void showFailedError();
}
