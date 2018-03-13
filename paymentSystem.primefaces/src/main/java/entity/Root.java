/**
 * 
 */
package entity;

import java.util.HashSet;
import java.util.Set;

/**
 * @author cz
 *
 * 2018年3月9日下午8:56:04
 */
public class Root {
	private String id;
	private String username;
	private String password;
	private String email;
	private String type;
	private String active;
	private String mailCode;
	private String certificationState;
	/*
	 * hibernate
	 * 多对多关系：管理用户VS支付条目  创建
	 * */
	private Set<Item> item = new HashSet<Item>();
	
	/*
	 * hibernate
	 * 多对多关系：管理用户VS一般用户 从属
	 * */
	private Set<User> user = new HashSet<User>();
	
	
	public Root() {
		super();
	}


	public Root(String id, String username, String password, String email, String type, String active, String mailCode,
			String certificationState, Set<Item> r_item, Set<User> user) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.type = type;
		this.active = active;
		this.mailCode = mailCode;
		this.certificationState = certificationState;
		this.item = r_item;
		this.user = user;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
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


	public String getMailCode() {
		return mailCode;
	}


	public void setMailCode(String mailCode) {
		this.mailCode = mailCode;
	}


	public String getCertificationState() {
		return certificationState;
	}


	public void setCertificationState(String certificationState) {
		this.certificationState = certificationState;
	}


	

	public Set<Item> getItem() {
		return item;
	}


	public void setItem(Set<Item> item) {
		this.item = item;
	}


	public Set<User> getUser() {
		return user;
	}


	public void setUser(Set<User> user) {
		this.user = user;
	}
}
