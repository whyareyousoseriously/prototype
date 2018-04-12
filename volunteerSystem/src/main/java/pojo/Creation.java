/**
 * 下午4:47:47
 * power
 */
package pojo;

/**
 * 
 * @author cz
 * 2018年4月12日下午4:47:47
 */
public class Creation {
	private String creationId;//发起表的id;
	private String userId;//志愿条目发起者id
	private String itemId;//志愿条目id
	public Creation() {
		super();
	}
	
	public Creation(String creationId, String userId, String itemId) {
		super();
		this.creationId = creationId;
		this.userId = userId;
		this.itemId = itemId;
	}

	
	public String getCreationId() {
		return creationId;
	}

	public void setCreationId(String creationId) {
		this.creationId = creationId;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	

}
