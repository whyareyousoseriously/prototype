/**
 * 下午4:07:19
 * power
 */
package pojo;

import java.util.Date;

/**
 * 
 * @author cz
 * 2018年4月25日下午4:07:19
 */
public class ManagerUser {
	private String managerUserId;//主键
	private String managerId;//管理者id
	private String userId;//用户id
	private Date createTime;//创建时间
	private Date updateTime;//最近一次更新时间
	
	public String getManagerUserId() {
		return managerUserId;
	}
	public void setManagerUserId(String managerUserId) {
		this.managerUserId = managerUserId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	

}
