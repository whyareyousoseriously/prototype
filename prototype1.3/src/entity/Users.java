package entity;
/*
 * entity of users
 * 用户实体类
 * */
public class Users {
	private int uid;
	private String username;
	private String password;
	private int power; //权限

	public Users() {

	}

	public Users(int uid, String username, String password, int power) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.power = power;  
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
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

}
