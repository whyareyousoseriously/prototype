package dao;

import model.Users;

public interface UsersDAO {
	/*
	 * @author cz
	 * 补充说明：root也是用户的一种，可继承此接口,但这次就先不了，
	 * */
	public Users  usersLogin(Users users);
	public int  usersRegister(Users users);
	
}
