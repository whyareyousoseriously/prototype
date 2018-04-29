/**
 * 下午4:18:31
 * power
 */
package pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 为了实现联合主键，必须满足两个条件
 * 1.实现序列号接口
 * 2.重写equals和hashCode方法
 * @author cz
 * 2018年4月25日下午4:18:31
 */
public class ManagerDetails implements Serializable{
	/**
	 * 下午8:21:10
	 * power
	 */
	private static final long serialVersionUID = 4483327113626192159L;
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
	
	@Override
	public int hashCode() {  
        return getAccountId().hashCode()*11 +   
                getManagerId().hashCode();  
    }  
      
	@Override
    public boolean equals(Object obj) {  
        if(null == obj){  
            return false;  
        }  
        if(this == obj){  
            return true;  
        }  
        if(obj.getClass() == ManagerDetails.class){  
            ManagerDetails m = (ManagerDetails)obj;  
            if(m.getAccountId().equals(getAccountId()) &&  
                    m.getManagerId().equals(getManagerId())){  
                return true;  
            }  
        }  
        return false;  
    }  

}
