/**
 * 下午3:25:20
 * power
 */
package vo;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * 前端界面显示vo
 * @author cz
 * 2018年4月28日下午3:25:20
 */
@ManagedBean
@SessionScoped
public class ManagerVo {
	private String managerId;//管理员id
	private String managerName;//管理员昵称
	private String managerPassword;//管理员密码;
	private String email;//管理员邮箱
	private String mailCode;//邮箱验证码；
	private String active;//激活状态
	private String loginTime;//登录时间
	private String createTime;//创建时间
	private String updateTime;//最近一次登陆时间
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
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	
	
	
}
