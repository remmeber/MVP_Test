package com.rhg.mvp.model;
/**
 * Model ��ȡ�û���Ϣ�Ľӿڣ�
 * @author RHG
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
