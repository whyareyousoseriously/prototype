/**
 * 下午7:49:16
 * power
 */
package vo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * 
 * @author cz
 * 2018年4月30日下午7:49:16
 */
@ManagedBean
@SessionScoped
public class UserOrderVo {
	private String id;//订单id

    private String orderNo;//订单号

    private String userId;//用户id

    private String shippingId; //用户地址表id,扩展保留字段
    
    private String payment;//实际付款金额，单位是元，保留两位小数

    private String paymentType;//支付类型，1-在线支付

    private String postage;//运费，单位是元.扩展保留字段

    private String status;//订单状态，0-已取消，10-未付款，20-已付款。40-已发货，50-交易成功，60-交易关闭

    private String paymentTime;//支付时间

    private String sendTime;//发货时间

    private String endTime;//交易完成时间

    private String closeTime;//交易关闭时间

    private String createTime;//创建时间

    private String updateTime;//更新时间

	public String getId() {
		return id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public String getUserId() {
		return userId;
	}

	public String getShippingId() {
		return shippingId;
	}

	public String getPayment() {
		return payment;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public String getPostage() {
		return postage;
	}

	public String getStatus() {
		return status;
	}

	public String getPaymentTime() {
		return paymentTime;
	}

	public String getSendTime() {
		return sendTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public String getCloseTime() {
		return closeTime;
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

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setShippingId(String shippingId) {
		this.shippingId = shippingId;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public void setPostage(String postage) {
		this.postage = postage;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setPaymentTime(String paymentTime) {
		this.paymentTime = paymentTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
    
    

}
