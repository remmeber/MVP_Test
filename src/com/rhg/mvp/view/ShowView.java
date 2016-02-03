package com.rhg.mvp.view;

import com.rhg.mvp.bean.User;
import com.rhg.mvp.model.IRetry;

/**
 * 
 * <View层显示用户信息的接口，供Activity继承>
 * 
 * @author rhg 1013773046@qq.com
 * 
 * @version [版本号, 2016年1月31日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface ShowView extends IBaseViewShow {


    /**
     * 将用户信息展示
     * 
     * @param user
     */
    void showDatainActivity(User user);

    /**
     * 显示失败
     */
    void showLoadFailedError(IRetry retry);

}
