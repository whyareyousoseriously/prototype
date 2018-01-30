package entity;
/*
 * 添加激活状态的属性
 * @author cz
 * @date 2018-1-30
 * */
public class User {
	private int uid;
	private String username;
	private String password;
	private String email;
	private String type;
	private String active;
	public User() {
		super();
	}
	public User(int uid, String username, String password, String email, String type, String active) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.email = email;
		this.type = type;
		this.active = active;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", password=" + password + ", email=" + email + ", type="
				+ type + ", active=" + active + "]";
	}
	
	
	
}
