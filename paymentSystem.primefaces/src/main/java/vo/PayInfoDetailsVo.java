/**
 * 下午7:53:37
 * power
 */
package vo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * 
 * @author cz
 * 2018年4月30日下午7:53:37
 */
@ManagedBean
@SessionScoped
public class PayInfoDetailsVo {
	private String id;

    private String userId;//用户id

    private String orderNo;//订单号

    private String payPlatform;//支付平台:1-支付宝,2-微信'

    private String platformNumber;//支付宝支付流水号

    private String platformStatus;//支付宝支付状态

    private String createTime;//创建时间

    private String updateTime;//更新时间

	public String getId() {
		return id;
	}

	public String getUserId() {
		return userId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public String getPayPlatform() {
		return payPlatform;
	}

	public String getPlatformNumber() {
		return platformNumber;
	}

	public String getPlatformStatus() {
		return platformStatus;
	}

	public String getCreateTime() {
		return createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public void setPayPlatform(String payPlatform) {
		this.payPlatform = payPlatform;
	}

	public void setPlatformNumber(String platformNumber) {
		this.platformNumber = platformNumber;
	}

	public void setPlatformStatus(String platformStatus) {
		this.platformStatus = platformStatus;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
    
    
}
