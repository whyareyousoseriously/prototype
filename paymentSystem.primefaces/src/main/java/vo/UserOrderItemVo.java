/**
 * 下午7:52:08
 * power
 */
package vo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * 
 * @author cz
 * 2018年4月30日下午7:52:08
 */
@ManagedBean
@SessionScoped
public class UserOrderItemVo {
	private String id;//订单子表Id

    private String userId;//用户id

    private String orderNo; //订单号

    private String itemId;//条目id，相当于商品id

    private String itemName;//条目名称，相当于商品名称

    private String itemImage;//条目图片，相当于条目图片

    private String currentUnitPrice;//生成订单时的条目(商品)单价，单位是元,保留两位小数

    private String quantity;//条目(商品)数量

    private String totalPrice;//条目(商品)总价

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

	public String getItemId() {
		return itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public String getItemImage() {
		return itemImage;
	}

	public String getCurrentUnitPrice() {
		return currentUnitPrice;
	}

	public String getQuantity() {
		return quantity;
	}

	public String getTotalPrice() {
		return totalPrice;
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

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setItemImage(String itemImage) {
		this.itemImage = itemImage;
	}

	public void setCurrentUnitPrice(String currentUnitPrice) {
		this.currentUnitPrice = currentUnitPrice;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
    
    
    
}
