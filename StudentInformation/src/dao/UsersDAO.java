package dao;

import model.Users;

public interface UsersDAO {
	/*
	 * @author cz
	 * ����˵����rootҲ���û���һ�֣��ɼ̳д˽ӿ�,����ξ��Ȳ��ˣ�
	 * */
	public Users  usersLogin(Users users);
	public int  usersRegister(Users users);
	
}
