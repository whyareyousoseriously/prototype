/**
 * 下午4:22:06
 * power
 */
package pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author cz
 * 2018年4月25日下午4:22:06
 */
public class Item {
	private String itemId;//项目Id;
	private String itemName;//项目名称
	private String managerId;//管理者id;
	private BigDecimal price;//项目价格
	private Date createTime;//创建时间
	private Date updateTime;//最近一次更新时间；
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
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
