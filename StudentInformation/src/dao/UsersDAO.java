package dao;

import java.util.List;

import model.Users;

public interface UsersDAO {
	public Users  usersLogin(Users users);
	public int  usersRegister(Users users);
	public List<Users> getAllUsers();
}
