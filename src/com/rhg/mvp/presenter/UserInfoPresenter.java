package com.rhg.mvp.presenter;

import com.rhg.mvp.bean.User;
import com.rhg.mvp.model.GetUser;
import com.rhg.mvp.model.GetUserInfo;
import com.rhg.mvp.model.OnUserInfoListener;
import com.rhg.mvp.view.ShowUserView;

import android.os.Handler;
/**
 * 展示用户信息的中间类
 * @author RHG
 *
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
						showUserView.toMainActivity(user);
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
		});
	}
}
