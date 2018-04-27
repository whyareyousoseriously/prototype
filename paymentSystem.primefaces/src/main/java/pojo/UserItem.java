/**
 * 下午4:28:59
 * power
 */
package pojo;

import java.sql.Blob;
import java.util.Date;

/**
 * 
 * @author cz
 * 2018年4月25日下午4:28:59
 */
public class UserItem {
	private String userItemId;//主键
	private String userId;//用户id
	private String ItemId;//条目id;
	private Integer paymethod;//支付方式 0-支付宝 1-微信 3-银联
	private Integer status;//是否支付成功 0-未成功，1-成功
	private Blob qrCode;//支付二维码
	private Date createTime;//创建时间
	private Date updateTime;//最后一次更新时间
	private Date payTime;//支付时间
	
	public String getUserItemId() {
		return userItemId;
	}
	public void setUserItemId(String userItemId) {
		this.userItemId = userItemId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getItemId() {
		return ItemId;
	}
	public void setItemId(String itemId) {
		ItemId = itemId;
	}
	public Integer getPaymethod() {
		return paymethod;
	}
	public void setPaymethod(Integer paymethod) {
		this.paymethod = paymethod;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Blob getQrCode() {
		return qrCode;
	}
	public void setQrCode(Blob qrCode) {
		this.qrCode = qrCode;
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
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	
}
