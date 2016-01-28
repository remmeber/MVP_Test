package com.rhg.mvp.view;

import com.rhg.mvp.bean.User;

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
