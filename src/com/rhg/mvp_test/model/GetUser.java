package com.rhg.mvp_test.model;
/**
 * Model ��ȡ�û���Ϣ�Ľӿڣ�
 * @author Administrator
 *
 */
public interface GetUser {
	/**
	 * �����û�id����ȡ�û�����Ϣ
	 * @param id
	 * @param listener ��ȡ��Ϣ�Ļص��ӿ�
	 */
	public void getUser(int id, OnUserInfoListener listener);
}
