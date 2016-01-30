package com.rhg.mvp.presenter;

import java.util.ArrayList;

import com.rhg.mvp.bean.User;
import com.rhg.mvp.model.GetUser;
import com.rhg.mvp.model.GetUserInfo;
import com.rhg.mvp.model.OnUserInfoListener;
import com.rhg.mvp.view.ShowUserView;

import android.os.Handler;

/**
 * 
 * <用来展示用户信息>
 * 
 * @author rhg 1013773046@qq.com
 * @version [版本号, 2016年1月31日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class UserInfoPresenter {
    private GetUser getUserInfo;
    private ShowUserView showUserView;
    private Handler mHandler = new Handler();

    public UserInfoPresenter(ShowUserView showUserView) {
        this.getUserInfo = new GetUserInfo();
        this.showUserView = showUserView;
    }

    public void getUserInfoById(int id) {
        /**
         * 在Activity中调出进度条
         */
        showUserView.showLoading();
        /**
         * 此处调用Model层的接口，并产生回调，后触发View层的接口
         */
        getUserInfo.getUser(id, new OnUserInfoListener() {
            @Override
            public void getUserInfoSuccess(final User user) {
                mHandler.post(new Runnable() {
                    public void run() {
                        /**
                         * 获取信息成功，调用View层的接口，触发显示
                         */
                        showUserView.showUserinActivity(user);
                        showUserView.hideLoading();
                    }
                });
            }

            @Override
            public void getUserInfoFailed() {
                mHandler.post(new Runnable() {
                    public void run() {
                        showUserView.showFailedError();
                        showUserView.hideLoading();
                    }
                });
            }

            @Override
            public void getUsersInfoSuccess(ArrayList<User> users) {}
        });

    }

    public void getUserGroup() {
        showUserView.showLoading();
        getUserInfo.getUsers(new OnUserInfoListener() {
            @Override
            public void getUsersInfoSuccess(final ArrayList<User> users) {
                mHandler.post(new Runnable() {
                    public void run() {
                        /**
                         * 获取信息成功，调用View层的接口，触发显示
                         */
                        showUserView.showUsersinActivity(users);
                        showUserView.hideLoading();
                    }
                });
            }

            @Override
            public void getUserInfoSuccess(User user) {}

            @Override
            public void getUserInfoFailed() {}
        });
    }
}
