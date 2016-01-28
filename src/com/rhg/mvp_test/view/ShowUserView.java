package com.rhg.mvp_test.view;

import com.rhg.mvp_test.bean.User;

/**
 * View����ʾ�û���Ϣ�Ľӿڣ���Activity�̳�
 * 
 * @author RHG
 *
 */
public interface ShowUserView {
	/**
	 * ������ʾ������
	 */
	void showLoading();

	/**
	 * �������ؽ�����
	 */
	void hideLoading();

	/**
	 * ���û���Ϣչʾ
	 * 
	 * @param user
	 */
	void toMainActivity(User user);

	/**
	 * ��ʾʧ��
	 */
	void showFailedError();
}
