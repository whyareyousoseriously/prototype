/**
 * 下午4:01:51
 * power
 */
package pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 因为要存session所以最好实现序列化接口
 * @author cz
 * 2018年4月25日下午4:01:51
 */
public class Manager implements Serializable{
	/**
	 * 下午10:07:57
	 * power
	 */
	private static final long serialVersionUID = -7356332366349247399L;
	private String managerId;//管理员id
	private String managerName;//管理员昵称
	private String managerPassword;//管理员密码;
	private String email;//管理员邮箱
	private String mailCode;//邮箱验证码；
	private Integer active;//激活状态
	private Date loginTime;//登录时间
	private Date createTime;//创建时间
	private Date updateTime;//最近一次登陆时间
	
	
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
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerPassword() {
		return managerPassword;
	}
	public void setManagerPassword(String managerPassword) {
		this.managerPassword = managerPassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMailCode() {
		return mailCode;
	}
	public void setMailCode(String mailCode) {
		this.mailCode = mailCode;
	}
	public Integer getActive() {
		return active;
	}
	public void setActive(Integer active) {
		this.active = active;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	
	

}
