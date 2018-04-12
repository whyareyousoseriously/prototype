/**
 * 下午5:24:02
 * power
 */
package pojo;

import java.util.Date;

/**
 * 
 * @author cz
 * 2018年4月12日下午5:24:02
 */
public class Permition {

	private String permitionId;//准许表id;
	private String rootId;//批准者id;
	private String itemId;//批准条目id;
	private Date createTime;//批准时间;
	private Date updateTime;//更新时间;
	public Permition() {
		super();
	}
	public Permition(String permitionId, String rootId, String itemId, Date createTime, Date updateTime) {
		super();
		this.permitionId = permitionId;
		this.rootId = rootId;
		this.itemId = itemId;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
	public String getPermitionId() {
		return permitionId;
	}
	public void setPermitionId(String permitionId) {
		this.permitionId = permitionId;
	}
	public String getRootId() {
		return rootId;
	}
	public void setRootId(String rootId) {
		this.rootId = rootId;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
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
