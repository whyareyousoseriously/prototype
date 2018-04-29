/**
 * 下午4:31:12
 * power
 */
package vo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * 支付信息VO
 * @author cz
 * 2018年4月29日下午4:31:12
 */
@ManagedBean
@SessionScoped
public class PayInfoVo {
	private String userId;//用户id
	private String itemId;//条目id;
	private String payInfoDetailsId;//支付明细详细，扩展字段
	private String status;//是否支付成功 0-未成功，1-成功
	private String qrCode;//支付二维码的url
	private String createTime;//创建时间
	private String updateTime;//最后一次更新时间
	private String payTime;//支付时间
	private ItemVo ownItemVo;//
	
	public ItemVo getOwnItemVo() {
		return ownItemVo;
	}
	public void setOwnItemVo(ItemVo ownItemVo) {
		this.ownItemVo = ownItemVo;
	}
	public String getUserId() {
		return userId;
	}
	public String getItemId() {
		return itemId;
	}
	public String getPayInfoDetailsId() {
		return payInfoDetailsId;
	}
	public String getStatus() {
		return status;
	}
	public String getQrCode() {
		return qrCode;
	}
	public String getCreateTime() {
		return createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public String getPayTime() {
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
	public void setStatus(String status) {
		this.status = status;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	

}
