package entity;
/*
 * entity of users
 * �û�ʵ����
 * */
public class Users {
	private int uid;
	private String username;
	private String password;
	private String email;
	private int power; //Ȩ��
	public Users() {
		
	}
	public Users(int uid, String username, String password, String email, int power) {
		//super();
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.email = email;
		this.power = power;
	}
	public int getUid() {
		return uid;
	}
	
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}

}

