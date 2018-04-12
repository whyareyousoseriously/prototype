/**
 * 下午4:45:45
 * power
 */
package pojo;

/**
 * 
 * @author cz
 * 2018年4月12日下午4:45:45
 */
public class Donation {
	private String donationId;//捐献表id;
	private String userId;//捐助者id;
	private String itemId;//被捐助的条目的id;
	private String count; //第几次被捐助
	private String detail;//捐助的细节
	public Donation() {
		super();
	}
	
	public Donation(String donationId, String userId, String itemId, String count, String detail) {
		super();
		this.donationId = donationId;
		this.userId = userId;
		this.itemId = itemId;
		this.count = count;
		this.detail = detail;
	}

	
	public String getDonationId() {
		return donationId;
	}

	public void setDonationId(String donationId) {
		this.donationId = donationId;
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
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}
