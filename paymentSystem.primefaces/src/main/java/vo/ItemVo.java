/**
 * 下午10:29:16
 * power
 */
package vo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * 支付条目的前端显示vo
 * 
 * @author cz 2018年4月28日下午10:29:16
 */
@ManagedBean
@SessionScoped
public class ItemVo {
	private String itemId;// 支付条目id;
	private String itemName;// 支付条目的名称
	private String price;// 支付条目的价格
	private String active;// 是否发布
	private String accountId;// 发布在从属的那个支付账号下
	private String accountType;// 支付账号的类型
	private String updateTime;// 支付条目的更新时间
	private String createTime;// 支付条目的创建时间
	private String managerId;// 管理者id;

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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

}
