/**
 * 下午4:38:35
 * power
 */
package pojo;

import java.util.Date;

/**
 * 
 * @author cz
 * 2018年4月12日下午4:38:35
 */
public class VolunteerItem {

	private String id;//志愿条目id
	private String userId;//发起者id
	private String itemName;//志愿条目的名字
	private String itemDetail;//志愿条目的细节
	private int count;//志愿条目的被帮助次数
	private int status;//志愿条目的状态0-未批准,1-批准,2-下架
	private Date createTime;//创建时间
	private Date updateTime;//更新时间
	
	public VolunteerItem() {
		super();
	}
	
	public VolunteerItem(String id, String userId, String itemName, String itemDetail, int count, int status,
			Date createTime, Date updateTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.itemName = itemName;
		this.itemDetail = itemDetail;
		this.count = count;
		this.status = status;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemDetail() {
		return itemDetail;
	}
	public void setItemDetail(String itemDetail) {
		this.itemDetail = itemDetail;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
