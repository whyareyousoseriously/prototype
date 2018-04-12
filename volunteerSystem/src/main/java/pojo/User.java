/**
 * 下午4:24:30
 * power
 */
package pojo;

import java.util.Date;

/**
 * 
 * @author cz
 * 2018年4月12日下午4:24:30
 */
public class User {
	private String id;//用户表id
	private String username;//用户名
	private String password;//用户密码
	private String email;
	private String phone;
	private String question;//找回密码问题
	private String answer;//找回密码答案
	private Date createTime;//创建时间
	private Date updateTime;//最后一次更新时间
	
	public User() {
		super();
	}
	
	public User(String id, String username, String password, String email, String phone, String question, String answer,
			Date createTime, Date updateTime) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.question = question;
		this.answer = answer;
		this.createTime = createTime;
		this.updateTime = updateTime;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
