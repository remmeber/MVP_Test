package com.rhg.mvp.model;

import java.util.ArrayList;
import java.util.List;

import com.rhg.mvp.bean.User;

/**
 * 
 * <Model层，进行获取用户信息的业务操作>
 * 
 * @author rhg 1013773046@qq.com
 * @version [版本号, 2016年1月31日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class GetUserInfo implements GetUser {

    /**
     * 
     * 重载方法 加载指定id的用户
     * 
     * @param id
     * @param listener
     */
    @Override
    public void getUser(final int id, final OnUserInfoListener listener) {
        // 根据id去获取相应的用户信息，并且使用OnUserInfoListener接口回调
        // 模拟子线程耗时操作
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(2000);// 耗时2s
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (id == 1) {
                    User user = new User();
                    user.setName("RHG");
                    user.setAge("22");
                    user.setSex("男");
                    user.setId("1");
                    listener.getUserInfoSuccess(user);// 显示一个用户

                } else {
                    listener.getUserInfoFailed();
                }

            };
        }.start();
    }

    /**
     * 
     * 重载方法 加载用户组
     * 
     * @param listener
     */
    @Override
    public void getUsers(final OnUserInfoListener listener) {
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ArrayList<User> userList = new ArrayList<User>();

                User user = new User();
                user.setName("RHG");
                user.setAge("22");
                user.setSex("男");
                user.setId("1");
                for (int i = 0; i < 60; i++) {
                    userList.add(user);
                }
                listener.getUsersInfoSuccess(userList);
            };
        }.start();

    }

}
