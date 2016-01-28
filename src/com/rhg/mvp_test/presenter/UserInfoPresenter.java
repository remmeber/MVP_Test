package com.rhg.mvp_test.presenter;

import com.rhg.mvp_test.bean.User;
import com.rhg.mvp_test.model.GetUser;
import com.rhg.mvp_test.model.GetUserInfo;
import com.rhg.mvp_test.model.OnUserInfoListener;
import com.rhg.mvp_test.view.ShowUserView;

import android.os.Handler;

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
		 * ��Activity�е���������
		 */
		showUserView.showLoading();
		/**
		 * �˴�����Model��Ľӿڣ��������ص����󴥷�View��Ľӿ�
		 */
		getUserInfo.getUser(id, new OnUserInfoListener() {

			@Override
			public void getUserInfoSuccess(final User user) {
				mHandler.post(new Runnable() {
					public void run() {
						/**
						 * ��ȡ��Ϣ�ɹ�������View��Ľӿڣ�������ʾ
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
