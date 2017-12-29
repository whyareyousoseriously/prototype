package dao;



import model.Users;

public interface UsersDAO {
	public Users  usersLogin(Users users);
	public int  usersRegister(Users users);
	
}
