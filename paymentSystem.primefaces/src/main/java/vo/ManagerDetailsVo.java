/**
 * 下午3:36:48
 * power
 */
package vo;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * 前端显示vo
 * @author cz
 * 2018年4月28日下午3:36:48
 */
@ManagedBean
@SessionScoped
public class ManagerDetailsVo {
	
	private String managerId;//管理员Id;
	private String accountType;//支付账户类型;
	private String accountId;//支付账户Id
	private String active;//支付账号是否启用
	private Date createTime;//创建时间
	private Date updateTime;//更新时间
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
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
