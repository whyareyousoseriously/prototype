/**
 * 下午7:17:20
 * power
 */
package pojo;

import java.util.Date;

/**
 * 扩展用，用户支付宝支付
 * @author cz
 * 2018年4月30日下午7:17:20
 */
public class PayInfoDetails {
	private String id;

    private String userId;//用户id

    private Long orderNo;//订单号

    private Integer payPlatform;//支付平台:1-支付宝,2-微信'

    private String platformNumber;//支付宝支付流水号

    private String platformStatus;//支付宝支付状态

    private Date createTime;//创建时间

    private Date updateTime;//更新时间

	public String getId() {
		return id;
	}

	public String getUserId() {
		return userId;
	}

	public Long getOrderNo() {
		return orderNo;
	}

	public Integer getPayPlatform() {
		return payPlatform;
	}

	public String getPlatformNumber() {
		return platformNumber;
	}

	public String getPlatformStatus() {
		return platformStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}

	public void setPayPlatform(Integer payPlatform) {
		this.payPlatform = payPlatform;
	}

	public void setPlatformNumber(String platformNumber) {
		this.platformNumber = platformNumber;
	}

	public void setPlatformStatus(String platformStatus) {
		this.platformStatus = platformStatus;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
    
    
}
