/**
 * 下午5:09:03
 * power
 */
package pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author cz
 * 2018年4月30日下午5:09:03
 */
public class UserOrder {
	private String id;//订单id

    private Long orderNo;//订单号

    private String userId;//用户id

    private Integer shippingId; //用户地址表id,扩展保留字段
    
    private BigDecimal payment;//实际付款金额，单位是元，保留两位小数

    private Integer paymentType;//支付类型，1-在线支付

    private Integer postage;//运费，单位是元.扩展保留字段

    private Integer status;//订单状态，0-已取消，10-未付款，20-已付款。40-已发货，50-交易成功，60-交易关闭

    private Date paymentTime;//支付时间

    private Date sendTime;//发货时间

    private Date endTime;//交易完成时间

    private Date closeTime;//交易关闭时间

    private Date createTime;//创建时间

    private Date updateTime;//更新时间

 

    

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

   

    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getShippingId() {
        return shippingId;
    }

    public void setShippingId(Integer shippingId) {
        this.shippingId = shippingId;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getPostage() {
        return postage;
    }

    public void setPostage(Integer postage) {
        this.postage = postage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
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
