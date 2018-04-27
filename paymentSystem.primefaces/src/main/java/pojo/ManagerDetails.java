/**
 * 下午4:18:31
 * power
 */
package pojo;

import java.util.Date;

/**
 * 
 * @author cz
 * 2018年4月25日下午4:18:31
 */
public class ManagerDetails {
	private String managerId;//管理员Id;
	private Integer accountType;//支付账户类型;
	private String accountId;//支付账户Id
	private Integer active;//支付账号是否启用
	private Date createTime;//创建时间
	private Date updateTime;//更新时间
	
	
	public Integer getActive() {
		return active;
	}
	public void setActive(Integer active) {
		this.active = active;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public Integer getAccountType() {
		return accountType;
	}
	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
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
