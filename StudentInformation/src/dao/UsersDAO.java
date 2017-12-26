package dao;

import model.Users;

public interface UsersDAO {
	public int  usersLogin(Users users);
	public int  usersRegister(Users users);
}
