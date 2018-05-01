/**
 * 下午5:42:41
 * power
 */
package pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author cz
 * 2018年4月30日下午5:42:41
 */
public class UserOrderItem {
	private String id;//订单子表Id

    private String userId;//用户id

    private Long orderNo; //订单号

    private String itemId;//条目id，相当于商品id

    private String itemName;//条目名称，相当于商品名称

    private String itemImage;//条目图片，相当于条目图片

    private BigDecimal currentUnitPrice;//生成订单时的条目(商品)单价，单位是元,保留两位小数

    private Integer quantity;//条目(商品)数量

    private BigDecimal totalPrice;//条目(商品)总价

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

	public String getItemId() {
		return itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public String getItemImage() {
		return itemImage;
	}

	public BigDecimal getCurrentUnitPrice() {
		return currentUnitPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
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

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setItemImage(String itemImage) {
		this.itemImage = itemImage;
	}

	public void setCurrentUnitPrice(BigDecimal currentUnitPrice) {
		this.currentUnitPrice = currentUnitPrice;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
    
}
