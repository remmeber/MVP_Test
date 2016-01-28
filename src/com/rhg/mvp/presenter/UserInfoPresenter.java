package com.rhg.mvp.presenter;

import com.rhg.mvp.bean.User;
import com.rhg.mvp.model.GetUser;
import com.rhg.mvp.model.GetUserInfo;
import com.rhg.mvp.model.OnUserInfoListener;
import com.rhg.mvp.view.ShowUserView;

import android.os.Handler;
/**
 * չʾ�û���Ϣ���м���
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
