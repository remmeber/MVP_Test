package com.rhg.mvp.model;

import com.rhg.mvp.bean.User;
/**
 * Model�㣬���л�ȡ�û���Ϣ��ҵ�����
 * @author RHG
 *
 */
public class GetUserInfo implements GetUser {

	@Override
	public void getUser(final int id, final OnUserInfoListener listener) {
		// ����idȥ��ȡ��Ӧ���û���Ϣ������ʹ��OnUserInfoListener�ӿڻص�
		// ģ�����̺߳�ʱ����
		new Thread() {
			public void run() {
				try {
					Thread.sleep(2000);// ��ʱ2s
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (id == 1) {
					User user = new User();
					user.setName("RHG");
					user.setAge("22");
					user.setSex("��");
					user.setId("1");
					listener.getUserInfoSuccess(user);
				} else {
					listener.getUserInfoFailed();
				}

			};
		}.start();
	}

}
