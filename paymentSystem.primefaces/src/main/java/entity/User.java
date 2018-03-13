package entity;

import java.util.HashSet;
import java.util.Set;

/*
 * 添加激活状态的属性
 * @author cz
 * @date 2018-1-30
 * */

public class User {
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
	 * 一对多关系双方：一般用户VS支付条目
	 * 在1的一方,表示持有n的多的一方的应用=>集合
	 * 并提供getter和setter方法
	 * 
	 * */
	private Set<Item> iteam = new HashSet<Item>();
	
	/*
	 * hibernate
	 * 多对多关系：管理用户VS一般用户
	 * */
	private Set<Root> root = new HashSet<Root>();
	
	public User() {
		super();
	}
	
	

	public User(String id, String username, String password, String email, String type, String active, String mailCode,
			String certificationState, Set<Item> u_Iteam, Set<Root> root) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.type = type;
		this.active = active;
		this.mailCode = mailCode;
		this.certificationState = certificationState;
		this.iteam = u_Iteam;
		this.root = root;
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
	
	public String getCertificationState() {
		return certificationState;
	}
	public void setCertificationState(String certificationState) {
		this.certificationState = certificationState;
	}
	/**
	 * @return the mailCode
	 */
	public String getMailCode() {
		return mailCode;
	}
	/**
	 * @param mailCode the mailCode to set
	 */
	public void setMailCode(String mailCode) {
		this.mailCode = mailCode;
	}
	
	

	public Set<Item> getIteam() {
		return iteam;
	}



	public void setIteam(Set<Item> iteam) {
		this.iteam = iteam;
	}



	public Set<Root> getRoot() {
		return root;
	}



	public void setRoot(Set<Root> root) {
		this.root = root;
	}
}
