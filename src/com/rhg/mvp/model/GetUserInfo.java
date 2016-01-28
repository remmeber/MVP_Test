package com.rhg.mvp.model;

import com.rhg.mvp.bean.User;
/**
 * Model层，进行获取用户信息的业务操作
 * @author RHG
 *
 */
public class GetUserInfo implements GetUser {

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
					listener.getUserInfoSuccess(user);
				} else {
					listener.getUserInfoFailed();
				}

			};
		}.start();
	}

}
