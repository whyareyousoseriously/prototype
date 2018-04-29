/**
 * 下午4:28:59
 * power
 */
package pojo;

import java.io.Serializable;

import java.util.Date;

/**
 * 
 * 联合主键两个条件
 * 1.实现序列化接口
 * 2.重写hashCode和equals方法
 * @author cz
 * 2018年4月25日下午4:28:59
 */
public class PayInfo implements Serializable{
	
	/**
	 * 下午4:19:36
	 * power
	 */
	private static final long serialVersionUID = -3787702851022762176L;
	private String userId;//用户id
	private String itemId;//条目id;
	private String payInfoDetailsId;//支付明细详细，扩展字段
	private Integer status;//是否支付成功 0-未成功，1-成功
	private String qrCode;//支付二维码Url
	private Date createTime;//创建时间
	private Date updateTime;//最后一次更新时间
	private Date payTime;//支付时间
	
	public String getUserId() {
		return userId;
	}
	public String getItemId() {
		return itemId;
	}
	public String getPayInfoDetailsId() {
		return payInfoDetailsId;
	}
	public Integer getStatus() {
		return status;
	}
	public String getQrCode() {
		return qrCode;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public void setPayInfoDetailsId(String payInfoDetailsId) {
		this.payInfoDetailsId = payInfoDetailsId;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PayInfo other = (PayInfo) obj;
		if (itemId == null) {
			if (other.itemId != null)
				return false;
		} else if (!itemId.equals(other.itemId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
	
	
}
