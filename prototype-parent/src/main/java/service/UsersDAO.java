package service;

import entity.Users;


//�û�ҵ���߼��ӿ�
public interface UsersDAO {
	/*
	 * The method of userLogin
	 * @param u{Users};
	 * @return the limits of authority.404 represent error.non-404 represent true;
	 * 
	 * �û���½�ķ���
	 * ���룺�û�u;
	 * ���أ��û���Ȩ�ޣ�404�����½ʧ��
	 * */
	public int usersLogin(Users u);
	/*
	 * The method of userRegister
	 * @param u{Users};
	 * @return the result
	 * 
	 * �û�ע��ķ���
	 * ���룺�û�u
	 * ���أ�������ɹ���ʧ�ܵ�ԭ��
	 * */
	public int usersRegister(Users u);
	
}
